package com.easyserviceapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PersonDto {

    @NotBlank
    @Size(max = 70)
    private String fullName;

    @NotBlank
    @Size(max = 40)
    private String email;

    @NotBlank
    @Size(max = 14)
    private String cpf;

    @NotBlank
    @Size(max = 70)
    private String userName;

    @Size(max = 10)
    private String password;
    
}
