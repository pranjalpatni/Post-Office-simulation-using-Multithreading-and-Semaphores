import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable {
    /*Structure of Worker object*/

    public int workerNum;
    public static Queue<Integer> workerQueue = new LinkedList<>();
    private int next_cust_number;
    /*Worker constructor*/

    Worker(int workerNum) {
        this.workerNum = workerNum;
    }
    /*Working of worker thread and providing mutual exclusion using semaphores*/

    @Override
    public void run() {
        work_created();
        while (true) {
            wait(PostOffice.custReady);
            wait(PostOffice.mutex);
            dequeue();
            signal(PostOffice.mutex);
            signal(PostOffice.coord);
            wait(PostOffice.coord2);
            serve_cust();
            signal(PostOffice.finished[next_cust_number]);
            wait(PostOffice.leave_workerDesk);
            signal(PostOffice.workerDesk);
        }
    }
    /*Dequeue the customer number*/

    void dequeue() {
        next_cust_number = PostOffice.queue.remove();
        System.out.println("Postal worker " + workerNum + " serving Customer " + next_cust_number);
        PostOffice.objCust[next_cust_number].worker_assigned = workerNum;
    }
    /*Print worker created*/

    void work_created() {
        System.out.println("Postal Worker " + workerNum + " created");
    }
    /*Signal semaphore*/

    void signal(Semaphore s) {

        s.release();

    }
    /*Serving request from customer*/

    void serve_cust() {
        int task;
        task = workerQueue.remove();
        switch (task) {
            case 1:
                try {
                    Thread.sleep(1000);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Postal worker " + workerNum + " finished serving customer " + next_cust_number);
                break;
            case 2:
                try {
                    Thread.sleep(1500);                 //1500 milliseconds is one and half second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Postal worker " + workerNum + " finished serving customer " + next_cust_number);
                break;
            case 3:
                wait(PostOffice.scale);
                System.out.println("Scales in use by postal worker " + workerNum);
                try {
                    Thread.sleep(2000);                 //2000 milliseconds is two second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Scales released by postal worker " + workerNum);
                signal(PostOffice.scale);
                System.out.println("Postal worker " + workerNum + " finished serving customer " + next_cust_number);
                break;
        }
    }
    /*Semaphore wait*/

    void wait(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {

        }
    }
}
