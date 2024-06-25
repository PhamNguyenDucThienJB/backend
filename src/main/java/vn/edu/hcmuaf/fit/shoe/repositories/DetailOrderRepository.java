package vn.edu.hcmuaf.fit.shoe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shoe.entity.DetailOrder;
import vn.edu.hcmuaf.fit.shoe.entity.DetailOrderId;

public interface DetailOrderRepository extends JpaRepository<DetailOrder, DetailOrderId> {
//    void deleteByOrderOrderId(int id);
//    void deleteByOrder_OrderId(int orderId);
}
