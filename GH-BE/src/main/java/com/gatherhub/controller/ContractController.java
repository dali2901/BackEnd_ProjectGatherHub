package com.gatherhub.controller;


import com.gatherhub.dto.AddNewContractDTO;
import com.gatherhub.dto.AddNewContractDTOResultMSG;
import com.gatherhub.dto.DeleteContractByIdDTO;
import com.gatherhub.entity.Contract;
import com.gatherhub.service.ContractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;


@RestController

@RequestMapping("contracts")
public class ContractController {

    @Autowired
    private  ContractService contractService ;





    //--------------------新增單筆資料並存取PDF資料--------------------//


    @PostMapping("/backstage-add-new-contract")
    public ResponseEntity<AddNewContractDTOResultMSG> addNewContract(@RequestPart(value = "companyName", required = false) String companyName,
                                                                     @RequestPart(value = "inCharge", required = false) String inCharge,
                                                                     @RequestPart(value = "memberPhone",required = false) String memberPhone,
                                                                     @RequestPart(value = "officeId",required = false) String officeId,
                                                                     @RequestPart(value = "companyTaxid",required = false) String companyTaxid,
                                                                     @RequestPart(value = "startDate",required = false) String startDate,
                                                                     @RequestPart(value = "endDate",required = false) String endDate,
                                                                     @RequestPart(value = "paymentStatus",required = false) Boolean paymentStatus,
                                                                     @RequestPart(value = "paymentMethod",required = false) String paymentMethod,
                                                                     @RequestPart(value = "remark",required = false) String remark,
                                                                     @RequestPart(value = "uploadFile",required = false) MultipartFile uploadFile)throws ParseException {

        String errormsg = "";

        //        if (StringUtils.isEmpty(companyName)) {
//            errormsg += "公司名稱為必填";
//        }


//        if(StringUtils.isEmpty(inCharge)){
//            errormsg += "負責人為必填";
//        }


        if (StringUtils.isEmpty(memberPhone)) {
            errormsg += "會員電話為必填";
        }



        if (StringUtils.isEmpty(officeId)) {
            errormsg += "辦公室編號為必填";
        }



        if (StringUtils.isEmpty(companyTaxid)) {
            errormsg += "公司統編為必填";
        }



        if (StringUtils.isEmpty(startDate)) {
            errormsg += "租借起始日為必填";
        }

        if (StringUtils.isEmpty(endDate)) {
            errormsg += "租借結束日為必填";
        }

//        if (paymentStatus == null) {
//            errormsg += "租借狀態為必填";
//        }


        if (StringUtils.isEmpty(paymentMethod)) {
            errormsg += "付款方式為必填";
        }

//        if (StringUtils.isEmpty(remark)) {
//            errormsg += "備註為必填";

        if (!errormsg.isEmpty()){

            AddNewContractDTOResultMSG addNewContractDTOResultMSG = new AddNewContractDTOResultMSG();
            addNewContractDTOResultMSG.setMessage(errormsg);

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addNewContractDTOResultMSG);
        }

        AddNewContractDTO addNewContractDTO = new AddNewContractDTO();


//        addNewContractDTO.setCompanyName(companyName);
//        addNewContractDTO.setInCharge(inCharge);
        addNewContractDTO.setMemberPhone(memberPhone);
        addNewContractDTO.setOfficeId(officeId);
        addNewContractDTO.setCompanyTaxid(companyTaxid);
        addNewContractDTO.setStartDate(startDate);
        addNewContractDTO.setEndDate(endDate);
        addNewContractDTO.setPaymentStatus(paymentStatus);
        addNewContractDTO.setPaymentMethod(paymentMethod);
        addNewContractDTO.setRemark(remark);
        addNewContractDTO.setUploadFile(uploadFile);

//        contractService.addNewContract(addNewContractDTO); //把資料送給contractService

        return ResponseEntity.status(HttpStatus.OK).body(contractService.addNewContract(addNewContractDTO));
//        return ResponseEntity.ok(contractService.addNewContract(addNewContractDTO));  與上方用途一樣但寫發不同

    }

    //--------------------刪除資料--------------------//

    @DeleteMapping("/delete_contract_id")
    public ResponseEntity<Contract> deleteById(@RequestBody DeleteContractByIdDTO contractId){

        contractService.deleteById(contractId);

        System.out.println("第" + contractId + "筆資料被刪除了");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    //--------------------修改資料--------------------//

    @PutMapping("/update_contract_id/{contract_id}")
    public ResponseEntity<Contract> updateContractById(@PathVariable Integer contract_id,
                                                       @RequestBody Contract contract){

//檢查contract是否存在
        Contract checkout = contractService.getContractById(contract_id);
        if(checkout == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            contractService.updateContractById(contract);
            Contract alreadyUpdateContract = contractService.getContractById(contract_id);
            System.out.println("訂單修改成功");

            return ResponseEntity.status(HttpStatus.OK).body(alreadyUpdateContract);
        }

    }
    //--------------------查詢所有合約資料--------------------//

    @GetMapping("/getAllContract")
    public List<Contract> getAllContract(){

        return contractService.getAllContract();
    }



    //--------------------查詢單一筆合約資料--------------------//



    @GetMapping("/getContractBy_contract_id/{contract_id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Integer contract_id){

        Contract contract = contractService.getContractById(contract_id);

        if(contract != null){
            return  ResponseEntity.status(HttpStatus.OK).body(contract);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
