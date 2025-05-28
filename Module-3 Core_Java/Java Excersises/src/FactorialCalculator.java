import java.util.Scanner;

public class FactorialCalculator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a non-negative integer: ");
        int n = sc.nextInt();

        if (n<0){
            System.out.println("Factorial is not defined for negative numbers.");
            return;
        }

        long factorial = 1;
        for (int i=1; i<=n; i++){
            factorial *= i;
        }
        System.out.println("Fcatorial of " + n + " is: " + factorial);
        sc.close();
    }
}
