package com.Softito.StockManagement.Controller;

import com.Softito.StockManagement.Entity.Product;
import com.Softito.StockManagement.Service.CategoryService;
import com.Softito.StockManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @RequestMapping("/products")
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "list-products";
    }

    @RequestMapping("/product/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "list-product";
    }

    @GetMapping("/add")
    public String showCreateForm(Product product, Model model) {

        model.addAttribute("categories", categoryService.getAll());
        return "add-product";
    }

    @RequestMapping("/add-product")
    public String createProduct(Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-product";
        }

        productService.add(product);
        model.addAttribute("Product", productService.getAll());
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.getById(id));
        return "update-product";
    }

    @RequestMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }

        productService.update(id,product);
        model.addAttribute("product", productService.getAll());
        return "redirect:/products";
    }

    @RequestMapping("/remove-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        productService.delete(id);

        model.addAttribute("product", productService.getAll());
        return "redirect:/products";
    }
}
