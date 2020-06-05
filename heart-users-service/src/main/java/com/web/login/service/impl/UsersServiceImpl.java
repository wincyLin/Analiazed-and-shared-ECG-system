package com.web.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.heart.mapper.UsersMapper;
import com.web.heart.pojo.PageResult;
import com.web.heart.pojo.Result;
import com.web.heart.pojo.Users;
import com.web.login.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper usersMapper;
	
	//查询所有用户
		@Override
		public List<Users> findAll() {
			List<Users>list= usersMapper.selectByExample(null);
			return list;
		}
		//根据ID查询某一用户
		@Override
		public Users findOne(Integer id) {
			return usersMapper.selectByPrimaryKey(id);
		}
		//增加用户
		@Override
		public void addUsers(Users users) {
			usersMapper.insert(users);

		}
		//批量删除用户
		@Override
		public void deleteUsers(Integer[] ids) {
			for (Integer id : ids) {
				usersMapper.deleteByPrimaryKey(id);
			}

		}
		//修改更新
		@Override
		public void updateUsers(Users users) {
			usersMapper.updateByPrimaryKey(users);
			
		}
		
		//分页
			@Override
			public PageResult findUsersByPage(int pageNo, int pageSize) {
				PageResult pageResult=new PageResult();
				//分页第一步：设置mybatis的分页拦截，然后重构sql分页语句
				PageHelper.startPage(pageNo, pageSize);
				List<Users>list=usersMapper.selectByExample(null);
				
				//分页的第二步骤：获取分页bean，里面提供分页所需参数
				PageInfo pageInfo=new PageInfo<>(list);
				
				pageResult.setRows(list);
				pageResult.setTotal(pageInfo.getTotal());
				
				return pageResult;
			}
		//根据账号进行条件查询
		@Override
		public Users findByAccount(String account) {
			return usersMapper.selectByOne(account);
		}
	
}
