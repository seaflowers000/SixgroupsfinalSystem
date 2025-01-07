package cn.lanqiao.sixgroupsfinalsystem.mapper;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;


import org.apache.ibatis.annotations.Insert;
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
     * 添加会员
     */
    @Insert("INSERT INTO vip_name(username, gender, email, address, status) " +
            "VALUES(#{username}, #{gender}, #{email}, #{address}, #{status})")
    int insert(VipName vipName);

     /**
     * 根据ID删除会员
     */
    @Update("UPDATE vip_name SET status = 1 WHERE id = #{id}")
    int deleteById(Integer id);
    
    /**
     * 批量逻辑删除会员
     */
    @Update({
        "<script>",
        "UPDATE vip_name SET status = 1",
        "WHERE id IN",
        "<foreach collection='list' item='item' open='(' separator=',' close=')'>",
        "#{item}",
        "</foreach>",
        "</script>"
    })
    int batchDelete(List<Integer> list);
    /**
     * 修改会员信息
     * @param username
     * @return
     */
    @Update("UPDATE vip_name SET username = #{username}, gender = #{gender}, email = #{email}, address = #{address} WHERE id = #{id}")
    int update(VipName vipName);
    /**
     * 模糊查询会员
     */
    @Select("SELECT * FROM vip_name WHERE status = 0 AND username LIKE CONCAT('%', #{username}, '%')" )
    List<VipName> search(@Param("username") String username);
}