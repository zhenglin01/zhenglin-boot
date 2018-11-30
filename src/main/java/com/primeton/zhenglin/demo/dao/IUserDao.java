package com.primeton.zhenglin.demo.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.primeton.zhenglin.demo.model.User;

/**
 * 用户数据增删该查接口类
 * @author Lion
 */
@Mapper
public interface IUserDao {
	/**
	 * 增加用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	@Insert("INSERT INTO  ZHENGLIN_USER (USERNAME,PASSWORD,PART_ID) VALUES(#{username},#{password},#{partId})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	Integer insert(User user);

	 /**
     * 根据id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
	@Select("SELECT A.ID,A.USERNAME,A.PASSWORD,B.DEPTNAME FROM  ZHENGLIN_USER A LEFT JOIN ZHENGLIN_DEPT B ON (A.PART_ID=B.ID) WHERE A.ID=#{id}")
	User getUserById(@Param(value = "id") Integer id);
	
	 /**
     * 根据用户名查询用户数据，精确匹配用户名
     * @param username 用户名
     * @return 用户信息
     */
	@Select("SELECT A.ID,A.USERNAME,A.PASSWORD,A.PART_ID PARTID,B.DEPTNAME FROM  ZHENGLIN_USER A LEFT JOIN ZHENGLIN_DEPT B ON (A.PART_ID=B.ID) WHERE A.USERNAME=#{username}")
    User getUserByName(@Param(value = "username") String username);

	/**
	 * 修改个人信息
	 * @param user 封装了被修改的用户id（必选）
	 * @return 受影响的行数，正确操作情况下返回1，否则返回0
	 */
	@Update("UPDATE ZHENGLIN_USER SET USERNAME=#{username},PASSWORD=#{password},PART_ID=#{partId} WHERE ID=#{id}")
	Integer updateUser(@Param(value = "id") Integer id,@Param(value = "username") String username,@Param(value = "password") String password,@Param(value = "partId") Integer partId);
	
	/**
	 * 删除个人信息
	 * @param id
	 * @return	受影响的行数
	 */
	@Delete("DELETE FROM  ZHENGLIN_USER WHERE ID=#{id}")
	Integer deleteUser(@Param(value = "id") Integer id);
	
	/**
	 * 通过用户名查询用户的所有信息
	 * @param username
	 * @return 用户的所以信息
	 */
	@Select("SELECT A.ID,A.USERNAME,A.PASSWORD,A.PART_ID PARTID,B.DEPTNAME FROM  ZHENGLIN_USER A LEFT JOIN ZHENGLIN_DEPT B ON (A.PART_ID=B.ID) WHERE A.USERNAME LIKE #{username}")
	List<User> queryUsers(@Param(value = "username")String username);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT A.USERNAME,A.PASSWORD,B.DEPTNAME FROM  ZHENGLIN_USER A JOIN ZHENGLIN_DEPT B ON A.PART_ID=B.ID WHERE A.PART_ID=#{partId}")
	List<User> queryUsersById(Integer partId);
}
