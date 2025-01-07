package cn.lanqiao.sixgroupsfinalsystem.service.impl;

import cn.lanqiao.sixgroupsfinalsystem.mapper.VipNameDelMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;


@Service
public class VipNameDelServiceImpl implements VipNameDelService {
    // 依赖注入
    @Autowired
    private VipNameDelMapper vipNameDelMapper;

    /**
     * 查询所有会员
     * @return
     */
    @Override
    public List<VipName> selectAll() {
        final List<VipName> vipNameMappers = vipNameDelMapper.selectAll();
        if(vipNameMappers != null){
            // 有参数返回值
            return vipNameMappers;
        }else{
            return null;
        }
    }

    /**
     * 恢复单个会员
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            int result = vipNameDelMapper.deleteById(id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量逻辑恢复会员
     */
    @Override
    public boolean batchDelete(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return false;
        }
        for (Integer id : ids) {
            vipNameDelMapper.deleteById(id);
        }
        return true;
    }




    /**
     * 模糊查询
     * @param username
     * @return
     */
    @Override
    public List<VipName> search(String username) {
        try {
            return vipNameDelMapper.search(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}