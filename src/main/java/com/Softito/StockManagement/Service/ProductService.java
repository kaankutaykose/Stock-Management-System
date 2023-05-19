package com.Softito.StockManagement.Service;

import com.Softito.StockManagement.Entity.Product;
import com.Softito.StockManagement.Repository.IRepositoryService;
import com.Softito.StockManagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IRepositoryService<Product> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product add(Product entity){
        return productRepository.save(entity);
    }

    @Override
    public String delete(Long id) {
        Product product = productRepository.getById(id);
        String result="Category not found";
        if(product!=null){
            productRepository.delete(product);
            result="Delete successful";
        }
        return result;
    }

    @Override
    public String update(Long id, Product entity) {
        Product product = productRepository.getById(id);
        System.out.println(product.getId());
        String result="Error update";
        if(product!=null){
            product.setName(entity.getName());
            productRepository.save(product);
            result="Update successful";
        }
        return result;
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id)!=null?productRepository.findById(id):null;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

}
