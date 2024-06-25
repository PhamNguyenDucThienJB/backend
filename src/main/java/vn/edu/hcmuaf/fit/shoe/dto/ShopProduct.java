package vn.edu.hcmuaf.fit.shoe.dto;

import java.util.ArrayList;

public class ShopProduct {
    private int itemProduct ;
    private int page;
    private ArrayList<FilterCriteria> list ;
    private String sort ;

    public int getItemProduct() {
        return itemProduct;
    }

    public void setItemProduct(int itemProduct) {
        this.itemProduct = itemProduct;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<FilterCriteria> getList() {
        return list;
    }

    public void setList(ArrayList<FilterCriteria> list) {
        this.list = list;
    }
    public String getSort(){
        return this.sort ;
    }
    public void setSort(String sort){
        this.sort = sort ;
    }

    @Override
    public String toString() {
        return "ShopProduct{" +
                "itemProduct=" + itemProduct +
                ", page=" + page +
                ", list=" + list.toString() +
                ", sort='" + sort + '\'' +
                '}';
    }
}
