package com.active_shoppe;

import com.active_shoppe.dto.ProductDto;
import com.active_shoppe.dto.ProductsCodeDto;
import com.active_shoppe.dto.ResponseDto;
import com.active_shoppe.exception.BusinessException;
import com.active_shoppe.model.CustomerActiveDaysModel;
import com.active_shoppe.model.CustomerModel;
import com.active_shoppe.model.ProductModel;
import com.active_shoppe.repository.CustomerRepository;
import com.active_shoppe.repository.ProductRepository;
import com.active_shoppe.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MomentumActiveShoppeControllerTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void savedProductsCanBeRetrieved() {

//        BootStrapTestData bootStrapTestData = new BootStrapTestData();
//        productRepository.saveAll(bootStrapTestData.getListOfProduct());

        List<ProductModel> productModels = new LinkedList<>();
        productModels.add(new ProductModel(UUID.randomUUID().toString(), "P1","Product Name", 20));
        when(productRepository.findAll()).thenReturn(productModels);

        List<ProductDto> productDtos = productService.getAllProducts();
        Assert.assertEquals(1, productDtos.size());
    }

    @Test
    public void validProductsCanBeRetrieved(){
        List<ProductModel> productModels = new LinkedList<>();
        productModels.add(new ProductModel(UUID.randomUUID().toString(), "P1","Product Name", 20));
        when(productRepository.findAll()).thenReturn(productModels);

        List<ProductDto> productDtos = productService.getAllProducts();
        Assert.assertEquals(Integer.valueOf(20), productDtos.get(0).getProductCost());
        Assert.assertEquals("Product Name", productDtos.get(0).getProductName());
        Assert.assertEquals("P1", productDtos.get(0).getProductCode());
    }

    @Test
    public void buyProduct(){
        List<ProductModel> productModels = new LinkedList<>();
        productModels.add(new ProductModel(UUID.randomUUID().toString(), "P1","Product Name", 20));
        when(productRepository.findByProductCodeIn(Collections.singletonList("P1"))).thenReturn(productModels);

        CustomerActiveDaysModel customerActiveDaysModel = new CustomerActiveDaysModel();
        customerActiveDaysModel.setPoints(1000L);
        String customerId = UUID.randomUUID().toString();
        CustomerModel customerModel = new CustomerModel(customerId, "Given",customerActiveDaysModel );
        when(customerRepository.findById(customerModel.getId())).thenReturn(java.util.Optional.of(customerModel));
        ProductsCodeDto productsCodeDto = new ProductsCodeDto();
        List<String> listOfProductsCode = new LinkedList<>();
        listOfProductsCode.add("P1");
        productsCodeDto.setProductCode(listOfProductsCode);
        ResponseDto responseDto = productService.buyProducts(customerModel.getId(), productsCodeDto);

        Assert.assertEquals("OK", responseDto.getMessage());
    }





}
