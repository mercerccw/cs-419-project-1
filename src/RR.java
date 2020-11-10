/**
 * This algorithm will run tasks according to round-robin scheduling.
 * <p>
 * Add your implementation inside the RR class below.
 */

import java.util.*;

public class RR implements Algorithm {
    private List<Task> queue;
    private Task currentTask;
    private int tasksRun;
    private List<Task> runningQueue;

    @Override
    public void schedule() {

    }

    @Override
    public Task pickNextTask() {
        return null;
    }
}
