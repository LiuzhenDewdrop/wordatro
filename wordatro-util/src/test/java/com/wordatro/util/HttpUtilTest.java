package com.wordatro.util;

import org.junit.Test;

import com.wordatro.util.bean.CodeMsgBean;
import com.wordatro.util.util.HttpUtil;

public class HttpUtilTest {
	
	@Test
	public void test() throws Exception {
		// 测试执行
		CodeMsgBean result = HttpUtil.post("https://steamcommunity.com/stats/TitanQuestAnniversaryEdition/achievements?l=schinese", "");
		System.out.println("result:");
		System.out.println();
		System.out.println(result);
		boolean checkResult = HttpUtil.check("https://shared.st.dl.eccdnx.com/store_item_assets/steam/apps/292030/header_schinese.jpg", null);
		System.out.println("result:");
		System.out.println();
		System.out.println(checkResult);
	}
	
}
