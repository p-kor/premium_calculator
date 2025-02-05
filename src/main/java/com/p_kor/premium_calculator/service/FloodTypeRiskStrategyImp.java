package com.p_kor.premium_calculator.service;

import java.math.BigDecimal;

public class FloodTypeRiskStrategyImp implements TypeRiskStrategy {
    BigDecimal baseCoefficient = new BigDecimal("0.014");
    BigDecimal overAmount = new BigDecimal("100.01");
    BigDecimal overAmountCoefficient = new BigDecimal("0.024");

    @Override
    public BigDecimal getRiskInsuranceCoefficient(BigDecimal insuredAmount) {
        if (insuredAmount.compareTo(overAmount) >= 0) {
            return overAmountCoefficient;
        }
        return baseCoefficient;
    }
}
