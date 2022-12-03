package com.easyserviceapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CredentialsDto {
    
    @NotBlank
    @Size(max = 70)
    private String userName;

    @NotBlank
    @Size(max = 10)
    private String password;

}
