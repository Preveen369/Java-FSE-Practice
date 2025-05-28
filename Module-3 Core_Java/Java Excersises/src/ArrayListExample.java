import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> students = new ArrayList<>();

        System.out.print("Enter number of student names: ");
        int count = sc.nextInt();
        sc.nextLine(); // Consume newline

        for (int i = 0; i < count; i++) {
            System.out.print("Enter name #" + (i + 1) + ": ");
            String name = sc.nextLine();
            students.add(name);
        }

        System.out.println("Student names entered:");
        for (String name : students) {
            System.out.println(name);
        }
    }
}
