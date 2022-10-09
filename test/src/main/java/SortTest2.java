import org.junit.Test;

public class SortTest2 {

    @Test
    public void testSort(){
        System.out.println("hello world");
        int[] arr = {1, 6, 2, 4, 3, 0, 7, 4};
        printArr(arr);
        arr = guiBingSort(arr,0,arr.length-1);
//        heapSort(arr);
//        quickSort(arr,0,arr.length-1);
        printArr(arr);
    }

    private int[] guiBingSort(int[] arr,int low,int high) {
        if(low == high){
            return new int[]{arr[low]};
        }
        int mid = low + (high-low)/2;
        int[] left = guiBingSort(arr,low,mid);
        int[] right = guiBingSort(arr,mid+1,high);
        return bing(left,right);
    }

    private int[] bing(int[] left, int[] right) {

        int[] ans = new int[left.length+right.length];
        int index = 0;
        int i=0;
        int j=0;
        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                ans[index++]=left[i++];
            }else {
                ans[index++]=right[j++];
            }
        }
        while(i < left.length){
            ans[index++] = left[i++];
        }
        while(j < right.length){
            ans[index++] = right[j++];
        }
        return ans;
    }

    private void heapSort(int[] arr) {
        int n = arr.length;
        //build heap
        for (int i = n/2; i >=0 ; i--) {
            heapily(arr,i,n);
        }
        for(int i=0;i<n;i++){
            swapIJ(arr,0,n-i-1);
            heapily(arr,0,n-i-1);
        }
    }

    private void heapily(int[] arr, int i, int n) {
        if(i>=n){
            return;
        }
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int maxIndex = i;
        if(l<n && arr[maxIndex] < arr[l]){
            maxIndex = l;
        }
        if(r<n && arr[maxIndex] < arr[r]){
            maxIndex = r;
        }
        if(maxIndex != i){
            swapIJ(arr,i,maxIndex);
            heapily(arr,maxIndex,n);
        }
    }

    private void quickSort(int[] arr, int low, int high) {
        if(low>=high){
            return;
        }
        int base = arr[low];
        int left = low;
        int right = high;
        while(left<right){
            while(left < right && arr[right] >= base){
                right--;
            }
            while(left < right && arr[left] <= base){
                left++;
            }
            if(left < right){
                swapIJ(arr,left,right);
            }
        }
        swapIJ(arr,low,left);
        quickSort(arr,low,left-1);
        quickSort(arr,left+1,high);
    }


    private void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i+",");
        }
        System.out.println();
    }

    private void swapIJ(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
