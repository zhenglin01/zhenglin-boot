package com.primeton.zhenglin.demo.service.imp;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.zhenglin.demo.dao.IUserDao;
import com.primeton.zhenglin.demo.exception.ErrorEnum;
import com.primeton.zhenglin.demo.exception.DemoException;
import com.primeton.zhenglin.demo.model.User;
import com.primeton.zhenglin.demo.service.IUserService;
/**
 * 用户管理service实现类
 * 实现用户的增删改查和用户登陆功能
 * @author Lion
 */
@Service("userServcie")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImp implements IUserService{

	@Autowired
	private IUserDao userDao;

	public User createUser(User user) {
		//获取用户输入的用户名与密码
		String username = user.getUsername();
		String password = user.getPassword();
		//判断用户名是否为空
		if(username==null&&"".equals(username)){
			//如果为空则返回用户名为空错误
			throw new DemoException(ErrorEnum.LACK_USER_NAME);
		}
		if(password==null&&"".equals(password)){
			//如果为空则返回密码为空错误
			throw new DemoException(ErrorEnum.LACK_USER_PASSWORD);
		}
		//查看数据中是否存在这个用户
		User data = userDao.getUserByName(username);
		if(data==null){
			//如果为空则不存在则将用户加入
			userDao.insert(user);
		}else{
			//如果不为空则返回用户已存在错误
			throw new DemoException(ErrorEnum. ERROR_USER_INUSE);
		}
		return user;
	}

	@Transactional(readOnly = true)
	public User login(String username, String password) {
		//查看数据中是否存在这个用户
		User data = userDao.getUserByName(username);
		if(data==null){
			//如果为空则用户不存在错误
			throw new DemoException(ErrorEnum.ERROR_USER);
		}else{
			//如果密码匹配则登录成功
			if(data.getPassword().equals(password)){
				return data;
			}
			//否则返回密码错误
			throw new DemoException(ErrorEnum.ERROR_USER_PASSWPRD);
		}
	}
	@Transactional
	public Integer modifyUser(Integer id, String username, String password, Integer partId) {
		//获取用户信息
		User data = getUserById(id);
		//如果输入的用户名，密码与部门id为空则将原数据插入
		if(username==null&&"".equals(username)){
			username=data.getUsername();
		}
		if(password==null&&"".equals(password)){
			password=data.getPassword();
		}
		if(password==null&&"".equals(password)){
			password=data.getPassword();
		}
		if(partId==null&&"".equals(partId)){
			partId=data.getPartId();
		}
		
		return userDao.updateUser(id, username, password, partId);
	}

	public Integer removeUser(Integer id) {
		//先判断用户是否存在
		User data = userDao.getUserById(id);
		if(data==null){
			//不存在返回用户不存在
			throw new DemoException(ErrorEnum.ERROR_USER);
		}else{
			//存在删除用户
			return userDao.deleteUser(id);
		}

	}

	public PageInfo<User> queryUsers(String username, Integer pageNum, Integer pageSize) {
		username = "%"+username+"%"; 
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=3;
		}
		PageHelper.startPage(pageNum,pageSize);
		List<User> users =  userDao.queryUsers(username);
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		return pageInfo;
	}


	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	public User getUserByUsername(String username) {
		return userDao.getUserByName(username);
	}
	
	public PageInfo<User> queryUsersById(Integer partId,Integer pageNum,Integer pageSize) {
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=3;
		}
		return  new PageInfo<User> (userDao.queryUsersById(partId));
	}

}

