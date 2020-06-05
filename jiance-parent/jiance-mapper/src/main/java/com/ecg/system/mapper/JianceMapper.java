package com.ecg.system.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ecg.system.pojo.Jiance;
import com.ecg.system.pojo.JianceExample;

public interface JianceMapper {
    int countByExample(JianceExample example);

    int deleteByExample(JianceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jiance record);

    int insertSelective(Jiance record);

    List<Jiance> selectByExampleWithBLOBs(JianceExample example);

    List<Jiance> selectByExample(JianceExample example);

    Jiance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jiance record, @Param("example") JianceExample example);

    int updateByExampleWithBLOBs(@Param("record") Jiance record, @Param("example") JianceExample example);

    int updateByExample(@Param("record") Jiance record, @Param("example") JianceExample example);

    int updateByPrimaryKeySelective(Jiance record);

    int updateByPrimaryKeyWithBLOBs(Jiance record);

    int updateByPrimaryKey(Jiance record);

	Jiance selectByOne(String phone);
}