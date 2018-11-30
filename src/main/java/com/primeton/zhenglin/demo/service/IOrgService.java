package com.primeton.zhenglin.demo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.primeton.zhenglin.demo.model.Org;
/**
 * 组织机构service接口
 * 定义了组织机构的增删改查和用户登陆接口等
 * @author Lion
 */
public interface IOrgService {
	/**
	 * 添加部门
	 * @param dept	添加的组织数据对象
	 * @return	成功添加的组织数据
	 */
	Org createOrg(Org dept);

	/**
	 * 根据用户输入的组织id与部门名称登陆
	 * @param deptName	用户输入的部门名称
	 * @return	返回用户登录后的对象信息
	 */
	Org login(String deptName);
	
	
	/**
	 * 修改用户个人信息
	 * @param id 用户id
	 * @param deptName 新用户名，如果不修改，则使用null值
	 * @return 受影响行数
	 */
	Integer modifyOrg(Integer id,String deptName,Integer mgr);
	
	/**
	 * 删除个人数据
	 * @param id 用户名
	 * @return	受影响行数
	 */
	Integer removeById(Integer id);
	
	/**
	 * 查询所有用户的信息
	 * @param deptName
	 * @return	受影响的行数
	 */
	PageInfo<Org> queryOrg(); 
	
	/**
	 * 通过上级部门号查询下级所有部门
	 * @param deptName
	 * @return
	 */
	List<Org> queryOrgByDeptName(String deptName);
	
	/**
	 * 
	 * @param mgr
	 * @return
	 */
	List<Org> queryOrgByMgr(Integer mgr);
	
}
