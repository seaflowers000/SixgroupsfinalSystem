package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;

import java.util.List;

public interface ManagerService {
    List<MagVO> selectAll();
}
