package vn.edu.hcmuaf.fit.shoe.controllers;

import vn.edu.hcmuaf.fit.shoe.entity.Customer;
import vn.edu.hcmuaf.fit.shoe.dto.FormRegister;
import vn.edu.hcmuaf.fit.shoe.dto.ResponseObject;
import vn.edu.hcmuaf.fit.shoe.services.CustomerService;
import vn.edu.hcmuaf.fit.shoe.services.OrderService;
import vn.edu.hcmuaf.fit.shoe.utils.HashMD5;
import vn.edu.hcmuaf.fit.shoe.utils.RenderOTP;
import vn.edu.hcmuaf.fit.shoe.utils.SendEmail;
import vn.edu.hcmuaf.fit.shoe.utils.UtilityCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UtilityCustomer utilityCustomer;
    @Autowired
    RenderOTP renderOTP;
    @Autowired
    SendEmail sendEmail;
    @Autowired
    OrderService orderService;
    @Autowired
    HashMD5 hashMD5;

    // Get theo List Customer
    @RequestMapping(value = "/allCustomers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Phương thức duy nhất cho cả id và email
    @RequestMapping(value = "/customer/{identifier}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomer(@PathVariable("identifier") String identifier) {
        try {
            int id = Integer.parseInt(identifier);
            return getCustomerById(id);
        } catch (NumberFormatException e) {
            return getCustomerByEmail(identifier);
        }
    }

    private ResponseEntity<Customer> getCustomerById(int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    private ResponseEntity<Customer> getCustomerByEmail(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Thêm
    @RequestMapping(value = "/add/customer", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    // Sửa
//    @RequestMapping(value = "/update/customer", method = RequestMethod.PUT)
//    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
//        Customer currentCustomer = customerService.getCustomerById(id);
//        if (currentCustomer == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        currentCustomer.setEmail(customer.getEmail());
//        currentCustomer.setFirstName(customer.getFirstName());
//        currentCustomer.setLastName(customer.getLastName());
//        currentCustomer.setPhone(customer.getPhone());
//        Customer updatedCustomer = customerService.updateCustomer(currentCustomer);
//        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
//    }
    @PutMapping(value = "/update/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        Customer currentCustomer = customerService.getCustomerById(id);
        if (currentCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setFirstName(customer.getFirstName());
        currentCustomer.setLastName(customer.getLastName());
        currentCustomer.setPhone(customer.getPhone());
        Customer updatedCustomer = customerService.updateCustomer(currentCustomer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }


    // Xóa
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "customer/register", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> register(FormRegister form) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("oke", customerService.saveCustomer(utilityCustomer.toEntity(form)))
        );
    }

    @RequestMapping(value = "/sendOTP", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> sendOTP(@RequestParam("email") String email) {
        System.out.println(!customerService.isExistEmail(email));
        if (customerService.isExistEmail(email)) {
            customerService.updateTimeAndCode(customerService.findByEmail(email), renderOTP.createOTP());
            sendEmail.sendEmail(email, renderOTP.getCode());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("oke", ""));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("NOT_EMAIL", ""));
    }

    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> resetPassWord(@RequestParam("email") String email, @RequestParam("otp") String otp,
                                                        @RequestParam("pass") String pass) {
        if (!customerService.checkTimeSend(email)) return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("CODE_TIME_OUT", ""));
        if (!customerService.checkCode(otp)) return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("INVALID_OTP", "")
        );
        customerService.updatePassWord(pass);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("oke", "")
        );
    }

    @RequestMapping(value = "resetOTP", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> resetOTP(@RequestParam("email") String email) {
        if (customerService.checkCode(email)) return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("NOT_TIME_OUT", ""));
        customerService.updateTimeAndCode(customerService.findByEmail(email), renderOTP.createOTP());
        sendEmail.sendEmail(email, renderOTP.getCode());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("oke", ""));
    }

    @RequestMapping(value = "/findCus", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> findCus(@RequestParam("idCus") int idCus) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("oke", customerService.findCustomer(idCus)));
    }

    @RequestMapping(value = "customer/updateProfile", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> updateProfile(
            @RequestParam("idCus") int idCus,
            @RequestParam("firstName") String firstName, @RequestParam("email") String email,
            @RequestParam("lastName") String lastName, @RequestParam("phone") String phone
    ) {
        customerService.updateCusProfile(idCus, email, firstName, lastName, phone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("oke", customerService.findCustomer(idCus)));
    }

    @RequestMapping(value = "/findCustomerByfilter", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> findCustomerByFilter(@RequestParam("idCus") int idCus) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("oke", customerService.findCustomerByFilter(idCus)));
    }

    @RequestMapping(value = "customer/changePass", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> changePass(
            @RequestParam("idCus") int idCus,
            @RequestParam("pass") String text,
            @RequestParam("newpass") String newpass) {
        Customer cus = customerService.findCustomer(idCus);
        hashMD5.setText(text);
        String pass = hashMD5.md5ToBase64();
        if (cus.getPass().equals(pass)) {
            customerService.updatePassProfile(idCus, newpass);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("oke", ""));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("wrong", ""));
    }

    @RequestMapping(value = "/findOrder", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> findOrder(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("oke", orderService.findOrderByCustomer(id)));
    }
}
