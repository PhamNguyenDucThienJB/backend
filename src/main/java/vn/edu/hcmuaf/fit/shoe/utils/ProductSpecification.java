package vn.edu.hcmuaf.fit.shoe.utils;

import vn.edu.hcmuaf.fit.shoe.entity.Product;
import vn.edu.hcmuaf.fit.shoe.dto.FilterCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product> {
    private FilterCriteria filterCriteria ;
    public ProductSpecification(FilterCriteria filterCriteria){
        this.filterCriteria = filterCriteria ;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        switch (filterCriteria.getOperation()){
            case "CONTAIN" :
                return criteriaBuilder.like(root.<String>get(filterCriteria.getKey()), "%"+filterCriteria.getValue()+"%");
            case "EQUAL" :
                return criteriaBuilder.equal(root.get(filterCriteria.getKey()),filterCriteria.getValue()) ;
            case "EQUALID" :
                return criteriaBuilder.equal(root.get(filterCriteria.getKey()),Integer.parseInt(filterCriteria.getValue())) ;
            default: return null ;

        }
    }

    public FilterCriteria getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(FilterCriteria filterCriteria) {
        this.filterCriteria = filterCriteria;
    }
}
