package com.active_shoppe;

import com.active_shoppe.dto.ProductDto;
import com.active_shoppe.model.CustomerActiveDaysModel;
import com.active_shoppe.repository.CustomerActiveDaysRepository;
import com.active_shoppe.repository.CustomerRepository;
import com.active_shoppe.repository.ProductRepository;
import com.active_shoppe.model.CustomerModel;
import com.active_shoppe.model.ProductModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class MomentumActiveShoppe implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerActiveDaysRepository customerActiveDaysRepository;

    public static void main(String[] args) {
        SpringApplication.run(MomentumActiveShoppe.class, args);
    }

    @Override
    public void run(String... args) {
        productDataInit();
        customerDataInit();
    }

    public void productDataInit() {
        productRepository.deleteAll();
        List<ProductModel> productModelList = new LinkedList<>();
        ProductModel productModel = new ProductModel();
        productModel.setProductCode("P1");
        productModel.setProductCost(50);
        productModel.setProductName("Product Name");
        productModelList.add(productModel);
        ProductModel productModel1 = new ProductModel();
        productModel1.setProductCode("P2");
        productModel1.setProductCost(100);
        productModel1.setProductName("Product Name1");
        productModelList.add(productModel1);
        productRepository.saveAll(productModelList);
    }

    public void customerDataInit() {
        customerRepository.deleteAll();
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerName("Given");
        CustomerActiveDaysModel customerActiveDaysModel = new CustomerActiveDaysModel();
        customerActiveDaysModel.setPoints(1000L);
        customerModel.setCustomerActiveDaysModel(customerActiveDaysModel);
        customerRepository.save(customerModel);
    }



}
