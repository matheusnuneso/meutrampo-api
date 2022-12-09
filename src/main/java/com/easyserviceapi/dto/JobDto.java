package com.easyserviceapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JobDto {
    

    @NotBlank
    @Size(max = 70)
    private String title;
    
    private double price;

    @NotBlank
    @Size(max = 20)
    private long idPerson;

}
