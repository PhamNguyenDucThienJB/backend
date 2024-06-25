package vn.edu.hcmuaf.fit.shoe.services;

import vn.edu.hcmuaf.fit.shoe.entity.Contact;
import vn.edu.hcmuaf.fit.shoe.entity.Customer;
import vn.edu.hcmuaf.fit.shoe.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRes;
    @Autowired
    CustomerService service;
    public Contact saveContact(int idCus, String title, String content){
        Customer cus = service.findCustomer(idCus);
        Contact contact = new Contact(title, content, cus);
        return contactRes.save(contact) ;
    }
}
