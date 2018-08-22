import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang.honglin on 2018/8/21.
 */
public class HashMapTest {
    @Test
    public void test() {
        String s = "abca";
        Integer h, k;
        h = s.hashCode();
        k = h >>> 16;
        System.out.println(Integer.toBinaryString(1073741824 * 2));
        System.out.println(Integer.toBinaryString(h));
        System.out.println(Integer.toBinaryString(k));
        System.out.println(Integer.toBinaryString(2631352));
        System.out.println(h);
        System.out.println(k);
        System.out.println(h ^ k );
        int n = 100;
        n |= n >>> 1;
        System.out.println(n);
        System.out.println(3 | 1);
        System.out.println(100 | 1);
        System.out.println(101 * 101);
        int num = 2147483647;
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        //num |= num >>> 32;
        System.out.println(num);
        System.out.println(1 << 30);
        new HashMap(5, 0.75F);
        System.out.println(16 & 7);
    }
}
