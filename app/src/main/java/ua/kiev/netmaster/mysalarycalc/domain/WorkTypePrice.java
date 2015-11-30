package ua.kiev.netmaster.mysalarycalc.domain;

/**
 * Created by ПК on 24.10.2015.
 */
public class WorkTypePrice {
    private Integer id;
    private String typeOfWork;
    private Double price;
    private String coment;

    public WorkTypePrice() {
    }

    public WorkTypePrice(Integer id, String typeOfWork, Double price, String coment) {
        this.id = id;
        this.typeOfWork = typeOfWork;
        this.price = price;
        this.coment = coment;
    }

    public WorkTypePrice(String typeOfWork, Double price, String coment) {
        this.typeOfWork = typeOfWork;
        this.price = price;
        this.coment = coment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    @Override
    public String toString() {
        return "WorkTypePrice{" +
                "id=" + id +
                ", typeOfWork='" + typeOfWork + '\'' +
                ", price=" + price +
                ", coment='" + coment + '\'' +
                '}';
    }
}
