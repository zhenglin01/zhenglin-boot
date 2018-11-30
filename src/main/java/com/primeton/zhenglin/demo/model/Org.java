package com.primeton.zhenglin.demo.model;
/**
 * 组织部门类
 * @author Lion
 *
 */
public class Org {
	private Integer id;
	private String deptName;
	private Integer mgr;
	private String mgrName;
	
	public Org() {
		super();
	}
	
	
	
	public Org(Integer id, String deptName, Integer mgr) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.mgr = mgr;
	}



	public Org(Integer id, String deptName, Integer mgr, String mgrName) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.mgr = mgr;
		this.mgrName = mgrName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
	
	
	
	
	
}
