package com.primeton.zhenglin.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.primeton.zhenglin.demo.dao.IOrgDao;
import com.primeton.zhenglin.demo.exception.ErrorEnum;
import com.primeton.zhenglin.demo.exception.DemoException;
import com.primeton.zhenglin.demo.model.Org;
import com.primeton.zhenglin.demo.service.IOrgService;
/**
 * 组织机构service接口
 * 定义了组织机构的增删改查和用户登陆接口等
 * @author Lion
 */
@Service("deptService")
@Transactional(rollbackFor = Exception.class)
public class OrgServiceImp implements IOrgService{

	@Autowired
	private IOrgDao orgDao;

	public Org createOrg(Org dept) {
		//获取用户输入的部门名
		String deptName = dept.getDeptName();
		//判断用户名是否为空
		if(deptName==null&&"".equals(deptName)){
			//如果为空则返回部门名为空错误
			throw new DemoException(ErrorEnum.LACK_ORG_NAME);
		}
		//查看数据中是否存在这个部门
		Org data = orgDao.getOrgByName(deptName);
		if(data==null){
			//如果为空则不存在则将部门加入
			orgDao.insert(dept);
		}else{
			//如果不为空则返回部门已存在错误
			throw new DemoException(ErrorEnum. ERROR_ORG_NAME_INUSE);
		}
		return dept;
	}

	@Transactional(readOnly = true)
	public Org login(String deptName) {
		//查看数据中是否存在这个用户
		Org data = orgDao.getOrgByName(deptName);
		if(data==null){
			//如果为空则用户不存在错误
			throw new DemoException(ErrorEnum.ERROR_ORG_ID);
		}else{
			//则登录成功
			return data;
		}
	}

	public PageInfo<Org> queryOrg() {
		return  new PageInfo<Org>(orgDao.queryOrg());
	}

	@Transactional
	public Integer modifyOrg(Integer id, String deptName, Integer mgr) {
		return orgDao.updateOrg(id,deptName,mgr);
	}

	

	public Integer removeById(Integer id) {
		return orgDao.deletById(id);
	}
	
	public List<Org> queryOrgByDeptName(String deptName) {
		deptName = "%"+deptName+"%";
		return orgDao.queryOrgByDeptName(deptName);
	}
	

	public List<Org> queryOrgByMgr(Integer mgr) {
		return orgDao.queryOrgByMgr(mgr);
	}
	
	



}





