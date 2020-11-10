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
    private List<Task> waitingQueue = new ArrayList<Task>();


    public SJF(List<Task> queue) {
        this.queue = queue;
        tasksRun = queue.size();
    }

    @Override
    public void schedule() {
        System.out.println("Shortest Job First (non-preemptive) Scheduling\n");
        while (!queue.isEmpty() || !waitingQueue.isEmpty()) {
            currentTask = pickNextTask();
            CPU.run(currentTask, currentTask.getBurst());
            if (!waitingQueue.isEmpty()) {
                System.out.println("Task " + currentTask.getName() + " finished at time " + CPU.getCurrentTime());
                waitingQueue.remove(currentTask);
                queue.remove(currentTask);
            } else {
                System.out.println("Task " + currentTask.getName() + " finished at time " + CPU.getCurrentTime());
                queue.remove(currentTask);
            }
            for (Task task : queue) {
                if (task.getArrivalTime() < CPU.getCurrentTime()) {
                    waitingQueue.add(task);
                } else {
                    break;
                }
            }
        }
    }


    @Override
    public Task pickNextTask() {
        Comparator<Task> compareBurstTime = Comparator.comparingInt(Task::getBurst);
        if (tasksRun > queue.size()) {
            if (!waitingQueue.isEmpty()) {
                for (Task task: waitingQueue) {
                    queue.remove(task);
                }
                waitingQueue.sort(compareBurstTime);
                return waitingQueue.get(0);
            } else {
                return queue.get(0);
            }
        } else {
            return queue.get(0);
        }
    }
}
