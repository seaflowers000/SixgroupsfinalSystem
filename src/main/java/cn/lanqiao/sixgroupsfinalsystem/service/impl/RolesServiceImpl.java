package cn.lanqiao.sixgroupsfinalsystem.service.impl;

import cn.lanqiao.sixgroupsfinalsystem.mapper.RolesMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Roles;
import cn.lanqiao.sixgroupsfinalsystem.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public List<Roles> searchRoles(String roleName) {
        System.out.println("Searching roles with roleName: " + roleName); // 添加日志
        return rolesMapper.searchRoles(roleName);
    }

    @Override
    public boolean addRole(Roles role) {
        return rolesMapper.addRole(role) > 0; // 返回插入的行数
    }

    @Override
    public boolean deleteRole(Integer id) {
        return rolesMapper.deleteRole(id) > 0; // 返回删除的行数
    }
}