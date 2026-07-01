package Workers;

import model.Job;

public interface Worker {
    void handleWork(Job job);
}
