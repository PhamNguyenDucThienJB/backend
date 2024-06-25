package vn.edu.hcmuaf.fit.shoe.services;
import vn.edu.hcmuaf.fit.shoe.entity.Product;
import vn.edu.hcmuaf.fit.shoe.dto.ResponseShopProduct;
import vn.edu.hcmuaf.fit.shoe.dto.ShopProduct;
import vn.edu.hcmuaf.fit.shoe.repositories.ProductRepository;
import vn.edu.hcmuaf.fit.shoe.utils.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void saveProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product entity is null");
        }
        if (product.getBranch() == null) {
            throw new IllegalArgumentException("Branch entity is null");
        }
        // Gắn kết lại đối tượng Branch vào phiên làm việc hiện tại
        product.setBranch(entityManager.merge(product.getBranch()));
        productRepository.save(product);
    }
    public Product getProductById(int id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = null;
        if (result.isPresent()) {
            product = result.get();
        } else {
            throw new RuntimeException("Không tìm thấy sản phẩm với id = " + id);
        }
        return product;
    }

    @Transactional
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> findProductNameContaining(String name){
    return  productRepository.findByNameContainingIgnoreCase(name);
}
        public ArrayList<Object> findProductById(String id){
        ArrayList<Object> list = new ArrayList<>() ;
        Product product  = productRepository.findById(Integer.parseInt(id)).get();
        list.add(product);
        list.add(product.getBranch());
        list.add(product.getListImage());
        return  list;
        }
        public Page<Product> findProductByFilter(ShopProduct shopProduct){
            Specification productSpecification = new ProductSpecification(shopProduct.getList().get(4)) ;
            Pageable pageable =  PageRequest.of(shopProduct.getPage()-1, shopProduct.getItemProduct(),Sort.by(shopProduct.getSort().equals("DES")?Sort.Direction.DESC:Sort.Direction.ASC,"price"));
            for(int i=0 ; i<shopProduct.getList().size()-1;i++){
               if(!shopProduct.getList().get(i).getValue().isBlank()) {
                   productSpecification =  Specification.where(productSpecification).and(new ProductSpecification(shopProduct.getList().get(i)));
               }
            }
    return  productRepository.findAll(productSpecification,  pageable) ;
        }
        public int productFiltered (ShopProduct shopProduct){
            Specification productSpecification = new ProductSpecification(shopProduct.getList().get(4)) ;
            for(int i=0 ; i<shopProduct.getList().size()-1;i++){
                if(!shopProduct.getList().get(i).getValue().isBlank()) {
                    productSpecification =  Specification.where(productSpecification).and(new ProductSpecification(shopProduct.getList().get(i)));
                }
            }
        return productRepository.findAll(productSpecification).size() ;
        }
        public ResponseShopProduct findProduct(ShopProduct shopProduct){
            ResponseShopProduct res = new ResponseShopProduct(shopProduct.getItemProduct(),productFiltered(shopProduct)
                    ,shopProduct.getPage(),  findProductByFilter(shopProduct).getContent());
            return  res ;
        }
}
