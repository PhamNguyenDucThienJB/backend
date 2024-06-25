package vn.edu.hcmuaf.fit.shoe.controllers;

import vn.edu.hcmuaf.fit.shoe.dto.ResponseObject;
import vn.edu.hcmuaf.fit.shoe.repositories.ContactRepository;
import vn.edu.hcmuaf.fit.shoe.services.ContactService;
import vn.edu.hcmuaf.fit.shoe.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ContactController {
    @Autowired
    ContactService contactService;
    @Autowired
    ContactRepository contactRes;
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/saveContact" , method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> saveContact(@RequestParam("idCus") int idCus,
                                                      @RequestParam("title") String title,
                                                      @RequestParam("content") String content) {
        contactService.saveContact(idCus, title, content);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("oke", ""));
    }

}
