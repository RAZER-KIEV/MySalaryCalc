package ua.kiev.netmaster.mysalarycalc.domain;

import java.util.Date;

/**
 * Created by ПК on 24.10.2015.
 */
public class Salary {
    private Integer id;
    private Integer emplId;
    private Integer dayCon;
    private Integer eveningCon;
    private Integer cableInst;
    private Integer boxesInst;
    private Integer otherTasks;
    private Integer rate;
    private Integer summary;
    private String date;
    private String comment;

    public Salary() {
    }

    public Salary(Integer id, Integer emplId, Integer dayCon, Integer eveningCon, Integer cableInst, Integer boxesInst, Integer otherTasks, Integer rate, Integer summary, Date date, String comment) {
        this.id = id;
        this.emplId = emplId;
        this.dayCon = dayCon;
        this.eveningCon = eveningCon;
        this.cableInst = cableInst;
        this.boxesInst = boxesInst;
        this.otherTasks = otherTasks;
        this.rate = rate;
        this.summary = summary;
        this.date = date.toString();
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmplId() {
        return emplId;
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public Integer getDayCon() {
        return dayCon;
    }

    public void setDayCon(Integer dayCon) {
        this.dayCon = dayCon;
    }

    public Integer getEveningCon() {
        return eveningCon;
    }

    public void setEveningCon(Integer eveningCon) {
        this.eveningCon = eveningCon;
    }

    public Integer getCableInst() {
        return cableInst;
    }

    public void setCableInst(Integer cableInst) {
        this.cableInst = cableInst;
    }

    public Integer getBoxesInst() {
        return boxesInst;
    }

    public void setBoxesInst(Integer boxesInst) {
        this.boxesInst = boxesInst;
    }

    public Integer getOtherTasks() {
        return otherTasks;
    }

    public void setOtherTasks(Integer otherTasks) {
        this.otherTasks = otherTasks;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getSummary() {
        return summary;
    }

    public void setSummary(Integer summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", emplId=" + emplId +
                ", dayCon=" + dayCon +
                ", eveningCon=" + eveningCon +
                ", cableInst=" + cableInst +
                ", boxesInst=" + boxesInst +
                ", otherTasks=" + otherTasks +
                ", rate=" + rate +
                ", summary=" + summary +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
