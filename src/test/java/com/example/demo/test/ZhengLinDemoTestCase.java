package com.example.demo.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.primeton.zhenglin.demo.ZhenglinDemoApplication;
import com.primeton.zhenglin.demo.controller.OrgController;
import com.primeton.zhenglin.demo.controller.UserController;
import com.primeton.zhenglin.demo.model.Org;
import com.primeton.zhenglin.demo.model.User;
import com.primeton.zhenglin.demo.service.IOrgService;
import com.primeton.zhenglin.demo.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ZhenglinDemoApplication.class)
@WebAppConfiguration
public class ZhengLinDemoTestCase {
	@Autowired
	private UserController userController;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private OrgController orgController;
	
	@Autowired
	private IOrgService orgService;

	/**
	 * 测试用户注册
	 */
	@Test
	public void testUser() {
		//测试 根据id删除用户功能
		Integer id = userService.getUserByUsername("machao").getId();
		userController.removeUser(id);;
		Assert.assertNull("删除不成功异常",userService.getUserById(id));
		//测试用户注册
		User data = new User("machao", "123456",3);
		userController.createUser(data);
		Assert.assertNotNull("用户注册异常", userController.queryUsers(data.getUsername(), 0,0));
		//修改用户资料
		//data = new User(12,"machao", "123456",3);
		//userController.modifyUser(data);;
		//Assert.assertNotNull("修改失败",userService.login("zzzzz","654321"));

	}
	
	@Test
	public void testOrg(){
		Integer id = 2;
		orgController.removeOrg(id);
		Assert.assertNotNull("删除不成功异常",orgService.queryOrgByMgr(2));
		Org data = new Org(2,"事业部",1);
		orgController.createOrg(data);
		Assert.assertNotNull("用户注册异常", orgService.login("事业部"));
	}
	
}
