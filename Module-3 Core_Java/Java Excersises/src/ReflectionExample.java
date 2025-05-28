import java.lang.reflect.Method;

class Sample {
    public void sayHello() {
        System.out.println("Hello from reflection!");
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("Sample");
        Object obj = clazz.getDeclaredConstructor().newInstance();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method found: " + method.getName());
            if (method.getName().equals("sayHello")) {
                method.invoke(obj);
            }
        }
    }
}
