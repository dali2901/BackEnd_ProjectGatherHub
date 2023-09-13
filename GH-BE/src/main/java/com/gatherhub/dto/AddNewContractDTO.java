package com.gatherhub.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class AddNewContractDTO {

    @NotNull
    private String memberPhone;
    @NotNull
    private String inCharge;
    @NotNull
    private String officeId;
    @NotNull
    private String companyTaxid;
    @NotNull
    private String companyName;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;

    private Boolean paymentStatus;
    @NotNull
    private String paymentMethod;

    private String remark;

    private String contractPDF;

    private MultipartFile uploadFile;
}
