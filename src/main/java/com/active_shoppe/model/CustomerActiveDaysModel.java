package com.active_shoppe.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Getter
@Setter
public class CustomerActiveDaysModel {

    @ApiModelProperty(notes = "The product cost", required = true)
    Long points;

}
