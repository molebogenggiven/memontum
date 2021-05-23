package com.active_shoppe.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Document
@Getter
@Setter
public class CustomerModel implements Serializable {

    @Id
    @ApiModelProperty(notes = "The database generated customer ID")
    private String id;

    @ApiModelProperty(notes = "Name of a customer", required = true)
    String customerName;

    @ApiModelProperty(notes = "Customer's active day's points")
    private CustomerActiveDaysModel customerActiveDaysModel;

    public CustomerModel(String id, String customerName, CustomerActiveDaysModel customerActiveDaysModel) {
        this.id = id;
        this.customerName = customerName;
        this.customerActiveDaysModel = customerActiveDaysModel;
    }

    public CustomerModel(){}
}
