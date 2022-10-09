import org.junit.Test;

public class NewCoderBaseTest {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @return int整型
     */
    public int minTrace (int n,int[] arr) {
        // write code here
        int ans = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    if(ans < dp[i]){
                        ans = dp[i];
                    }
                }
            }
        }

        return ans;
    }

    @Test
    public void testMinTrace(){
        minTrace(7,new int[]{6,3,1,5,2,3,7});
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<=n;i++){
            dp[0][i] = 0;
        }
        int ans = 0;
        int i=1;
        int j=1;
        while(i<=m && j<=n){
            if(text1.charAt(i-1) == text2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1]+1;
            }else{
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
            ans = Math.max(ans,dp[i][j]);
        }
        return ans;
    }


}
