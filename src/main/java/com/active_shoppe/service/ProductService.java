package com.active_shoppe.service;

import com.active_shoppe.dto.CustomerDto;
import com.active_shoppe.dto.ProductDto;
import com.active_shoppe.dto.ProductsCodeDto;
import com.active_shoppe.dto.ResponseDto;
import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ResponseDto buyProducts(String customerId, ProductsCodeDto productsCodeDto);
    List<CustomerDto> getAllCustomers();
}
