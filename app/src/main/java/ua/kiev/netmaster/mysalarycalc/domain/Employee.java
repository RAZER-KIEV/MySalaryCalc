package ua.kiev.netmaster.mysalarycalc.domain;

import java.util.Date;

/**
 * Created by ПК on 19.10.2015.
 */
public class Employee {
    private Integer id;
    private Post post;
    private String name;
    private Integer rate;
    private String startDay;
    private String inn;
    private String email;
    private String phone;
    private String comment;

    public Employee() {
    }

    public Employee(int id, String name, int rate, Date startDay, String inn, String email, String phone, String comment, Post post) {

        this.id = id;
        this.name = name;
        this.rate = rate;
        this.startDay = startDay.toString();
        this.inn = inn;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", post=" + post +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", startDay='" + startDay + '\'' +
                ", inn='" + inn + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
