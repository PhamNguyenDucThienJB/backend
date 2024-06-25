package vn.edu.hcmuaf.fit.shoe.repositories;

import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.shoe.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
