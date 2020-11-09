/**
 * SJF scheduling algorithm.
 * <p>
 * Add your implementation inside the SJF class below.
 */

import java.util.*;

public class SJF implements Algorithm {
    private List<Task> queue;
    private Task currentTask;
    private int tasksRun;
    private List<Task> waitingQueue;

    public SJF(List<Task> queue) {
        this.queue = queue;

        tasksRun = queue.size();
    }

    @Override
    public void schedule() {
        System.out.println("Shortest Job First Scheduling\n");
        while (!queue.isEmpty()) {
            currentTask = pickNextTask();

            CPU.run(currentTask, currentTask.getBurst());
            System.out.println("Task " + currentTask.getName() + " finished at time " + CPU.getCurrentTime());

            // remove the task
            queue.remove(currentTask);
        }
    }

    @Override
    public Task pickNextTask() {
        if (tasksRun > queue.size()) {
            Task smallest_task = queue.get(0);
            for (Task task: queue) {
                if(task.getBurst() < smallest_task.getBurst()){
                    smallest_task = task;
                }
            }
            return queue.get(queue.indexOf(smallest_task));
        } else {
            return queue.get(0);
        }
    }
}
