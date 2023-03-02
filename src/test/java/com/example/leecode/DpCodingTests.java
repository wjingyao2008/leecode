package com.example.leecode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Dynamic Programming Leecode
 */
class DpCodingTests {

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
		int[] dp = new int[nums.length];
		List<List<Integer>> list = new ArrayList<>();
//		permuteHelper(list);

		return null;
	}

    //	47. Permutations II https://leetcode.cn/problems/permutations-ii/
	public List<List<Integer>> permuteUnique(int[] nums) {

		return null;
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
