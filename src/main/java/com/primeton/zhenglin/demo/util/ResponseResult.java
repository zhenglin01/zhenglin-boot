package com.primeton.zhenglin.demo.util;

public class ResponseResult<T>{
	public static final int STATE_OK = 1;
	public static final int STATE_ERR = 0;

	private Integer state;
	private String message;
	private T data;

	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}

	public ResponseResult(Integer state, String message) {
		super();
		setState(state);
		setMessage(message);
	}
	

	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	

	public ResponseResult(Integer state, String message, T data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public ResponseResult(Exception e) {
		super();
		setState(STATE_ERR);
		setMessage(e.getMessage());
	}

	public Integer getState() {
		return state;
	}

	
	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}

}
