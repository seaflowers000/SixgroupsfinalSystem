package cn.lanqiao.sixgroupsfinalsystem.controller;

import ch.qos.logback.core.model.Model;
import cn.lanqiao.sixgroupsfinalsystem.mapper.ManagerMapper;
import cn.lanqiao.sixgroupsfinalsystem.model.entity.ManagerSearch;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.Manager;
import cn.lanqiao.sixgroupsfinalsystem.model.pojo.VipName;
import cn.lanqiao.sixgroupsfinalsystem.model.vo.MagVO;
import cn.lanqiao.sixgroupsfinalsystem.service.ManagerService;
import cn.lanqiao.sixgroupsfinalsystem.service.VipNameService;
import cn.lanqiao.sixgroupsfinalsystem.service.impl.ManagerServiceImpl;
import cn.lanqiao.sixgroupsfinalsystem.utils.ImportExcelUtils;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResponseUtils;
import cn.lanqiao.sixgroupsfinalsystem.utils.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
@RestController
@RequestMapping("/mang")
public class ManagerController {
    @Autowired
    private ManagerServiceImpl managerService;
    @Resource
    private ManagerMapper managerMapper;
//    @GetMapping("/select")
//    public ResponseUtils select(){
//        try {
//            List<MagVO> magVOS = managerService.selectAll();
//            System.out.println("查询结果：" + magVOS);
//
//            if(magVOS != null && !magVOS.isEmpty()){
//                return new ResponseUtils(200, "查询成功", magVOS);
//            } else {
//                return new ResponseUtils(0, "暂无数据", new ArrayList<>());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseUtils(500, "查询失败: " + e.getMessage());
//        }
//    }
//    Model model
    @RequestMapping("/getAllUserByCon")
    public ResponseUtils getAllUserByCon(@RequestBody ManagerSearch managerSearch) {
        try {
            // 设置默认值
            if (managerSearch.getPageNo() == null) {
                managerSearch.setPageNo(1);
            }
            if (managerSearch.getPageSize() == null) {
                managerSearch.setPageSize(7);
            }
            
            // 分页对象
            Page<Manager> page = new Page<>(managerSearch.getPageNo(), managerSearch.getPageSize());
            
            // 条件构造器
            QueryWrapper<Manager> wrapper = new QueryWrapper<>();
            
            // 添加状态条件，只查询未删除的记录
            wrapper.ne("status", 1);
            
            // 添加其他查询条件
            if(managerSearch.getLoginName() != null && !managerSearch.getLoginName().equals("")) {
                wrapper.like("login_name", managerSearch.getLoginName());
            }
            if(managerSearch.getRole() != null) {
                wrapper.eq("role_id", managerSearch.getRole());
            }

            // 执行分页查询
            Page<Manager> result = managerMapper.selectPage(page, wrapper);
            
            // 返回分页数据
            Map<String, Object> data = new HashMap<>();
            data.put("records", result.getRecords());
            data.put("total", result.getTotal());
            data.put("current", result.getCurrent());
            data.put("pages", result.getPages());
            data.put("size", result.getSize());
            
            return new ResponseUtils(200, "查询成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "查询失败: " + e.getMessage());
        }
    }
    @PostMapping("/addMag")
    public ResponseUtils addMag(@RequestBody Manager manager) {
        try {
            // 添加日志
            System.out.println("接收到的数据：" + manager);
            
            // 验证必填字段
            if (manager.getLoginName() == null || manager.getLoginName().trim().isEmpty()) {
                return new ResponseUtils(500, "登录名不能为空", null);
            }
            if (manager.getPassword() == null || manager.getPassword().trim().isEmpty()) {
                return new ResponseUtils(500, "密码不能为空", null);
            }
            
            // 设置默认值
            manager.setJoinTime(new Timestamp(System.currentTimeMillis()));
            manager.setStatus(0);
            
            // 执行插入操作
            int result = managerMapper.insert(manager);
            System.out.println("插入结果：" + result); // 添加日志
            
            if (result > 0) {
                return new ResponseUtils(200, "添加成功", null);
            } else {
                return new ResponseUtils(500, "添加失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "添加失败: " + e.getMessage());
        }
    }
    @PostMapping("/updateUser")
    public ResponseUtils updateUser(@RequestBody Manager manager){
        try {
            int i = managerMapper.updateById(manager);
            if(i == 1){
                return new ResponseUtils(200, "更新成功", null);
            } else {
                return new ResponseUtils(500, "更新失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils(500, "更新失败: " + e.getMessage(), null);
        }
    }
    @PostMapping("/delete")
    public ResponseUtils<String> delete(@RequestBody Map<String, Integer> params) {
        try {
            Integer id = params.get("id");
            if (id == null) {
                return new ResponseUtils<>(400, "参数错误", null);
            }
            boolean result = managerService.deleteById(id);
            if (result) {
                return new ResponseUtils<>(200, "删除成功", null);
            } else {
                return new ResponseUtils<>(500, "删除失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "删除失败: " + e.getMessage(), null);
        }
    }
    @PostMapping("/batchDelete")
    public ResponseUtils<String> batchDelete(@RequestBody List<Integer> ids) {
        System.out.println(ids);
        try {
            if (ids == null || ids.isEmpty()) {
                return new ResponseUtils<>(400, "参数错误", null);
            }

            // 调用 Mapper 的批量删除方法
            int result = managerMapper.batchDelete(ids);

            if (result > 0) {
                return new ResponseUtils<>(200, "批量删除成功", null);
            } else {
                return new ResponseUtils<>(500, "批量删除失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "批量删除失败: " + e.getMessage(), null);
        }
    }
    /*** 模糊查询*/
    @PostMapping("/likeSelect")
    public ResponseUtils<List<Manager>> search(@RequestBody Map<String, Object> params) {
        try {
            String loginname = (String) params.get("loginname");
            List<Manager> result = managerService.likeselect(loginname);
            return new ResponseUtils<>(200, "模糊查询成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "模糊查询失败: " + e.getMessage(), null);
        }
    }

    @RequestMapping("/downloadTemplate")
    public ResponseEntity<byte[]> downloadTemplate() throws IOException {

        //1.获取到文件模版资源
        //在resources目录放入模版资源，注意项目导出后resource中的文件被打包到/WEB-INF/classes下,服务器的真实路径
        org.springframework.core.io.Resource resource = new ClassPathResource("管理员列表数据模板.xls");
        // 2. 读取文件内容
        byte[] fileContent = resource.getInputStream().readAllBytes();

        // 3. 设置文件名（使用UTF-8编码）
        String fileName = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8.toString());

        // 4. 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        // 5. 返回响应实体
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(fileContent);
    }
    @RequestMapping("/import")
    //Mysql 事务
    @Transactional(rollbackFor = Exception.class)
    public ResponseUtils fileImport(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws ServletException, IOException { //这行代码是用来接收前端发送过来的文件
        try {
            //这个集合是用来存储文件中的数据，方便我们后期添加到数据库中
            List<Manager> studentList = new ArrayList<>();
            //判断文件是否为空
            if (file.isEmpty()) {
                return new ResponseUtils(500,"文件不能为空");
            }
            // 获取文件名
            Part part = req.getPart("file");
            String submittedFileName = part.getSubmittedFileName();
            System.out.println("上传的文件名:"+submittedFileName);
            //获取文件当前的输入流
            InputStream inputStream = part.getInputStream();
            //读取excel中的数据
            Workbook workbook = ImportExcelUtils.getWorkbookByInputStream(inputStream,submittedFileName);
            //获取工作表
            Sheet sheet  = ImportExcelUtils.getSheetByWorkbook(workbook, 0);
            if (sheet.getRow(1000) != null){
                throw new RuntimeException("系统已限制单批次导入必须小于或等于1000笔!");
            }
            int rowNum = 0;//已取值的行数
            int colNum = 0;//列号
            //获取数据行数
            int realRowCount = sheet.getPhysicalNumberOfRows();
            for (Row row : sheet){
                if (realRowCount == rowNum){
                    //excel文件所有的行读取完毕，结束循环
                    break;
                }
                if (ImportExcelUtils.isBlankRow(row)){//空行跳过
                    continue;
                }
                if (row.getRowNum() == -1){
                    continue;
                }else {
                    if (row.getRowNum() == 0 || row.getRowNum() == 1){//第一行，第二行表头跳过
                        continue;
                    }
                }
                rowNum++;
                colNum = 1;
                Manager student = new Manager();
                //验证Excel文件字段，这里根据表格内名称
                ImportExcelUtils.validCellValue(sheet,row,colNum,"管理员编号");
                student.setId(Integer.valueOf(ImportExcelUtils.getCellValue(sheet,row,colNum-1)));

                ImportExcelUtils.validCellValue(sheet,row,++colNum,"管理员密码");
                student.setPassword(ImportExcelUtils.getCellValue(sheet,row,colNum-1));
                //列号需要自加获取
                ImportExcelUtils.validCellValue(sheet,row,++colNum,"管理员姓名");
                student.setLoginName(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                ImportExcelUtils.validCellValue(sheet,row,++colNum,"邮箱");
                student.setEmail(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                ImportExcelUtils.validCellValue(sheet,row,++colNum,"职能");
                student.setRole(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                ImportExcelUtils.validCellValue(sheet,row,++colNum,"加入时间");
                student.setJoinTime(Timestamp.valueOf(ImportExcelUtils.getCellValue(sheet,row,colNum-1)));

                ImportExcelUtils.validCellValue(sheet,row,++colNum,"联系电话");
                student.setPhone(ImportExcelUtils.getCellValue(sheet,row,colNum-1));

                ImportExcelUtils.validCellValue(sheet,row,++colNum,"状态(0未删除,1已删除)");
                student.setStatus(Integer.valueOf(ImportExcelUtils.getCellValue(sheet,row,colNum-1)));

                //存储对象到list集合中
                studentList.add(student);
            }
            System.out.println("===================导入的数据是=================");
            for (Manager student : studentList) {
                System.out.println(student);
//                //在这里能够拿到我想要的两个参数
//                TClass tClass = tClassService.selectClassno(student.getCname(), student.getClassroom());
//                student.setClassno(tClass.getClassno());
//                //需要添加到数据库中
            managerService.insertSelective(student);
            }
            return new ResponseUtils<>(200,"导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
