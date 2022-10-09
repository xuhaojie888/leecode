package singleton2;

import java.io.*;

public class InnerSingleton implements Serializable {

    private static final long serialVersionUID = -2109668532760489727L;

    private static class Holder{
        private static InnerSingleton singleton = new InnerSingleton();
    }
    private InnerSingleton(){
        if(Holder.singleton !=null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    public static InnerSingleton getInstance(){
        return Holder.singleton;
    }

    public Object readResolve() throws ObjectStreamException{
        return Holder.singleton;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(Holder.singleton.hashCode());
//        Constructor<InnerSingleton> declaredConstructor = InnerSingleton.class.getDeclaredConstructor();
//        declaredConstructor.setAccessible(true);
//        InnerSingleton innerSingleton = declaredConstructor.newInstance();
//        System.out.println(innerSingleton.hashCode());

        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testObjectSerialize"));
        oos.writeObject(Holder.singleton);
        oos.close();
        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testObjectSerialize"));
        InnerSingleton o = (InnerSingleton) ois.readObject();
        System.out.println(o.hashCode());
        ois.close();
    }
}
