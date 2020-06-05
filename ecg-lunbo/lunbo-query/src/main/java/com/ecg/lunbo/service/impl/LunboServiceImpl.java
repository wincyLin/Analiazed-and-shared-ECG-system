package com.ecg.lunbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ecg.lunbo.service.LunboService;
import com.ecg.system.mapper.CategoryMapper;
import com.ecg.system.mapper.LunboMapper;
import com.ecg.system.pojo.Category;
import com.ecg.system.pojo.Lunbo;
import com.ecg.system.pojo.LunboExample;
import com.ecg.system.pojo.LunboExample.Criteria;
import com.ecg.system.pojo.PageResult;
import com.ecg.system.pojo.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;



@Service
public class LunboServiceImpl implements LunboService{

	@Autowired
	private LunboMapper lunboMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return categoryMapper.selectByExample(null);
	}
	
	@Override
	public List<Lunbo> findAllContent() {
		// TODO Auto-generated method stub
		return lunboMapper.selectByExample(null);
	}

	@Override
	public PageResult findByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Lunbo> page=   (Page<Lunbo>) lunboMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void addContent(Lunbo content) {
		lunboMapper.insert(content);
		redisTemplate.boundHashOps("content").delete(content.getCategoryid());
		
	}

	@Override
	public void updateContent(Lunbo content) {
		// TODO Auto-generated method stub
		Lunbo oldContent= lunboMapper.selectByPrimaryKey(content.getId());
		redisTemplate.boundHashOps("content").delete(oldContent.getCategoryid());
		lunboMapper.updateByPrimaryKey(content);
	}

	@Override
	public Lunbo findOneContent(Long id) {
		// TODO Auto-generated method stub
		return lunboMapper.selectByPrimaryKey(id);
	}

	@Override
	public Result deleteContent(Long[] ids) {
		Result result = null;
		try {
			for (int i=0;i<ids.length;i++){
			Lunbo content = lunboMapper.selectByPrimaryKey(ids[i]);
			redisTemplate.boundHashOps("content").delete(content.getCategoryid());
			lunboMapper.deleteByPrimaryKey(ids[i]);
			result = new Result(true , "删除成功！");
		}
	} catch (Exception e) {
		e.printStackTrace();
		result = new Result(false,"删除失败！");
	}
	return result;
	}
	
	@Override
	public Category findOneCategory(Long id) {
		// TODO Auto-generated method stub
		return categoryMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public PageResult findByPage(Lunbo content, int pageNum, int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		LunboExample example= new LunboExample();
		Criteria criteria = example.createCriteria();
		
		if(content!=null) {
			if(content.getTitle()!=null && content.getTitle().length()>0) {
				criteria.andTitleLike("%"+content.getTitle()+"%");
			}
			if(content.getPic()!=null && content.getPic().length()>0) {
				criteria.andPicLike("%"+content.getTitle()+"%");
				
			}
			if(content.getStatus()!=null &&  content.getStatus().length()>0) {
				criteria.andStatusLike("%"+content.getTitle()+"%");
			}
		}
		
		Page<Lunbo> page=   (Page<Lunbo>) lunboMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Lunbo> findByCategoryId(Long categoryId) {
		//加入缓存的代码
		
		List<Lunbo> list = (List<Lunbo>) redisTemplate.boundHashOps("content").get(categoryId);
		
		if(list==null) {
			System.out.println("查询数据库============================");
			LunboExample example = new LunboExample();
			Criteria criteria = example.createCriteria();
			//有效广告
			criteria .andStatusEqualTo("1");
			criteria.andCategoryidEqualTo(categoryId);
			//排序
//			example.setOrderByClause("sort_order");
			list = lunboMapper.selectByExample(example);
			redisTemplate.boundHashOps("content").put(categoryId, list);
			
			
		}else {
			System.out.println("从缓存中获取===============================");
		}
		return list;
	}

	


}
