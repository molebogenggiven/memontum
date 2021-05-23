package com.active_shoppe;


import com.active_shoppe.model.ProductModel;

import java.util.LinkedList;
import java.util.List;

public class BootStrapTestData {


    public List<ProductModel> getListOfProduct(){

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
      return productModelList;
    }

}
