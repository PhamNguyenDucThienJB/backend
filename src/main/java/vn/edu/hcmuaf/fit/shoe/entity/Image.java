package vn.edu.hcmuaf.fit.shoe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idImage ;

    @Column(name = "link_image")
    private String linkImage ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product ;

    public Image() {
    }

    public Image( String linkImage, Product product) {

        this.linkImage = linkImage;
        this.product = product;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
