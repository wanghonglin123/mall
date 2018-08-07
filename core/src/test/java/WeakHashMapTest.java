import org.junit.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by wang.honglin on 2018/7/31.
 */
public class WeakHashMapTest {
    @org.junit.Test
    public void test() {
        Map weakHashMap = new WeakHashMap();
        Object o = new Object();
        Object o1 = new Object();
        Object o2 = new Object();

        String s = new String("1");
        weakHashMap.put(s, o);
        weakHashMap.put("2", o2);
        weakHashMap.put("3", o1);

        s = null;
        System.gc();

        String uuId = UUID.randomUUID().toString();
        System.out.println(uuId);
        System.out.printf("wmap:%s", weakHashMap);
    }

    // Vector ConcurrentModificationException 异常模拟
    private static final Vector vector = new Vector(10000);
    static {
        for(int i = 0; i < 10000; i++) {
            vector.add(i);
        }
    }
    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println(atomicInteger.compareAndSet(1, 0));
        System.out.println(atomicInteger.compareAndSet(1, 0));
    }

}
