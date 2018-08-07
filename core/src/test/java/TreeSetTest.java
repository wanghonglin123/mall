import java.util.*;

/**
 * Created by wang.honglin on 2018/8/1.
 */
public class TreeSetTest {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        Object o = new Object();
        hashSet.add(o);
        hashSet.add(o);
        hashSet.forEach(o1 -> {
            System.out.println(o1);
        });

        HashMap hashMap = new HashMap();

        hashMap.put(o, "1");
        hashMap.put(o, "1");
        hashMap.forEach((o1, o2) -> {
            System.out.println(o1 + "------" + o2);
        });
    }
}
