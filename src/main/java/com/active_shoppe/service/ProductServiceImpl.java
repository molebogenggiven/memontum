package com.active_shoppe.service;

import com.active_shoppe.dto.*;
import com.active_shoppe.exception.BusinessException;
import com.active_shoppe.model.CustomerModel;
import com.active_shoppe.repository.CustomerActiveDaysRepository;
import com.active_shoppe.model.CustomerActiveDaysModel;
import com.active_shoppe.model.ProductModel;
import com.active_shoppe.repository.CustomerRepository;
import com.active_shoppe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("productService")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModels = productRepository.findAll();

       return productModels.stream().map( productModel -> {
            return ProductDto.builder()
                    .productName(productModel.getProductName())
                    .productCost(productModel.getProductCost())
                    .productCode(productModel.getProductCode())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public ResponseDto buyProducts(String customerId, ProductsCodeDto productsCodeDto) {
        Optional<CustomerModel> customerModel = customerRepository.findById(customerId);

        if (!customerModel.isPresent()){
            throw new BusinessException("The customer id : "+ customerId +" not found", HttpStatus.NOT_FOUND );
        }

        if (customerModel.get().getCustomerActiveDaysModel().getPoints() <= 0){
            throw  new BusinessException("You won't be able to buy because your points is insufficient", HttpStatus.NOT_ACCEPTABLE);
        }
        if (productsCodeDto.getProductCode().isEmpty()){
            throw new BusinessException("Please provide your desired product code ", HttpStatus.BAD_REQUEST);
        }
        List<ProductModel> productModels = productRepository.findByProductCodeIn(productsCodeDto.getProductCode());
        if (productModels.size() != productsCodeDto.getProductCode().size()){
            throw new BusinessException("Please make sure that you provide a valid product codes", HttpStatus.BAD_REQUEST);
        }

        Integer totalProductsPoints = productModels.stream().map(ProductModel::getProductCost).
                mapToInt(Integer::intValue).sum();
        log.info("The total sum is {}", totalProductsPoints);

        if (customerModel.get().getCustomerActiveDaysModel().getPoints() < totalProductsPoints){

            throw new BusinessException("You do not have enough points to purchase the products", HttpStatus.NOT_ACCEPTABLE);
        }
        long customerUpdatedPoints = customerModel.get().getCustomerActiveDaysModel().getPoints() - totalProductsPoints;
        customerModel.get().getCustomerActiveDaysModel().setPoints(customerUpdatedPoints);
        customerRepository.save(customerModel.get());

        return ResponseDto
                .builder()
                .message("OK")
                .build();
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerModel> customerModelList = customerRepository.findAll();

        return customerModelList.stream()
                .map(customerModel -> {
                    CustomerActiveDaysDto customerActiveDaysDto = CustomerActiveDaysDto.builder()
                            .points(customerModel.getCustomerActiveDaysModel().getPoints())
                            .build();
                    return CustomerDto.builder()
                            .customerId(customerModel.getId())
                            .customerName(customerModel.getCustomerName())
                            .customerActiveDaysDto(customerActiveDaysDto)
                            .build();
                }).collect(Collectors.toList());
    }
}
