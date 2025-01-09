package cn.lanqiao.sixgroupsfinalsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListLogin {
    private long id;
    private String title;
    private String author;
    private String publisher;
    private java.sql.Date publishDate;
    private String keyword;

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getKeyword() {
        return keyword;
    }
}
