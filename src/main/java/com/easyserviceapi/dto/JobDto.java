package com.easyserviceapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JobDto {
    
    @NotBlank
    @Size(max = 70)
    private String title;
    
    @NotNull
    private double price;

    @NotNull
    private long idPerson;

}
