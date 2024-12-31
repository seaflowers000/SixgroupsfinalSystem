package cn.lanqiao.sixgroupsfinalsystem.mapper;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VipNameMapper {
    /**
     * 查询所有会员
     */
    @Select("select * from vip_name where status = 0")
    List<VipName> selectAll();
     /**
     * 根据ID删除会员
     */
    @Update("UPDATE vip_name SET status = 1 WHERE id = #{id}")
    int deleteById(Integer id);
    /**
     * 批量逻辑删除会员
     */
    @Update("UPDATE vip_name SET status = 1 WHERE id IN (#{ids})")
    boolean batchDelete(List<Integer> ids);
    /**
     * 模糊查询会员
     */
    @Select("SELECT * FROM vip_name WHERE status = 0 AND username LIKE CONCAT('%', #{username}, '%')" )
    List<VipName> search(@Param("username") String username);
}