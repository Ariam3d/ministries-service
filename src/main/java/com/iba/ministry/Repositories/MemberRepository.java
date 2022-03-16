package com.iba.ministry.Repositories;

import com.iba.ministry.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

}