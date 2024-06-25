package vn.edu.hcmuaf.fit.shoe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shoe.entity.Branch;
import vn.edu.hcmuaf.fit.shoe.repositories.BranchRepository;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public Branch findById(int id) {
        return branchRepository.findById(id).orElse(null);
    }
}