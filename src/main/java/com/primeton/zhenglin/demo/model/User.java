package com.primeton.zhenglin.demo.model;
/**
 * 用户数据类
 * @author Lion
 *
 */
public class User {
	private Integer id;
	private String username;
	private String password;
	private Integer partId;
	private String deptName;
	public User() {
		super();
	}
	
	
	public User(String username, String password, Integer partId) {
		super();
		this.username = username;
		this.password = password;
		this.partId = partId;
	}


	public User(Integer id, String username, String password, Integer partId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.partId = partId;
	}

	public User(Integer id, String username, String password, Integer partId, String deptName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.partId = partId;
		this.deptName = deptName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPartId() {
		return partId;
	}
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", part_id=" + partId
				+ ", deptName=" + deptName + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
