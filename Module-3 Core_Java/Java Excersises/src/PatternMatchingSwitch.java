public class PatternMatchingSwitch {
    public static void printType(Object obj) {
        switch (obj) {
            case Integer i -> System.out.println("Integer: " + i);
            case String s -> System.out.println("String: " + s);
            case Double d -> System.out.println("Double: " + d);
            default -> System.out.println("Unknown type");
        }
    }

    public static void main(String[] args) {
        printType(100);
        printType("Hello");
        printType(3.14);
        printType(true);
    }
}
