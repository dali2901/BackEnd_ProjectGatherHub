package com.gatherhub.dao;

import com.gatherhub.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ContractDao extends JpaRepository<Contract, Integer> {



//    @Query("SELECT con FROM Contract con JOIN FETCH con.company inner JOIN FETCH con.member ")
//    List<Contract> getAllByContract();

}



