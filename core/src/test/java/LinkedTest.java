import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang.honglin on 2018/8/28.
 */
public class LinkedTest {
    @org.junit.Test
    public void test() {
        Test test = new Test(0, 0);
        Test tail = test;
        for (int i = 0; i < 5; i++) {
            Test test2 = new Test(i, i);
            System.out.println(test2);
            tail.next = test2;
            tail = test2;
            System.out.println(tail);
        }
        Test test1 = test.next;
        Test next = null;
        Test head = null;
        do {
            next = test1.next;
            if ((test1.key & 2) == 0) {
                head = test1;
            } else {
                tail = test1;
            }
        } while ((test1 = next) != null);
        System.out.println(head.next);
        System.out.println(tail.next);
    }
    class Test<T, E>{
        final int key;
        final E value;
        Test next;
        public Test(int key, E value){
            this.key = key;
            this.value = value;
        }
    }
    class Test2 {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @org.junit.Test
    public void test2() {
        Test2 test2 = new Test2();
        test2.setName("zhangsan");
        Test2 test21 = test2;
        test21.setName("lisi");
        System.out.println(test2.getName());
    }
}
