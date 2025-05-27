public class OperatorPrecedence {
    public static void main(String[] args) {
        int result = 10 + 5 * 2; // 5 * 2 = 10, then 10 + 10 = 20
        int result2 = (10 + 5) * 2; // Parentheses first: 15 * 2 = 30
        System.out.println("Without parentheses: " + result);
        System.out.println("With parentheses: " + result2);
    }
}
