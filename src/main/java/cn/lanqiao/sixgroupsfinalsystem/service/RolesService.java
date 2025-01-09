package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Roles;

import java.util.List;

public interface RolesService {
    List<Roles> searchRoles(String roleName);
    boolean addRole(Roles role);
    boolean deleteRole(Integer id);
}