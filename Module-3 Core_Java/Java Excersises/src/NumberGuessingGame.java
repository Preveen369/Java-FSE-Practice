import java.util.Scanner;

public class NumberGuessingGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int target = (int) (Math.random() * 100) + 1;
        int guess;

        do{
            System.out.print("Guess a number(1 to 100): ");
            guess = sc.nextInt();

            if (guess > target){
                System.out.println("Too High!");
            }
            else if(guess < target){
                System.out.println("Too Low!");
            }
            else{
                System.out.println("Correct! The number was " + target);
            }
        } while (guess != target);
        sc.close();
    }
}