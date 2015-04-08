import java.util.Random;
import java.util.concurrent.Semaphore;

public class Customer implements Runnable {
    /*Customer class structure*/

    public int custNum;
    public int task;
    public int worker_assigned;
    /*Customer constructor*/

    Customer(int custNum) {
        this.custNum = custNum;
    }
    /*Working of customer thread and providing mutual exclusion using semaphores*/

    @Override
    public void run() {
        cust_created();
        wait(PostOffice.maxCapacity);
        cust_enterShop();
        wait(PostOffice.workerDesk);
        wait(PostOffice.mutex);
        enqueue(custNum);
        signal(PostOffice.custReady);
        signal(PostOffice.mutex);
        wait(PostOffice.coord);
        cust_request();
        signal(PostOffice.coord2);
        wait(PostOffice.finished[custNum]);
        finish_work();
        leave();
        signal(PostOffice.leave_workerDesk);
        signal(PostOffice.maxCapacity);
        PostOffice.count++;
    }
    /*Print customer leaving the office*/

    void leave() {
        System.out.println("Customer " + custNum + " leaves post office");
    }
    /*Print customer finishes the work*/

    void finish_work() {
        System.out.println("Customer " + custNum + " finished " + taskname2(task));
    }
    /*To print the specific task*/

    private String taskname2(int task) {
        String message = new String();
        switch (task) {
            case 1:
                message = "buying stamps";
                break;
            case 2:
                message = "mailing a letter";
                break;
            case 3:
                message = "mailing a package";
                break;
        }
        return message;
    }
    /*Print customer requesting a postal worker for some work and adding the work in the worker's queue*/

    void cust_request() {
        System.out.println("Customer " + custNum + " asks postal worker " + worker_assigned + " to " + taskname(task));
        PostOffice.objWork[worker_assigned].workerQueue.add(task);
    }
    /*To print the correct output*/

    private String taskname(int task) {
        String message = new String();
        switch (task) {
            case 1:
                message = "buy stamps";
                break;
            case 2:
                message = "mail a letter";
                break;
            case 3:
                message = "mail a package";
                break;
        }
        return message;
    }
    /*Add the customer id to the queue*/

    void enqueue(int custId) {
        PostOffice.queue.add(custId);
    }
    /*Print 'customer created' and assign a random task to it*/

    void cust_created() {
        System.out.println("Customer " + custNum + " created");
        Random r = new Random();
        int low = 1;
        int high = 4;
        this.task = r.nextInt(high - low) + low;
    }
    /*Semaphore wait*/

    void wait(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {

        }
    }
    /*Print customer enters shop*/

    void cust_enterShop() {
        System.out.println("Customer " + custNum + " enters shop");
    }
    /*Signal semaphore*/

    void signal(Semaphore s) {
        s.release();
    }
}
