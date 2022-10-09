package singleton2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public enum EnumSingleton {
    INSTANCE;

    public void display(){
        System.out.println(INSTANCE.hashCode());
    }

    public static void main(String[] args) throws Exception{
        System.out.println(INSTANCE.hashCode());
        //序列化
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("enum"));
//        oos.writeObject(INSTANCE);
//        oos.close();
        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("enum"));
        EnumSingleton o = (EnumSingleton) ois.readObject();
        System.out.println(o.hashCode());
        ois.close();
    }
}
