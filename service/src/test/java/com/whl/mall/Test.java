package com.whl.mall;
/**
 * @Title: Test
 * @Package: com.whl.mall
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-17 下午 9:42
 * @Version: V2.0.0
 */

import com.whl.mall.core.transcation.base.TranscationEnum;
import com.whl.mall.core.transcation.common.enums.RoleTranscationPropertiesEnum;
import com.whl.mall.core.transcation.pojo.Transcation;
import org.springframework.util.MethodInvoker;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName: Test
 * @Description:
 * @Author: WangHongLin
 * @Date: 2018-04-17 下午 9:42
 */
public class Test {
    @org.junit.Test
    public void test() throws Exception{
        Class tanscationClass = RoleTranscationPropertiesEnum.class;
        RoleTranscationPropertiesEnum[] transcationEnums = RoleTranscationPropertiesEnum.values();
        TranscationEnum transcationEnum = (TranscationEnum) Enum.valueOf(tanscationClass, "ROLE");
        //System.out.println(transcationEnum.getTargetBeanName());
        /*for (TranscationEnum transcationEnum : transcationEnums) {
            if (transcationEnum instanceof TranscationEnum) {
                System.out.println(transcationEnum.getTargetBeanName());
            }
        }*/
    }

    @org.junit.Test
    public void  test1() throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i ++) {
            final int k = i;
            new Thread(() -> {
                try {
                    System.out.println(k);
                    Thread.sleep(3000);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        add();
    }
    public void add() {
        int i = 0;
        int b = i++;
        System.out.println("test");
    }

    @org.junit.Test
    public void test2() {
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i < 100000; i++) {
            pages.add(i);
        }
        Set pagesSet = pages.parallelStream().collect(Collectors.toSet());
        System.out.println(pagesSet.size());
        System.out.println(1);
    }

    @org.junit.Test
    public void test3() {
        System.out.println(1);
        new Thread(() -> {
            System.out.println(2);
        }).start();
        System.out.println(3);
    }

    @org.junit.Test
    public void test4() {
        IntStream.range(1, 6).parallel().forEach(value -> {
            System.out.println(value);
        });
    }

    @org.junit.Test
    public void test5() {
        Class s = Transcation.class;
        Class b = Transcation.class;
        System.out.println(s);
        System.out.println(b);
    }

    /**
     * 用于流和线程池性能对比, test 6 vs test7
     * @throws Exception
     */
    @org.junit.Test
    public void test6() throws Exception{
        final List<Integer> integers = IntStream.range(1, 100000).collect(ArrayList<Integer>::new, (integers1, value) -> {
            integers1.add(value);
        }, List :: addAll);
        long beginTime = System.nanoTime();
        final List list = new ArrayList();
        for (Integer integer : integers) {
            String test = "zhangsan" + integer;
            String value = "wangwu";
            String b = test + value;
            b = b.substring(1);
            list.add(b);
        }
        /*integers.forEach(integer -> {
            String test = "zhangsan";
            String value = "wangwu";
            String b = test + value;
            b = b.substring(1);
            list.add(b);
        });*/
        System.out.println(System.nanoTime() - beginTime);
        System.out.println(list.size());
    }

    /**
     * 用于流和线程池性能对比, test 6 vs test7
     * @throws Exception
     */
    @org.junit.Test
    public void test7() {
        final List<Integer> integers = IntStream.range(1, 100000).collect(ArrayList<Integer>::new, (integers1, value) -> {
            integers1.add(value);
        }, List :: addAll);
        long beginTime = System.nanoTime();
        final List list = Collections.synchronizedList(new ArrayList());
        IntStream.range(1,8).forEach(value -> {
            integers.forEach(integer -> {
                String test = "zhangsan" + integer;
                String value1 = "wangwu";
                String b = test + value1;
                b = b.substring(1);
                list.add(b);
            });
        });
        System.out.println(System.nanoTime() - beginTime);
        System.out.println(list.size());
    }

    public void test8() {
        Arrays.stream(IntStream.range(1, 10).toArray()).forEach(value -> {

        });
    }
}
