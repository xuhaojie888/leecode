import org.junit.Test;

import java.util.Arrays;

public class SortTest {
    String s = new String();
    @Test
    public void testSort(){
        int[] arr = {1, 5,2, 4,3};
        System.out.println(Arrays.toString(arr));
//        quickSort(arr,0,arr.length-1);
//        heapSort(arr);
        guibingSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private void guibingSort(int[] arr, int l, int r) {
        if(l>=r){
            return;
        }
        int mid = l+(r-l)/2;
        guibingSort(arr,l,mid);
        guibingSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    private void merge(int[] arr, int l, int mid, int r) {
        int[] ans = new int[r-l+1];
        int lIndex = l;
        int rIndex = mid+1;
        int i = 0;
        while(lIndex <= mid && rIndex <= r){
            if(arr[lIndex] < arr[rIndex]){
                ans[i++] = arr[lIndex++];
            }else {
                ans[i++] = arr[rIndex++];
            }
        }
        while(lIndex <= mid){
            ans[i++] = arr[lIndex++];
        }
        while(rIndex <= r){
            ans[i++] = arr[rIndex++];
        }
        i = 0;
        while(i<ans.length){
            arr[l+i] = ans[i];
            i++;
        }
    }


    private void heapSort(int[] arr) {
       int n = arr.length;
       for(int i = n/2;i>=0;i--){
           heapliy(arr,i,n);
       }
       for(int i=0;i<n-1;i++){
           swapInt(arr,0,n-1-i);
           heapliy(arr,0,n-1-i);
       }
    }

    private void heapliy(int[] arr, int i,int len) {
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int x = i;
        if(l<len && arr[x] < arr[l]){
            x = l;
        }
        if(r<len && arr[x] < arr[r]){
            x = r;
        }
        if(x != i){
            swapInt(arr,x,i);
            heapliy(arr,x,len);
        }
    }

    private void quickSort(int[] arr,int startIndex ,int endIndex) {
        if(startIndex >= endIndex){
            return;
        }
        int shaobing = arr[startIndex];

        int l = startIndex;
        int r = endIndex;
        while(l<r) {
            while (r > startIndex && arr[r] >= shaobing) {
                r--;
            }
            while (l < endIndex && arr[l] <= shaobing) {
                l++;
            }
            if (l < r) {
                swapInt(arr, l, r);
            }
        }
        swapInt(arr,startIndex,r);
        quickSort(arr,startIndex,r-1);
        quickSort(arr,r+1,endIndex);
    }

    private void swapInt(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
