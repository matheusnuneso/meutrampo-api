package com.easyserviceapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "TB_JOB")
@Entity
@SequenceGenerator(name = "seq" , initialValue = 10)
@Data
public class JobModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq")
    private Long id;

    @Column(nullable = false, length = 70)
    private String title;

    @Column(nullable = false)
    private double price;
    
    @Column(nullable = false)
    private long idPerson;

}