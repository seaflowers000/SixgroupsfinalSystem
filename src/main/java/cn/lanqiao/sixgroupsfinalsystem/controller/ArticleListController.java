package cn.lanqiao.sixgroupsfinalsystem.controller;

import cn.lanqiao.sixgroupsfinalsystem.model.pojo.ArticleList;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.ArticleListVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ArticleListService;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleListController {

    @Autowired
    private ArticleListService articleListService;

    /**
     * 列表新增
     * @param articleListVO
     * @return
     */
    @RequestMapping("/add")
    public ResponseUtils add(@RequestBody ArticleListVO articleListVO){
        try {
            // 修改这一行：使用注入的实例调用方法
            int result = articleListService.add(articleListVO);  // 改用实例方法调用
            if (result == 1){
                return new ResponseUtils(200,"新增成功");
            }else {
                return new ResponseUtils(500,"新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(400,"新增异常");
        }
    }

    /**
     * 列表查询所有
     * @return
     */
    @GetMapping("/select")
    public ResponseUtils select(){
        try {
            List<ArticleList> articleLists = articleListService.selectAll();
            System.out.println("查询结果：" + articleLists);

            if(articleLists != null && !articleLists.isEmpty()){
                return new ResponseUtils(200, "查询成功", articleLists);
            } else {
                return new ResponseUtils(0, "暂无数据", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "查询失败: " + e.getMessage());
        }
    }
    /**
     * 分类查询所有
     */

    /**
     * 分类添加
     *
     */
}