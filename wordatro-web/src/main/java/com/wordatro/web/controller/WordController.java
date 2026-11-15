package com.wordatro.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordatro.bean.CommonReq;
import com.wordatro.bean.IReturnBean;
import com.wordatro.entity.Word;
import com.wordatro.web.service.WordService;

import lombok.extern.slf4j.Slf4j;

/**
 * @class:  WordController
 * @description: 
 * @author: L.zhen
 * @date:   2026/11/8 20:54
 */
@Slf4j
@Controller
@RequestMapping("main")
public class WordController {
	
	@Resource
	private WordService wordService;
	
	/**
	 * @title  toView
	 * @description 跳转
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String toView() {
		return "main_page";
	}
	
	@RequestMapping(value = "/calc.do")
	@ResponseBody
	public IReturnBean<List<Word>> list(HttpServletRequest request, @RequestBody CommonReq req) {
//	public IReturnBean<List<WordResp>> list(HttpServletRequest request, @RequestBody List<WordReq> reqs) {
		try {
			return wordService.list(req.getStrValue());
		} catch (Exception e) {
			log.error("calc error:", e);
			return IReturnBean.error();
		}
	}
	
	@RequestMapping(value = "/del.do")
	@ResponseBody
	public IReturnBean<?> del(HttpServletRequest request, @RequestParam("id") Integer id) {
		try {
			return wordService.del(id);
		} catch (Exception e) {
			log.error("del error:", e);
			return IReturnBean.error();
		}
	}
}
