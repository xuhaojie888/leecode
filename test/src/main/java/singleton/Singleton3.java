package singleton;

/**
 * Singleton1
 *
 * @author xuhaojie5
 * @date 2022/6/22 3:09 下午
 */
public class Singleton3 {
    private static Singleton3 singleton3 ;

    private Singleton3(){
    }

    public static synchronized Singleton3 getSingleton(){
        if(singleton3 == null){
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}
