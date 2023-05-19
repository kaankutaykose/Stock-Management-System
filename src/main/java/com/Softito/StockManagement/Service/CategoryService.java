package com.Softito.StockManagement.Service;

import com.Softito.StockManagement.Entity.Category;
import com.Softito.StockManagement.Repository.CategoryRepository;
import com.Softito.StockManagement.Repository.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IRepositoryService<Category> {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category add(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public String delete(Long id) {
        Category category = categoryRepository.getById(id);
        String result="Category not found";
        if(category!=null){
            categoryRepository.delete(category);
            result="Delete successful";
        }
        return result;
    }

    @Override
    public String update(Long id, Category entity) {
        Category category = categoryRepository.getById(id);
        System.out.println(category.getId());
        String result="Error update";
        if(category!=null){
            category.setName(entity.getName());
            category.setStatus(entity.isStatus());
            categoryRepository.save(category);
            result="Update successful";
        }
        return result;
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id)!=null?categoryRepository.findById(id):null;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
