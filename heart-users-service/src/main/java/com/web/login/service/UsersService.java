package com.web.login.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.web.heart.pojo.PageResult;
import com.web.heart.pojo.Result;
import com.web.heart.pojo.Users;


public interface UsersService {
    //查询所有用户
	public List<Users> findAll();
	//根据ID查询某一用户
	public Users findOne(Integer id);
	//根据账号条件查询
	public Users findByAccount(String account);
	//增加新账号
	public void addUsers(Users users);
	//批量删除账号
	public void deleteUsers(Integer[] ids);
	//修改
	public void updateUsers(Users users);
	//分页
	public PageResult findUsersByPage(int pageNo,int pageSize);
}
