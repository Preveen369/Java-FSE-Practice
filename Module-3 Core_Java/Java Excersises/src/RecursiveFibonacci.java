import java.util.Scanner;

public class RecursiveFibonacci {
    public static int nthFibonacci(int n) {
        if (n<=1) return n;
        return nthFibonacci(n-1) + nthFibonacci(n-2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a positive integer: ");
        int n = sc.nextInt();

        System.out.println("Fibonaci number at position "+n+" is: " + nthFibonacci(n));
        sc.close();
    }
}
