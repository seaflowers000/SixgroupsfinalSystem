package cn.lanqiao.sixgroupsfinalsystem.mapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.ArticleList;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.ArticleListVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.AbstractList;
import java.util.List;

@Mapper
@Repository
public interface ArticleListMapper {
    /**
    * 新增
    *
     * */
    @Insert("INSERT INTO article_list(title, author, created_at,update_at,kind) " +
            "VALUES(#{title}, #{author}, #{created_at}, NOW(),#{kind})")
    int add(ArticleListVO articleListVO);
    /**
     * 查询功能
     */
    @Select("select * from articleList")
    List<ArticleList> slectAll();

}