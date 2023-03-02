package com.example.leecode;

import org.junit.jupiter.api.Test;

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
