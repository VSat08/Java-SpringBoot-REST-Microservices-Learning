class Table {
    public synchronized  void printTable(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class FirstThread extends Thread {
    Table t;

    public FirstThread(Table t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.printTable(17);
    }
}

class SecondThread extends Thread {
    Table t;

    public SecondThread(Table t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.printTable(13);
    }
}


class ThirdThread extends Thread {
    Table t;

    public ThirdThread(Table t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.printTable(19);
    }
}

public class SynchronizationExample {
    public static void main(String[] args) {

        Table t = new Table();

        FirstThread t1 = new FirstThread(t);
        t1.start();

        SecondThread t2 = new SecondThread(t);
        t2.start();

       ThirdThread t3 = new ThirdThread(t);
        t3.start();


    }
}
