package online.flowerinsnow.reflectionutils.test.object;

public class Tiger extends Animal {
    public Tiger() {
        super("Tiger");
    }

    public void rest() {
        System.out.println(name + " is resting");
    }

    public void eat(Animal animal) {
        System.out.println(name + " is eating " + animal.name);
    }
}
