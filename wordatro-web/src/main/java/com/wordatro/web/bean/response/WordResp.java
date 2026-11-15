package com.wordatro.web.bean.response;

import com.wordatro.entity.Word;

import lombok.Data;

@Data
public class WordResp {
	private Integer id;
	private String word;
	private String score;
	
	public WordResp() {
	}
	
	public WordResp(Word w) {
		this.id = w.getId();
		this.word = w.getWord();
	}
	
	
}
