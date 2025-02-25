/**
 * This algorithm will run tasks according to round-robin scheduling.
 * <p>
 * Add your implementation inside the RR class below.
 */

import java.util.*;

public class RR implements Algorithm {
    private List<Task> queue;
    private int quantum = 10;

    public RR(List<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void schedule() {
        List<Task> completedTasks = new ArrayList<Task>();
        while (!queue.isEmpty()) {
            for (Task task : queue) {
                if (task.getBurst() - quantum > 0) {
                    CPU.run(task, quantum);
                    task.setBurst(task.getBurst() - quantum);
                } else {
                    CPU.run(task, task.getBurst());
                    System.out.println("Task " + task.getName() + " finished at time " + CPU.getCurrentTime());
                    completedTasks.add(task);
                    break;
                }
            }
            for (Task task: completedTasks) {
                queue.remove(task);
            }
            completedTasks.clear();

        }
    }

    @Override
    public Task pickNextTask() {
        return null;
    }
}
