package com.spfood.cms.utils;

/**
 * json 返回结果
 * @author fengjunchao 2017-01-23
 *
 */
public class JsonResult{

	//状态
	private String status;
	//消息
	private String messages;
	//结果对象
	private Object result;

	public JsonResult(String status, String messages, Object result) {
		super();
		this.status = status;
		this.messages = messages;
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
}
