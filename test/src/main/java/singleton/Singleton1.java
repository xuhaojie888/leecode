package singleton;

/**
 * Singleton1
 *
 * @author xuhaojie5
 * @date 2022/6/22 3:09 下午
 */
public class Singleton1 {
    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1(){
    }

    public static Singleton1 getSingleton(){
        return singleton1;
    }
}
