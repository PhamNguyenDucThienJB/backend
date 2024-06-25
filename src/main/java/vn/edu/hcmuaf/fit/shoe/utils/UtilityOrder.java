package vn.edu.hcmuaf.fit.shoe.utils;

import vn.edu.hcmuaf.fit.shoe.entity.Customer;
import vn.edu.hcmuaf.fit.shoe.entity.Order;
import vn.edu.hcmuaf.fit.shoe.dto.OrderCustomer;
import vn.edu.hcmuaf.fit.shoe.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class UtilityOrder {
    @Autowired
    CustomerRepository customer ;
    public Order toEntity(OrderCustomer order){
        Order or = new Order() ;
        or.setPhone(order.getPhone());
        or.setCompany(order.getCompany());
        or.setTimestamp(new Date());
        or.setNote(order.getNote());
        or.setStatus("Đang Chờ Xác Nhận");
        or.setPriceOrder(order.getPriceOrder());
        Customer cus = customer.findById(order.getIdCus());
        or.setCustomer(cus);
        or.setAddress(order.getAddress());

        return  or ;

    }
}
