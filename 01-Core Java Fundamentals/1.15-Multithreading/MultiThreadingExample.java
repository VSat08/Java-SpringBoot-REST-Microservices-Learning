class MyThread extends Thread {
    @Override
    public void run() { // controlled by JVM
        System.out.println("Running state");
        for (int i = 0; i < 10; i++) {
            System.out.println("-------------- I am User Thread --------------");
        }
    }
}

public class MultiThreadingExample {
    public static void main(String[] args) { // main thread
        MyThread t1 = new MyThread(); // New state //user thread
        t1.start(); // Runnable state
        // Scheduler moves to Running, then Terminated

        for (int i = 0; i < 10; i++) {
            System.out.println("-------------- I am Main Thread --------------");
        }
    }
}
