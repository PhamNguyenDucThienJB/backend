package vn.edu.hcmuaf.fit.shoe.utils;

import vn.edu.hcmuaf.fit.shoe.entity.Customer;
import vn.edu.hcmuaf.fit.shoe.dto.FormRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilityCustomer {
    @Autowired
    HashMD5 hashMD5 ;
    public Customer toEntity(FormRegister form){
        hashMD5.setText(form.getPass());
        String pass = hashMD5.md5ToBase64();
        Customer customer = new Customer() ;
        customer.setEmail(form.getEmail());
        customer.setFirstName(form.getFirstName());
        customer.setPass(pass);
        customer.setPhone(form.getPhone());
        customer.setLastName(form.getLastName());
        customer.setRole("USER");
        customer.setStatus("ACTIVE");
        return  customer ;

    }
}
