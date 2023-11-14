package co.istad;

import co.istad.controller.MenuController;
import co.istad.controller.ProductController;
import co.istad.dao.ProductDao;
import co.istad.model.Product;
import co.istad.service.ProductService;
import co.istad.service.ProductServiceImpl;
import co.istad.util.Singleton;
import co.istad.view.MenuView;
import co.istad.view.ProductView;

import java.security.Signature;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainApplication {

    private final Scanner scanner;
    private final MenuController menuController;
    private final ProductController productController;
    public MainApplication() {
        scanner = Singleton.scanner();
        menuController = Singleton.menuController();
        productController = Singleton.productController();
    }

    private void run() {


        boolean condition=false;
        do{
            condition = false;
            menuController.index();
            System.out.print("Enter options : ");
            int options = scanner.nextInt();
            switch (options) {
                case 1 -> productController.index();
                case 2 -> productController.create();
                case 3 -> productController.delete();
                case 4 -> productController.update();
                default -> {

                    throw new IllegalArgumentException();

                }
            }
        }while (!condition);

    }

    public static void main(String[] args) {

        new MainApplication().run();;

    }
}
