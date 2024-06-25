package vn.edu.hcmuaf.fit.shoe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "status")
    private String status;
    @Column(name = "priceSale")
    private int priceSale;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private int quantity;

    @JoinColumn(name = "branch_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch;
    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Image> listImage;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<DetailOrder> listDetailProduct;

    public Product() {

    }

    public Product(String name, int price, String status, int priceSale, String thumbnail, String description, Branch branch, List<Image> listImage, List<DetailOrder> listDetailProduct, int quantity) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.priceSale = priceSale;
        this.thumbnail = thumbnail;
        this.description = description;
        this.branch = branch;
        this.listImage = listImage;
        this.listDetailProduct = listDetailProduct;
        this.quantity = quantity;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Image> getListImage() {
        return listImage;
    }

    public void setListImage(List<Image> listImage) {
        this.listImage = listImage;
    }

    public List<DetailOrder> getListDetailProduct() {
        return listDetailProduct;
    }

    public void setListDetailProduct(List<DetailOrder> listDetailProduct) {
        this.listDetailProduct = listDetailProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }


}
