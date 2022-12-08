package com.easyserviceapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JobSignedDto {


    @NotBlank
    @Size(max = 20)
    private long idJob;

    @NotBlank
    private double finalPrice;
    
    @NotBlank
    @Size(max = 20)
    private long idClient;
        
    @NotBlank
    @Size(max = 20)
    private long idPerson;

}