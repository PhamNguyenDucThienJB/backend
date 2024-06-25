package vn.edu.hcmuaf.fit.shoe.controllers;

import vn.edu.hcmuaf.fit.shoe.entity.Order;
import vn.edu.hcmuaf.fit.shoe.dto.OrderCustomer;
import vn.edu.hcmuaf.fit.shoe.dto.ResponseObject;
import vn.edu.hcmuaf.fit.shoe.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*" , maxAge = 3600)
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/order/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return ResponseEntity.ok().body(order);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(Order order) {
        Optional<Order> optionalOrder = orderService.findById(order.getIdOrder());
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

            Order updatedOrder = orderService.createOrder(existingOrder);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        System.out.println("Deleting order with ID: " + id); // Thêm dòng này để kiểm tra xem phương thức có được gọi không
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }



    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> order(@RequestBody OrderCustomer orderCustomer){
        orderService.order(orderCustomer);
       return ResponseEntity.status(HttpStatus.OK)
               .body(new ResponseObject("oke" , "")) ;
    }
}
