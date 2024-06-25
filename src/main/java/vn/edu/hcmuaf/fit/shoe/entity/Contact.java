package vn.edu.hcmuaf.fit.shoe.entity;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id ;
    private String title ;
    private String content ;
    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer cus;

    public Contact() {
    }

    public Contact(String title, String content, Customer customer) {
        this.title = title;
        this.content = content;
        this.cus = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Customer getCustomer() {
        return cus;
    }

    public void setCustomer(Customer customer) {
        this.cus = customer;
    }
}
