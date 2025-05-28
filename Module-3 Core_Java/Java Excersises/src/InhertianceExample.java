class Animal{
    void makeSound(){
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal{
    @Override
    void makeSound(){
        System.out.println("Bark");
    }
}

public class InhertianceExample {
    public static void main(String[] args) {
        Animal generic = new Animal();
        Dog dog = new Dog();
        generic.makeSound();
        dog.makeSound();
    }
}
