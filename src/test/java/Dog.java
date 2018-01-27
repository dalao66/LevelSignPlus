import java.util.Comparator;

/**
 * Created by shell on 2018/2/14.
 * <p>
 * Github: https://github.com/shellljx
 */
public class Dog implements Comparable<Dog> {
    public int age;

    public Dog(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Dog o) {
        return o.age-age;
    }
}
