package com.wordatro.web.mapper;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wordatro.mapper.WordMapper;

@Component
public class MapperSupport {
	@Resource
	public WordMapper wordMapper;

}
