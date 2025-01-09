package cn.lanqiao.sixgroupsfinalsystem.controller;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Roles;
import cn.lanqiao.sixgroupsfinalsystem.service.RolesService;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    // 查询角色
    @GetMapping("/search")
    public ResponseUtils<List<Roles>> search(@RequestParam(required = false) String roleName) {
        System.out.println("Received roleName: " + roleName); // 添加日志
        List<Roles> roles = rolesService.searchRoles(roleName);
        return new ResponseUtils<>(200, "查询成功", roles);
    }

    @PostMapping("/add")
    public ResponseUtils<String> addRole(@RequestBody Roles role) {
        boolean success = rolesService.addRole(role);
        if (success) {
            return new ResponseUtils<>(200, "角色添加成功", null);
        } else {
            return new ResponseUtils<>(500, "角色添加失败", null);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseUtils<String> deleteRole(@PathVariable Integer id) {
        boolean success = rolesService.deleteRole(id);
        if (success) {
            return new ResponseUtils<>(200, "角色删除成功", null);
        } else {
            return new ResponseUtils<>(500, "角色删除失败", null);
        }
    }
}