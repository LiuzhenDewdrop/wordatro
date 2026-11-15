package com.wordatro.bean;

import lombok.Data;

@Data
public class CommonReq {
	private Integer id;
	
	private Integer intValue;
	
	private Integer offset=0;
	private Integer pageSize=20;
	private String strValue;
	
}
