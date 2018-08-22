/**
 * @Title: EnumTest
 * @Package: PACKAGE_NAME
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 10:00
 * @Version: V2.0.0
 */

import com.whl.mall.core.transcation.common.enums.RoleTranscationPropertiesEnum;
import com.whl.mall.core.common.utils.MallThreadPollUtils;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @ClassName: EnumTest
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-06-03 上午 10:00
 */
public abstract class EnumTest {
    public EnumTest(int i){
        System.out.println(i);
    }

    public EnumTest() {
    }
    @org.junit.Test
    public void test() throws Exception{
        Enum[] enums = RoleTranscationPropertiesEnum.values();
        Enum transcation = enums[0];
        Class classes = transcation.getClass();
        Method method = classes.getDeclaredMethod("getTargetMethods");
        String[] result = (String[])method.invoke(transcation);
        Arrays.stream(result).forEach(s -> {
            System.out.println(s);
        });
    }

    @org.junit.Test
    public void test1() {
        for(int i = 0; i < 3; i++) {
            MallThreadPollUtils.executorService.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            });
        }
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        System.out.println("执行完毕");
    }

    @org.junit.Test
    public void test2() {
        String idx = "张三";
        byte[] body = idx.getBytes();
        idx = new String(body);
        System.out.println(idx);
    }

    @org.junit.Test
    public void  test3() throws Exception{
        final StringBuilder builder = new StringBuilder();
        String result = null;
        CompletableFuture completableFuture = null;
        for (int i = 0; i < 1; i++) {
            completableFuture = CompletableFuture.runAsync(() -> {
                    System.out.println(1 / 0);
            }).handle((aVoid, throwable) -> {
                 return throwable;
             });
        }
        System.out.println(completableFuture.get());

        Tets.class.newInstance();
    }

    @Test
    public void test5() throws Exception{
        Test2.class.newInstance();
    }

    /*@org.junit.EnumTest
    public void test() {
        List<Integer> pages = Arrays.asList(1, 2,3,4);
        pages.parallelStream().forEach(integer -> {
            System.out.println(integer);
        });
    }*/

    @FunctionalInterface
    public interface Tets{
        void test();
    }

    public static class Test2 extends EnumTest{
        public Test2(int i) {
            super(i);
        }

        public Test2() {
        }
        @Test
        public void test() {
            new Test2(2);
        }
    }
}
