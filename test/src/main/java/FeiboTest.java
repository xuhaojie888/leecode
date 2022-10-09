/**
 * FeiboTest
 *
 * @author xuhaojie5
 * @date 2022/5/3 9:00 上午
 */
public class FeiboTest {
    public static void main(String[] args) {
//        System.out.println(digui(10));
//        System.out.println(digui2(10));
//        System.out.println(ite(10));
        System.out.println(m1(3));
    }

    private static int digui(int num) {
       if(num == 0 || num == 1){
           return num;
       }

       return digui(num-1)+digui(num-2);
    }

    private static int digui2(int num) {
        int[] cache = new int[num+1];
        cache[0] =0;
        cache[1] =1;
        return rec(cache,num);
    }

    private static int rec(int[] cache, int num) {
        if(num < 2){
            return cache[num];
        }
        if(cache[num] == 0){
            cache[num] = rec(cache,num-1)+rec(cache,num-2);
        }
        return cache[num];
    }

    private static int ite(int num) {
        if(num == 0 || num == 1){
            return num;
        }
        int left = 0;
        int right = 1;
        for (int i = 2; i <= num; i++) {
            int sum = left +right;
            left = right;
            right = sum;
        }
        return right;
    }

    private static int m1(int n){
        if(n<1){
            return 0;
        }
        int sum = 0;
        int count = 0;
        while(sum<=n){
            count++;
            sum = sum+count;
        }
        return count-1;
    }

}
