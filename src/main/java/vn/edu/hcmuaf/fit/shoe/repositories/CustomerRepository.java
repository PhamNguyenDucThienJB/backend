package vn.edu.hcmuaf.fit.shoe.repositories;

import vn.edu.hcmuaf.fit.shoe.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

     Customer findCustomerByEmailAndPassAndStatus(String email , String pass , String status) ;
     Customer findByEmail(String email) ;
     Customer findById(int idCustomer) ;
}
