package com.mobisoft.mobisoftapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobisoft.mobisoftapi.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
