package cn.lanqiao.sixgroupsfinalsystem.controller;


import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameDelService;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
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
@RequestMapping("/vipNameDel")
@CrossOrigin
public class VipNameDelController {
    @Autowired
    private VipNameDelService vipNameDelService;

    /**
     * 会员列表查询所有
     * @return
     */
    @GetMapping("/select")
    public ResponseUtils select(){
        try {
            List<VipName> vipNameMappers = vipNameDelService.selectAll();
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
     * 恢复单个会员
     */

    @PostMapping("/delete")
    public ResponseUtils<String> delete(@RequestBody Map<String, Integer> params) {
        try {
            Integer id = params.get("id");
            if (id == null) {
                return new ResponseUtils<>(400, "参数错误", null);
            }
            boolean result = vipNameDelService.deleteById(id);
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
     * 批量恢复会员
     */
    @PostMapping("/batchDelete")
    public ResponseUtils<String> batchDelete(@RequestBody Map<String, List<Integer>> params) {
        try {
            List<Integer> ids = params.get("ids");
            if (ids == null || ids.isEmpty()) {
                return new ResponseUtils<>(400, "参数错误", null);
            }
            boolean result = vipNameDelService.batchDelete(ids);
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
     * 模糊查询会员
     */
    @PostMapping("/likeSelect")
    public ResponseUtils<List<VipName>> search(@RequestBody Map<String, Object> params) {
        try {
            String username = (String) params.get("username");
            List<VipName> result = vipNameDelService.search(username);
            return new ResponseUtils<>(200, "模糊查询成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "模糊查询失败: " + e.getMessage(), null);
        }
    }

}

