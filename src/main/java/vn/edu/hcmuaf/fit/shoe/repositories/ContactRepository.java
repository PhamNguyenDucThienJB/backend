package vn.edu.hcmuaf.fit.shoe.repositories;

import vn.edu.hcmuaf.fit.shoe.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
