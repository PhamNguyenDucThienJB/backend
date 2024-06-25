package vn.edu.hcmuaf.fit.shoe.dto;

import vn.edu.hcmuaf.fit.shoe.entity.Product;

import java.util.List;

public class OrderCustomer {

    private int idCus ;
    private String phone ;
    private String note ;
    private  String address;
    private String name ;
    private String company ;
    private int priceOrder ;
    private List<Product> listOrder ;

    public OrderCustomer(int idCus, String phone, String note, String address, String name, String company, int priceOrder, List<Product> listOrder) {
        this.idCus = idCus;
        this.phone = phone;
        this.note = note;
        this.address = address;
        this.name = name;
        this.company = company;
        this.priceOrder = priceOrder;
        this.listOrder = listOrder;
    }

    @Override
    public String toString() {
        return "OrderCustomer{" +
                "idCus=" + idCus +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", priceOrder=" + priceOrder +
                ", listOrder=" + listOrder.toString() +
                '}';
    }

    public int getIdCus() {
        return idCus;
    }

    public void setIdCus(int idCus) {
        this.idCus = idCus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(int priceOrder) {
        this.priceOrder = priceOrder;
    }

    public List<Product> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Product> listOrder) {
        this.listOrder = listOrder;
    }
}
