package cn.lanqiao.sixgroupsfinalsystem.model.vo;

public class ArticleListVO {
    private Long id; // 确保有这个字段
    private String title;
    private String author;
    private String publisher;
    private String publishDate; // 确保这个字段的命名与数据库一致
    private String kind; // 确保这个字段存在

    // Getter 和 Setter 方法
    public Long getId() { // 确保有这个 getter
        return id;
    }

    public void setId(Long id) { // 确保有这个 setter
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getKind() { // 确保有这个 getter
        return kind;
    }

    public void setKind(String kind) { // 确保有这个 setter
        this.kind = kind;
    }
}