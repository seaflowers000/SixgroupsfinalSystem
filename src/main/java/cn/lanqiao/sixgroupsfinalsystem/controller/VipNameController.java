package cn.lanqiao.sixgroupsfinalsystem.controller;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;

import cn.lanqiao.sixgroupsfinalsystem.service.impl.VipNameServiceImpl;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vipName")
@CrossOrigin
public class VipNameController {
    @Autowired
    private VipNameServiceImpl vipNameServiceImpl;

    /**
     * 会员列表查询所有
     * @return
     */
    @GetMapping("/select")
    public ResponseUtils select(){
        try {
            List<VipName> vipNameMappers = vipNameServiceImpl.selectAll();
            // System.out.println("查询结果：" + vipNameMappers);
            
            if(vipNameMappers != null && !vipNameMappers.isEmpty()){
                return new ResponseUtils(200, "查询成功", vipNameMappers);
            } else {
                return new ResponseUtils(0, "暂无数据", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "查询失败: " + e.getMessage());
        }
    }
    /**
     * 添加会员
     * @return
     */
    @PostMapping("/add")
    public ResponseUtils<String> add(@RequestBody VipName vipName) {
        try {
            // 参数验证
            if (vipName == null || StringUtils.isEmpty(vipName.getUsername())) {
                return new ResponseUtils<>(400, "用户名不能为空", null);
            }
            
            // 设置默认状态
            vipName.setStatus(0);
            
            boolean result = vipNameServiceImpl.add(vipName);
            if (result) {
                return new ResponseUtils<>(200, "添加成功", null);
            } else {
                return new ResponseUtils<>(500, "添加失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "添加失败: " + e.getMessage(), null);
        }
    }
    /**
     * 删除单个会员
     */

    @PostMapping("/delete")
    public ResponseUtils<String> delete(@RequestBody Map<String, Integer> params) {
        try {
            Integer id = params.get("id");
            if (id == null) {
                return new ResponseUtils<>(400, "参数错误", null);
            }
            boolean result = vipNameServiceImpl.deleteById(id);
            if (result) {
                return new ResponseUtils<>(200, "删除成功", null);
            } else {
                return new ResponseUtils<>(500, "删除失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "删除失败: " + e.getMessage(), null);
        }
    }

    /**
     * 批量删除会员
     */
    @PostMapping("/batchDelete")
    public ResponseUtils<String> batchDelete(@RequestBody Map<String, List<Integer>> params) {
        try {
            List<Integer> ids = params.get("ids");
            if (ids == null || ids.isEmpty()) {
                return new ResponseUtils<>(400, "参数错误", null);
            }
            boolean result = vipNameServiceImpl.batchDelete(ids);
            if (result) {
                return new ResponseUtils<>(200, "批量删除成功", null);
            } else {
                return new ResponseUtils<>(500, "批量删除失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "批量删除失败: " + e.getMessage(), null);
        }
    }
    /**
     * 修改会员信息
     */
    @PostMapping("/update")
    public ResponseUtils<String> update(@RequestBody VipName vipName) {
        boolean result = vipNameServiceImpl.update(vipName);
        if (result) {
            return new ResponseUtils<>(200, "修改成功", null);
        } else {
            return new ResponseUtils<>(500, "修改失败", null);
        }
    }
    /**
     * 模糊查询会员
     */
    @PostMapping("/likeSelect")
    public ResponseUtils<List<VipName>> search(@RequestBody Map<String, Object> params) {
        try {
            String username = (String) params.get("username");
            List<VipName> result = vipNameServiceImpl.search(username);
            return new ResponseUtils<>(200, "模糊查询成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "模糊查询失败: " + e.getMessage(), null);
        }
    }
}

