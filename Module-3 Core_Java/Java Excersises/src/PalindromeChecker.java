import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string: ");
        String original = sc.nextLine();

        String cleaned = original.replaceAll("[^A-Za-z0-9]" ,"").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();

        if (cleaned.equals(reversed)){
            System.out.println("It is a plaindrome.");
        } else{
            System.out.println("It is not a plaindrome.");
        }
        sc.close();
    }
}
