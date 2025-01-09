package cn.lanqiao.sixgroupsfinalsystem.service;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.ArticleList;

import cn.lanqiao.sixgroupsfinalsystem.model.vo.ArticleListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    /**
     * 删除
     */
    int delete(long id);
    /**
     *模糊查询
     */
    List<ArticleList> selectByLike(String title);
    /**
     * 批量删除
     */
    int batchDelete(@Param("ids") List<Long> ids);
    /**
     * 改
     */
    int update(ArticleListVO articleListVO);
    /**
     * 查询单个文章
     */
    ArticleList selectById(long id);


}