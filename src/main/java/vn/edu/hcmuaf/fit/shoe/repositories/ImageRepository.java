package vn.edu.hcmuaf.fit.shoe.repositories;

import vn.edu.hcmuaf.fit.shoe.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
