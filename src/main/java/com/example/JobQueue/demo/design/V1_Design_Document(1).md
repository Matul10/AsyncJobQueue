# V1 Design Document

## Overview
V1 provides synchronous job processing for **EMAIL**, **SMS**, and **PDF** jobs through REST APIs. Every job is stored in PostgreSQL, processed immediately, and its status can be queried later.

---

## Core Functionality
- Submit jobs (EMAIL, SMS, PDF) via REST API.
- Process jobs synchronously (client waits for completion).
- Store all job details in PostgreSQL.
- Check job status via REST API.
- Maintain complete status history with timestamps and error messages.

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/jobs/submit` | Submit a new job |
| GET | `/api/jobs/{id}/status` | Get job status |

---

## Job Processing

### Workers
- **EmailWorker** – Sends emails using Gmail SMTP.
- **SmsWorker** – Prints SMS to terminal.
- **PdfWorker** – Generates PDF using iText.

### Status Lifecycle
`PENDING → PROCESSING → COMPLETED / FAILED`

For failures:
- Store error message.
- Update status to `FAILED`.
- Track created and updated timestamps.

---

## Main Flow – Submit Job

### 1. Client Request
```http
POST /api/jobs/submit
Body: { type, payload }
```

### 2. Controller
- Receive request.
- Validate job type (EMAIL/SMS/PDF).
- Validate payload is not empty.
- Call `service.submitJob(request)`.

### 3. Service
- Create Job object.
- Set status = `PENDING`.
- Save job to database and generate `jobId`.
- Invoke worker based on job type:
  - EMAIL → `emailWorker.process(job)`
  - SMS → `smsWorker.process(job)`
  - PDF → `pdfWorker.process(job)`
- Return job.

### 4. Worker
- Update status to `PROCESSING`.
- Execute job.
- On success:
  - Update status to `COMPLETED`.
- On failure:
  - Update status to `FAILED`.
  - Save `errorMessage`.

### 5. Response
- Service returns job.
- Controller returns `jobId` and current status.

---

## Validation
- Validate job type.
- Validate payload based on job type.
- Return **400 Bad Request** for invalid requests.

---

## Error Handling

| Scenario | Result |
|----------|--------|
| Invalid job type | Return **400 Bad Request**. No DB save or worker execution. |
| Job not found | Return **404 Not Found**. |
| Worker failure | Mark job as `FAILED`, save error message, persist to DB, return failed response. |
| Database error | Return **500 Internal Server Error**. Transaction rolled back, no job created. |

---

## Data Persistence
- Save every job to PostgreSQL.
- Persist status updates throughout processing.
- Store timestamps.
- Store failure messages.
- Maintain complete audit trail.

---

## Test Scenarios

### Email Job
1. Submit EMAIL job.
2. Verify `jobId` returned.
3. DB status should be `COMPLETED`.
4. Email received.

### SMS Job
1. Submit SMS job.
2. Verify `jobId`.
3. DB status should be `COMPLETED`.
4. SMS printed on terminal.

### PDF Job
1. Submit PDF job.
2. Verify `jobId`.
3. DB status should be `COMPLETED`.
4. PDF generated.

### Status API
1. Call `GET /api/jobs/{id}/status`.
2. Verify status.
3. Invalid ID returns **404**.

### Invalid Job
1. Submit job with type `WHATSAPP`.
2. Receive **400 Bad Request**.
3. No database entry created.
