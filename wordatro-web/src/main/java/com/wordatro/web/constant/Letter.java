package com.wordatro.web.constant;

import lombok.Data;

@Data
public class Letter {
	private static final long A = build('a');
	private static final long B = build('b');
	private static final long C = build('c');
	private static final long D = build('d');
	private static final long E = build('e');
	private static final long F = build('f');
	private static final long G = build('g');
	private static final long H = build('h');
	private static final long I = build('i');
	private static final long J = build('j');
	private static final long K = build('k');
	private static final long L = build('l');
	private static final long M = build('m');
	
	public static final long[] VALUES ={
		A, B, C, D, E, F, G, H, I, J, K, L, M
	};
	
	private static long build(char c) {
		return 1L << (c - 'a') * 3;
	}
}
