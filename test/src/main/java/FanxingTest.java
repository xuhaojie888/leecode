import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FanxingTest {

    @Test
    public void testFanXing(){
        List list  = new ArrayList();
        list.add("1");
        list.add(1);
        list.add(1.9f);
        Object o1 = list.get(1);

        List<?> list2 = new ArrayList<>();
//        list2.add("1");//编译错误
        List<Object> list3 = new ArrayList<>();
        list3.add(1);
        list3.add("1");
        list3.add(1.9f);
        list3.add(new Object());

        List<Integer> list4 = new ArrayList<>();
        list4.add(1);
//        list4.add("a");//编译错误

        List<? extends  Number> list5 = new ArrayList<>();
//        list5.add("a");//编译错误
//        list5.add(1);//编译错误
        Number number = list5.get(1);

        List<? super Number> list6 = new ArrayList<>();
        list6.add(1);
        list6.add(0.1);
        Object object = list6.get(1);
    }
}
