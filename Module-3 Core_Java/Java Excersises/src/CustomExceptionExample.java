class InvalidAgeException extends Exception{
    public InvalidAgeException(String message){
        super(message);
    }
}

public class CustomExceptionExample {
    public static void main(String[] args) {
        int age = 14;
        try{
            if (age < 18){
                throw new InvalidAgeException("Age must be at least 18.");
            }
            System.out.println("Valid age: " + age);
        } catch(InvalidAgeException e){
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}
