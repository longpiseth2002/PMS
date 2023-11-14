package co.istad.controller;

import co.istad.dao.ProductDaoImpl;
import co.istad.model.Product;
import co.istad.service.ProductService;
import co.istad.service.ProductServiceImpl;
import co.istad.util.Singleton;
import co.istad.view.ProductView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private static Scanner scanner;
    private Product product;
    private ProductService productService;
    public ProductController() {
        scanner = Singleton.scanner();
        productService = new ProductServiceImpl();
    }

    public void index() {
        List<Product> products = productService.select();
        ProductView.printProductList(products);
    }
    public void create() {
        List<Product> products = productService.select();
        productService.insert(product);
        ProductView.printProductList(products);
    }
    public void delete() {
        System.out.print("Input Data to delete : ");
        Long deleteData = scanner.nextLong();
        productService.deleteById(deleteData);
        List<Product> products = productService.select();
        ProductView.printProductList(products);
    }

    public void update(){
        productService.updateById(product);
        List<Product> products = productService.select();
        ProductView.printProductList(products);
    }



}
