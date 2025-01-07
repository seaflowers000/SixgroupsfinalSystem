package cn.lanqiao.sixgroupsfinalsystem.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleList {

  private long id;
  private String title;
  private String author;
  private String publisher;
  private String kind;
  private java.sql.Date publishDate;

  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
  public void setKind(String kind) {
    this.kind = kind;
  }
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
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
  public String getKind() {
    return kind;
  }

  public Date getPublishDate() {
    return publishDate;
  }
}
