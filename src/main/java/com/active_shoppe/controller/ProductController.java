package com.active_shoppe.controller;

import com.active_shoppe.dto.ProductDto;
import com.active_shoppe.dto.ProductsCodeDto;
import com.active_shoppe.dto.ResponseDto;
import com.active_shoppe.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
@Api(value="momentum_active_shoppe")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    @ApiOperation(value = "View a list of available products", response = ResponseEntity.class)
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/buyProducts/{customerId}")
    @ApiOperation(value = "Buy an existing product/products",response = ResponseEntity.class)
    public ResponseEntity<ResponseDto> buyProducts(@PathVariable final String customerId, @RequestBody final ProductsCodeDto productsCode) {
        ResponseDto responseDto = productService.buyProducts(customerId, productsCode);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
