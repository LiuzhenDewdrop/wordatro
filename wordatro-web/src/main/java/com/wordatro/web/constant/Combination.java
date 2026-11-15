package com.wordatro.web.constant;

public class Combination {
	private static final boolean[][] LEETER_01 = build(1);
	private static final boolean[][] LEETER_02 = build(2);
	private static final boolean[][] LEETER_03 = build(3);
	private static final boolean[][] LEETER_04 = build(4);
	private static final boolean[][] LEETER_05 = build(5);
	private static final boolean[][] LEETER_06 = build(6);
	private static final boolean[][] LEETER_07 = build(7);
	private static final boolean[][] LEETER_08 = build(8);
	private static final boolean[][] LEETER_09 = build(9);
	private static final boolean[][] LEETER_10 = build(10);
	private static final boolean[][] LEETER_11 = build(11);
	private static final boolean[][] LEETER_12 = build(12);
	
	public static final boolean[][][] ALL_COMBINATIONS = {
		null,
		LEETER_01, LEETER_02, LEETER_03, LEETER_04, LEETER_05, LEETER_06,
		LEETER_07, LEETER_08, LEETER_09, LEETER_10, LEETER_11, LEETER_12
	};
	
	
	private static boolean[][] build(int n) {
		int total = (1 << n) - 1;          // 组合数
		boolean[][] result = new boolean[total][n];
		int row = 0;
		for (int mask = (1 << n) - 1; mask >= 1; mask--) {
			for (int j = 0; j < n; j++) {
				result[row][j] = ((mask >> (n - 1 - j)) & 1) == 1;
			}
			row++;
		}
		return result;
	}
}
