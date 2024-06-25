package vn.edu.hcmuaf.fit.shoe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "branch_id")
    private int idBranch ;
    @Column(name = "branch_name")
    private  String nameBranch;
    @JsonIgnore
    @OneToMany(mappedBy = "branch")
    private List<Product> productList ;

    public Branch() {
    }

    public Branch( String nameBranch, List<Product> productList) {
        this.nameBranch = nameBranch;
        this.productList = productList;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
