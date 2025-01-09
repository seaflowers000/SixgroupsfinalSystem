package cn.lanqiao.sixgroupsfinalsystem.mapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Roles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RolesMapper {
    @Select("<script>" +
            "SELECT * FROM roles " +
            "WHERE 1=1 " +
            "<if test='roleName != null and roleName.trim() != \"\"'> " +
            "AND role_name LIKE CONCAT('%', #{roleName}, '%') " +
            "</if>" +
            "</script>")
    List<Roles> searchRoles(String roleName);

    @Insert("INSERT INTO roles (role_name, permission_rules, description, status) VALUES (#{roleName}, #{permissionRules}, #{description}, #{status})")
    int addRole(Roles role);
    @Delete("DELETE FROM roles WHERE id = #{id}")
    int deleteRole(Integer id);
}