package cn.lanqiao.sixgroupsfinalsystem.service.impl;

import cn.lanqiao.sixgroupsfinalsystem.mapper.ArticleListMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.ArticleList;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.ArticleListVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ArticleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.List;

@Service
public class ArticleListServiceImpl implements ArticleListService {

    @Autowired
    private ArticleListMapper articleListMapper;

    /**
     * 新增
     * @param articleListVO
     * @return
     */
    @Override
    public int add(ArticleListVO articleListVO) {
        if (articleListVO == null) {
            throw new IllegalArgumentException("ArticleListVO cannot be null");
        }
        return articleListMapper.add(articleListVO);
    }
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<ArticleList> selectAll() {
        List<ArticleList> articleLists = articleListMapper.slectAll();
        if(articleLists != null ){
            return articleLists;
        }else {
            return  null;
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
        try {
            int result = articleListMapper.delete(id);
            if (result > 0){
                //添加成功
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ArticleList> selectByLike(String title) {
        try {
            List<ArticleList> articleLists = articleListMapper.selectByLike(title);
            if (articleLists != null){
                //查询到数据，返回
                return articleLists;
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public int batchDelete(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0; // 返回0表示没有删除
        }
        return articleListMapper.batchDelete(ids); // 直接调用一次
    }

    /**
     * 改
     * @param articleListVO
     * @return
     */
    @Override
    public int update(ArticleListVO articleListVO) {
        try {
            int result = articleListMapper.update(articleListVO);
            if (result > 0){
                //操作成功 result > 0
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询单个文章
     * @param id
     * @return
     */
    @Override
    public ArticleList selectById(long id) {
        try {
            ArticleList articleList = articleListMapper.selectById(id);
            if (articleList != null){
                //查询到数据，返回
                return articleList;
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}