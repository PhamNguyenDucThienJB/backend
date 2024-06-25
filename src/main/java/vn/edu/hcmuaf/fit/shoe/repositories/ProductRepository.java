package vn.edu.hcmuaf.fit.shoe.repositories;

import vn.edu.hcmuaf.fit.shoe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {

     List<Product>  findByNameContainingIgnoreCase(String name);
//     List<Product> findByBranchId(int branchId);

}
