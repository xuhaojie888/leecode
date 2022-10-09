

import org.junit.Test;

import java.util.*;

/**
 * Leecode50Test
 *
 * @author xuhaojie5
 * @date 2022/5/4 6:09 下午
 */
public class Leecode50Test {

    @Test
    public void testArrayCoins() {
        System.out.println(arrayCoins(100));
        System.out.println(arrayCoins2(100));
        System.out.println(arrayCoins3(100));
    }

    private int arrayCoins3(int n) {
        if (n <= 0) {
            return 0;
        }
        return (int) sqrt(n, n);
    }

    private double sqrt(double x, int n) {
        double res = (x + (2 * n - x) / x) / 2;
        if (res == x) {
            return x;
        }
        return sqrt(res, n);
    }

    private int arrayCoins2(int n) {
        if (n <= 0) {
            return 0;
        }
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int cost = (mid * (mid + 1)) / 2;
            if (cost == n) {
                return mid;
            } else if (cost > n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    /**
     * @param n 硬币总数
     * @return 可排列的完整行数
     */
    private int arrayCoins(int n) {
        for (int i = 1; i <= n; i++) {
            n = n - i;
            if (n < i + 1) {
                return i;
            }
        }
        return 0;
    }

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    @Test
    public void testLinkHasCycle() {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
//        node5.next = node3;
        System.out.println(hasCycle2(node1));
    }

    private boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    private boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    @Test
    public void testArraySort() {
        int[] num1 = new int[]{1, 3, 5, 7, 9, 0, 0, 0, 0};
        int[] num2 = new int[]{2, 4, 6, 8};
        System.out.println(Arrays.toString(mergeSort3(num1, 5, num2, 4)));
    }

    private int[] mergeSort(int[] num1, int m, int[] num2, int n) {
        System.arraycopy(num2, 0, num1, m, n);
        Arrays.sort(num1);
        return num1;
    }

    private int[] mergeSort2(int[] num1, int m, int[] num2, int n) {
        int[] num1copy = new int[m + n];
        System.arraycopy(num1, 0, num1copy, 0, m);
        int p1 = 0;//num1copy index
        int p2 = 0;//num2 index
        int p = 0;//num1 index
        while (p1 < m && p2 < n) {
            num1[p++] = num1copy[p1] < num2[p2] ? num1copy[p1++] : num2[p2++];
        }
        if (p1 < m) {
            System.arraycopy(num1copy, p1, num1, p1 + p2, m + n - p);
        }
        if (p2 < n) {
            System.arraycopy(num1copy, p1, num1, p1 + p2, m + n - p);
        }
        return num1;
    }

    private int[] mergeSort3(int[] num1, int m, int[] num2, int n) {
        int p1 = m - 1;//num1 index
        int p2 = n - 1;//num2 index
        int p = m + n - 1;//从后往前放
        while (p1 >= 0 && p2 >= 0) {
            num1[p--] = num1[p1] > num2[p2] ? num1[p1--] : num2[p2--];
        }
        if (p2 >= 0) {
            System.arraycopy(num2, p2, num1, p, m + n - p);
        }
        return num1;
    }

    @Test
    public void testSubArrayMaxAvg() {
        System.out.println(subArrayMaxAvg2(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }

    private double subArrayMaxAvg(int[] nums, int k) {
        int nowSum = 0;
        for (int i = 0; i < k; i++) {
            nowSum += nums[i];
        }
        int maxSum = nowSum;
        for (int i = k; i < nums.length; i++) {
            nowSum = nowSum - nums[i - k] + nums[i];
            maxSum = Math.max(nowSum, maxSum);
        }
        return 1.0 * maxSum / k;
    }

    private double subArrayMaxAvg2(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        int nowSum = 0;
        for (int i = 0; i < k; i++) {
            nowSum += nums[i];
        }
        int maxSum = nowSum;
        while (right < nums.length - 1) {
            nowSum = nowSum - nums[left++] + nums[++right];
            maxSum = Math.max(nowSum, maxSum);
        }
        return 1.0 * maxSum / k;
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        int deep;

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void testMinDepth() {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, node7, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        System.out.println(minDepth2(node1));
    }

    private int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(min, minDepth(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, minDepth(root.right));
        }
        return min + 1;
    }

    private int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList();
        root.deep = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) {
                return node.deep;
            }
            if (node.left != null) {
                node.left.deep = node.deep + 1;
                queue.offer(node.left);
            }
            if (node.right != null) {
                node.right.deep = node.deep + 1;
                queue.offer(node.right);
            }
        }

        return 0;
    }

    @Test
    public void testMaxLength() {
        System.out.println(findMaxLength(new int[]{1, 2, 3, 2, 3, 4, 3, 4, 5, 6, 7}));
    }

    private int findMaxLength(int[] nums) {
        int start = 0;
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                start = i;
            }
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    @Test
    public void testChange() {
        System.out.println(change(new int[]{5, 5, 10}));
        System.out.println(change(new int[]{5, 5, 10, 20}));
        System.out.println(change(new int[]{5, 5, 20, 20}));
        System.out.println(change(new int[]{5, 20}));
        System.out.println(change(new int[]{10, 20}));
    }

    private boolean change(int[] bills) {
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five > 0) {
                    five--;
                    ten++;
                } else {
                    return false;
                }
            } else {//20
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testLargestPerimeter() {
        System.out.println(largestPerimeter(new int[]{3, 6, 2, 3}));
    }

    private int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        return 0;
    }


    @Test
    public void testScanTree() {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, node6, node7);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
//        recursionOrder(node1);
//        ArrayList<Integer> list = new ArrayList<>();
//        leverOrder(node1,1,list);
//        System.out.println(Arrays.toString(list.toArray()));
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(node1.value);
//        leverOrder2(node1,list);
//        System.out.println(Arrays.toString(list.toArray()));
//        iteratorOrderPre(node1);
//        iteratorOrderPre2(node1);
//        iteratorOrderMid(node1);
//        iteratorOrderMid2(node1);
//        iteratorOrderMid3(node1);
//        iteratorOrderPost(node1);
//        iteratorOrderLevel(node1);
//        morrisOrderPre(node1);
//        morrisOrderPre2(node1);
//        morrisOrderMid(node1);
//        morrisOrderMid2(node1);
        morrisOrderPost(node1);
    }

    private void morrisOrderPost(TreeNode cur) {
        if (cur == null) {
            return;
        }
        TreeNode head = cur;
        TreeNode mostRight = null;
        while (cur != null) {
            if (cur.left != null) {//左子树
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {//建立线索
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else if (mostRight.right == cur) {//删除线索
                    mostRight.right = null;
                    morrisPrintNode(cur.left);
                }
            }
            cur = cur.right;
        }
        morrisPrintNode(head);
    }

    private void morrisPrintNode(TreeNode head) {
        head = reverseNode(head);
        printNode(head);
        reverseNode(head);
    }

    @Test
    public void testReverseNode(){
        TreeNode node4 = new TreeNode(4,null,null);
        TreeNode node3 = new TreeNode(3,null,node4);
        TreeNode node2 = new TreeNode(2,null,node3);
        TreeNode node1 = new TreeNode(1,null,node2);
//        printNode(node1);
        printNode(reverseNode2(node1));

    }

    private void printNode(TreeNode node) {
        while(node != null){
            System.out.print(node.value);
            node = node.right;
        }
    }

    private TreeNode reverseNode(TreeNode head) {
        if(head == null || head.right == null){
            return head;
        }
        TreeNode pre = null;
        TreeNode next = null;
        TreeNode cur = head;
        while(cur != null){
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private TreeNode reverseNode21(TreeNode head) {
        if(head == null || head.right == null){
            return head;
        }
        TreeNode newHead = reverseNode21(head.right);
        head.right.right = head;
        head.right = null;
        return newHead;

    }

    private TreeNode reverseNode2(TreeNode cur) {
        if(cur == null || cur.right == null){
            return cur;
        }
        TreeNode next = reverseNode2(cur.right);
        cur.right.right = cur;
        cur.right = null;
        return next;

    }

    private void morrisOrderMid(TreeNode cur) {
        if (cur == null) {
            return;
        }
        TreeNode mostRight = null;
        while (cur != null) {
            if (cur.left != null) {//左子树
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {//建立线索
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else if (mostRight.right == cur) {//删除线索
                    mostRight.right = null;
                    System.out.print(cur.value);
                    cur = cur.right;
                }
            } else {//右子树
                System.out.print(cur.value);
                cur = cur.right;
            }
        }
    }

    private void morrisOrderMid2(TreeNode cur) {
        if (cur == null) {
            return;
        }
        TreeNode mostRight = null;
        while (cur != null) {
            if (cur.left != null) {//左子树
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {//建立线索
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else if (mostRight.right == cur) {//删除线索
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value);
            cur = cur.right;

        }
    }

    private void morrisOrderPre(TreeNode cur) {
        if (cur == null) {
            return;
        }
        TreeNode mostRight = null;
        while (cur != null) {
            if (cur.left != null) {//左子树
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {//建立线索
                    mostRight.right = cur;
                    System.out.print(cur.value);
                    cur = cur.left;
                    continue;
                } else if (mostRight.right == cur) {//删除线索
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {//右子树
                System.out.print(cur.value);
                cur = cur.right;
            }

        }
    }

    private void morrisOrderPre2(TreeNode cur) {
        if (cur == null) {
            return;
        }
        TreeNode mostRight = null;
        while (cur != null) {
            if (cur.left != null) {//左子树
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {//建立线索
                    mostRight.right = cur;
                    System.out.print(cur.value);
                    cur = cur.left;
                    continue;
                } else if (mostRight.right == cur) {//删除线索
                    mostRight.right = null;
                }
            } else {//右子树
                System.out.print(cur.value);
            }
            cur = cur.right;

        }
    }


    private void iteratorOrderLevel(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root != null) {
                System.out.print(root.value);
                queue.add(root.left);
                queue.add(root.right);
            }
        }

    }

    private void iteratorOrderPost(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cache = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == cache) {
                System.out.print(root.value);
                cache = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
    }

    private void iteratorOrderMid3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.value);
                root = root.right;
            }
        }
    }

    private void iteratorOrderMid2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.value);
                root = root.right;
            }
        }
    }

    //根左右
    private void iteratorOrderPre2(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode root = stack.pop();
            if (root != null) {
                System.out.print(root.value);
                stack.push(root.right);
                stack.push(root.left);
            }
        }
    }

    //中序：左根右
    private void iteratorOrderMid(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.value);
                root = root.right;
            }
        }
    }

    //前序：根左右
    private void iteratorOrderPre(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root != null) {
                System.out.print(root.value);
                stack.push(root.right);
                stack.push(root.left);
            }
        }
    }

    private void leverOrder(TreeNode root, int index, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        int len = list.size();
        if (len <= index) {
            for (int i = 0; i <= index - len; i++) {
                list.add(i + len, null);
            }
        }
        list.set(index, root.value);
        leverOrder(root.left, index * 2, list);
        leverOrder(root.right, index * 2 + 1, list);
    }

    private void leverOrder2(TreeNode root, ArrayList<Integer> list) {
        if (root.left != null) {
            list.add(root.left.value);
        }
        if (root.right != null) {
            list.add(root.right.value);
        }
        if (root.left != null) {
            leverOrder2(root.left, list);
        }
        if (root.right != null) {
            leverOrder2(root.right, list);
        }
    }


    private void recursionOrder(TreeNode root) {
        if (root == null) {
            return;
        }
//        System.out.print(root.value);//前序
        recursionOrder(root.left);
//        System.out.print(root.value);//中序
        recursionOrder(root.right);
        System.out.print(root.value);//后序
    }

    @Test
    public void testGetProvinces(){
        System.out.println(mergeFind(new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
        System.out.println(mergeFind(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        System.out.println(mergeFind(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));
    }

    private int mergeFind(int[][] cityConnected) {
        int provinces = 0;
        int[] head = new int[cityConnected.length];
        int[] level = new int[cityConnected.length];
        //init
        for (int i = 0; i < cityConnected.length; i++) {
            head[i] = i;
            level[i] = 1;
        }
        //merge
        for (int i = 0; i < cityConnected.length; i++) {
            for (int j = i+1; j < cityConnected.length; j++) {
                if(cityConnected[i][j] == 1){
                    merge(i,j,head,level);
                }
            }
        }

        //find
        for (int i = 0; i < head.length; i++) {
            if(head[i] == i){
                provinces ++;
            }
        }

        return provinces;
    }

    private void merge(int i, int j, int[] head, int[] level) {
        if(head[i] == head[j]){
            return;
        }
       int ihead = findHead(i,head);
       int jhead = findHead(j,head);
       if(level[ihead] >= level[jhead]){
           head[jhead] = i;
       }else {
           head[ihead] = j;
       }
       if(level[ihead] == level[jhead]){
            level[jhead]++;
            level[ihead]++;
        }
    }

    private int findHead(int i, int[] head) {
        if(head[i] == i){
            return i;
        }
        head[i] = findHead(head[i],head);
        return head[i];
    }

    private int getProvincesBfs(int[][] cityConnected) {
        int provinces = 0;
        boolean[] visited = new boolean[cityConnected.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < cityConnected.length; i++) {
            if(visited[i]){
                continue;
            }
            queue.offer(i);
            while(!queue.isEmpty()) {
                Integer k = queue.poll();
                visited[k] = true;
                for (int j = 0; j < cityConnected.length; j++) {
                    if (cityConnected[k][j] == 1 && !visited[j]) {
                        queue.offer(j);
                    }
                }
            }

            provinces++;
        }

        return provinces;
    }

    private int getProvincesDfs(int[][] cityConnected) {
        int provinces = 0;
        boolean[] visited = new boolean[cityConnected.length];
        for (int i = 0; i < cityConnected.length; i++) {
            if(visited[i]){
               continue;
            }
            dfsProvinces(i,cityConnected,visited);
            provinces++;
        }

        return provinces;
    }

    private void dfsProvinces(int i, int[][] cityConnected, boolean[] visited) {
        for (int j = 0; j < cityConnected.length; j++) {
            if(cityConnected[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfsProvinces(j, cityConnected,visited);
            }
        }
    }

    @Test
    public void testPredictWinner(){
//        int[] arr = new int[]{1,200,5,6};//p1必赢
        int[] arr = new int[]{1,200,5,6,7};//p2必赢
//        int sum1 = 0;
//        for (int i : arr) {
//            sum1 += i;
//        }
//        int p1Score = maxScore(arr,0,arr.length-1);
//        int p2Score = sum1 - p1Score;
//        System.out.println(p1Score > p2Score);
//        System.out.println(diffScore(arr,0,arr.length-1) >= 0);
        System.out.println(dynamicPlan(arr));

    }

    public int maxScore(int[] arr,int left,int right){
        int distance = right -left ;
        int leftScore = 0;
        int rightScore = 0;
        if(distance < 0){
            return 0;
        }else if(distance == 0){
            return arr[left];
        }else if(distance == 1){
            leftScore = arr[left];
            rightScore = arr[right];
        }else if(distance > 1){
            leftScore = arr[left] + Math.min(maxScore(arr,left+2,right),maxScore(arr,left+1,right-1));
            rightScore = arr[right] + Math.min(maxScore(arr,left+1,right-1),maxScore(arr,left,right-2));
        }

        return Math.max(leftScore,rightScore);
    }

    public int maxScore2(int[] arr,int left,int right){
        int distance = right -left ;
        int leftScore = 0;
        int rightScore = 0;
        if(distance < 0){
            return 0;
        }else if(distance == 0){
            return arr[left];
        }else if(distance == 1){
            leftScore = arr[left];
            rightScore = arr[right];
        }else if(distance > 1){
            int temp = maxScore(arr, left + 1, right - 1);
            leftScore = arr[left] + Math.min(maxScore(arr,left+2,right),temp);
            rightScore = arr[right] + Math.min(temp,maxScore(arr,left,right-2));
        }

        return Math.max(leftScore,rightScore);
    }

    public int diffScore(int[] arr,int left,int right){
        int distance = right -left ;
        int leftDiffScore = 0;
        int rightDiffScore = 0;
        if(distance < 0){
            return 0;
        }else if(distance == 0){
            return arr[left];
        }else if(distance == 1){
            leftDiffScore = arr[left]-arr[right];
            rightDiffScore = arr[right]-arr[left];
        }else if(distance > 1){
            leftDiffScore = arr[left] - maxScore(arr,left+1,right);
            rightDiffScore = arr[right] - maxScore(arr,left,right-1);
        }

        return Math.max(leftDiffScore,rightDiffScore);
    }

    public boolean dynamicPlan(int[] arr){
        int length = arr.length;

        if(length < 1){
            return false;
        }
        if(length == 1){
            return arr[0] >= 0;
        }
        // diffArr[i][j]表示从i到j的子数组中最大差值是多少
        int[][] diffArr = new int[length][length];
        for (int i = 0; i < length; i++) {
            // 初始化diffArr[i][j] 当i=j时表示只有一个元素，则此时p1取到该元素，p无元素可取，此时差值就是这个元素本身
            diffArr[i][i] = arr[i];
        }
        for (int p1 = length-2; p1 >= 0; p1--) {
            for (int p2 = p1+1; p2 < length; p2++) {
                diffArr[p1][p2]=Math.max(arr[p1]-diffArr[p1+1][p2],arr[p2]-diffArr[p1][p2-1]);
            }
        }
        return diffArr[0][length-1] >= 0;
    }

    @Test
    public void testChampagneTower(){
        //香槟
        System.out.println(getChampagneTowerCapacity(5, 2, 0));
        System.out.println(getChampagneTowerCapacity(5, 2, 1));
        System.out.println(getChampagneTowerCapacity(5, 2, 2));

    }

    public double getChampagneTowerCapacity(int pour,int row,int line){
        double[][] capacity = new double[100][100];
        capacity[0][0] = pour;
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= i; j++) {
                double yichu = capacity[i][j]-1;
                if(yichu > 0) {
                    capacity[i+1][j] += yichu / 2;
                    capacity[i+1][j+1] += yichu/2;
                }
            }
        }
        return capacity[row][line];
    }

    @Test
    public void testValidBoard() {
        System.out.println(validBoard2(new char[][]{{'O','O','O'},{'X','X','X'},{' ',' ',' '}}));
        System.out.println(validBoard2(new char[][]{{'O','O',' '},{'X','X','X'},{' ',' ',' '}}));
    }

    private boolean validBoard(char[][] chars) {
        int oCount = 0;
        int xCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(chars[i][j] == 'O'){
                    oCount++;
                }else if(chars[i][j] == 'X'){
                    xCount++;
                }
            }
        }
        int diffCount = xCount-oCount;
        if(isWin(chars,'X') && !isWin(chars,'O') && diffCount == 1){
            return true;
        }
        if(isWin(chars,'O') && !isWin(chars,'X') && diffCount == 0){
            return true;
        }
        if(!isWin(chars,'O') && !isWin(chars,'X') && (diffCount == 0 || diffCount == 1)){
            return true;
        }
        return false;
    }

    private boolean isWin(char[][] chars, char flag) {
        for (int i = 0; i < 3; i++) {
            //横向三连
            if(chars[i][0]==flag && chars[i][1]==flag && chars[i][2]==flag){
                return true;
            }
            //纵向三连
            if(chars[0][i]==flag && chars[1][i]==flag && chars[2][i]==flag){
                return true;
            }
        }
        // /向三连
        if(chars[0][2]==flag && chars[1][1]==flag && chars[2][0]==flag){
            return true;
        }
        // \向三连
        if(chars[0][0]==flag && chars[1][1]==flag && chars[2][2]==flag){
            return true;
        }
        return false;
    }


    private boolean validBoard2(char[][] chars) {
        int oCount = 0;
        int xCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(chars[i][j] == 'O'){
                    oCount++;
                }else if(chars[i][j] == 'X'){
                    xCount++;
                }
            }
        }
        int diffCount = xCount-oCount;
        if(diffCount != 0 && diffCount != 1){
            return false;
        }
        if(isWin(chars,'X') && diffCount != 1){
            return false;
        }
        if(isWin(chars,'O') && diffCount != 0){
            return false;
        }
        return true;
    }


    @Test
    public void testStringSearch(){
        String a = "abcabcaabcabcd";
//        String a = "aabcabcd";
        String b = "abcabcd";
//        System.out.println(a.indexOf(b));
        System.out.println(rk(a,b));
    }

    private int bf(String a, String b) {
        int bIndex = 0;
        for (int i = 0; i < a.toCharArray().length; i++) {
            for (int j = i; j < a.toCharArray().length; ) {

                if(a.charAt(j) == b.charAt(bIndex)){
                    j++;
                    bIndex++;
                    if(bIndex == b.length()){
                        return i;
                    }
                }else {
                    bIndex = 0;
                    break;
                }
            }
        }
        return -1;
    }

    private int rk(String a, String b) {
        String zimu = "abcdefghijklmnopqrstuvwxyz";
        int  blength = getCharLength(b,0,b.length());
        int alength = getCharLength(a, 0, b.length());
        if(alength == blength && abequal(a,0,b)){
            return 0;
        }
        for (int i = 1; i < a.toCharArray().length; i++) {
            alength = alength - zimu.indexOf(a.charAt(i-1)) + zimu.indexOf(a.charAt(b.length()-1+i));
            if(alength == blength && abequal(a,i,b)){
                return i;
            }
        }
        return -1;
    }

    private boolean abequal(String a, int index, String b) {
        for (int i = index,j=0; i < b.length(); i++,j++) {
            if(a.charAt(i) != b.charAt(j)){
                return false;
            }
        }
        return true;
    }

    private int getCharLength(String b, int i, int length) {
        String zimu = "abcdefghijklmnopqrstuvwxyz";
        int  bindexLength = 0;
        for (int j = i; j < length; j++) {
            bindexLength += zimu.indexOf(b.charAt(j));
        }
        return bindexLength;
    }

    //bm 从后往前匹配，坏字符、好后缀  跳着往后移 效率高

    private int kmp(String a, String b) {
        return -1;
    }

    @Test
    public void testMaxMoney(){
        int[] nums = new int[]{2,7,9,3,1};
//        System.out.println(maxMoney(nums,nums.length-1));
//        System.out.println(maxMoney2(nums));
//        System.out.println(maxMoney3(nums));
        int maxMoney = Math.max(maxMoney4(nums,0,nums.length-2),maxMoney4(nums,1,nums.length-1));
        System.out.println(maxMoney);
    }

    private int maxMoney(int[] nums, int index) {
        if(nums == null || nums.length <= 0 || index < 0){
            return 0;
        }
        if(index == 0){
            return nums[0];
        }

        return Math.max(maxMoney(nums,index-1),maxMoney(nums,index-2)+nums[index]);
    }

    private int maxMoney2(int[] nums) {
        int length = nums.length;
        if(nums == null || length <= 0){
            return 0;
        }
        if(length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[length-1];
    }

    private int maxMoney3(int[] nums) {
        int length = nums.length;
        if(nums == null || length <= 0){
            return 0;
        }
        if(length == 1){
            return nums[0];
        }
        int first=nums[0];
        int second=Math.max(nums[0],nums[1]);
        int temp = 0;
        for (int i = 2; i < length; i++) {
            temp = second;
            second = Math.max(second,first+nums[i]);
            first = temp;
        }
        return second;
    }

    //首尾相连
    private int maxMoney4(int[] nums,int start,int end) {
        int length = nums.length;
        if(nums == null ||length <= 0 || start < 0 || end > length-1){
            return 0;
        }
        if(length == 1){
            return nums[0];
        }
        int first=nums[start];
        int second=Math.max(nums[start],nums[start+1]);
        int temp = 0;
        for (int i = start+2; i <= end; i++) {
            temp = second;
            second = Math.max(second,first+nums[i]);
            first = temp;
        }
        return second;
    }

    @Test
    public void testMaxMoney5(){
        //二叉树
        TreeNode node5 = new TreeNode(1,null,null);
        TreeNode node4 = new TreeNode(3,null,null);
        TreeNode node3 = new TreeNode(3,null,node5);
        TreeNode node2 = new TreeNode(2,null,node4);
        TreeNode node1 = new TreeNode(3,node2,node3);
        int[] r = dfs(node1);
        System.out.println(Math.max(r[0], r[1]));
    }

    /**
     *
     * @param node
     * @return int[0]表示选中该节点的最优解 int[1]表示不选中该节点的最优解
     */
    private int[] dfs(TreeNode node) {
        if(node == null){
            return new int[]{0,0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int select = node.value + left[1]+right[1];
        int notSelect = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        return new int[]{select,notSelect};

    }

    @Test
    public void testDR(){
        System.out.println(dr("RDDDR"));
    }

    private String dr(String string) {
        Queue<Integer> dQueue = new LinkedList<>();
        Queue<Integer> rQueue = new LinkedList<>();
        int length = string.length();
        for (int i = 0; i < length; i++) {
            if(string.charAt(i) == 'R'){
                rQueue.offer(i);
            }else {
                dQueue.offer(i);
            }
        }
        while(!dQueue.isEmpty() && !rQueue.isEmpty()){
            Integer rIndex = rQueue.poll();
            Integer dIndex = dQueue.poll();
            if(rIndex < dIndex){
               rQueue.offer(rIndex+ length);
            }else {
                dQueue.offer(dIndex+ length);
            }
        }
        if(dQueue.isEmpty()){
            return "r";
        }
        return "d";
    }

    @Test
    public void testHorseRacing(){
        int[] a = new int[]{10,24,8,32};
        int[] b = new int[]{13,25,25,11};
        System.out.println(Arrays.toString(advantageMax(a,b)));
    }

    private int[] advantageMax(int[] a, int[] b) {
        Map<Integer,Deque<Integer>> bMap = new HashMap<>();
        for (int i = 0; i < b.length; i++) {
            bMap.put(b[i],new LinkedList<>());
        }
        int[] sortB = b.clone();
        Arrays.sort(sortB);
        Arrays.sort(a);

        Deque<Integer> aQueue = new LinkedList<>();
        int j= 0;
        for (int i : a) {
            if(i > sortB[j]){
                bMap.get(sortB[j++]).addLast(i);
            }else {
                aQueue.add(i);
            }
        }
        int[] resultA = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            Deque<Integer> deque = bMap.get(b[i]);
            if(deque.isEmpty()){
                resultA[i] = aQueue.removeLast();
            }else {
                resultA[i] = deque.removeLast();
            }
        }
        return resultA;
    }

}
