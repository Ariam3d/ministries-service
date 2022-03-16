package com.iba.ministry.Repositories;

import com.iba.ministry.Entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}