package com.easyserviceapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "TB_PERSON")
@Entity
@Data
public class PersonModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String fullName;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 14)
    private String cpf;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserModel user;

}