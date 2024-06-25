package vn.edu.hcmuaf.fit.shoe.dto;

import vn.edu.hcmuaf.fit.shoe.entity.Product;

import java.util.List;

public class ResponseShopProduct {
    private int itemPerPage ;
    private int itemFiltered ;
    private int pageActive ;
    private List<Product> list ;

    public ResponseShopProduct(int itemPerPage, int itemFiltered, int pageActive, List<Product> list) {
        this.itemPerPage = itemPerPage;
        this.itemFiltered = itemFiltered;
        this.pageActive = pageActive;
        this.list  = list ;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getItemFiltered() {
        return itemFiltered;
    }

    public void setItemFiltered(int itemFiltered) {
        this.itemFiltered = itemFiltered;
    }

    public int getPageActive() {
        return pageActive;
    }

    public void setPageActive(int pageActive) {
        this.pageActive = pageActive;
    }
    public void setList(List<Product> list){
        this.list=list;
    }
    public List<Product> getList(){
        return  this.list ;
    }
}
