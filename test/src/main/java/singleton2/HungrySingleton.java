package singleton2;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class HungrySingleton {
    private static HungrySingleton singleton = new HungrySingleton();
    private HungrySingleton(){
        if(singleton !=null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    public static HungrySingleton getSingleton(){
        return singleton;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(singleton.hashCode());
//        Constructor<HungrySingleton> declaredConstructor = HungrySingleton.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        HungrySingleton hungrySingleton = declaredConstructor.newInstance();
//        System.out.println(hungrySingleton.hashCode());
    }
}
