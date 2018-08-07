import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Vector ConcurrentModificationException 异常模拟, 和解决
 * Created by wang.honglin on 2018/8/1.
 */
public class VectorTest {
    /**
     * Vector ConcurrentModificationException 异常模拟
     */
    static class ExceptionTest {
        public static void main(String[] args) {
            Vector vector = new Vector(10);

            new Thread(() -> {
                // 在使用迭代器iterator()或者listIterator()的时候，只有当前迭代器可以add()和remove()。
                // 如果其它线程并发操作该数组元素，那么iterator.next()会快速抛出ConcurrentModificationException
                Iterator iterator = vector.listIterator();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }).start();

            new Thread(() -> {
                vector.add(3);
            }).start();

        }
    }

    /**
     * 解决Vector ConcurrentModificationException 问题
     * 当需要使用迭代器的时候，添加锁，遍历完成后，再释放锁
     */
    static class MeasuresTest{
        public static void main(String[] args) {
            Vector vector = new Vector(10);
            vector.add("1");
            vector.add("2");
            vector.add("3");

            ReentrantLock reentrantLock = new ReentrantLock();
            new Thread(() -> {
                try {
                    // 尝试获取锁，设置一个获取锁超时时间
                    if (reentrantLock.tryLock(2000, TimeUnit.SECONDS)) {
                        Iterator iterator = vector.listIterator();
                        Thread.sleep(1000);
                        while (iterator.hasNext()) {
                            System.out.println(iterator.next());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }).start();

            new Thread(() -> {
                try {
                    // 尝试获取锁，设置一个获取锁超时时间
                    if (reentrantLock.tryLock(2000, TimeUnit.SECONDS)) {
                        vector.add(3);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }).start();
        }
    }
}
