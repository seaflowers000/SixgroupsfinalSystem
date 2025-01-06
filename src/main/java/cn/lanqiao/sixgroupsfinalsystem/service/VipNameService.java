package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VipNameService {
    /**
     * 查询所有会员
     */
    List<VipName> selectAll();
    
    /**
     * 添加会员
     */
    boolean add(VipName vipName);
    
    /**
     * 删除会员
     */
    boolean deleteById(Integer id);
    
    /**
     * 批量删除会员
     */
    boolean batchDelete(List<Integer> ids);
    
    /**
     * 模糊查询会员
     */
    List<VipName> search(String username);
}