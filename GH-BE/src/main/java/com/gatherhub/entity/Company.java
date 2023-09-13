package com.gatherhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name ="company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "company_taxid")
    String companyTaxId;

    @Column(name ="company_name")
    String companyName;

    @Column(name ="company_address")
    String address;

    @Column(name ="company_phone")
    String companyPhone;

}
