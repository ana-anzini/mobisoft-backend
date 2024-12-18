package com.mobisoft.mobisoftapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobisoft.mobisoftapi.models.Financial;
import com.mobisoft.mobisoftapi.models.Project;

@Repository
public interface FinancialRepository extends JpaRepository<Financial, Long> {
	
	Financial findByProjectId(Long projectId);
}
