import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wang.honglin on 2018/8/1.
 */
public class ReetrantLockTest {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                if (reentrantLock.tryLock(2000, TimeUnit.SECONDS)) {
                    System.out.println(1);
                    reentrantLock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                if (reentrantLock.tryLock(2000, TimeUnit.SECONDS)) {
                    System.out.println(2);
                    reentrantLock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
