package com.p_kor.premium_calculator.service;

import java.math.BigDecimal;

public class TheftTypeRiskStrategyImp implements TypeRiskStrategy {
    BigDecimal baseCoefficient = new BigDecimal("0.11");
    BigDecimal overAmount = new BigDecimal("15.0");
    BigDecimal overAmountCoefficient = new BigDecimal("0.05");

    @Override
    public BigDecimal getRiskInsuranceCoefficient(BigDecimal insuredAmount) {
        if (insuredAmount.compareTo(overAmount) >= 0) {
            return overAmountCoefficient;
        }
        return baseCoefficient;
    }
}
