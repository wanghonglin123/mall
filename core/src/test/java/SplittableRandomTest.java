import java.util.SplittableRandom;

/**
 * Created by wang.honglin on 2018/8/8.
 *
 */
public class SplittableRandomTest {
    public static void main(String[] args) {
        SplittableRandom splittableRandom = new SplittableRandom();
        SplittableRandom splittableRandom1 = splittableRandom.split();
        SplittableRandom splittableRandom2 = splittableRandom.split();
        System.out.println(splittableRandom.nextDouble());
        System.out.println(splittableRandom1.nextDouble());
        System.out.println(splittableRandom2.nextDouble());
        System.out.println(splittableRandom.nextInt());
        System.out.println(splittableRandom.nextLong());
    }
}
