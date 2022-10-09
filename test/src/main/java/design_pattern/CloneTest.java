package design_pattern;

import com.alibaba.fastjson.JSON;
import design_pattern.A;
import design_pattern.B;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CloneTest {
    public static void main(String[] args) throws Exception{
        A a = new A("xxx",1,new B("1"));
//        design_pattern.A clone = a.clone();
        String s = JSON.toJSONString(a);
        A clone = JSON.parseObject(s, A.class);
        System.out.println(a);
        System.out.println(clone);
        a.getB().setB("b");
        System.out.println(a);
        System.out.println(clone);
    }

    @Test
    public void testInteger(){
        int i = 127;
//        int j = 128;
        Integer i1 = i;
        Integer i2 = Integer.valueOf(i);
        Integer i3 = new Integer(127);
        Integer i4 = Integer.parseInt("127");
        Integer i5 = Integer.parseInt(new String("127"));
        Integer i6 = Integer.parseInt(new String("127").intern());
        System.out.println(i1==i2);
        System.out.println(i1==i3);
        System.out.println(i1==i4);
        System.out.println(i1==i5);
        System.out.println(i1==i6);
        System.out.println(i1.equals(i2));
        System.out.println(i1.equals(i3));
        System.out.println(i1.equals(i4));
        System.out.println(i1.equals(i5));
        System.out.println(i1.equals(i6));
    }

    @Test
    public void testArray(){
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers);
        System.out.println(integers.get(0));
//        integers.add(1);
        integers.set(0,4);
        System.out.println(integers.get(0));
//        integers.remove(2);
    }



}
