package ua.kiev.netmaster.mysalarycalc.domain;

/**
 * Created by ПК on 24.10.2015.
 */
public class Post {
    private Integer id;
    private String postName;
    private String comment;

    public Post() {
    }

    public Post(Integer id, String postName, String comment) {
        this.id = id;
        this.postName = postName;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
