package com.primeton.zhenglin.demo.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.primeton.zhenglin.demo.model.Org;
import com.primeton.zhenglin.demo.service.IOrgService;
import com.primeton.zhenglin.demo.util.ResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 组织部门类控制层
 * @author Lion
 */
@RestController
@RequestMapping("/api/depts")
@Api(value = "组织结构管理api", tags = "组织机构管理接口")
public class OrgController{
	@Autowired
	private IOrgService deptService;

	/**
	 * 增加组织机构信息
	 * @param id	部门id
	 * @param deptName	部门名称
	 * @param mgr	上级部门id
	 * @return
	 */
	@PostMapping(produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(value = "增加组织机构信息", response = ResponseResult.class)
	public ResponseResult<Org> createOrg(@RequestBody Org org) {
		// 声明返回值
		ResponseResult<Org> rr;
		// 把各参数封装到Dept对象中
		Org dept = new Org(org.getId(),org.getDeptName(),org.getMgr());
		System.out.println(dept);
		// 调用业务层的对象的insert()方法实现注册
		deptService.createOrg(dept);
		// 封装返回结果
		rr = new ResponseResult<Org>(ResponseResult.STATE_OK,"添加成功",dept);
		return rr;
	}

	/**
	 * 组织机构登陆
	 * @param deptName	部门名称
	 * @param session
	 * @return
	 */
	@PostMapping(value="/login",produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(value = "组织机构登陆", response = ResponseResult.class)
	public ResponseResult<Org> login(@RequestBody String deptName,HttpSession session) {
		// 声明返回值
		ResponseResult<Org> rr;
		// 执行登录
		Org dept= deptService.login(deptName);
		// 在session中封装id和deptName
		session.setAttribute("id", dept.getId());
		session.setAttribute("deptName", dept.getDeptName());
		// 返回：成功
		rr = new ResponseResult<Org>(ResponseResult.STATE_OK,dept);
		// 执行返回
		return rr;
	}

	/**
	 * 组织机构修改信息
	 * @param id	部门id
	 * @param deptName	部门名称
	 * @param mgr	上级部门id
	 */
	@PutMapping
	@ResponseBody
	@ApiOperation(value = "组织机构修改信息", response = ResponseResult.class)
	public ResponseResult<Void> modifyOrg(@RequestBody Org org) {
		// 声明返回值
		ResponseResult<Void> rr;
		//执行修改
		deptService.modifyOrg(org.getId(), org.getDeptName(), org.getMgr());
		 rr = new ResponseResult<Void>(ResponseResult.STATE_OK,"修改成功");
		 return rr;
	}

	/**
	 * 组织机构删除信息
	 * @param id 部门 id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "组织机构删除信息", response = ResponseResult.class)
	public void removeOrg(@PathVariable Integer id){
		// 调用业务层对象的deleteById(id)方法
		deptService.removeById(id);
	}      

	/**
	 * 组织机构查询信息
	 * @param deptName	部门名称
	 * @param pageNum	行数
	 * @param pageSize	个数
	 * @return
	 */
	@GetMapping
	@ResponseBody
	@ApiOperation(value = "组织机构查询信息", response = ResponseResult.class)
	public PageInfo<Org> queryOrg(){
		return deptService.queryOrg();
	}
	
	/**
	 * 
	 * @param deptName
	 * @return
	 */
	@GetMapping(value = "/actions/names")
	@ResponseBody
	@ApiOperation(value="模糊查询",response = ResponseResult.class)
	public List<Org> querOrgByDeptName(String deptName){
		 return deptService.queryOrgByDeptName(deptName);
	}
	
	/**
	 * 
	 * @param mgr
	 * @return
	 */
	@GetMapping(value = "/actions")
	@ResponseBody
	@ApiOperation(value="查询下级所有信息",response = ResponseResult.class)
	public List<Org> querOrgByMgr(Integer mgr){
		 return deptService.queryOrgByMgr(mgr);
	}
	
}
