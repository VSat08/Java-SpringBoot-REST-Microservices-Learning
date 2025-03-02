/*


 There are Total 10 Priority

 10 - MAX_PRIORITY
 5 - NORM_PRIORITY
 1 - MIN_PRIORITY

 */

//All User Threads has NORM_PRIORITY


class MyThread extends Thread {
    @Override
    @SuppressWarnings("UseSpecificCatch")
    public void run() { // controlled by JVM
        System.out.println("Running state");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " -------------- I am "+ Thread.currentThread().getName()+"  --------------" + Thread.currentThread().getPriority() + "--------------");
            try {
                Thread.sleep(1000, 300);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

public class SleepExample {
    public static void main(String[] args) throws InterruptedException {

        MyThread t1 = new MyThread(); // Thread-0
        t1.setPriority(10);
        t1.start();
        t1.join(5000);

        MyThread t2 = new MyThread(); // thread-1
        t2.setName("Secondry Threat");
        t2.start();
    }
}
