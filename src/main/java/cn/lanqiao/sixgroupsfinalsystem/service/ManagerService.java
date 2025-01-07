package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;

import java.util.List;

public interface ManagerService {
    List<MagVO> selectAll();
    boolean batchDelete(List<Integer> ids);
    boolean deleteById(Integer id);


//     模糊查询

    List<Manager> likeselect(String loginname);
}
