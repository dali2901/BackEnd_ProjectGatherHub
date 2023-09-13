package com.gatherhub.dao;

import com.gatherhub.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member, Integer> {

}
