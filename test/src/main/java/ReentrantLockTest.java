import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
//        testReentrantLock();
        System.out.println(Integer.bitCount(4));
        System.out.println(Integer.bitCount(6));
        System.out.println(Integer.bitCount(7));
    }

    public static void testReentrantLock() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "######获取####锁 ");
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "*****释放*****锁 ");
                }
            }, "thread" + i).start();
        }
    }
}
