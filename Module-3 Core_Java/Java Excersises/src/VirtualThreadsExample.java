public class VirtualThreadsExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 100_000; i++) {
            int id = i;
            Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread #" + id);
            });
        }
    }
}
