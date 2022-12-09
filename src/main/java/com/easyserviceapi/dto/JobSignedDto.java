package com.easyserviceapi.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class JobSignedDto {


    @NotNull
    private long idJob;

    @NotNull
    private double finalPrice;
    
    @NotNull
    private long idClient;
        
    @NotNull
    private long idPerson;

    @NotNull   
    private Date jobDate;
    
    @NotNull   
    private Date contractDate;

}