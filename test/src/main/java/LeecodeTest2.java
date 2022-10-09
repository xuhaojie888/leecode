import org.junit.Test;

import java.util.*;

public class LeecodeTest2 {
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    List<Integer> preorderTraversalList = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return preorderTraversalList;
        }
        preorderTraversalList.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return preorderTraversalList;
    }

    List<Integer> postorderTraversalList = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null){
            return postorderTraversalList;
        }
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        postorderTraversalList.add(root.val);

        return postorderTraversalList;
    }

    class MyStack {
        List<Integer> list;

        public MyStack() {
            list = new ArrayList<>();
        }

        public void push(int x) {
            list.add(x);
        }

        public int pop() {
            return list.remove(list.size()-1);
        }

        public int top() {
            return list.get(list.size()-1);
        }

        public boolean empty() {
            return list.isEmpty();
        }
    }

    class MyQueue2 {
        List<Integer> list;

        public MyQueue2() {
            list = new ArrayList<>();
        }

        public void push(int x) {
            list.add(x);
        }

        public int pop() {
            return list.remove(0);
        }

        public int peek() {
            return list.get(0);
        }

        public boolean empty() {
            return list.isEmpty();
        }
    }

    ListNode pre;
    public boolean isPalindrome(ListNode head) {
        if(head.next == null){
            return true;
        }
        pre = head;
        return rec(head);
    }

    private boolean rec(ListNode head) {
        if(head != null){
            if(!rec(head.next)){
                return false;
            }
            if(head.val != pre.val){
                return false;
            }
            pre = pre.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if(head.next == null){
            return true;
        }

        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        int l = 0;
        int r = list.size()-1;
        while(l<=r){
            if(list.get(l++) != list.get(r--)){
                return false;
            }
        }
        return true;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int num = -1;
            int base = nums1[i];
            boolean isFindSame = false;
            for (int j = 0; j < nums2.length; j++) {
                if(!isFindSame ){
                   if(nums2[j] == base){
                       isFindSame = true;
                   }
                }else if(nums2[j] > base){
                    num = j;
                }
            }
            ans[i] = num;
        }
        return ans;
    }

    List<Integer> preorderList = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if(root == null){
            return preorderList;
        }
        preorderList.add(root.val);
        for (Node child : root.children) {
            preorder(child);
        }

        return preorderList;
    }

    List<Integer> postorderList = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null){
            return postorderList;
        }
        for (Node child : root.children) {
            postorder(child);
        }
        postorderList.add(root.val);

        return postorderList;
    }

    /*
    整数 x - 表示本回合新获得分数 x
"+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
"D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
"C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
     */
    public int calPoints(String[] ops) {
       Stack<Integer> stack = new Stack<>();
        int num = Integer.parseInt(ops[0]);
        stack.push(num);
        int sum = num;
        for (int i = 1; i < ops.length; i++) {
            String op = ops[i];
            switch (op){
                case "+":
                    Integer pre = stack.pop();
                    Integer prepre = stack.pop();
                    stack.push(prepre);
                    stack.push(pre);
                    stack.push(pre+prepre);
                    sum+=(pre+prepre);
                    break;
                case "D":
                    Integer pre2 = stack.peek();
                    stack.push(pre2*2);
                    sum+=(pre2*2);
                    break;
                case "C":
                    Integer pre3 = stack.pop();
                    sum-=pre3;
                    break;
                default:
                    int num2 = Integer.parseInt(op);
                    stack.push(num2);
                    sum+=num2;
            }
        }
        return sum;
    }

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack1 = backspaceCompareString(s);
        Stack<Character> stack2 = backspaceCompareString(t);
        if(stack1.size() != stack2.size()){
            return false;
        }
        while(!stack1.isEmpty()){
            if(stack1.pop() != stack2.pop()){
                return false;
            }
        }
        return true;
    }

    public Stack<Character> backspaceCompareString(String s){
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '#' ){
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }else {
                stack.push(c);
            }
        }
        return stack;
    }

    Stack<Integer> stack = new Stack<>();
    public TreeNode increasingBST(TreeNode root) {

        middleScan(root);

        Integer pop = stack.pop();
        TreeNode current = new TreeNode(pop);
        while(!stack.isEmpty()){
            TreeNode parent = new TreeNode(stack.pop());
            parent.right = current;
            current = parent;
        }
        return current;
    }

    private void middleScan(TreeNode root) {
        if(root == null){
            return;
        }
        middleScan(root.left);
        stack.push(root.val);
        middleScan(root.right);
    }

    public boolean isPalindrome(String s) {
        if(s.length() < 2){
            return true;
        }
        int i = 0;
        int j = s.length()-1;
        while(i< j) {
            while (i<j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            while (i<j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            if(i<j){
                if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    public boolean canPermutePalindrome(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        int count = 0;
        for (Map.Entry<Character, Integer> en : map.entrySet()) {
            if(en.getValue() %2 == 1){
                count++;
            }
        }
        return count <= 1;
    }

    public int longestPalindrome2(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        int ans = 0;
        int count = 0;
        for (Map.Entry<Character, Integer> en : map.entrySet()) {
            Integer value = en.getValue();
            if(value == 1){
                count++;
            }else if(value %2 == 0){
                ans+=value;
            }else {
                ans+=(value-1);
                count++;
            }
        }
        if(count >=1){
            ans += 1;
        }
        return ans;
    }

    public int longestPalindrome(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        int ans = 0;
        int count = 0;//奇数个数的字符总和
        for (Map.Entry<Character, Integer> en : map.entrySet()) {
            Integer value = en.getValue();
            count += value%2;
        }
        return count == 0 ? s.length():(s.length()-count+1);
    }

    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while(l<r){
            if(l<r && s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            }else if(l<r && s.charAt(l) != s.charAt(r)){
                return validPalindromeLR(s,l,r-1) || validPalindromeLR(s,l+1,r);
            }
        }
        return true;
    }

    private boolean validPalindromeLR(String s, int l, int r) {
        while(l<r) {
            if (l < r && s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }else {
                return false;
            }
        }
        return true;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        int j = 0;
        for (int i = 0; i < g.length; i++) {
            while(j<s.length && s[j] < g[i] ){
                j++;
            }
            if(j<s.length){
                j++;
                ans++;
            }else {
               break;
            }
        }
        return ans;
    }

    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if(bill == 5){
                five++;
            }else if(bill == 10){
                if(five < 1){
                    return false;
                }
                five--;
                ten++;
            }else if(bill ==20){
                if(ten > 0 && five > 0){
                    ten--;
                    five--;
                }else if(five > 2){
                    five -=3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public int arrayPairSum(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i+=2) {
            sum +=nums[i];
        }
        return sum;
    }

    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int len = flowerbed.length;
        if(n == 0){
            return true;
        }
        if(len == 0){
            return false;
        }else if(len == 1){
            if(flowerbed[0] == 0 && n == 1){
                return true;
            }else {
                return false;
            }
        }
        int max = 0;
        int i = 0;
        while (i < len) {
            if(flowerbed[i] == 1){
                i++;
            }else if(i==0 && (flowerbed[i]+flowerbed[i+1]) == 0){
                max++;
                i = i+1;
            }else if((i+2)<len && flowerbed[i]+flowerbed[i+1]+flowerbed[i+2] == 0){
                max++;
                i = i+2;
            }else if((i+2) == len && (flowerbed[i]+flowerbed[i+1]) == 0){
                max++;
                break;
            }else{
                i++;
            }
        }
        return max >= n;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n== 0){
            return true;
        }
        int len = flowerbed.length;
        if(len == 1) {
            if (flowerbed[0] == 0 && n == 1) {
                return true;
            } else {
                return false;
            }
        }
        int i = 0;
        while(i<len){
            if(i==0 && (flowerbed[i]+flowerbed[i+1])==0){
                n--;
                i++;
            }else if((i+2) == len && (flowerbed[i]+flowerbed[i+1])==0){
                n--;
                break;
            }else if((i+2) < len && (flowerbed[i]+flowerbed[i+1]+flowerbed[i+2])==0){
                n--;
                i+=2;
            }else {
                i++;
            }
        }
        return n<=0;
    }

    public int[] diStringMatch(String s) {
        int len = s.length();
        int l = 0;
        int r = len;
        int[] ans = new int[len+1];
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == 'I'){
                ans[i] = l++;
            }else {
                ans[i] = r--;
            }
        }
        ans[len] = l;
        return ans;
    }

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length-1; i >= 0 ; i--) {
            if(i>=2 && nums[i]<(nums[i-1]+nums[i-2])){
                return nums[i]+nums[i-1]+nums[i-2];
            }
        }
        return 0;
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int fuNum = 0;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < 0){
                fuNum++;
                if(min>nums[i]*-1){
                    min = nums[i]*-1;
                    minIndex = i;
                }
            }else {
                if(min>nums[i]){
                    min = nums[i];
                    minIndex = i;
                }
            }
        }
        if(fuNum ==0 && min != 0 && k%2 == 1){
            nums[0] = nums[0]*-1;
        }else if(fuNum >= k){
            while(k>0){
                nums[--k] = nums[k]*-1;
            }
        }else {
            while(k> 0 && fuNum >0){
                nums[fuNum-1] = nums[fuNum-1]*-1;
                fuNum--;
                k--;
            }
            if(min != 0 && k%2 == 1){
                nums[minIndex] = min*-1;
            }
        }
        //求和
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        return sum;
    }

    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        int len = arr.length;
        for (int i : arr) {
            sum+=i;
        }
        if(sum%3 != 0){
            return false;
        }
        int i = 0;
        int currentSum = 0;
        int target = sum/3;
        while(i<len-2){
            currentSum += arr[i];
            if(currentSum == target){
                break;
            }
            i++;
        }
        if(currentSum != target){
            return false;
        }
        i++;
        while(i<len-1){
            currentSum += arr[i];
            if(currentSum == target*2){
               return true;
            }
            i++;
        }
        return false;
    }

    public boolean canThreePartsEqualSum2(int[] arr) {
        int sum = 0;
        int len = arr.length;
        for (int i : arr) {
            sum+=i;
        }
        if(sum%3 != 0){
            return false;
        }
        int i = 0;
        int currentSum = 0;
        int target = sum/3;
        while(i<len-2){
            currentSum += arr[i];
            if(currentSum == target){
                currentSum =0;
                break;
            }else {
                i++;
            }
        }
        int j= i+1;
        while(j<len-1){
            currentSum += arr[j];
            if(currentSum == target){
                return true;
            }else {
                j++;
            }
        }
        return false;
    }

    public int maxNumberOfApples(int[] weight) {
        int max = 5000;
        int ans = 0;
        int sum = 0;
        Arrays.sort(weight);
        for (int w : weight) {
            sum+=w;
            if(sum > max){
                break;
            }
            ans++;

        }
        return ans;
    }

    public int minCostToMoveChips(int[] position) {
        int ou = 0;
        int qi = 0;
        for (int i : position) {
            if(i%2==0){
                ou++;
            }else {
                qi++;
            }
        }
        return Math.min(ou,qi);
    }

    public int balancedStringSplit(String s) {
        int ans = 0;
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='L'){
                diff--;
            }else if(c == 'R'){
                diff++;
            }
            if(diff == 0){
                ans++;
            }
        }
        return ans;
    }

    public int maximum69Number (int num) {
        StringBuffer sb = new StringBuffer(Integer.toString(num));
        for (int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i)=='6'){
                sb.deleteCharAt(i);
                sb.insert(i,'9');
                break;
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public int maximum69Number2 (int num) {
        String s = Integer.toString(num);
        int index = s.length()-1;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='6'){
                index = i;
                break;
            }
        }
        String newS = s.substring(0,index)+'9'+s.substring(index+1);
        return Integer.parseInt(newS);
    }

    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int len = nums.length;
        if(len == 1){
            ans.add(nums[0]);
            return ans;
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        int cur = 0;
        for (int i = len-1; i >= 0; i--) {
            cur +=nums[i];
            ans.add(nums[i]);
            if(cur > sum/2){
                break;
            }
        }
        return ans;
    }

    public int[] largestSubarray(int[] nums, int k) {
        int maxIndex = 0;
        for (int i = 0; i <= nums.length-k; i++) {
            if(nums[maxIndex]<nums[i]){
                maxIndex = i;
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = nums[maxIndex+i];
        }
        return ans;
    }

    public int[] largestSubarray2(int[] nums, int k) {
        int len = nums.length;
        if(len == 1){
            return nums;
        }
        int maxStartIndex = 0;
        int curStartIndex = 1;
        while(curStartIndex<=len-k){
            if(nums[maxStartIndex] < nums[curStartIndex]){
                maxStartIndex = curStartIndex;
            }else if(nums[maxStartIndex] == nums[curStartIndex]){
                for (int i = 1; i < k; i++) {
                    if(nums[maxStartIndex+i] < nums[curStartIndex+i]){
                        maxStartIndex = curStartIndex;
                        break;
                    }else if(nums[maxStartIndex+i] > nums[curStartIndex]){
                        break;
                    }
                }
            }

            curStartIndex++;
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = nums[maxStartIndex+i];
        }
        return ans;
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Map<Integer,Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i = 0; i < boxTypes.length; i++) {
            map.put(boxTypes[i][1],map.getOrDefault(boxTypes[i][1],0)+boxTypes[i][0]);
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(truckSize <= 0){
                return sum;
            }
            Integer value = entry.getValue();
            int num = truckSize - value >= 0 ? value : truckSize;
            sum += entry.getKey()*num;
            truckSize -=num;
        }
        return sum;
    }

    public int minOperations(int[] nums) {
        int ans = 0;
        int len = nums.length;
        if(len == 1){
            return ans;
        }
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] >= nums[i+1]){
                int temp = nums[i+1];
                nums[i+1] = nums[i]+1;
                ans += (nums[i+1]-temp);
            }
        }
        return ans;
    }

    public String largestOddNumber(String num) {
        int oddIndex = num.length()-1;
        while(oddIndex >= 0){
            int n = num.charAt(oddIndex)-'0';
            if(n%2 == 1){
                break;
            }
            oddIndex--;
        }
        if(oddIndex<0){
            return "";
        }
        return num.substring(0,oddIndex+1);
    }

    public int numIslands(char[][] grid) {
        int num = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1'){
                    num++;
                    dfs(grid,i,j);
                }
            }
        }
        return num;
    }

    private void dfs(char[][] grid, int i, int j) {
        if(i<0||j<0||i>=grid.length||j>=grid[0].length){
            return;
        }
        if(grid[i][j]=='1'){
            grid[i][j] = '2';
            dfs(grid,i+1,j);
            dfs(grid,i,j+1);
            dfs(grid,i-1,j);
            dfs(grid,i,j-1);
        }
    }

    public int numIslands2(char[][] grid) {
        int num = 0;
        int nr = grid.length;
        int nc = grid[0].length;
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if(grid[i][j] == '1'){
                    num++;
                    grid[i][j] = '2';
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i*nc+j);
                    while(!queue.isEmpty()){
                        Integer key = queue.poll();
                        int r = key/nc;
                        int c = key%nc;
                        if(r+1<nr && grid[r+1][c] == '1'){
                            grid[r+1][c] = '2';
                            queue.offer((r+1)*nc+c);
                        }
                        if(r-1>=0 && grid[r-1][c] == '1'){
                            grid[r-1][c] = '2';
                            queue.offer((r-1)*nc+c);
                        }
                        if(c+1<nc && grid[r][c+1] == '1'){
                            grid[r][c+1] = '2';
                            queue.offer(r*nc+(c+1));
                        }
                        if(c-1>=0 && grid[r][c-1] == '1'){
                            grid[r][c-1] = '2';
                            queue.offer(r*nc+(c-1));
                        }
                    }
                }
            }
        }
        return num;
    }

    @Test
    public void testnumIsLands(){
//        System.out.println(numIslands3(new char[][]{{'1','0','1','1','0','1','1'}}));
        System.out.println(null == null);
    }

    public int numIslands3(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    if(i+1 < m && grid[i+1][j] == '1'){
                        unionFind.union(i*n+j,(i+1)*n+j);
                    }
                    if(i-1 >= 0 && grid[i-1][j] == '1'){
                        unionFind.union(i*n+j,(i-1)*n+j);
                    }
                    if(j+1 < n && grid[i][j+1] == '1'){
                        unionFind.union(i*n+j,i*n+j+1);
                    }
                    if(j-1 >= 0 && grid[i][j-1] == '1'){
                        unionFind.union(i*n+j,i*n+j-1);
                    }
                }
            }
        }
        return unionFind.count;
    }

    private class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m*n];
            rank = new int[m*n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(grid[i][j] == '1'){
                        parent[i*n+j] = i*n+j;
                        count++;
                    }
                    rank[i*n+j] = 0;
                }
            }
        }

        public int find(int i){
            if(parent[i] != i){
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int i,int j){
            int rootI = find(i);
            int rootJ = find(j);
            if(rootI == rootJ){
                return;
            }
            if(rank[rootI] == rank[rootJ]){
                parent[rootI] = rootJ;
                rank[rootJ]++;
            }else if(rank[rootI] > rank[rootJ]){
                parent[rootJ] = rootI;
            }else {
                parent[rootI] = rootJ;
            }
            count--;
        }
    }

    public void solve(char[][] board) {
        int m = board.length;
        if(m == 0){
            return;
        }
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            solveDFS(board,i,0);
            solveDFS(board,i,n-1);
        }
        for (int i = 0; i < n; i++) {
            solveDFS(board,0,i);
            solveDFS(board,m-1,i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'A'){
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void solveDFS(char[][] board, int i, int j) {
        if(i<0 || i >= board.length || j<0 || j >= board[0].length){
            return;
        }
        if(board[i][j] == 'O'){
            board[i][j] = 'A';
            solveDFS(board,i+1,j);
            solveDFS(board,i-1,j);
            solveDFS(board,i,j+1);
            solveDFS(board,i,j-1);
        }
    }

    @Test
    public void testlongestConsecutive(){
        System.out.println(longestConsecutive(new int[]{1,2,0,1}));;
    }

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int maxLen = 1;
        int curLen = 1;
        int pre = nums[0];
        int cur = pre;
        for (int i = 1; i < nums.length; i++) {
           cur = nums[i];
           if(cur-pre == 1){
               curLen++;
           }else if(cur-pre == 0){
               continue;
           } else {
               if(curLen > maxLen){
                   maxLen = curLen;
               }
               curLen=1;
           }
           pre = cur;
        }
        if(curLen > maxLen){
            maxLen = curLen;
        }
        return maxLen;
    }

    public String removeOuterParentheses(String s) {
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        int startIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
           if(c == '('){
               stack.push(c);
           }else {
               stack.pop();
               if(stack.isEmpty()){
                   sb.append(s.substring(startIndex+1,i));
                   startIndex = i+1;
               }
           }
        }
        return sb.toString();
    }

    @Test
    public void testremoveDuplicates(){
        System.out.println(removeDuplicates("abbaca"));
    }

    public String removeDuplicates(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if(sb.length()>0 && sb.charAt(sb.length()-1) == s.charAt(i)){
                sb.deleteCharAt(sb.length()-1);
            }else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public String removeDuplicates2(String s) {
        LinkedList<Character> linkedList = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if(linkedList.size()>0 && linkedList.getLast() == s.charAt(i)){
                linkedList.removeLast();
            }else {
                linkedList.addLast(s.charAt(i));
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < linkedList.size(); i++) {
            sb.append(linkedList.get(i));
        }
        return sb.toString();
    }

    public List<String> buildArray(int[] target, int n) {
        List<String> list = new ArrayList<>();
        int index = 0;
        for (int i = 1; i <= n; i++) {
            if(index > target.length-1){
                return list;
            }
            if(target[index] == i){
                list.add("Push");
                index++;
            }else {
                list.add("Push");
                list.add("Pop");
            }
        }
        return list;
    }

    public int[] finalPrices(int[] prices) {
        int[] ans = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()]>= prices[i]){
                Integer index = stack.pop();
                ans[index] = prices[index]-prices[i];
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            Integer index = stack.pop();
            ans[index] = prices[index];
        }
        return ans;
    }

    public int[] finalPrices2(int[] prices) {
        int[] ans = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int base = prices[i];
            for (int j = i+1; j < prices.length; j++) {
                if(prices[j]<= base){
                    base -=prices[j];
                    break;
                }
            }
            ans[i] = base;
        }
        return ans;
    }

    public String makeGood(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if(stringBuffer.length() > 0 && isDaxiaoxie(stringBuffer.charAt(stringBuffer.length()-1),s.charAt(i))){
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
            }else {
                stringBuffer.append(s.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    private boolean isDaxiaoxie(char a,char b){
        if(a == b){
            return false;
        }
        if(Character.toLowerCase(a) == b){
            return true;
        }
        if(Character.toUpperCase(a) == b){
            return true;
        }
        return false;
    }

    /*
    "../" ：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则 继续停留在当前文件夹 。
"./" ：继续停留在当前文件夹。
"x/" ：移动到名为 x 的子文件夹中。题目数据 保证总是存在文件夹 x
     */
    public int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < logs.length; i++) {
            String log = logs[i];
            if(log.equals("../")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else if(log.equals("./")){
                continue;
            }else {
                stack.push(log);
            }
        }
        int ans = 0;
        while(!stack.isEmpty()){
            stack.pop();
            ans++;
        }
        return ans;
    }

    public int maxDepth(String s) {
        int ans = 0;
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='('){
                size++;
                ans = Math.max(ans,size);
            }else if(c==')'){
                size--;
            }
        }
        return ans;
    }

    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while(head != null){
            stack.push(head.val);
            head = head.next;
        }
        int[] ans = new int[stack.size()];
        int i = 0;
        while(!stack.isEmpty()){
            ans[i++] = stack.pop();
        }
        return ans;
    }

    class CQueue {
        Stack<Integer> appendStack;
        Stack<Integer> deleteStack;
        public CQueue() {
            appendStack = new Stack<>();
            deleteStack = new Stack<>();
        }

        public void appendTail(int value) {
            appendStack.push(value);
        }

        public int deleteHead() {
            if(deleteStack.isEmpty()){
                while (!appendStack.isEmpty()){
                    deleteStack.push(appendStack.pop());
                }
            }
            if(deleteStack.isEmpty()){
                return -1;
            }
            return deleteStack.pop();
        }
    }

    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if(minStack.isEmpty()){
                minStack.push(x);
            }else {
                minStack.push(Math.min(x,minStack.peek()));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public boolean isPalindrome3(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        int l = 0;
        int r = list.size()-1;
        while(l<r){
            if(list.get(l) != list.get(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    class MyQueue {
        Stack<Integer> addStack;
        Stack<Integer> deleteStack;

        /** Initialize your data structure here. */
        public MyQueue() {
            addStack = new Stack<>();
            deleteStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            addStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(deleteStack.isEmpty()){
                while(!addStack.isEmpty()){
                    deleteStack.push(addStack.pop());
                }
            }
            if(deleteStack.isEmpty()){
                return -1;
            }
            return deleteStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(deleteStack.isEmpty()){
                while(!addStack.isEmpty()){
                    deleteStack.push(addStack.pop());
                }
            }
            if(deleteStack.isEmpty()){
                return -1;
            }
            return deleteStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            if(addStack.isEmpty() && deleteStack.isEmpty()){
                return true;
            }
            return false;
        }
    }

    TreeNode res;
    public TreeNode convertBiNode(TreeNode root) {
        TreeNode pre = new TreeNode(-1);
        res = pre;
        convertBiNodeInorder(root);
        return res.right;
    }

    private void convertBiNodeInorder(TreeNode root) {
        if(root == null){
            return;
        }
        convertBiNodeInorder(root.left);
        res.right = root;
        root.left = null;
        res = root;
        convertBiNodeInorder(root.right);
    }

    public int mySqrt(int x) {
        int ans = (int) Math.exp(0.5*Math.log(x));
        return  (ans+1)*(ans+1) <= x ? ans+1 : ans;
    }

    public int mySqrt3(int x) {
        int ans = -1;
        int l = 0;
        int r = x;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(mid*mid<=x){
                ans = mid;
                l = mid+1;
            }else {
                r = mid-1;
            }
        }
        return ans;
    }

    public int mySqrt2(int x) {
        if(x==0){
            return 0;
        }
        double c = x;
        double x0 = x;
        while(true){
            double xi = 0.5 * (x0+c/x0);
            if(Math.abs(xi-x0)<1e-7){
                break;
            }
            x0=xi;
        }
        return (int) x0;
    }

    public int maxProfit(int[] prices) {
        int ans = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
           if(minPrice>prices[i]){
               minPrice = prices[i];
           }else if(prices[i]-minPrice>ans){
               ans = prices[i]-minPrice;
           }
        }
        return ans;
    }

    public int maxProfit2(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if(ans < profit){
                    ans = profit;
                }
            }
        }
        return ans;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aNode = headA;
        ListNode bNode = headB;
        while(aNode != bNode){
            aNode = aNode == null ? headB :aNode.next;
            bNode = bNode == null ? headA :bNode.next;
        }
        return aNode;
    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode aNode = headA;
        ListNode bNode = headB;
        boolean a2b = false;
        boolean b2a = false;
        while(aNode != null && bNode != null){
            if(aNode == bNode){
                return aNode;
            }
            if(aNode.next != null || a2b){
                aNode = aNode.next;
            }else if(!a2b){
                a2b = true;
                aNode = headB;
            }
            if(bNode.next != null || b2a){
                bNode = bNode.next;
            }else if(!b2a){
                b2a = true;
                bNode = headA;
            }
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while(temp != null){
            set.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while(temp != null){
            if(set.contains(temp)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    public ListNode reverseList3(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode cur = head;
        ListNode next = head.next;
        cur.next = null;
        while(next != null){
            ListNode temp = next.next;
            next.next = cur;
            cur = next;
            next = temp;
        }
        return cur;
    }

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        while(l<r){
            int mid = l+(r-l)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return -1;
    }

    int[] cache;
    public int rob2(int[] nums) {
        cache = new int[nums.length];
        return robtemp(nums,0,nums.length-1);
    }

    private int robtemp(int[] nums, int start, int end) {
        if(start > end){
            return 0;
        }
        if(start == end){
            return nums[start];
        }
        if(start == end-1){
            return Math.max(nums[start],nums[end]);
        }
        if(cache[start] > 0){
            return cache[start];
        }
        int robMax = nums[start]+ robtemp(nums,start+2,end);
        int notRobMax = robtemp(nums,start+1,end);
        int max = Math.max(robMax,notRobMax);
        cache[start] = max;
        return max;
    }

    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }
        int dp0 = nums[0];
        int dp1 = Math.max(nums[0],nums[1]);
        for (int i = 2; i < len; i++) {
            int temp  = Math.max(dp1,nums[i]+dp0);
            dp0 = dp1;
            dp1 = temp;
        }
        return dp1;
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount+1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,max);
        dp[0]=0;
        for (int i = 1; i <=amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if(i>= coins[j]){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount ? -1:dp[amount];
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length <=1){
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if(merged.size() == 0){
                merged.add(intervals[i]);
            }else {
                int left = intervals[i][0];
                int right = intervals[i][1];
                int maxLeft = merged.get(merged.size() - 1)[0];
                int maxRight = merged.get(merged.size() - 1)[1];
                if(left > maxRight){
                    merged.add(intervals[i]);
                }else if(maxRight < right){
                    merged.set(merged.size()-1,new int[]{maxLeft,right});
                }
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public int[][] merge2(int[][] intervals) {
        if(intervals.length <=1){
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size()-1)[1] < left){
                merged.add(new int[]{left,right});
            }else {
                int[] ints = merged.get(merged.size() - 1);
                int maxLeft = ints[0];
                int maxRight = ints[1];
                if(maxRight < right){
                    merged.set(merged.size()-1,new int[]{maxLeft,right});
                }
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[m*n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1]+grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i*n] = dp[(i-1)*n]+grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i*n+j] = grid[i][j]+Math.min(dp[(i-1)*n+j],dp[i*n+j-1]);
            }
        }
        return dp[(m-1)*n+n-1];

    }

    class LRUCache extends LinkedHashMap<Integer,Integer>{
        int capacity;
        public LRUCache(int capacity) {
            super(capacity,0.75f,true);
            this.capacity = capacity;
        }

        public int get(int key) {
           return super.getOrDefault(key,-1);
        }

        public void put(int key, int value) {
            super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
            return size() > capacity;
        }

    }

    @Test
    public void testLRUCache(){
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        int i = lruCache.get(1);
        System.out.println(i);
    }

    Map<Character,Integer> tMap = new HashMap<>();
    Map<Character,Integer> winMap = new HashMap<>();
    public String minWindow(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tMap.put(c,tMap.getOrDefault(c,0)+1);
        }
        int ansL = -1;
        int ansR = -1;
        int ansLen = Integer.MAX_VALUE;
        int l = 0;
        int r = -1;
        while(r<s.length()){
            r++;
            if(r<s.length() && tMap.containsKey(s.charAt(r))) {
                char c = s.charAt(r);
                winMap.put(c, winMap.getOrDefault(c, 0) + 1);
            }
            while(check() && l <= r){
                if(ansLen > (r-l+1)){
                    ansR = r;
                    ansL = l;
                    ansLen = r-l+1;
                }
                char c2 = s.charAt(l);
                if(tMap.containsKey(c2)) {
                    winMap.put(c2, winMap.getOrDefault(c2, 0) - 1);
                }
                l++;
            }
        }
        return ansL == -1 ? "":s.substring(ansL,ansR+1);
    }

    private boolean check() {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            char character = entry.getKey();
            if(entry.getValue() > winMap.getOrDefault(character,0)){
                return false;
            }
        }
        return true;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[nums.length-k+1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
            while(deque.peekFirst() < i-k+1){
                deque.pollFirst();
            }
            ans[i-k+1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        //0:num 1:index
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] != o1[0] ? o2[0]-o1[0] : o2[1]-o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i],i});
        }
        int len = nums.length;
        int[] ans = new int[len-k+1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < len; i++) {
            pq.offer(new int[]{nums[i],i});
            while(pq.peek()[1] < i-k+1){
                pq.poll();
            }
            ans[i-k+1] = pq.peek()[0];
        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        //队列中元素是一个二元数组：0数字大小 1索引 大顶堆优先队列
        PriorityQueue<int[]> pq= new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0]-o1[0] : o2[1]-o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i],i});
        }
        int[] ans = new int[nums.length-k+1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < nums.length; i++) {
            while(!pq.isEmpty() && pq.peek()[1] < i-k+1){
                pq.poll();
            }
            pq.offer(new int[]{nums[i],i});
            ans[i-k+1] = pq.peek()[0];
        }
        return ans;
    }

    public int strStr(String haystack, String needle) {
        int n = needle.length();
        int m = haystack.length();
        for (int i = 0; i < m-n+1; i++) {
            String substring = haystack.substring(i, i + n - 1);
            if(substring.equals(needle)){
                return i;
            }
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        int n = needle.length();
        int m = haystack.length();
        for (int i = 0; i < m-n+1; i++) {
            int j = 0;
           while(j < n) {
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    break;
                }
                j++;
            }
           if(j == n){
               return i;
           }
        }
        return -1;
    }

    public int[] plusOne(int[] digits) {
        int i = digits.length-1;
        int[] ans = new int[digits.length];
        while(i>=0){
            while(i>= 0 && digits[i] == 9){
                i--;
            }
            if(i>= 0 && digits[i] != 9){
                ans[i] = digits[i]+1;
                break;
            }
        }
        if(i<0){
            ans = new int[digits.length+1];
            ans[0] = 1;
        }else {
            while(i>=0){
                ans[i] = digits[i];
            }
        }
        return ans;
    }

    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        if( n == 2){
            return 2;
        }
        int n1 = 1;
        int n2 = 2;
        for (int i = 3; i <= n ; i++) {
            int temp = n1;
            n1 = n2;
            n2 = temp+n2;
        }
        return n2;
    }

    public int climbStairs2(int n) {
        if(n == 1){
            return 1;
        }
        if( n == 2){
            return 2;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTFunction(nums,0,nums.length-1);
    }

    private TreeNode sortedArrayToBSTFunction(int[] nums, int l, int r) {
        if(l>r || l < 0 || r >= nums.length){
            return null;
        }
        int mid = (l+r)/2;
        TreeNode root = new TreeNode(mid);
        root.left = sortedArrayToBSTFunction(nums,l,mid-1);
        root.right = sortedArrayToBSTFunction(nums,mid+1,r);
        return root;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(1));
        if(numRows == 1){
            return ans;
        }
        ans.add(Arrays.asList(1,1));
        if(numRows == 2){
            return ans;
        }
        for (int i = 2; i < numRows ; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i ; j++) {
                list.add(ans.get(i-1).get(j-1)+ans.get(i-1).get(j));
            }
            list.add(1);
            ans.add(list);
        }
        return ans;
    }

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(set.contains(num)){
                set.remove(num);
            }else {
                set.add(num);
            }
        }
        for (Integer integer : set) {
            return integer;
        }
        return -1;
    }

    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length;
        int maxNum = nums[0];
        int maxCount = 0;
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0)+1;
            if(count > len/2){
                return num;
            }
            map.put(num,count);
            if(maxCount < count){
                maxCount = count;
                maxNum = num;
            }
        }
        return maxNum;
    }

    public int titleToNumber(String columnTitle) {
        int ans = 0;
        char[] chars = columnTitle.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            int num = chars[i]-'A'+1;
            ans = ans*26+num;
        }
        return ans;
    }

    public String convert(String s, int numRows) {
        if(numRows < 2){
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int flag = -1;
        int i = 0;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            if(i == 0 || i == numRows-1){
                flag = -1*flag;
            }
            i = i+flag;
        }
        StringBuilder ans = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            ans.append(list.get(i));
        }
        return ans.toString();
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i> 0 && nums[i] == nums[i-1]){
                continue;
            }
            int start = i+1;
            int end = nums.length-1;
            while(start < end) {
                int value = nums[i] + nums[start] + nums[end];
                if (value == target) {
                    return target;
                }
//                if (())
            }
        }
        return ans;
    }

    class Parent{

    }

    class Child extends Parent{

    }

    class CC<T>{

        public T get(){
            return null;
        }

        public void set( T t){
        }
    }

    @Test
    public void testSushu(){
        System.out.println(sushu(100));
        System.out.println(sushu2(100));
    }

    private int sushu(int n) {
        int count = 0;
        if(n<=1){
            return count;
        }

        for(int i=2;i<n;i++){
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }

    private boolean isPrime(int x) {
        for(int i = 2;i*i <= x ; i++){
            if(x%i == 0){
                return false;
            }
        }
        return true;
    }

    private int sushu2(int n) {
        int count = 0;
        if(n<=1){
            return count;
        }
        boolean[] isHeshu = new boolean[n];
        for(int i=2;i<n;i++){
            if(!isHeshu[i]){
                for(int j= i*i;j<n;j+=i){
                    isHeshu[j] = true;
                }
                count++;
            }
        }
        return count;
    }

}
