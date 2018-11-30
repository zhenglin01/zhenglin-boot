package com.primeton.zhenglin.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.primeton.zhenglin.demo.model.Org;

/**
 * 部门组织机构增删该查接口类
 * @author Lion
 */
@Mapper
public interface IOrgDao {
	/**
	 * 增加组织部门数据
	 * @param dept 组织机构数据
	 * @return 受影响的行数
	 */
	@Insert("INSERT INTO ZHENGLIN_DEPT (ID,DEPTNAME,MGR) VALUES(#{id},#{deptName},#{mgr})")
	@Options(useGeneratedKeys=true,keyProperty="id")
	Integer insert(Org dept);

	/**
	 * 通过部门名查询部门的信息
	 * @param deptName 部门名称
	 * @return 如果用户名存在则返回用户信息，否则返回null
	 */
	@Select("SELECT ID,DEPTNAME FROM ZHENGLIN_DEPT  WHERE DEPTNAME=#{deptName}")
	Org getOrgByName(String deptName);

	/**
	 * 修改机构信息
	 * @param dept 封装了被修改的用户id（必选）
	 * @return 受影响的行数，正确操作情况下返回1，否则返回0
	 */
	@Update("UPDATE ZHENGLIN_DEPT A SET  A.DEPTNAME=#{deptName}, A.MGR=#{mgr} WHERE A.ID=#{id}")
	Integer updateOrg(@Param(value = "id") Integer id,@Param(value = "deptName")String deptName,@Param(value = "mgr")Integer mgr);

	/**
	 * 删除部门信息
	 * @param id
	 * @return	受影响的行数
	 */
	@Delete("DELETE FROM ZHENGLIN_DEPT WHERE ID=#{id}")
	Integer deletById(Integer id);

	/**
	 * 通过部门名称查询所有的用户信息
	 * @param deptName
	 * @return 所有的用户信息
	 */
	@Select("SELECT ID,DEPTNAME,MGR FROM ZHENGLIN_DEPT")
	List<Org> queryOrg();
	
	/**
	 * 
	 * @param deptName
	 * @return
	 */
	@Select("SELECT ID,DEPTNAME,MGR FROM ZHENGLIN_DEPT WHERE DEPTNAME like #{deptName}")
	List<Org> queryOrgByDeptName(String deptName);
	
	/**
	 * 
	 * @param mgr
	 * @return
	 */
	@Select("SELECT ID,DEPTNAME,MGR FROM ZHENGLIN_DEPT WHERE MGR=#{mgr}")
	List<Org> queryOrgByMgr(@Param(value = "mgr") Integer mgr);
	
}
