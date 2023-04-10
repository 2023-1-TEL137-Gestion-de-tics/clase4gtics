package com.example.clase4gtics.controller;

import com.example.clase4gtics.entity.Product;
import com.example.clase4gtics.repository.CategoryRepository;
import com.example.clase4gtics.repository.ProductRepository;
import com.example.clase4gtics.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;
    final SupplierRepository supplierRepository;

    public ProductController(ProductRepository productRepository,
                             CategoryRepository categoryRepository,
                             SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping(value = {"", "/"})
    public String listaProductos(Model model) {
        model.addAttribute("listaProductos", productRepository.findAll());
        return "product/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(Model model) {
        model.addAttribute("listaCategorias",categoryRepository.findAll());
        model.addAttribute("listaTransportistas",supplierRepository.findAll());
        return "product/newFrm";
    }

    @PostMapping("/save")
    public String guardarProducto(Product product, RedirectAttributes attr) {
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model, @RequestParam("id") int id) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            model.addAttribute("listaCategorias",categoryRepository.findAll());
            model.addAttribute("listaTransportistas",supplierRepository.findAll());
            model.addAttribute("product", product);
            return "product/editFrm";
        } else {
            return "redirect:/product";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            productRepository.deleteById(id);
        }
        return "redirect:/product";

    }

}
