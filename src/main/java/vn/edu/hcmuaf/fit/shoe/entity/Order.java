package vn.edu.hcmuaf.fit.shoe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int idOrder;
    @Column(name = "price_order")
    private int priceOrder;
    @ManyToOne
    @JoinColumn(name ="customer_id")
    private Customer customer;
    @Column(name = "create_order")
    private Date timestamp;
    @Column(name = "status")
    private String status;
    @Column(name = "address")
    private String address ;
    @Column(name = "note_order")
    private String note;
    @Column(name="company")
    private String company ;
    @Column(name = "phone")
    private String phone;
    @JsonIgnore
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private List<DetailOrder> listDetailOrder;

    public Order() {

    }

    public Order( int priceOrder, Customer customer, Date timestamp, String status, String address, String note,String company,String phone , List<DetailOrder> listDetailOrder) {

        this.priceOrder = priceOrder;
        this.customer = customer;
        this.timestamp = timestamp;
        this.status = status;
        this.address = address;
        this.note = note;
        this.listDetailOrder = listDetailOrder;
        this.company = company ;
        this.phone = phone ;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(int priceOrder) {
        this.priceOrder = priceOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public void setCompany(String company){
        this.company = company ;
    }
    public String getCompany(){
        return company ;
    }
    public void setPhone(String phone){
        this.phone = phone ;
    }
    public String getPhone(){
        return phone ;
    }

    public List<DetailOrder> getListDetailOrder() {
        return listDetailOrder;
    }

    public void setListDetailOrder(List<DetailOrder> listDetailOrder) {
        this.listDetailOrder = listDetailOrder;
    }
}
