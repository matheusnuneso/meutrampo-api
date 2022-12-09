package com.easyserviceapi.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobSignedDto {


    @NotBlank
    private long idJob;

    @NotBlank
    private double finalPrice;
    
    @NotBlank
    private long idClient;
        
    @NotBlank
    private long idPerson;

    @NotBlank   
    private Date jobDate;
    
    @NotBlank    
    private Date contractDate;

}