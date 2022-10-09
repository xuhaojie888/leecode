package design_pattern;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * design_pattern.ArrayTest
 *
 * @author xuhaojie5
 * @date 2022/5/2 6:26 下午
 */
public class ArrayTest {

    public static void main(String[] args) {
//        int[] arr = new int[]{1,2,3,3,4,4,5,5,6,6,6,6,6,7};
//        System.out.println(arr.length);
//        System.out.println(removeDuplicate(arr));
//        int[] arr = new int[]{1,2,3,4,3,1,2};
//        System.out.println(test2(arr));
//        System.out.println(sqrt(25));
//        System.out.println(sqrt(24));
//        System.out.println(sqrt(26));
        System.out.println(newton(25));
//        System.out.println(newton(24));
//        System.out.println(newton(26));
//        System.out.println(sortF(new int[]{1,2,3,4,5,6,7}));
//        System.out.println(sortF(new int[]{-1,-2,-3,-4,-5,6,7}));
//        System.out.println(sortF(new int[]{-1,-2,-3,-4,-5,-6,7}));
//        System.out.println(sortF(new int[]{-1,-2,-3,-4,-5,-6,-7}));
//        System.out.println(lineIterator(new int[]{1,2,3,4,5,6,7}));
//        System.out.println(lineIterator(new int[]{-1,-2,-3,-4,-5,6,7}));
//        System.out.println(lineIterator(new int[]{-1,-2,-3,-4,-5,-6,7}));
//        System.out.println(lineIterator(new int[]{-1,-2,-3,-4,-5,-6,-7}));
//        printArray(solution(new int[]{4,1,3,2,5,6},10));
//        printArray(solution2(new int[]{4,1,3,2,5,6},10));
//        printArray(solution(new int[]{1,2,3,4,5,6},10));
//        printArray(solution2(new int[]{1,2,3,4,5,6},10));
//        printArray(twoSearch(new int[]{1,2,3,4,5,6},10));
//        printArray(twoPoint(new int[]{1,2,3,4,5,6},10));

    }

    public static void printArray(int[] arr){
        for (int i : arr) {
            System.out.print(i+",");
        }
        System.out.println();
    }

    private static int removeDuplicate(int[] arr) {
        if(arr == null || arr.length <= 0){
            return 0;
        }
        int slow = 0;
        for (int fast = 1; fast < arr.length; fast++) {
            if(arr[slow] != arr[fast]){
                slow++;
                arr[slow] = arr[fast];
            }
        }
        return slow+1;
    }

    public static int test(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
            if(total == sum ){
                return i;
            }
            sum -= arr[i];
        }
        return -1;
    }

    public static int test2(int[] arr) {
        System.out.println("test2");
        int sum = Arrays.stream(arr).sum();
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if(total * 2 + arr[i] == sum ){
                return i;
            }
            total += arr[i];
        }
        return -1;
    }

    public static int sqrt(int n){
        int index = -1;
        int left = 0;
        int right = n;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(mid * mid <= n){
                index = mid;
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return index;
    }

    public static int newton(int n){
        if(n==0){
            return 0;
        }
        return (int)newtonSqrt(n,n);
    }

    public static double newtonSqrt(double x,int n){
        double res = (x + n/x)/2;
        if(res == x){
            return x;
        }
        return newtonSqrt(res,n);
    }

    public static int sortF(int[] arr){
        Arrays.sort(arr);
        int n = arr.length;
        return Math.max(arr[0]*arr[1]*arr[n-1],arr[n-1]* arr[n-2]*arr[n-3]);
    }

    public static int lineIterator(int[] arr) {
        int min1=Integer.MAX_VALUE;
        int min2=Integer.MAX_VALUE;
        int max1=Integer.MIN_VALUE;
        int max2=Integer.MIN_VALUE;
        int max3=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            if(x<min1){
                min2 = min1;
                min1 = x;
            }else if(x < min2){
                min2 = x;
            }
            if(x > max1){
                max3 = max2;
                max2 = max1;
                max1 = x;
            }else if(x>max2){
                max3 = max2;
                max2 = x;
            }else if(x > max3){
                max3 = x;
            }
        }
        return Math.max(min1*min2*max1,max1*max2*max3);
    }

    public static int[] solution(int[] arr,int target){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]+arr[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0};
    }

    public static int[] solution2(int[] arr,int target){
        //key:arr[i]中具体值 value:数组下标
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int dest = target - arr[i];
            if(map.containsKey(dest)){
                return new int[]{map.get(dest),i};
            }
            map.put(arr[i],i);
        }
        return new int[]{0};
    }

    public static int[] twoSearch(int[] arr,int target){
        for (int i = 0; i < arr.length; i++) {
            int low = i+1;
            int high = arr.length-1;
            while(low<=high){
                int mid = low + (high-low)/2;
                int predit = arr[mid] + arr[i];
                if(predit == target){
                    return new int[]{i,mid};
                }else if(predit > target){
                    high = mid-1;
                }else {
                    low = mid+1;
                }
            }
        }
        return new int[]{0};
    }

    public static int[] twoPoint(int[] arr,int target){
        int low = 0;
        int high = arr.length-1;
        while(low<high){
            int predit = arr[low] + arr[high];
            if(predit == target){
                return new int[]{low,high};
            }else if(predit > target){
                high--;
            }else {
                low++;
            }
        }
        return new int[]{0};
    }

}
