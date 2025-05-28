class MessagePrinter implements Runnable {
    private String message;

    public MessagePrinter(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(message + " - " + i);
            try {
                Thread.sleep(500); // pause for 500ms
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MessagePrinter("Thread 1"));
        Thread t2 = new Thread(new MessagePrinter("Thread 2"));

        t1.start();
        t2.start();
    }
}
