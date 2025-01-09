package cn.lanqiao.sixgroupsfinalsystem.mapper;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.ArticleList;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.ArticleListVO;
import org.apache.ibatis.annotations.*;
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
    @Insert("INSERT INTO articleList(title, author, publisher, publish_date, kind) " +
            "VALUES(#{title}, #{author}, #{publisher},NOW(),#{kind})")
    int add(ArticleListVO articleListVO);
    /**
     * 查询功能
     */
    @Select("select * from articleList")
    List<ArticleList> slectAll();
    /**
     *删除
     *
     * */
    @Delete("DELETE FROM articleList WHERE id = #{id}")
    int delete(long id);
    /**
     *模糊查询
     */
    @Select(" SELECT * FROM articleList where title like concat('%',#{title},'%')")
    List<ArticleList> selectByLike(String title);
    /**
     * 批量删除
     */
    @Delete("<script>" +
            "DELETE FROM articleList WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int batchDelete(@Param("ids") List<Long> ids);
    /**
     * 改
     */
    @Update("UPDATE articleList SET title = #{title}, author = #{author}, " +
            "publisher = #{publisher}, publish_date = #{publishDate}, " +
            "kind = #{kind} WHERE id = #{id}")  // 添加 WHERE 条件
    int update(ArticleListVO articleListVO);
    /**
     * 查询单个文章
     */
    @Select("select * from articleList where id = #{id}")
    ArticleList selectById(long id);
}