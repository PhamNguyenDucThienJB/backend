package vn.edu.hcmuaf.fit.shoe.utils;

import vn.edu.hcmuaf.fit.shoe.entity.DetailOrder;
import vn.edu.hcmuaf.fit.shoe.entity.DetailOrderId;
import vn.edu.hcmuaf.fit.shoe.entity.Order;
import vn.edu.hcmuaf.fit.shoe.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class UtilityDetailOrder {

    public DetailOrder toEntity(Product p , Order order){
        DetailOrder detailOrder = new DetailOrder() ;
        DetailOrderId detailOrderId = new DetailOrderId();
        detailOrderId.setOrderId(order.getIdOrder());
        detailOrderId.setProductId(p.getIdProduct());
        detailOrder.setId(detailOrderId);
        detailOrder.setOrder(order);
        detailOrder.setProduct(p);
        detailOrder.setQuantity(p.getQuantity());
        return detailOrder ;
    }
}
