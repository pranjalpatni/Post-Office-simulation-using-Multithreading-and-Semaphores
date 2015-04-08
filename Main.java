public class Main{
    public static void main(String... args) {
        /*Customer Thread Creation*/
        PostOffice po = new PostOffice();
        for (int i = 0; i < PostOffice.custNum; i++) {
            PostOffice.objCust[i] = new Customer(i);
            PostOffice.t1[i] = new Thread(PostOffice.objCust[i]);
            PostOffice.t1[i].start();

        }
        /*Worker Thread Creation*/
        for (int i = 0; i < PostOffice.workNum; i++) {
            PostOffice.objWork[i] = new Worker(i);
            PostOffice.t2[i] = new Thread(PostOffice.objWork[i]);
            PostOffice.t2[i].start();
        }
        /*Customers joined*/
        for (int i = 0; i < PostOffice.custNum; i++) {
            try {
               PostOffice.t1[i].join();
                System.out.println("Joined customer " + i);
            } catch (InterruptedException e) {

            }
        }
        System.exit(0);
    }
}
