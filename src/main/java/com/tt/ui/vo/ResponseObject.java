package com.tt.ui.vo;

public class ResponseObject<T> {
	private int status;
	// 消息,成功消息或者失败消息
	private String msg;
	// 要返回的数据
	private T data;
	private String url;

	public ResponseObject() {

	}

	public ResponseObject(String msg) {

		this.msg = msg;

	}

	public ResponseObject(int status, String msg) {
		this.status = status;
		this.msg = msg;

	}

	public ResponseObject(int status, String msg, String url) {
		this(status, msg);
		this.url = url;
	}

	public ResponseObject(int status, String msg, T data) {
		this(status, msg);
		this.data = data;
	}

	public ResponseObject(int status, String msg, T data, String url) {
		this(status, msg, url);
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
