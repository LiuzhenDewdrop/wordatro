package com.wordatro.web.bean.request;

import lombok.Data;

@Data
public class WordReq {
	private Character letter;
	private Integer point;
	private Integer mul;
	private Integer special;
}
