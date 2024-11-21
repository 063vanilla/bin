// Consider the scheduling problem. n tasks to be scheduled on single processor. Let d1,
// ...,dn be deadline and p1,….pn be the profit of each task to execute on single processor is
// known. The tasks can be executed in any order but one task at a time and each task take 1
// unit of time to execute. Design a greedy algorithm

import java.util.Scanner;

class Job {
    int id;
    int profit;
    int deadline;

    // Method to initialize job properties
    void input(int id, int profit, int deadline) {
        this.id = id;
        this.profit = profit;
        this.deadline = deadline;
    }

    // Method to display job details
    void display() {
        System.out.println("Task ID: " + id + " | Deadline: " + deadline + " | Profit: " + profit);
    }
}

public class JobScheduling {
    private Job[] jobs;
    private int maxDeadline = 0;
    private int totalProfit = 0;

    // Constructor to initialize job array
    public JobScheduling(int jobCount) {
        jobs = new Job[jobCount];
        for (int i = 0; i < jobCount; i++) {
            jobs[i] = new Job();
        }
    }

    // Method to input job details from the user
    public void userInput() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < jobs.length; i++) {
            System.out.println((i + 1) + " Job");
            System.out.print("Enter job ID: ");
            int id = sc.nextInt();
            System.out.print("Enter job profit: ");
            int profit = sc.nextInt();
            System.out.print("Enter job deadline: ");
            int deadline = sc.nextInt();
            jobs[i].input(id, profit, deadline);
        }
        sc.close();
    }

    // Method to display the job details entered by the user
    public void displayInput() {
        System.out.println("\nInput Jobs:");
        for (int i = 0; i < jobs.length; i++) {
            jobs[i].display();
        }
    }

    // Method to sort jobs by profit in descending order
    private void sortJobsByProfit() {
        for (int i = 0; i < jobs.length - 1; i++) {
            for (int j = 0; j < jobs.length - i - 1; j++) {
                if (jobs[j].profit < jobs[j + 1].profit) {
                    Job temp = jobs[j + 1];
                    jobs[j + 1] = jobs[j];
                    jobs[j] = temp;
                }
            }
        }
    }

    // Method to calculate the maximum deadline among the jobs
    public void calculateMaxDeadline() {
        for (int i = 0; i < jobs.length; i++) {
            maxDeadline = Math.max(maxDeadline, jobs[i].deadline);
        }
    }

    // Method to initialize slots for job scheduling
    private int[] initializeSlots() {
        int[] slots = new int[maxDeadline];
        for (int i = 0; i < maxDeadline; i++) {
            slots[i] = -1;
        }
        return slots;
    }

    // Method to schedule jobs to maximize profit
    public void scheduleJobs() {
        sortJobsByProfit();
        calculateMaxDeadline();
        int[] slots = initializeSlots();

        for (int i = 0; i < jobs.length; i++) {
            for (int j = jobs[i].deadline - 1; j >= 0; j--) {
                if (slots[j] == -1) {
                    slots[j] = jobs[i].id;
                    totalProfit += jobs[i].profit;
                    break; // Exit loop after assigning job
                }
            }
        }

        displayScheduledJobs(slots);
    }

    // Method to display scheduled jobs and total profit
    public void displayScheduledJobs(int[] slots) {
        System.out.println("\nScheduled Jobs (by slot):");
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != -1) {
                System.out.println("Slot " + (i + 1) + ": Job ID " + slots[i]);
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    // Main method to run the program
    public static void main(String[] args) {
        JobScheduling scheduler = new JobScheduling(5);
        scheduler.userInput();
        scheduler.displayInput();
        scheduler.scheduleJobs();
    }
}
