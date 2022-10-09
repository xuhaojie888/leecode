import org.junit.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * DateTest
 *
 * @author xuhaojie5
 * @date 2022/6/10 6:54 上午
 */
public class DateTest {

    @Test
    public void testGetDayNumber(){
        System.out.println(LocalDate.now().getDayOfYear());
    }

    @Test
    public void testString() throws Exception {
        String s = new String("abc");
        System.out.println(s);
        System.out.println(Integer.toHexString(System.identityHashCode(s)));
        System.out.println(Integer.toHexString(System.identityHashCode(new String("abc"))));
        Field value = s.getClass().getDeclaredField("value");
        value.setAccessible(true);
        value.set(s,"abcd".toCharArray());
        System.out.println(s);
        System.out.println(Integer.toHexString(System.identityHashCode(s)));
    }



}
