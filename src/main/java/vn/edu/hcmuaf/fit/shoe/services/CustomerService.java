package vn.edu.hcmuaf.fit.shoe.services;

import vn.edu.hcmuaf.fit.shoe.entity.Customer;
import vn.edu.hcmuaf.fit.shoe.dto.CustomerProfile;
import vn.edu.hcmuaf.fit.shoe.repositories.CustomerRepository;
import vn.edu.hcmuaf.fit.shoe.utils.HashMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    HashMD5 hashMD5;
    private Customer cus;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    public boolean login(String email , String text ){
        hashMD5.setText(text);
        String pass = hashMD5.md5ToBase64();
        Customer u = customerRepository.findCustomerByEmailAndPassAndStatus(email,pass,"ACTIVE") ;
        if(u==null) return  false ;
        this.cus = u ;
        return  true ;
    }
    public  boolean isExistEmail(String email){
       try{
           if(customerRepository.findByEmail(email)==null) return  false ;
       }catch (Exception e){
           return  false ;
       }
        return true ;
    }

    public Customer findCustomer(int idCustomer){
       return customerRepository.findById(idCustomer);
    }


    public Customer saveCustomer(Customer cus){
       return customerRepository.save(cus) ;
    }
    public void updateTimeAndCode(Customer customer , String code){
        customer.setCode(code);
        customer.setTime(System.currentTimeMillis());
        this.customerRepository.save(customer) ;
    }
    public  Customer findByEmail(String email){
        return  customerRepository.findByEmail(email) ;
    }

    public boolean checkTimeSend(String email){
        Customer customer = findByEmail(email) ;
        if(customer==null) return  false ;
        this.cus = customer ;
        long start = System.currentTimeMillis()/1000;
        if((start-(customer.getTime()/1000))>60) return  false ;
        else  return  true  ;
    }
    public boolean checkCode(String code){
        if(this.cus.getCode().equals(code)) return true ;
        return  false ;
    }
    public void updatePassWord(String text){
        hashMD5.setText(text);
        String pass = hashMD5.md5ToBase64();
        this.cus.setPass(pass);
        customerRepository.save(this.cus) ;
    }

    public void updatePassProfile(int idCus, String text){
        hashMD5.setText(text);
        String newpass = hashMD5.md5ToBase64();
        Customer cus = customerRepository.findById(idCus);
        cus.setPass(newpass);
        customerRepository.save(cus) ;
    }

    public void updateCusProfile(int idCus, String email, String firstName, String lastName, String phone){
        Customer cus = customerRepository.findById(idCus);
            cus.setEmail(email);
            cus.setFirstName(firstName);
            cus.setLastName(lastName);
            cus.setPhone(phone);
            customerRepository.save(cus);
    }

    public CustomerProfile findCustomerByFilter(int id){
        Customer customer1 = customerRepository.findById(id);
        CustomerProfile profile = new CustomerProfile(customer1.getIdCustomer(),
                customer1.getEmail(),customer1.getFirstName(),
                customer1.getLastName(), customer1.getPhone(), customer1.getLinkAvatar()
        );
        return  profile;
    }
    public Customer getCus(){
        return this.cus ;
    }


}
