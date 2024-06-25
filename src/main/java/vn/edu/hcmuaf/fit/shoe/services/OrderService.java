package vn.edu.hcmuaf.fit.shoe.services;

import vn.edu.hcmuaf.fit.shoe.entity.Customer;
import vn.edu.hcmuaf.fit.shoe.entity.DetailOrder;
import vn.edu.hcmuaf.fit.shoe.dto.OrderCustomer;
import vn.edu.hcmuaf.fit.shoe.repositories.DetailOrderRepository;
import vn.edu.hcmuaf.fit.shoe.repositories.OrderRepository;
import vn.edu.hcmuaf.fit.shoe.utils.UtilityDetailOrder;
import vn.edu.hcmuaf.fit.shoe.utils.UtilityOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.shoe.entity.Order;
import vn.edu.hcmuaf.fit.shoe.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository ;
    @Autowired
    UtilityOrder utilityOrder ;
    @Autowired
    DetailOrderRepository detailOrderRepository;
    @Autowired
    UtilityDetailOrder detailOrder ;
    @Autowired
    CustomerService service ;
    @Transactional

    // thuc hien 2 thao tac luu vao db do la : luu order va luu detail order
    // Transactional dung de roll back neu 1 trong 2 thao tac xay ra loi trong qua trinh save
    public void order(OrderCustomer orderCustomer){
       Order o = utilityOrder.toEntity(orderCustomer) ;
       Order or = orderRepository.save(o) ;
       for(Product p : orderCustomer.getListOrder()){
           DetailOrder detail = detailOrder.toEntity(p,or) ;
           detailOrderRepository.save(detail) ;
       }


    }
    public List<Order> findOrderByCustomer(int id){
        Customer cus = service.findCustomer(id);
       return orderRepository.findAllByCustomer(cus);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        Optional<Order> result = orderRepository.findById(id);
        Order order = null;
        if(result.isPresent()) {
            order = result.get();
        } else {
            throw new RuntimeException("Không tìm thấy đơn hàng với id = " + id);
        }
        return order;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(order.getIdOrder());
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setPriceOrder(order.getPriceOrder());
            existingOrder.setCustomer(order.getCustomer());
            existingOrder.setTimestamp(order.getTimestamp());
            existingOrder.setStatus(order.getStatus());
            existingOrder.setAddress(order.getAddress());
            existingOrder.setNote(order.getNote());
            existingOrder.setCompany(order.getCompany());
            existingOrder.setPhone(order.getPhone());
            existingOrder.setListDetailOrder(order.getListDetailOrder());
            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }
    public void deleteOrder_detail(int id) {
//       DetailOrder detailoder_id = getOrderById(id);
    }

    public void deleteOrder(int id) {
        try {
            System.out.println("Attempting to delete order with ID: " + id);
            // Xóa các bản ghi liên quan trong bảng detail_order trước
//            detailOrderRepository.deleteByOrderOrderId(id);
            orderRepository.deleteById(id);
            System.out.println("Order with ID: " + id + " deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting order with ID: " + id);
            e.printStackTrace();
        }
    }
    public Optional<Order> findById(int idOrder) {
        return orderRepository.findById(idOrder);
    }
}
