package com.example.leecode;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Dynamic Programming Leecode
 */
class LeetCodeTests {

	//53. Maximum Subarray https://leetcode.cn/problems/maximum-subarray/
	public int maxSubArray(int[] nums) {
		// -2 1 2
		int maxSum=nums[0];
		int totalMax= maxSum;
		for(int i=1;i<nums.length;i++) {
			maxSum= Math.max(nums[i],maxSum+nums[i]);
			totalMax=Math.max(totalMax,maxSum);
		}
		return totalMax;
	}

	//46. Permutations https://leetcode.cn/problems/permutations/
	//Input: nums = [1,2,3]
	//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
	public List<List<Integer>> permute(int[] nums) {
		boolean[] dp = new boolean[nums.length];
		List<List<Integer>> list = new ArrayList<>();

		List<Integer> path = new ArrayList<>();
		permuteHelper(list, path, dp, nums);
		return list;
	}

	private void permuteHelper(List<List<Integer>> list, List<Integer> path, boolean[] dp, int[] nums) {
		if (path.size() == nums.length) {
			list.add(new ArrayList<>(path));
		}
		for (int i = 0; i < nums.length; i++) {
			if (!dp[i]) {
				path.add(nums[i]);
				dp[i] = true;
				permuteHelper(list, path, dp, nums);
				dp[i] = false;
				path.remove(path.size() - 1);
			}
		}
	}

	@Test
	void testPermute() {
		int[] nums = {1, 2, 3};
		List<List<Integer>> permute = this.permute(nums);
		for (List<Integer> integers : permute) {
			System.out.println(Arrays.toString(integers.toArray()));
		}
	}

	//	47. Permutations II https://leetcode.cn/problems/permutations-ii/
	public List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		boolean[] dp = new boolean[nums.length];
		List<List<Integer>> lists = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		permuteUniqueHelper(lists, stack, nums, dp);
		return lists;
	}

	@Test
	void testPermuteUnique() {
		int[] nums = {1, 2, 2, 2};
		//1 2 2 2
		//2 1 2 2
		//2 2 1 2
		//2 2 1 1
		List<List<Integer>> permute = this.permuteUnique(nums);
		for (List<Integer> integers : permute) {
			System.out.println(Arrays.toString(integers.toArray()));
		}
	}
	private void permuteUniqueHelper(List<List<Integer>> lists, Stack<Integer> stack, int[] nums, boolean[] dp) {
		if (stack.size() == nums.length) {
			lists.add(new ArrayList<>(stack));
		}
		for (int i = 0; i < nums.length;) {
			if (!dp[i]) {
				stack.add(nums[i]);

				dp[i] = true;
				permuteUniqueHelper(lists, stack, nums, dp);
				dp[i] = false;
				stack.pop();
				int j = i + 1;
				while (j < nums.length && nums[i] == nums[j]) {
					j++;
				}
				i = j;
			} else {
				i++;
			}
		}
	}


	//241. Different Ways to Add Parentheses
//	public List<Integer> diffWaysToCompute(String expression) {
//
//	}

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
	//95. Unique Binary Search Trees II
	//https://leetcode.cn/problems/unique-binary-search-trees-ii/
	public List<TreeNode> generateTrees(int n) {

		List<TreeNode> ans = new ArrayList<TreeNode>();
		if (n == 0) {
			return ans;
		}
		return getAns(1, n);

	}

	private List<TreeNode> getAns(int start, int end) {
		List<TreeNode> list = new ArrayList<>();
		if (start > end) {
			list.add(null);
			return list;
		}
		if (start == end) {
			list.add(new TreeNode(start));
			return list;
		}
		for (int i = start; i <= end; i++) {

			List<TreeNode> left = getAns(start, i - 1);
			List<TreeNode> right = getAns(i + 1, end);

			for (TreeNode l : left) {
				for (TreeNode r : right) {
					TreeNode mid = new TreeNode(i);
					list.add(mid);
					mid.left = l;
					mid.right = r;
				}
			}

		}
		return list;
	}

	//96. Unique Binary Search Trees
	//https://leetcode.cn/problems/unique-binary-search-trees/
	// a better solution is dp(left)*dp(right) since the content is irrelevant,the numbers are always n-th number
	public int numTrees(int n) {
		return numTreesHelper(1, n);
	}

	private int numTreesHelper(int start, int end) {
		if (start > end) {
			return 1;
		}
		if (start == end) {
			return 1;
		}
		int num = 0;
		for (int i = start; i <= end; i++) {
			int left = numTreesHelper(start, i - 1);
			 //2 3
			int right = numTreesHelper(i + 1, end);
			num += left * right;
		}
		return num;
	}

	//131. Palindrome Partitioning
	//https://leetcode.cn/problems/palindrome-partitioning/
//	public List<List<String>> partition(String s) {
//
//
//
//		partitionHelper( s, 0, s.length() - 1);
//		return lists;
//	}
//
//	private List<List<String>> partitionHelper(String s, int start, int end) {
//
//
//		for (int i = start; i <= end; i++) {
//
//			List<List<String>> left = partitionHelper(lists, s, start, i - 1);
//			List<List<String>> right = partitionHelper(lists, s, i + 1, end);
//
//			for (List<String> l : left) {
//				for (List<String> r : right) {
//
//
//				}
//			}
//		}
//
//	}

	//	https://leetcode.cn/problems/coin-change-ii/
	//Input: amount = 5, coins = [1,2,5]
	//Output: 4
	//Explanation: there are four ways to make up the amount:
	//	 DP[[i] = DP[i] + DP[i-k]
	//在求装满背包有几种方案的时候，认清遍历顺序是非常关键的。
	//
	//**如果求组合数就是外层for循环遍历物品，内层for遍历背包**。
	//
	//**如果求排列数就是外层for遍历背包，内层for循环遍历物品**。
	public int change(int amount, int[] coins) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int coin : coins) {
			//DP[3]=DP[3]+DP[1]; 凑够3这个数，我们需要使用1个2的coin，那么只需要在3方法的基础上，加上dp[3-2]=dp[1]的数加上来
			for (int i = coin; i <= amount; i++) {
				dp[i] += dp[i - coin];
			}
		}
		return dp[amount];
	}

	//63. Unique Paths II
	//https://leetcode.cn/problems/unique-paths-ii/submissions/
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int[] dp=new int[obstacleGrid[0].length];
		dp[0]=1;
		for(int[] row: obstacleGrid) {
			for(int i=0;i<row.length;i++){
				if(row[i]==1) {
					dp[i]=0;
				} else if(i!=0) {
					dp[i]+=dp[i-1];
				}
			}
		}
		return dp[obstacleGrid[0].length-1];
	}

	//343. Integer Break
	//https://leetcode.cn/problems/integer-break/submissions/
	public int integerBreak(int n) {
		//define dp[i] ,given integer n , maximum product of it.
		int[] dp=new int[n+1];
		dp[1]=1;
		for(int i=2;i<=n;i++) {
			for(int j=1;j<i;j++) {
				dp[i]=Math.max(dp[i],j*dp[i-j]);
				dp[i] = Math.max(dp[i], j * (i - j));
			}
		}
		return dp[n];
	}

	@Test
	void contextLoads() {

		int change = integerBreak(4);
		System.out.println(change);
	}


	// for s3,each character is fixed,you can choose neither from s1 or s2
	// if both can choose then
	//
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		boolean[][] dp = new boolean[s2.length() + 1][s1.length() + 1];
		dp[0][0] = true;
		for (int j = 1; j <= s1.length(); j++) {
			dp[0][j] = dp[0][j - 1] && s3.charAt(j - 1) == s1.charAt(j - 1);
		}
		for (int i = 1; i <= s2.length(); i++) {
			dp[i][0] = dp[i-1][0] && s3.charAt(i - 1) == s2.charAt(i - 1);
		}

		for (int t1 = 1; t1 <= s1.length(); t1++) {
			for (int t2 = 1; t2 <= s2.length(); t2++) {
				dp[t2][t1] = dp[t2 - 1][t1] && s3.charAt(t2 + t1 - 1) == s2.charAt(t2 - 1);
				dp[t2][t1] = dp[t2][t1] || (dp[t2][t1 - 1] && s3.charAt(t2 + t1 - 1) == s1.charAt(t1 - 1));
			}
		}
		return dp[s2.length()][s1.length()];
	}

	@Test
	void testIsInterleave() {
		Assert.assertTrue(isInterleave("aabcc" ,
				"dbbca" ,
				"aadbbcbcac"));
	}
}
