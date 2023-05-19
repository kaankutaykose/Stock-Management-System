package com.Softito.StockManagement.Controller;

import com.Softito.StockManagement.Entity.Category;
import com.Softito.StockManagement.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @RequestMapping("/categories")
    public String getAll(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "list-categories";
    }

    @RequestMapping("/category/{id}")
    public String findCategoryById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        return "list-category";
    }

    @GetMapping("/addCategory")
    public String showCreateForm(Category category) {
        return "add-category";
    }

    @RequestMapping("/add-category")
    public String createCategory(Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-category";
        }

        categoryService.add(category);
        model.addAttribute("category", categoryService.getAll());
        return "redirect:/categories";
    }

    @GetMapping("/updateCategory/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("category", categoryService.getById(id));
        return "update-category";
    }

    @RequestMapping(value = "/update-category/{id}", method = RequestMethod.POST)
    public String updateCategory(@PathVariable("id") Long id, Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "update-category";
        }

        categoryService.update(id,category);
        model.addAttribute("category", categoryService.getAll());
        return "redirect:/categories";
    }

    @RequestMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        categoryService.delete(id);

        model.addAttribute("category", categoryService.getAll());
        return "redirect:/categories";
    }
}
