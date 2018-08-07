import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by wang.honglin on 2018/7/30.
 */
public class Test1 {

    @org.junit.Test
    public void test() {
        ArrayList list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Object[] data = list.toArray(Arrays.asList(1,2,4,5,6).toArray());
        Arrays.asList(data).forEach(o -> {
            System.out.println(o);
        });
        System.out.println(UUID.randomUUID().toString());
    }

    public static <T> void copy(T t){

    }


}
