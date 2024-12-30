package cn.lanqiao.sixgroupsfinalsystem.mapper;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VipNameMapper {
    /**
     * 查询所有会员
     */
    @Select("SELECT * FROM vip_name")
    List<VipName> selectAll();

     /**
     * 逻辑删除会员（将status改为1）
     */
    @Update("UPDATE vip_name SET status = 1 WHERE id = #{id}")
    int deleteById(Integer id);

}