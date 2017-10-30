package com.yoo.wxcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Value("${server.port}")
	private String port;

	@RequestMapping("/port")
	public String port(String name) {
		return "gongzhonghao[port]=" + port;
	}

	@RequestMapping("/index")
	public String index(String name) {
		return "hello:" + name;
	}

	@RequestMapping("/weixin")
	public String wx(String signature, String echostr, String timestamp, String nonce) {
		logger.info("IndexController.wx[signature]==" + signature);
		logger.info("IndexController.wx[echostr]==" + echostr);
		logger.info("IndexController.wx[timestamp]==" + timestamp);
		logger.info("IndexController.wx[nonce]==" + nonce);

		String resStr = null;
		try {
			String chkSignature = SHA1.getSHA1(token, timestamp, nonce);
			if (chkSignature.equals(signature)) {
				resStr = echostr;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			resStr = e.getMessage();
		}
		return resStr;

	}
}
