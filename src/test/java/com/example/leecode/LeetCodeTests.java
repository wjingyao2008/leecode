package com.example.leecode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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


	@Test
	void contextLoads() {
		System.out.println("11");
	}

}
