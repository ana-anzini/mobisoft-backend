package com.mobisoft.mobisoftapi.models;

import com.mobisoft.mobisoftapi.base.Base;
import com.mobisoft.mobisoftapi.enums.suppliers.SupplierType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SUPPLIER")
public class Supplier extends Base {
	
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
