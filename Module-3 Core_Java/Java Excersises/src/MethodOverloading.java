public class MethodOverloading {
    public static int add(int a, int b) {
        return a + b;
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println("Sum of 5 and 10: " + add(5, 10));
        System.out.println("Sum of 5.5 and 4.5: " + add(5.5, 4.5));
        System.out.println("Sum of 1,2 and 3: " + add(1, 2, 3));
    }
}
