package com.gatherhub.service.Impl;


import com.gatherhub.dao.ContractDao;
import com.gatherhub.dto.AddNewContractDTO;
import com.gatherhub.dto.AddNewContractDTOResultMSG;
import com.gatherhub.dto.DeleteContractByIdDTO;
import com.gatherhub.dto.UploadFilePDFDTO;
import com.gatherhub.entity.Contract;
import com.gatherhub.service.ContractService;
import com.gatherhub.service.UploadPDFToAwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private  UploadPDFToAwsService uploadPDFToAwsService;









    //--------------------新增單筆資料並存取PDF資料--------------------//

    @Override
    public AddNewContractDTOResultMSG addNewContract(AddNewContractDTO addNewContractDTO) throws ParseException {

        Contract contract = new Contract();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//        contract.setCompanyName(addNewContractDTO.getCompayName());
//        contract.setInCharge(addNewContractDTO.getInCharge());
        contract.setMemberPhone(addNewContractDTO.getMemberPhone());
        contract.setOfficeId(addNewContractDTO.getOfficeId());
        contract.setCompanyTaxid(addNewContractDTO.getCompanyTaxid());

       contract.setStartDate(sdf.parse(addNewContractDTO.getStartDate()));
        contract.setEndDate(sdf.parse(addNewContractDTO.getEndDate()));

        contract.setPaymentStatus(addNewContractDTO.getPaymentStatus());
        contract.setPaymentMethod(addNewContractDTO.getPaymentMethod());
        contract.setRemark(addNewContractDTO.getRemark());




        UploadFilePDFDTO uploadFilePDFDTO = uploadPDFToAwsService.uploadFilePDF(addNewContractDTO.getUploadFile());
        //將前端傳進來的PDF丟給uploadPDFToAwsService裡面的uploadFilePDF方法

        System.out.println(uploadFilePDFDTO.getFileType());  //捕捉一下是否有拿到檔案

        contract.setContractPDF(uploadFilePDFDTO.getUrl());


        contractDao.save(contract);

        AddNewContractDTOResultMSG addNewContractDTOResultMSG =new AddNewContractDTOResultMSG();
        addNewContractDTOResultMSG.setMessage("訂單新增成功");
        return addNewContractDTOResultMSG;


    }



//--------------------刪除資料--------------------//

    @Override
    public void deleteById(DeleteContractByIdDTO deleteContractByIdDTO) {

        contractDao.deleteById(deleteContractByIdDTO.getContractId());
    }


//--------------------修改資料--------------------//

    @Override
    public Contract updateContractById(Contract contract) {
        return contractDao.save(contract);
    }

//--------------------查詢所有合約資料--------------------//

    @Override
    public List<Contract> getAllContract() {

        List<Contract> contracts = contractDao.findAll();
        return contracts ;
    }


//--------------------查詢單一筆合約資料--------------------//


    @Override
    public Contract getContractById(Integer contractId) {
        return contractDao.findById(contractId).orElse(null);
    }
}
