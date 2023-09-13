package com.gatherhub.dao;

import com.gatherhub.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeDao extends JpaRepository<Office, String> {
}
