package com.mobisoft.mobisoftapi.dtos.financial;

import java.math.BigDecimal;
import java.util.Calendar;

import com.mobisoft.mobisoftapi.enums.payment.PaymentType;
import com.mobisoft.mobisoftapi.enums.project.StatusType;

import lombok.Data;

@Data
public class FinancialDTO {

	private int installmentsNumber;
    private Calendar firstPayment;
    private PaymentType paymentType;
    private Long projectId;
    private BigDecimal discount;
    private BigDecimal additionalExpenses;
    private BigDecimal totalValue;
    private BigDecimal totalCusts;
    private BigDecimal totalProjectDesigner;
    private BigDecimal totalSeller;
    private BigDecimal totalProfit;
}
