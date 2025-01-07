package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VipNameDelService {
    /**
     * 查询所有会员
     */
    List<VipName> selectAll();

    /**
     * 恢复会员
     */
    boolean deleteById(Integer id);

    /**
     * 批量恢复会员
     */
    boolean batchDelete(List<Integer> ids);

    /**
     * 模糊查询会员
     */
    List<VipName> search(String username);
}