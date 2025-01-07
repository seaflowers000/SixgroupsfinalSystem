package cn.lanqiao.sixgroupsfinalsystem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVO {
    private long id;
    private String title;
    private String author;
    private String publisher;
    private java.sql.Date publishDate;

}