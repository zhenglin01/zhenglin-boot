package com.primeton.zhenglin.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.primeton.zhenglin.demo.model.User;
import com.primeton.zhenglin.demo.service.IUserService;
import com.primeton.zhenglin.demo.util.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 用户类控制层
 * @author Lion
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "用户管理api", tags = "用户管理接口")
public class UserController{

	@Autowired
	private IUserService userService;

	/**
	 * 用户注册
	 * @param user 接受的用户数据
	 * @return
	 */
	@PostMapping
	@ResponseBody
	@ApiOperation(value = "用户注册", response = ResponseResult.class)
	public ResponseResult<Void> createUser(@RequestBody User user) {
		// 声明返回值
		ResponseResult<Void> rr;
		// 把各参数封装到data对象中
		// 调用业务层的对象的createUser(user)方法实现注册
		userService.createUser(user);
		// 封装返回结果
		rr = new ResponseResult<Void>(ResponseResult.STATE_OK,"添加成功");
		return rr;
	}

	/**
	 * 用户登陆
	 * @param user	接受的用户数据
	 * @param session	
	 * @return
	 */
	@PostMapping(value="/actions/login")
	@ApiOperation(value = "用户登陆", response = ResponseResult.class)
	@ResponseBody
	public ResponseResult<User> login(@RequestBody User user) {
		// 声明返回值
		ResponseResult<User> rr;
		// 封装返回结果
		rr = new ResponseResult<User>(ResponseResult.STATE_OK,userService.login(user.getUsername(),user.getPassword()));
		return rr;
	}

	/**
	 * 用户修改
	 * @param user 接受的用户数据
	 */
	@PutMapping
	@ApiOperation(value = "用户修改", response = ResponseResult.class)
	@ResponseBody
	public ResponseResult<Void> modifyUser(@RequestBody User user) {
		// 声明返回值
		ResponseResult<Void> rr;
		//执行修改操作
		userService.modifyUser(user.getId(),user.getUsername(),user.getPassword(),user.getPartId());
		rr = new ResponseResult<Void>(ResponseResult.STATE_OK,"修改成功");
		return rr;
	}

	/**
	 * 用户删除
	 * @param id	用户id（必填）
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "用户删除", response = ResponseResult.class)
	@ResponseBody
	public void removeUser(@PathVariable("id") Integer id) {
		// 调用业务层对象的deleteUser(id)方法
		userService.removeUser(id);
	}

	/**
	 * 用户信息分页查询
	 * @param username 用户名
	 * @param pageNum 页数
	 * @param pageSize 行数
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "用户信息分页查询", response = PageInfo.class)
	public PageInfo<User> queryUsers(
			@RequestParam("username") String username,
			Integer pageNum,Integer pageSize){
		return userService.queryUsers(username,pageNum,pageSize);
	}

	/**
	 * 通过组织id查询用户所有信息
	 * @return
	 */
	@GetMapping(value = "/actions/users")
	@ResponseBody
	@ApiOperation(value="查询用户所有信息",response = ResponseResult.class)
	public PageInfo<User> queryUsersById(@RequestParam("partId")Integer partId,Integer pageNum,Integer pageSize){
		return userService.queryUsersById(partId,pageNum,pageSize);
	}
	
}

