package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.ArticleList;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.ArticleListVO;

import java.util.AbstractList;
import java.util.List;

public interface ArticleListService {
    /**
     * 新增
     * @param articleListVO
     * @return
     */
    int add(ArticleListVO articleListVO);
/**
 *
 * 查询
 */
List<ArticleList> selectAll();
}