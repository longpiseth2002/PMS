package co.istad.dao;

import co.istad.database.ProductDatabase;
import co.istad.exception.ProductIdNotFoundException;
import co.istad.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao{
    private final ProductDatabase productDb;
    private final Scanner scanner;

    public ProductDaoImpl() {
        productDb = new ProductDatabase();
        scanner = new Scanner(System.in);
    }

    @Override
    public Product insert(Product product) {
        product = new Product(); // Create a new Product object

        System.out.println("Enter product details:");
        System.out.print("ID: ");
        product.setId(scanner.nextLong());
        scanner.nextLine(); // Consume newline character

        System.out.print("Name: ");
        product.setName(scanner.nextLine());

        System.out.print("Quantity: ");
        product.setQty(scanner.nextInt());

        System.out.print("Price: ");
        product.setPrice(scanner.nextDouble());

        System.out.print("Import Date (YYYY-MM-DD): ");
        product.setImportDate(LocalDate.parse(scanner.next()));

        productDb.getProducts().add(product);
        return product;
    }

    @Override
    public List<Product> select() {

        return productDb.getProducts();
    }

    @Override
    public Optional<Product> selectById(Long id) {
        return productDb.getProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public Product updateById(Product product) {
        System.out.print("Input product ID to update : ");
        Long upID = scanner.nextLong();

        Product foundProduct = productDb.getProducts().stream()
                .filter(product1 -> product1.getId().equals(upID))
                .findFirst()
                .orElseThrow(()
                        -> new ProductIdNotFoundException(
                        String.format("Product ID : %s does not exist in DB", upID))
                );
        scanner.nextLine(); // Consume newline character
        System.out.print("Name: ");
        foundProduct.setName(scanner.nextLine());

        System.out.print("Quantity: ");
        foundProduct.setQty(scanner.nextInt());

        System.out.print("Price: ");
        foundProduct.setPrice(scanner.nextDouble());

        System.out.print("Import Date (YYYY-MM-DD): ");
        foundProduct.setImportDate(LocalDate.parse(scanner.next()));

        productDb.getProducts().add(foundProduct);
        return foundProduct;

    }


    @Override
    public Product deleteById(Long id) {
        Product foundProduct = productDb.getProducts().stream()
                .filter(product1 -> product1.getId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new ProductIdNotFoundException(
                        String.format("Product ID : %s does not exist in DB", id))
                );
        productDb.getProducts().remove(foundProduct);
        return foundProduct;
    }

    @Override
    public List<Product> selectByName(String name) {
        return productDb.getProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
