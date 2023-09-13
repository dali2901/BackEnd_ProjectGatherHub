package com.gatherhub.dao;

import com.gatherhub.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, String> {


}
