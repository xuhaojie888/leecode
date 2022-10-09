package singleton2;

import java.lang.reflect.Constructor;

public class LazySingleton2 {
    private volatile static LazySingleton2 singleton;
    private LazySingleton2(){
        if(singleton !=null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    public static LazySingleton2 getSingleton(){
        if(singleton == null){
            synchronized (LazySingleton2.class){
                if(singleton == null){
                    singleton = new LazySingleton2();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getSingleton().hashCode());
        Constructor<LazySingleton2> declaredConstructor = LazySingleton2.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        LazySingleton2 lazySingleton2 = declaredConstructor.newInstance();
        System.out.println(lazySingleton2.hashCode());
    }
}
