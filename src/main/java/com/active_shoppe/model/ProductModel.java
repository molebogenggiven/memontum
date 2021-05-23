package com.active_shoppe.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document
@Getter
@Setter
@RequiredArgsConstructor
public class ProductModel implements Serializable {
    @Id
    private String id;

    @ApiModelProperty(notes = "The database generated product code")
    String productCode;

    @ApiModelProperty(notes = "The name of a product", required = true)
    String productName;

    @ApiModelProperty(notes = "The cost of a product", required = true)
    Integer productCost;


    public ProductModel(String id, String productCode, String productName, Integer productCost) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.productCost = productCost;
    }
}
