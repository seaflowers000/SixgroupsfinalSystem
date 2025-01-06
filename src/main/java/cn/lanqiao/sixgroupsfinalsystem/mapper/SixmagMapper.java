package cn.lanqiao.sixgroupsfinalsystem.mapper;


import cn.lanqiao.sixgroupsfinalsystem.model.dto.SixmagLogin;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SixmagMapper {

    @Insert("insert into manager values (null,#{loginName},#{password},'游客',0)")
    int register(Manager manager);

    @Select("select * from manager  where  login_name= #{loginName} and password=#{password} and status=0")
    Manager login(SixmagLogin manager);
}