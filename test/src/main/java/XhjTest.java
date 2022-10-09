import org.junit.Test;

import java.util.*;

public class XhjTest {

    /**
     * 终于写出来了。。。。。。
     */
    @Test
    public void testTopN(){
        int[][] nums = new int[][]{{1,2,3},{2,3,4},{3,4,5},{5,6,7}};
        int n = 10;
        List<Integer> topAns = new ArrayList<>();
        Queue<String> positionQueue = new LinkedList<>();
        positionQueue.offer((nums.length-1)+"-"+(nums[0].length-1));
        while(!positionQueue.isEmpty() && n > 0){
            int size = positionQueue.size();
            int maxR = -1;
            int maxC = -1;
            while(size>0){
                String rc = positionQueue.poll();
                String[] position = rc.split("-");
                int r = Integer.parseInt(position[0]);
                int c = Integer.parseInt(position[1]);
                int num = nums[r][c];
                if(topAns.contains(num)){
                    String left = (r-1)+"-"+c;
                    if(r-1>=0 && !positionQueue.contains(left)) {
                        positionQueue.offer(left);
                    }
                    String up = r+"-"+(c-1);
                    if(c-1 >= 0&& !positionQueue.contains(up)) {
                        positionQueue.offer(up);
                    }
                }else {
                    if (maxR == -1) {
                        maxR = r;
                        maxC = c;
                    }else {
                        if(nums[maxR][maxC] < nums[r][c]){
                            maxR = r;
                            maxC = c;
                        }
                    }
                    positionQueue.offer(rc);
                }
                size--;
            }
            if(maxR >= 0){
                int max = nums[maxR][maxC];
                topAns.add(max);
                positionQueue.remove(maxR+"-"+maxC);
                if(maxR-1>=0 && !positionQueue.contains((maxR-1)+"-"+maxC)) {
                    positionQueue.offer((maxR-1)+"-"+maxC);
                }
                if(maxC-1 >= 0&& !positionQueue.contains(maxR+"-"+(maxC-1))) {
                    positionQueue.offer(maxR+"-"+(maxC-1));
                }
                n--;
            }

        }
        System.out.println(topAns);
    }

    @Test
    public void test2TopN() {
        int[][] nums = new int[][]{{1, 2, 3}, {2, 3, 4}, {3, 4, 5}, {5, 6, 7}};
        int n = 10;
        List<Integer> ans = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(getKey(nums.length-1,nums[0].length-1));
        while(!queue.isEmpty() && n > 0){
            int size = queue.size();
            String maxKey = null;
            int maxNum = Integer.MIN_VALUE;
            while(size>0){
                String key = queue.poll();
                int row = getRow(key);
                int col = getCol(key);
                int num = nums[row][col];
                if(ans.contains(num)){
                    //与已找出的top值重复
                    if(row-1>=0){
                        queue.offer(getKey(row-1,col));
                    }
                    if(col-1 >= 0){
                        queue.offer(getKey(row,col-1));
                    }
                    size--;
                    continue;
                }
                if(maxKey == null || maxNum < num){
                    maxNum = num;
                    maxKey = key;
                }
                queue.offer(key);
                size--;
            }
            if(maxKey != null){
                ans.add(maxNum);
                queue.remove(maxKey);
                int row = getRow(maxKey);
                int col = getCol(maxKey);
                if(row-1>=0){
                    queue.offer(getKey(row-1,col));
                }
                if(col-1 >= 0){
                    queue.offer(getKey(row,col-1));
                }
                n--;
            }
        }

        System.out.println(ans);

    }

    private String getKey(int row,int col){
        return row+"-"+col;
    }

    private int getRow(String key){
        return Integer.parseInt(key.split("-")[0]);
    }
    private int getCol(String key){
        return Integer.parseInt(key.split("-")[1]);
    }

    /**
     * 写一个程序，找到由其它单词组成的最长单词。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(longestWord2(new String[]{"apple","iOS","dog","nana","man","good","goodman"}));
    }

    public static String longestWord(String[] words) {
        // write code here
        if(words == null || words.length < 3){
            return "";
        }
        String ans = "";
        List<String> wordList = Arrays.asList(words);
        for (String word : words) {
            for (String s : words) {
                if(s.equals(word)){
                    continue;
                }
                if(s.length() > ans.length() && s.startsWith(word) && wordList.contains(s.substring(word.length()))){
                    if(ans.length() == 0){
                        ans = s;
                    }else {
                        if(ans.length() < s.length()){
                            ans = s;
                        }else if(ans.length() == s.length() && ans.compareTo(s)<0){
                            ans = s;
                        }
                    }
                }
            }
        }
        return ans;
    }
    public static String longestWord2 (String[] words) {
        if(words == null || words.length < 3){
            return "";
        }
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if( o1.length()>o2.length()){
                    return 0;
                }else if(o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }
                return -1;
            }
        });
        String ans = "";
        for (int i = words.length-1 ; i >0; i--) {
            String word = words[i];
            for (int j = i-1; j >= 0; j--) {
                while(word.startsWith(words[j])){
                    word = word.substring(words[j].length());
                }
            }
            if(word.length() == 0){
                return words[i];
            }

        }
        return ans;

    }

    @Test
    public void testTopK(){
       int[] arr1 = {1,2,3,4,5};
       int[] arr2 = {4,5,6,7,8};
       int k = 10;
       List<Integer> ans = new ArrayList<>();
       Queue<String> queue = new LinkedList<>();
       queue.offer(getKey(arr1.length-1,arr2.length-1));
       while(k>0 && !queue.isEmpty()){
           int size = queue.size();
           int max = Integer.MIN_VALUE;
           String maxKey = null;
           while(size > 0){
               String key = queue.poll();
               int row = getRow(key);
               int col = getCol(key);
               int sum = arr1[row]+arr2[col];
               if(maxKey == null || max < sum){
                   max = sum;
                   maxKey = key;
               }
               queue.offer(key);
               size--;
           }
           queue.remove(maxKey);
           ans.add(max);
           int row = getRow(maxKey);
           int col = getCol(maxKey);
           if(row > 0) {
               queue.offer(getKey(row - 1, col));
           }
           if(col > 0) {
               queue.offer(getKey(row, col-1));
           }
           k--;
       }
        System.out.println(ans);
    }
}
