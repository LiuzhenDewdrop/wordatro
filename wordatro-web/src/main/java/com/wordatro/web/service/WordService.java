package com.wordatro.web.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wordatro.bean.IReturnBean;
import com.wordatro.entity.Word;
import com.wordatro.util.util.CollectionUtil;
import com.wordatro.web.bean.request.WordReq;
import com.wordatro.web.constant.Combination;
import com.wordatro.web.constant.Letter;
import com.wordatro.web.mapper.MapperSupport;

@Service
public class WordService {
	
	@Resource
	private MapperSupport mapperSupport;
	
	public IReturnBean<List<Word>> list(String reqs) {
//	public IReturnBean<List<WordResp>> list(List<WordReq> reqs) {
//		filter(reqs);
//		if (CollectionUtil.isEmpty(reqs)) {
//			return IReturnBean.fail("无字母,不可算");
//		}
		if (reqs.length() >= Combination.ALL_COMBINATIONS.length) {
			return IReturnBean.fail("字母太多，目前不支持，需要升级");
		}
		boolean[][] combination = Combination.ALL_COMBINATIONS[reqs.length()];
		List<Word> resultList = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for (boolean[] subset : combination) {
			Word query = calcSubset(subset, reqs.toCharArray());
			List<Word> list = mapperSupport.wordMapper.queryByLetters(query);
			for (Word word : list) {
				if(set.add(word.getId())) {
					resultList.add(word);
				}
			}
		}
		resultList.sort(Comparator.comparingInt(Word::getQuantity).reversed());
		return IReturnBean.success(resultList);
	}
	
	private void filter(List<WordReq> reqs) {
		if (CollectionUtil.isEmpty(reqs)) {
			return;
		}
		reqs.removeIf(req -> req == null || req.getLetter() == null);
	}

	private Word calcSubset(boolean[] subset, char[] reqs) {
//	private Word calcSubset(boolean[] subset, List<WordReq> reqs) {
		assert subset.length == reqs.length;
		long low = 0;
		long high = 0;
		for (int i = 0; i <subset.length; i++) {
			if (subset[i]) {
				char c = reqs[i];
//				char c = reqs.get(i).getLetter();
				if (c > 'm') {
					high += Letter.VALUES[c - 'n'];
				} else {
					low += Letter.VALUES[c - 'a'];
				}
			}
		}
		Word w = new Word();
		w.setLow(low);
		w.setHigh(high);
		return w;
	}
	
	private void calcWord(Word w, List<WordReq> reqs) {
	
	}
	
	public IReturnBean<?> del(Integer id) {
		if (id == null) {
			return IReturnBean.fail("未查询到word");
		}
		mapperSupport.wordMapper.deleteByPrimaryKey(id);
		return IReturnBean.success("已删除");
	}
}
