package cn.lanqiao.sixgroupsfinalsystem.mapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
    @Select("select * from manager")
    List<MagVO> selectAll();

    @Update("UPDATE manager SET status = 1 WHERE id = #{id}")
    int deleteById(Integer id);

    @Update({
            "<script>", "UPDATE manager SET status = 1", "WHERE id IN", "<foreach collection='list' item='item' open='(' separator=',' close=')'>", "#{item}", "</foreach>", "</script>"})
    int batchDelete(List<Integer> list);
    @Select("SELECT * FROM manager WHERE status = 0 AND login_name LIKE CONCAT('%', #{loginname}, '%')" )
    List<Manager> likeselect(@Param("loginname") String loginname);


    @Insert("    insert into manager(id,password,login_name,phone,role,join_time,status) values(#{id},#{password},#{loginName},#{phone},#{role},#{joinTime},#{status})")
    int insertSelective(Manager record);
}
