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
        try {
            int result = articleListMapper.add(articleListVO);
            if (result > 0){
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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


}