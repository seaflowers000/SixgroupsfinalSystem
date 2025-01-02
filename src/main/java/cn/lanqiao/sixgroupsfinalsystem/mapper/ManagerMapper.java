package cn.lanqiao.sixgroupsfinalsystem.mapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
//    @Select("select * from manager")
//    List<MagVO> selectAll();
}
