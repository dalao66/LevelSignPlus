import java.util.Arrays;

/**
 * Created by shell on 2018/2/14.
 * <p>
 * Github: https://github.com/shellljx
 */
public class Test {

    public static void main(String[] args){
        Dog[] dogs = new Dog[2];
        Dog d1 = new Dog(7);
        Dog d2 = new Dog(5);
        dogs[0] = d1;
        dogs[1] = d2;
        Arrays.sort(dogs);
        for (Dog dog: dogs){
            System.out.println(dog.age+" years");
        }
    }
}
