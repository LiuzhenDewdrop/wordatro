package com.steamhelper.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.wordatro.web.constant.Letter;

import lombok.Data;

public class Test002 {
	
	private static void calcWord(String word, BufferedWriter writer) throws IOException {
		char[] arr = word.toCharArray();
		long low = 0;
		long high = 0;
		for (char c : arr) {
			if (c > 'm') {
				high += Letter.VALUES[c - 'n'];
			} else {
				low += Letter.VALUES[c - 'a'];
			}
		}
		// 写一行
		writer.write("INSERT INTO `word` (`word`, `low`, `high`, `quantity`) VALUES ('"
				+word+"', " + low +", " + high + ", " + arr.length + ");");
		writer.newLine();
	}

	private static final String inputPath  = "./wordatro-web/src/test/java/com/steamhelper/web/raw-10000.txt";  // 输入文件
	private static void genSql(int done) {
		String outputPath = "./wordatro-web/src/test/java/com/steamhelper/web/words_out.txt";             // 输出文件
		
		// try-with-resources 自动关闭流
		try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
			 BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if (i++<done) {
					continue;
				}
				String word = line.trim();
				calcWord(word, writer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void statistic() {
		List<LetterStat> list = new ArrayList<>(26);
		for (int i = 0; i < 26; i++) {
			list.add(new LetterStat((char) ('a'+i)));
		}
		int num = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String word = line.trim();
				char[] chars = word.toCharArray();
				for (char c : chars) {
					list.get(c-'a').quantity++;
					num++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		BigDecimal total = new BigDecimal(num);
		for (LetterStat ls : list) {
			BigDecimal ratio = new BigDecimal(ls.quantity*100).divide(total, 2, BigDecimal.ROUND_HALF_UP);
			System.out.println(ls.c + " : " + ls.quantity + " : " + ratio + "%");
		}
	}
	
	public static void main(String[] args) {
//		calcWord("apple");
		int done = 0;
//		genSql(done);
		statistic();
	}
	
	@Data
	private static class LetterStat {
		private char c;
		private int quantity;
		private BigDecimal ratio;
		
		public LetterStat(char c) {
			this.c = c;
			this.quantity = 0;
			this.ratio = new BigDecimal(0.0);
		}
	}
	
}
