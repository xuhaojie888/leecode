package singleton;

/**
 * Singleton1
 *
 * @author xuhaojie5
 * @date 2022/6/22 3:09 下午
 */
public class Singleton4 {
    private static class Holder{
        private static Singleton4 singleton4 = new Singleton4();
    }

    private Singleton4(){
    }

    public static Singleton4 getSingleton(){
        return Holder.singleton4;
    }
}
