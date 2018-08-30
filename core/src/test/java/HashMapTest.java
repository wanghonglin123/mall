import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang.honglin on 2018/8/21.
 */
public class HashMapTest {
    transient Test[] tests;
    private Test test2;
    int o = 5;
    @org.junit.Test
    public void test() {
        Map map = new HashMap(16);
        int k = 0;
        for (int i = 0; i < 9; i++) {
            k += 16;
            System.out.println(k);
            map.put(k, k);
        }
    }

    class Test3 {
        public int k = 5;
    }

    @org.junit.Test
    public void test2() {
        int[] num = new int[5];
        System.out.println(num);
        int[] num1 = num;
        System.out.println(num1);
        num = new int[10];
        System.out.println(num);
        System.out.println(num1.length);
    }

    private void add(int f) {
        edit();
        System.out.println(f);
    }
    private void edit() {
        o = 6;
        System.out.println();
    }
    private void resize(Test[] test1) {
        resize();
        System.out.println(test1[1].getName());
    }

    private void resize(Test test1) {
        resize1();
        System.out.println(test1.getName());
    }

    private void resize() {
    }

    private void resize1() {
        test2.setName("2");
    }

    public class Test {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class Test2{

    }
}
