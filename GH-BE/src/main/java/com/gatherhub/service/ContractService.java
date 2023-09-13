package com.gatherhub.service;

import com.gatherhub.dto.AddNewContractDTO;
import com.gatherhub.dto.AddNewContractDTOResultMSG;
import com.gatherhub.dto.DeleteContractByIdDTO;
import com.gatherhub.entity.Contract;
import org.springframework.data.jpa.repository.Query;


import java.text.ParseException;
import java.util.List;


public interface ContractService {

    //--------------------新增單筆資料並存取PDF資料--------------------//

    AddNewContractDTOResultMSG   addNewContract(AddNewContractDTO  addNewContractDTO) throws ParseException;


    //--------------------刪除資料--------------------//

    void deleteById(DeleteContractByIdDTO deleteContractByIdDTO);

//--------------------修改資料--------------------//

    Contract updateContractById(Contract contract);

//--------------------查詢所有合約資料--------------------//

    @Query("SELECT con FROM Contract con JOIN FETCH con.company inner JOIN FETCH con.member ")
    List<Contract> getAllContract();

//--------------------查詢單一筆合約資料--------------------//

Contract getContractById(Integer contractId);

};


