package com.ecg.system.service;

import java.util.List;

import com.ecg.system.pojo.Jiance;
import com.ecg.system.pojo.PageResult;



public interface UsersService {
	//查询所有用户
		public List<Jiance> findAll();
		//根据ID查询某一用户
		public Jiance findOne(Integer id);
		
		public Jiance findByAccount(String phone);
		//增加新账号
		public void addUsers(Jiance users);
		//批量删除账号
		public void deleteUsers(Integer[] ids);
		//修改
		public void updateUsers(Jiance users);
		//分页
		public PageResult findUsersByPage(int pageNo,int pageSize);
		
	
	

}
