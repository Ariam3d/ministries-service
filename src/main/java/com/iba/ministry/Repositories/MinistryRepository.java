package com.iba.ministry.Repositories;

import com.iba.ministry.Entities.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinistryRepository extends JpaRepository<Ministry, Long> {
}