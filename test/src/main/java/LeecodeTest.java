import org.junit.Test;
//import org.springframework.util.StringUtils;

import java.util.*;

public class LeecodeTest {

    @Test
    public void test() {
//        System.out.println(longestCommonPrefix2(new String[]{"flower","flow","flight"}));
        System.out.println(myAtoi2("4193 sdfad"));
    }

    public String longestCommonPrefix(String[] strs) {
        int minSize = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            if (minSize > strs[i].length()) {
                minSize = strs[i].length();
            }
        }
        int maxIndex = -1;
        for (int i = 0; i < minSize; i++) {
            char x = strs[0].charAt(i);
            boolean isAllSame = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != x) {
                    isAllSame = false;
                    break;
                }
            }
            if (isAllSame) {
                maxIndex = i;
            } else {
                break;
            }
        }
        if (maxIndex < 0) {
            return "";
        } else {
            return strs[0].substring(0, maxIndex + 1);
        }
    }

    public String longestCommonPrefix2(String[] strs) {
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                return "";
            }
        }
        return prefix;
    }

    public String longestPrefix(String prefix, String s) {
        int len = Math.min(prefix.length(), s.length());
        int index = 0;
        while (index < len && prefix.charAt(index) == s.charAt(index)) {
            index++;
        }
        return prefix.substring(0, index);
    }

    @Test
    public void test2() {
        System.out.println(isValid2("[](){}"));
        System.out.println(isValid2("[](){}}"));

    }

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        //左括号入站 有括号出战
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            Character c2 = stack.pop();
            if (c2 == '[' && c == ']') {
                continue;
            }
            if (c2 == '(' && c == ')') {
                continue;
            }
            if (c2 == '{' && c == '}') {
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isValid2(String s) {
        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        //左括号入站 有括号出战
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && map.containsKey(c)) {
                if (stack.peek() == map.get(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public int maxSubArray(int[] nums) {
        int pre = 0;
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            maxSum = Math.max(pre, maxSum);
        }
        return maxSum;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1, index2 = n - 1, currentIndex = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (nums2[index2] > nums1[index1]) {
                nums1[currentIndex--] = nums2[index2--];
            } else {
                nums1[currentIndex--] = nums1[index1--];
            }
        }
        while (index2 >= 0) {
            nums1[currentIndex--] = nums2[index2--];
        }
    }

    public String addStrings(String num1, String num2) {
        List<Integer> resultList = new ArrayList<>();
        int addNum = 0;
        int minLen = Math.min(num1.length(), num2.length());
        String maxString = num1.length() > num2.length() ? num1 : num2;
        for (int i = 0; i < minLen; i++) {
            int n1 = Integer.valueOf(num1.substring(num1.length() - 1 - i, num1.length() - i));
            int n2 = Integer.valueOf(num2.substring(num2.length() - 1 - i, num2.length() - i));
            int sum = n1 + n2 + addNum;
            resultList.add(sum % 10);
            addNum = sum / 10;
        }
        int m3 = maxString.length() - minLen;
        for (int i = 0; i < maxString.length() - minLen; i++) {
            int n1 = Integer.valueOf(maxString.substring(m3 - 1 - i, m3 - i));
            int sum = n1 + addNum;
            resultList.add(sum % 10);
            addNum = sum / 10;
        }
        if (addNum > 0) {
            resultList.add(addNum);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = resultList.size() - 1; i >= 0; i--) {
            sb.append(Integer.toString(resultList.get(i)));
        }
        return sb.toString();
    }

    public String addStrings2(String num1, String num2) {
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        int add = 0;
        StringBuilder sb = new StringBuilder();
        while (p1 >= 0 || p2 >= 0) {
            int x = 0;
            if (p1 >= 0) {
                x = num1.charAt(p1) - '0';
            }
            int y = 0;
            if (p2 >= 0) {
                y = num2.charAt(p2) - '0';
            }
            int sum = x + y + add;
            sb.append(sum % 10);
            add = sum / 10;
            p1--;
            p2--;
        }
        sb.reverse();
        return sb.toString();
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        int[] nums = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            nums[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            nums[index]--;
            if (nums[index] < 0) {
                return false;
            }
        }
        return true;
    }

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String s = Integer.toString(i);
            if (i % 15 == 0) {
                s = "fizzBuzz";
            } else if (i % 3 == 0) {
                s = "fizz";
            } else if (i % 5 == 0) {
                s = "Buzz";
            }
            list.add(s);
        }
        return list;
    }

    public ListNode middleNode(ListNode head) {
        ListNode[] arr = new ListNode[100];
        int index = 0;
        int len = 0;
        ListNode current = head;
        while (current != null) {
            arr[index++] = current;
            current = current.next;
            len++;
        }
        return arr[len / 2];
    }

    public ListNode middleNode2(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            len++;
        }
        current = head;
        int i = 0;
        while (i < len / 2) {
            current = current.next;
            i++;
        }
        return current;
    }

    public ListNode middleNode3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public int oddCells(int m, int n, int[][] indices) {
        int[][] mn = new int[m][n];
        for (int i = 0; i < indices.length; i++) {
            int r = indices[i][0];
            int l = indices[i][1];
            for (int j = 0; j < n; j++) {
                mn[r][j]++;
            }
            for (int j = 0; j < m; j++) {
                mn[j][l]++;
            }
        }
        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mn[i][j] % 2 == 1) {
                    num++;
                }
            }
        }
        return num;
    }

    public int oddCells2(int m, int n, int[][] indices) {
        int num = 0;
        int[] mCount = new int[m];
        int[] rCount = new int[n];
        for (int i = 0; i < indices.length; i++) {
            mCount[indices[i][0]]++;
            rCount[indices[i][1]]++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mCount[i] + rCount[j] % 2 == 1) {
                    num++;
                }
            }
        }
        return num;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int max = 0;
        Set set = new HashSet();
        int rk = -1;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                set.remove(s.charAt(i - 1));
            }
            while (rk + 1 < s.length() && !set.contains(s.charAt(rk + 1))) {
                set.add(s.charAt(rk + 1));
                rk++;
            }
            max = Math.max(max, rk - i + 1);
        }
        return max;
    }

    @Test
    public void test4() {
        System.out.println(longestPalindrome("abba"));
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int start = -1;
        int maxlen = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int L = 2; L < len; L++) {
            for (int i = 0; i < len - L; i++) {
                int j = i + L - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (L == 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    int nowLen = j - i + 1;
                    if (maxlen < nowLen) {
                        maxlen = nowLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, maxlen);
    }

    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < len; i++) {
            int len1 = middleExapnd(s, i, i);
            int len2 = middleExapnd(s, i, i + 1);
            int maxLen = Math.max(len1, len2);
            if (maxLen > (end - start)) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int middleExapnd(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i;

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(-1);
        int add = 0;
        ListNode current = pre;
        while (l1 != null || l2 != null || add != 0) {
            int sum = add;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            current.next = new ListNode(sum / 10);
            current = current.next;

            add = sum % 10;
        }
        return pre.next;
    }

    public int removeDuplicates(int[] nums) {
        int oldIndex = 1;
        int newIndex = 0;
        while (oldIndex < nums.length) {
            if (nums[oldIndex] != nums[newIndex]) {
                newIndex++;
                nums[newIndex] = nums[oldIndex];
            }
            oldIndex++;
        }
        return newIndex + 1;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int l = l1 + l2;
        if (l % 2 == 1) {
            //奇数
            int k = l / 2 + 1;
            return findMedian(nums1, nums2, k);
        } else {
            int k1 = l / 2, k2 = l / 2 + 1;
            return (findMedian(nums1, nums2, k1) + findMedian(nums1, nums2, k2)) / 2.0;
        }
    }

    //k表示第k个数
    private double findMedian(int[] nums1, int[] nums2, int k) {

        int index1 = 0;
        int index2 = 0;
        while (true) {
            if (index1 >= nums1.length) {
                return nums2[index2 + k - 1];
            }
            if (index2 >= nums2.length) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int index11 = Math.min(index1 + half, nums1.length) - 1;
            int index22 = Math.min(index2 + half, nums2.length) - 1;
            if (nums1[index11] <= nums2[index22]) {
                k = k - (index11 - index1 + 1);
                index1 = index11 + 1;
            } else {
                k = k - (index22 - index2 + 1);
                index2 = index22 + 1;
            }
        }
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (x < Integer.MIN_VALUE / 10 || x > Integer.MAX_VALUE / 10) {
                return 0;
            }
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return rev;
    }

    /*
    请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

函数 myAtoi(string s) 的算法如下：

读入字符串并丢弃无用的前导空格
检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
返回整数作为最终结果。
注意：

本题中的空白字符只包括空格字符 ' ' 。
除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     */
    public int myAtoi(String s) {
        int result = 0;
        boolean isPre = true;
        int flag = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isPre && c == ' ') {
                continue;
            }
            if (isPre && c == '-') {
                flag = -1;
                isPre = false;
                continue;
            }
            if (isPre && c == '+') {
                isPre = false;
                continue;
            }
            if (isPre) {
                isPre = false;
            }
            int digit = c - '0';
            if (digit > 9 || digit < 0) {
                return result;
            }
            if (result == 0) {
                result = flag * digit;
                continue;
            }
            if (result > 0) {
                if (result > Integer.MAX_VALUE / 10 || digit > 7) {
                    return Integer.MAX_VALUE;
                }
                result = result * 10 + digit;
            } else {
                if (result < Integer.MIN_VALUE / 10 || digit > 8) {
                    return Integer.MIN_VALUE;
                }
                result = result * 10 - digit;
            }

        }

        return result;
    }

    public int myAtoi2(String s) {
        int result = 0;//结果
        //0开始状态 1符号状态 2数字状态 结束状态直接返回
        int state = 0;
        int flag = 1;//符号位
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int digit = c - '0';
            if (state == 0) {//开始状态
                if (c == ' ') {//空格
                    continue;
                }
                if (c == '+') {//+
                    state = 1;
                    continue;
                }
                if (c == '-') {
                    state = 1;
                    flag = -1;
                    continue;
                }
                if (digit > 9 || digit < 0) {
                    return result;
                }
                state = 2;
            }
            if (state == 1) {
                if (digit > 9 || digit < 0) {
                    return result;
                }
                state = 2;
            }
            if (state == 2) {
                if (digit > 9 || digit < 0) {
                    return result;
                }
                if (result == 0) {
                    result = flag * digit;
                } else if (result > 0) {
                    //边界值处理
                    if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                        return Integer.MAX_VALUE;
                    }
                    result = result * 10 + digit;
                } else {
                    if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10) && digit > Integer.MIN_VALUE % 10 * -1) {
                        return Integer.MIN_VALUE;
                    }
                    result = result * 10 - digit;
                }
            }
        }
        return result;
    }


    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int len = nums.length;
        List<String> result = new ArrayList<>();
        if (len == 0) {
            addString(result, getString(lower - 1, upper + 1));
        }
        if (nums[0] > lower) {
            addString(result, getString(lower - 1, nums[0]));
        }
        for (int i = 0; i < len - 1; i++) {
            addString(result, getString(nums[i], nums[i + 1]));
        }
        if (nums[len - 1] < upper) {
            addString(result, getString(nums[len - 1], upper + 1));
        }
        return result;
    }

    private String getString(int i, int j) {
        StringBuilder stringBuilder = new StringBuilder();
        if (i + 2 == j) {
            stringBuilder.append(i + 1);
        } else if (i + 2 < j) {
            stringBuilder.append(i + 1).append("->").append(j - 1);
        }
        return stringBuilder.toString();
    }

    private void addString(List<String> list, String s) {
        if (s==null || s.length()==0) {
            return;
        }
        list.add(s);
    }

    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            } else {
                map.put(num, i);
            }
        }
        return new int[]{};
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (len < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int num1 = nums[i];
            if (i > 0 && num1 == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len - 1; j++) {
                int num2 = nums[j];
                if (j-i > 1 && num2 == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    int num3 = nums[k];
                    if (k-j> 1 && num3 == nums[k - 1]) {
                        continue;
                    }
                    int sum = num1 + num2 + num3;
                    if (sum == 0) {
                        ans.add(Arrays.asList(num1, num2, num3));
                    }
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (len < 3) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            int num1 = nums[i];
            if (i > 0 && num1 == nums[i - 1]) {
                continue;
            }
            int k = len-1;
            for (int j = i + 1; j < len - 1; j++) {
                int num2 = nums[j];
                if (j-i > 1 && num2 == nums[j - 1]) {
                    continue;
                }
                while((num1 + num2 + nums[k]) > 0){
                    k--;
                }
                if((num1 + num2 + nums[k]) == 0){
                    ans.add(Arrays.asList(num1, num2, nums[k]));
                }
            }
        }
        return ans;
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] chars = new char[2*n];
        generateAll(chars,0,ans);
        return ans;
    }

    private void generateAll(char[] chars, int position, List<String> ans) {
        if(position == chars.length){
            if(check(chars)){
                ans.add(new String(chars));
            }
        }else {
            chars[position] = '(';
            generateAll(chars,position+1,ans);
            chars[position] = ')';
            generateAll(chars,position+1,ans);
        }
    }

    private boolean check(char[] chars) {
        int num = 0;
        for (char c : chars) {
            if(c=='('){
                num++;
            }else {
                num--;
            }
            if(num<0){
                return false;
            }
        }
        return num == 0;
    }

    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        bak(ans,sb,n,0,0);
        return ans;
    }

    private void bak(List<String> ans, StringBuilder sb, int max, int open, int close) {
        if(sb.length()==max){
            ans.add(sb.toString());
            return;
        }
        if(open < max){
            sb.append("(");
            bak(ans,sb,max,open+1,close);
            sb.deleteCharAt(sb.length()-1);
        }
        if(close < open){
            sb.append(")");
            bak(ans,sb,max,open,close+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len-k];
    }

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

    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree1(p.left,q.left) && isSameTree1(p.right,q.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        Queue<TreeNode> quene1 = new LinkedList<>();
        Queue<TreeNode> quene2 = new LinkedList<>();
        quene1.offer(p);
        quene2.offer(q);
        while(!quene1.isEmpty() && !quene2.isEmpty()){
            TreeNode node1 = quene1.poll();
            TreeNode node2 = quene2.poll();
            if(node1.val != node2.val){
                return false;
            }
            if(node1.left == null ^ node2.left == null){
                return false;
            }
            if(node1.right == null ^ node2.right == null){
                return false;
            }
            if(node1.left != null){
                quene1.offer(node1.left);
            }
            if(node2.left != null){
                quene2.offer(node2.left);
            }
            if(node1.right != null){
                quene1.offer(node1.right);
            }
            if(node2.right != null){
                quene2.offer(node2.right);
            }
        }
        return quene1.isEmpty() && quene2.isEmpty();
    }

    public boolean isSymmetric(TreeNode root) {
        TreeNode p = root;
        TreeNode q = root;
        return isSymmetricTree(p,q);
    }

    private boolean isSymmetricTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }

        return isSymmetricTree(p.left,q.right) && isSymmetricTree(p.right,q.left);
    }

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    public int minDepth(TreeNode root) {
        if(root.left == null && root.right == null){
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if(root.left != null){
            min = Math.min(min,minDepth(root.left));
        }
        if(root.right != null){
            min = Math.min(min,minDepth(root.right));
        }
        return min + 1;
    }

    @Test
    public void testHeapSort(){
        int[] nums = new int[]{3,2,8,6,7,1,4};
        int length = nums.length;
        for (int i = length/2; i >=0; i--) {
           heapliy(nums,i,length);
        }

        for (int i = 0; i < length; i++) {
           swapInt(nums,0,length-1-i);
           heapliy(nums,0,length-1-i);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }

    private void heapliy(int[] nums, int i, int length) {
        int l = 2*i+1;
        int r = 2*i+2;
        int maxIndex = i;
        if(l<length && nums[l] > nums[maxIndex]){
            maxIndex = l;
        }
        if(r<length && nums[r] > nums[maxIndex]){
            maxIndex = r;
        }
        if(maxIndex != i){
            swapInt(nums,maxIndex,i);
            heapliy(nums,maxIndex,length);
        }
    }

    private void swapInt(int[] nums, int maxIndex, int i) {
        int temp = nums[i];
        nums[i] = nums[maxIndex];
        nums[maxIndex] = temp;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(isLeafNode(root)) {
            return root.val == targetSum;
        }else {
            targetSum -= root.val;
            return hasPathSum(root.left,targetSum) || hasPathSum(root.right,targetSum);
        }
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList();
        Queue<Integer> queue1 = new LinkedList<>();
        queue.offer(root);
        queue1.offer(root.val);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            Integer sum = queue1.poll();
            if(isLeafNode(node)){
               if(sum == targetSum){
                   return true;
               }
            }else {
                if(node.left != null){
                    queue.offer(node.left);
                    queue1.offer(sum+node.left.val);
                }
                if(node.right != null){
                    queue.offer(node.right);
                    queue1.offer(sum+node.right.val);
                }
            }
        }
        return false;
    }



    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        if(isLeafNode(root)){
            return root;
        }
        TreeNode newLeft = invertTree(root.left);
        TreeNode newRight = invertTree(root.right);
        root.right = newLeft;
        root.left = newRight;
        return root;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int ans = 0;
        if(root.left != null){
            if(isLeafNode(root.left)){
                ans += root.left.val;
            }else {
                ans += sumOfLeftLeaves(root.left);
            }
        }
        if(root.right != null){
            if(!isLeafNode(root.right)){
                ans += sumOfLeftLeaves(root.right);
            }
        }
        return ans;

    }

    private boolean isLeafNode(TreeNode root){
        return root.left == null && root.right == null;
    }

    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1){
                    return dfs(grid,i,j);
                }
            }
        }
        return 0;

    }

    private int dfs(int[][] grid, int r, int c) {
        if(!(r>= 0 && r<grid.length && c >= 0 && c < grid[0].length)){
            return 1;
        }
        if(grid[r][c] == 0){
            return 1;
        }
        if(grid[r][c] == 2){
            return 0;
        }
        grid[r][c] = 2;

        return dfs(grid,r-1,c) + dfs(grid,r+1,c) + dfs(grid,r,c-1) + dfs(grid,r,c+1);
    }

    public int islandPerimeter2(int[][] grid) {
        int land = 0;
        int border = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1){
                    land++;
                    if(i<grid.length-1 && grid[i+1][j] == 1){
                        border++;
                    }
                    if(j<grid[0].length-1 && grid[i][j+1] == 1){
                        border++;
                    }
                }
            }
        }
        return land*4-border*2;
    }

    @Test
    public void testHeapSort2(){
        int[] nums = new int[]{3,2,5,4,1,8,7,9,6};
        for (int i = nums.length/2; i >= 0; i--) {
            heapliy2(nums,i,nums.length);
        }
        for (int i = 0; i < nums.length; i++) {
            swapInt(nums,0,nums.length-i-1);
            heapliy2(nums,0,nums.length-i-1);
        }
        for (int num : nums) {
            System.out.println(num);
        }
    }

    private void heapliy2(int[] nums, int i, int length) {
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int maxIndex = i;
        if (l < length && nums[l] > nums[maxIndex]) {
            maxIndex = l;
        }
        if (r < length && nums[r] > nums[maxIndex]) {
            maxIndex = r;
        }
        if(maxIndex != i){
            swapInt(nums,i,maxIndex);
            heapliy2(nums,maxIndex,length);
        }
    }

    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        getMin(root,min);
        return min;
    }

    private int getMin(TreeNode root, int min) {
        if(root.left != null){
            min = Math.min(min,root.val-root.left.val);
            int leftMin = getMin(root.left, min);
            min = Math.min(min,leftMin);
        }
        if(root.right != null){
            min = Math.min(min,root.right.val-root.val);
            int rightMin =getMin(root.right,min);
            min = Math.min(min,rightMin);
        }
        return min;
    }

    public int getMinimumDifference2(TreeNode root) {
        int min = Integer.MAX_VALUE;
        List<Integer> list = getList(root);
        for (int i = 0; i < list.size()-1; i++) {
            min = Math.min(list.get(i+1)-list.get(i),min);
        }
        return min;
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

    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max,maxDepth(child));
        }
        return max+1;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            return null;
        }
        TreeNode newRoot = null;
        if(root1 != null && root2 != null){
            newRoot = new TreeNode(root1.val+root2.val);
        }else if(root1 != null){
            newRoot = new TreeNode(root1.val);
        }else if(root2 != null){
            newRoot = new TreeNode(root2.val);
        }
        TreeNode newLeft = mergeTrees(root1 == null ? null : root1.left,root2 == null ? null :root2.left);
        TreeNode newRight = mergeTrees(root1 == null ? null : root1.right,root2 == null ? null :root2.right);
        newRoot.left = newLeft;
        newRoot.right = newRight;
        return newRoot;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int count = 0;
            double sum = 0.0;
            int size = queue.size();
            while(size > 0){
                TreeNode node = queue.poll();
                count++;
                sum += node.val;
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            ans.add(sum/count);
        }
        return ans;
    }

    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return false;
        }
        if(root.left != null && findTarget(root.left,k)){
            return true;
        }
        if(set.contains(k-root.val)){
            return true;
        }else {
            set.add(root.val);
        }
        if(root.right != null && findTarget(root.right,k)){
            return true;
        }
        return false;
    }
    private List<Integer> getList(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        if(root.left != null){
            list.addAll(getList(root.left));
        }
        list.add(root.val);
        if(root.right != null){
            list.addAll(getList(root.right));
        }
        return list;
    }

    public int[][] floodFill11(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];
        if(oldColor == color){
            return image;
        }
        floodFill2(image,sr,sc,oldColor,color);
        return image;
    }

    private void floodFill2(int[][] image, int sr, int sc, int oldColor, int color) {
        if(!(sr>=0&& sr<image.length&& sc>=0&& sc<image[0].length)){
            return;
        }
        if(image[sr][sc] == oldColor) {
            image[sr][sc] = color;
            floodFill2(image,sr+1,sc,oldColor,color);
            floodFill2(image,sr-1,sc,oldColor,color);
            floodFill2(image,sr,sc+1,oldColor,color);
            floodFill2(image,sr,sc-1,oldColor,color);
        }
    }

    int ans;
    int pre;
    public int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        minDiffInBSTDFS(root);
        return ans;
    }

    private void minDiffInBSTDFS(TreeNode root) {
        if(root == null){
            return;
        }
        minDiffInBSTDFS(root.left);
        if(pre == -1){
            pre = root.val;
        }else {
            ans = Math.min(ans,root.val-pre);
            pre = root.val;
        }
        minDiffInBSTDFS(root.right);
    }

    public boolean isUnivalTree(TreeNode root) {
        int val = root.val;
        return isUnivalTreeDFS(root.left,val) && isUnivalTreeDFS(root.right,val);
    }

    private boolean isUnivalTreeDFS(TreeNode root,int val) {
        if(root == null){
            return true;
        }
        if(root.val != val){
            return false;
        }
        return isUnivalTreeDFS(root.left,val) && isUnivalTreeDFS(root.right,val);
    }

    int xlevel = 0;
    int ylevel = 0;
    int xParent = 0;
    int yParent = 0;
    public boolean isCousins(TreeNode root, int x, int y) {
        isCousinsDFS(root,0,null,x,y);
        return xlevel == ylevel && xParent != yParent;
    }

    private void isCousinsDFS(TreeNode root, int depth, TreeNode parent, int x, int y) {
        updateParent(parent,root,depth,x,y);
        if(xlevel >0 && ylevel > 0){
            return;
        }
        if(root.left != null){
            isCousinsDFS(root.left,depth+1,root,x,y);
        }
        if(xlevel >0 && ylevel > 0){
            return;
        }
        if(root.right != null){
            isCousinsDFS(root.right,depth+1,root,x,y);
        }
        if(xlevel >0 && ylevel > 0){
            return;
        }
    }


    private void updateParent(TreeNode parent, TreeNode node, int level, int x, int y) {
        if(node.val == x){
            xlevel = level;
            xParent = parent.val;
        }else if(node.val == y){
            ylevel = level;
            yParent = parent.val;
        }
    }

    public boolean isCousins3(TreeNode root, int x, int y) {
        if(root.val == x || root.val == y){
            return false;
        }
        if(root.left == null || root.right == null){
            return false;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        nodeQueue.offer(root);
        levelQueue.offer(0);
        while(!nodeQueue.isEmpty() && (xlevel == 0 || ylevel == 0)){
            TreeNode parent = nodeQueue.poll();
            Integer level = levelQueue.poll();
            if(parent.left != null){
                nodeQueue.offer(parent.left);
                levelQueue.offer(level+1);
                updateParent(parent,parent.left,level+1,x,y);
            }
            if(parent.right != null){
                nodeQueue.offer(parent.right);
                levelQueue.offer(level+1);
                updateParent(parent,parent.right,level+1,x,y);
            }
        }
        if(xlevel == ylevel && xlevel > 0 && xParent != yParent && xParent > 0 && yParent > 0){
            return true;
        }
        return false;
    }

    public boolean isCousins2(TreeNode root, int x, int y) {
        if(root.val == x || root.val == y){
            return false;
        }
        if(root.left == null || root.right == null){
            return false;
        }
        int xlevel = 0;
        int ylevel = 0;
        int xParent = 0;
        int yParent = 0;
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer,Integer> map = new HashMap<>();//key:child value:parent
        queue.offer(root);
        while(!queue.isEmpty() && (xlevel == 0 || ylevel == 0)){
            int size = queue.size();
            if(size == 1){
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                    map.put(node.left.val,node.val);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                    map.put(node.right.val,node.val);
                }
            }else {
                while (size > 0) {
                    TreeNode node = queue.poll();
                    if(node.left != null) {
                        queue.offer(node.left);
                        map.put(node.left.val,node.val);
                    }
                    if(node.right != null) {
                        queue.offer(node.right);
                        map.put(node.right.val,node.val);
                    }
                    if (xlevel == 0 && node.val == x) {
                        xlevel = level;
                        xParent = map.get(x);
                    }
                    if (ylevel == 0 && node.val == y) {
                        ylevel = level;
                        yParent = map.get(y);
                    }
                    size--;
                }
            }
            level++;

        }
        if(xlevel == ylevel && xlevel > 0 && xParent != yParent && xParent > 0 && yParent > 0){
            return true;
        }
        return false;
    }

    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getLonelyNodesDFS(root,list);
        return list;
    }

    private void getLonelyNodesDFS(TreeNode root,List<Integer> list) {
        if(root.left == null && root.right == null){
            return;
        }
        if(root.left == null && root.right != null){
            list.add(root.right.val);
        }
        if(root.left != null && root.right == null){
            list.add(root.left.val);
        }
        if(root.left != null){
            getLonelyNodesDFS(root.left,list);
        }
        if(root.right != null){
            getLonelyNodesDFS(root.right,list);
        }
    }

    Set<Integer> colorNums = new HashSet<>();
    public int numColor(TreeNode root) {
        if(!colorNums.contains(root.val)){
            colorNums.add(root.val);
        }
        if(root.left != null) {
            numColor(root.left);
        }
        if(root.right != null){
            numColor(root.right);
        }
        return colorNums.size();

    }

    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode mirror = new TreeNode(root.val);
        mirror.left = mirrorTree(root.right);
        mirror.right = mirrorTree(root.left);
        return mirror;
    }

    public boolean isSymmetric22(TreeNode root) {
        return isSymmetric2(root,root);
    }

    private boolean isSymmetric2(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p == null ^ q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSymmetric2(p.left,q.right) && isSymmetric2(p.right,q.left);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size>0){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            ans.add(list);
        }
        return ans;
    }

    public int maxDepth2(TreeNode root) {
        int ans = 0;
        if(root ==null){
            return ans;
        }
        if(root.left != null){
            ans = Math.max(ans,maxDepth2(root.left));
        }
        if(root.right != null){
            ans = Math.max(ans,maxDepth2(root.right));
        }
        return ans;
    }

    Set<Integer> findTarget2Set = new HashSet<>();
    public boolean findTarget2(TreeNode root, int k) {
        if(findTarget2Set.contains(k-root.val)){
            return true;
        }else {
            findTarget2Set.add(root.val);
        }
        if(root.left != null && findTarget2(root.left,k)){
            return true;
        }
        if(root.right != null && findTarget2(root.right,k)){
            return true;
        }
        return false;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if(oldColor == newColor){
            return image;
        }
        floodFillold(image,sr,sc,oldColor,newColor);
        return image;
    }

    private void floodFillold(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if(!(sr>=0 && sr<image.length && sc >=0 && sc < image[0].length)){
            return;
        }
        if(image[sr][sc]==oldColor){
            image[sr][sc] = newColor;
        }
        floodFillold(image,sr+1,sc,oldColor,newColor);
        floodFillold(image,sr-1,sc,oldColor,newColor);
        floodFillold(image,sr,sc+1,oldColor,newColor);
        floodFillold(image,sr,sc-1,oldColor,newColor);
    }

    List<List<Integer>> paths = new ArrayList<>();
    int ways = 0;
    public int numWays(int n, int[][] relation, int k) {
        for (int i = 0; i < n; i++) {
            paths.add(new ArrayList<>());
        }
        for (int i = 0; i < relation.length; i++) {
            int start = relation[i][0];
            int end = relation[i][1];
            paths.get(start).add(end);
        }
        numWaysDFS(0,n-1,k);
        return ways;
    }

    private void numWaysDFS(int startIndex, int endIndex, int k) {
        if(k==0 ){
            if(startIndex == endIndex) {
                ways++;
            }
            return;
        }
        List<Integer> integers = paths.get(startIndex);
        for (int i = 0; i < integers.size(); i++) {
            numWaysDFS(integers.get(i),endIndex,k-1);
        }
    }

    int[] father;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            fatherJoin(start,end);
        }
        return fatherSame(source,destination);
    }

    private boolean fatherSame(int source, int destination) {
        return fatherFind(source) == fatherFind(destination);
    }

    private void fatherJoin(int start, int end) {
        int f1 = fatherFind(start);
        int f2 = fatherFind(end);
        if(f1 == f2){
            return;
        }
        father[f2] = f1;
    }

    private int fatherFind(int start) {
        if(father[start] == start){
            return start;
        }
        return fatherFind(father[start]);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        if(root.left != null){
            list.addAll(inorderTraversal(root.left));
        }
        list.add(root.val);
        if(root.right != null){
            list.addAll(inorderTraversal(root.left));
        }
        return list;
    }

}

