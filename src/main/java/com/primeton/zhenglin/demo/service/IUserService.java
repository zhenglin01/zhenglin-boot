package com.primeton.zhenglin.demo.service;



import com.github.pagehelper.PageInfo;
import com.primeton.zhenglin.demo.model.User;
/**
 * 用户管理service接口
 * 定义了用户的增删改查和用户登陆接口等
 * @author Lion
 */
public interface IUserService {
	/**
	 * 用户注册方法
	 * @param user	注册的用户数据对象
	 * @return	成功注册的用户数据对象，包含用户的id
	 */
	User createUser(User user);

	/**
	 * 根据用户输入的用户名和密码登录
	 * @param username	用户输入的用户名
	 * @param password	用户输入的密码
	 * @return	返回用户登录后的对象信息
	 */
	User login(String username,String password);
	
	/**
	 *通过用户id查找用户数据 
	 * @param id	用户id
	 * @return	存在返回用户信息，不存在返回null
	 */
	
	User getUserById(Integer id);
	
	/**
	 * 通过用户名查询用户信息
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * 修改用户个人信息
	 * @param id 用户id
	 * @param username 新用户名
	 * @param password 新密码
	 * @param partId 新部门id
	 * @return 受影响行数
	 */
	Integer modifyUser(Integer id, String username, String password, Integer partId);
	
	/**
	 * 删除个人数据
	 * @param id 用户名
	 * @return	受影响行数
	 */
	Integer removeUser(Integer id);
	
	/**
	 * 分页查询,通过用户名查询所有信息
	 * @param username
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<User> queryUsers(String username,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	 * @return
	 */
	PageInfo<User> queryUsersById(Integer partId,Integer pageNum,Integer pageSize); 
}
