/**
 * Created by wang.honglin on 2018/8/1.
 */
public class BitTest {
    public static void main(String[] args) {
        // 0000
        System.out.println(15 & "8".hashCode());
        // 1001 100001
        String s = Integer.toBinaryString(1);
        int i = 6;
        do {
            System.out.println(i % 2);
        } while ((i = i / 2) > 0);

        System.out.println(new int[3].length);
    }
}
