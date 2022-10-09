package singleton2;

import java.lang.reflect.Constructor;

public class LazySingleton1 {
    private static LazySingleton1 singleton;

    private LazySingleton1(){
        if(singleton !=null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    public synchronized static LazySingleton1 getInstance(){
        if(singleton == null){
            singleton = new LazySingleton1();
        }
        return singleton;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getInstance().hashCode());
//        Constructor<LazySingleton1> declaredConstructor = LazySingleton1.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        LazySingleton1 lazySingleton1 = declaredConstructor.newInstance();
//        System.out.println(lazySingleton1.hashCode());
    }
}
