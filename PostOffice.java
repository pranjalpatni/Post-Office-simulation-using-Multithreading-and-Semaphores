import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class PostOffice {
    /*List of semaphores and other global variables*/

    public static Semaphore maxCapacity = new Semaphore(10, true);
    public static Semaphore custReady = new Semaphore(0, true);
    public static Semaphore workerDesk = new Semaphore(3, true);
    public static Semaphore mutex = new Semaphore(1, true);
    public static Semaphore coord = new Semaphore(0, true);
    public static Semaphore leave_workerDesk = new Semaphore(0, true);
    public static Semaphore coord2 = new Semaphore(0, true);
    public static Semaphore scale = new Semaphore(1, true);
    public static int count;
    public static Queue<Integer> queue = new LinkedList<>();
    public static final int custNum = 50;
    public static final int workNum = 3;
    public static Semaphore[] finished = new Semaphore[custNum];

    static {
        for (int i = 0; i < custNum; i++) {
            finished[i] = new Semaphore(0, true);
        }
    }
    public static Customer[] objCust = new Customer[custNum];
    public static Worker[] objWork = new Worker[workNum];
    public static Thread[] t1 = new Thread[custNum];
    public static Thread[] t2 = new Thread[workNum];
}
