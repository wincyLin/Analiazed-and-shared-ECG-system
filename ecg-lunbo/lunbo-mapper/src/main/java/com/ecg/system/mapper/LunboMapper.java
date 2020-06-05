package com.ecg.system.mapper;

import com.ecg.system.pojo.Lunbo;
import com.ecg.system.pojo.LunboExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LunboMapper {
    int countByExample(LunboExample example);

    int deleteByExample(LunboExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Lunbo record);

    int insertSelective(Lunbo record);

    List<Lunbo> selectByExample(LunboExample example);

    Lunbo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Lunbo record, @Param("example") LunboExample example);

    int updateByExample(@Param("record") Lunbo record, @Param("example") LunboExample example);

    int updateByPrimaryKeySelective(Lunbo record);

    int updateByPrimaryKey(Lunbo record);
}