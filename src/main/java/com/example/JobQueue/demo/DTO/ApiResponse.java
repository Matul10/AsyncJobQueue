package com.example.JobQueue.demo.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApiResponse <T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data, String message){
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(T data, String message){
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}
