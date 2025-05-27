public class TypeCastingExample {
    public static void main(String[] args){
        double doubleValue = 9.78;
        int intValue = (int) doubleValue;   // Explicit cast
        int smallInt = 7;
        double convertedDouble = smallInt;  // Implicit cast

        System.out.println("Original double: " + doubleValue);
        System.out.println("After casting to int: " + intValue);
        System.out.println("Original int: " + smallInt);
        System.out.println("After casting to double: " + convertedDouble);
    }
}
