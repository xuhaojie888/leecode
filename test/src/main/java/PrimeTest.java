/**
 * PrimeTest
 * 统计素数个数
 *
 * @author xuhaojie5
 * @date 2022/5/2 4:42 下午
 */
public class PrimeTest {

    public static void main(String[] args) {
        System.out.println(fn(100));
        System.out.println(asf(100));
    }

    private static int fn(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if(isPrime(i)){
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i*i <= n; i++) {
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }

    private static int asf(int n) {
        int count = 0;
        boolean[] flags = new boolean[n];//false表示素数
        for (int i = 2; i < n; i++) {
            if(!flags[i]){
                count++;
                for (int j = i*i; j < n; j+=i) {
                    flags[j] = true;
                }
            }
        }
        return count;
    }

}
