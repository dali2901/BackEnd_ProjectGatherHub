package com.gatherhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "contract")

public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    Integer contractId;



    @Column(name = "member_phone")
    String memberPhone;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_phone",referencedColumnName = "member_phone", insertable = false, updatable = false)
    Member member;

    @Column(name = "office_id")
    String officeId;

    @Column(name = "company_taxid")
    String companyTaxid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_taxid", referencedColumnName = "company_taxid", insertable = false, updatable = false)
    Company company;

//    @Column(name = "company_name")
//    String companyName;

    //    @Column(name = "inCharge")
//    String inCharge;


    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;

    @Column(name = "payment_status")
    Boolean paymentStatus;

    @Column(name = "payment_method")
    String paymentMethod;

    @Column(name = "remark")
    String remark;

    @Column(name = "contract_pdf")
    String contractPDF;


}