package cn.lanqiao.sixgroupsfinalsystem.service;



import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;

import java.util.List;

public interface VipNameService {
    /**
     * 查询所有会员列表
     */

    List<VipName> selectAll();
        /**
     * 根据ID删除会员
     */
    boolean deleteById(Integer id);
    /**
     * 批量逻辑删除会员
     */
    boolean batchDelete(List<Integer> ids);
}