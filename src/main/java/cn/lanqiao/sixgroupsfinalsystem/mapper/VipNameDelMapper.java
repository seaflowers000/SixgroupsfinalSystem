package cn.lanqiao.sixgroupsfinalsystem.mapper;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VipNameDelMapper {
    /**
     * 查询所有会员
     */
    @Select("select * from vip_name where status = 1")
    List<VipName> selectAll();

    /**
     * 根据ID恢复会员
     */
    @Update("UPDATE vip_name SET status = 0 WHERE id = #{id}")
    int deleteById(Integer id);

    /**
     * 批量逻辑恢复会员
     */
    @Update("UPDATE vip_name SET status = 0 WHERE id IN #{id};")
    int batchDelete(List<Integer> list);

    /**
     * 模糊查询会员
     */
    @Select("SELECT * FROM vip_name WHERE status = 1 AND username LIKE CONCAT('%', #{username}, '%')" )
    List<VipName> search(@Param("username") String username);
}