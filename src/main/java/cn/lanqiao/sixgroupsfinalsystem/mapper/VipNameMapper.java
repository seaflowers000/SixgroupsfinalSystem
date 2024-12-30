package cn.lanqiao.sixgroupsfinalsystem.mapper;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VipNameMapper {
    /**
     * 查询所有会员
     */
    @Select("select * from vip_name")
    List<VipName> selectAll();
}