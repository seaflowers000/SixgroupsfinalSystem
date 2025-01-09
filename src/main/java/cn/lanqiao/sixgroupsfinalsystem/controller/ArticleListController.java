package cn.lanqiao.sixgroupsfinalsystem.controller;


import cn.lanqiao.sixgroupsfinalsystem.model.dto.ArticleListLogin;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.ArticleList;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.ArticleListVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ArticleListService;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleListController {

    @Autowired
    private ArticleListService articleListService;

//    @Autowired
//    private ArticleListServiceImpl articleListServiceimpl;

    /**
     * 列表新增
     *
     * @param articleListVO
     * @return
     */
    @RequestMapping("/add")
    public ResponseUtils add(@RequestBody ArticleListVO articleListVO) {
        try {
            // 打印接收到的数据
            System.out.println("接收到的数据：" + articleListVO);
            // 数据验证
            if (articleListVO == null) {
                return new ResponseUtils(400, "数据不能为空");
            }

            int result = articleListService.add(articleListVO);
            if (result == 1) {
                return new ResponseUtils(200, "新增成功");
            } else {
                return new ResponseUtils(500, "新增失败");
            }
        } catch (Exception e) {
            // 打印详细错误信息
            e.printStackTrace();
            return new ResponseUtils(400, "新增异常：" + e.getCause().getMessage());
        }
    }


    /**
     * 列表查询所有
     *
     * @return
     */
    @GetMapping("/select")
    public ResponseUtils select() {
        try {
            List<ArticleList> articleLists = articleListService.selectAll();
            // 添加日志查看返回的数据
            System.out.println("查询结果：" + articleLists);

            if (articleLists != null && !articleLists.isEmpty()) {
                return new ResponseUtils(200, "查询成功", articleLists);
            } else {
                return new ResponseUtils(0, "暂无数据", new ArrayList<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete")  // 修改为POST方法，匹配前端请求
    public ResponseUtils delete(@RequestBody Map<String, Long> params) {  // 接收JSON格式的id
        try {
            Long id = params.get("id");
            if (id == null) {
                return new ResponseUtils(400, "ID不能为空");
            }

            int result = articleListService.delete(id);
            if (result > 0) {
                return new ResponseUtils(200, "删除成功");
            } else {
                return new ResponseUtils(500, "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "删除异常：" + e.getMessage());
        }
    }
//
//    /**
//     * 修改
//     * @param article
//     * @return
//     */
//    @PutMapping("/update")
//    public ResponseUtils update(@RequestBody ArticleList article) {
//        // 添加日志
//        System.out.println("接收到的更新数据：" + article);
//
//        try {
//            // 参数校验
//            if (article.getId() == null) {
//                return new ResponseUtils(400, "ID不能为空");
//            }
//
//            int result = articleListService.update(article);
//            if (result > 0) {
//                return new ResponseUtils(200, "修改成功");
//            } else {
//                return new ResponseUtils(500, "修改失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseUtils(500, "修改失败：" + e.getMessage());
//        }
//    }
    /**
     * 模糊查询
     */
    @RequestMapping("/likeSelect")
    public ResponseUtils slectlByLike(@RequestBody ArticleListLogin articleListLogin){
//        System.out.println(articleListLogin.getKeyword());
//        return null;
        try {
            List<ArticleList> articleLists = articleListService.selectByLike(articleListLogin.getKeyword());
            if (articleLists != null){
                return new ResponseUtils(200,"查询成功",articleLists);
            }else {
                return new ResponseUtils(400,"查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
//}

//    @RequestMapping("/delete{id}")
//    public ResponseUtils delete(@PathVariable("id") Long id){
//        System.out.println(id);
//        System.out.println("1111");
//        return null;
//    }
//
    /**
     * 批量删除
     */
    @PostMapping("/batchDelete")
    public ResponseUtils<String> batchDelete(@RequestBody Map<String, List<Long>> params) {
//        System.out.println(111);
        try {
            List<Long> ids = params.get("ids");
            if (ids == null || ids.isEmpty()) {
                return new ResponseUtils<>(400, "参数错误", null);
            }
            int result = articleListService.batchDelete(ids);
            if (result > 0) {  // 修改这里：检查删除的记录数是否大于0
                System.out.println(111);
                return new ResponseUtils<>(200, "批量删除成功，删除了" + result + "条记录", null);
            } else {
                return new ResponseUtils<>(500, "批量删除失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "批量删除失败: " + e.getMessage(), null);
        }
    }
}
    /**
     * 改
     */



