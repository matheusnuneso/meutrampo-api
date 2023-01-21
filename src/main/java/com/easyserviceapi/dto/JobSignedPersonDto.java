package com.easyserviceapi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobSignedPersonDto {

    @NotNull
    private Long id;

    @NotBlank
    private String titleJob;

    @NotNull
    private double finalPrice;
    
    @NotBlank
    private String nameClient;

    @NotNull   
    private Date jobDate;
    
    @NotNull   
    private Date contractDate;

}