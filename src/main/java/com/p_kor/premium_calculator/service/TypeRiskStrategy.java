package com.p_kor.premium_calculator.service;

import java.math.BigDecimal;

public interface TypeRiskStrategy {
    BigDecimal getRiskInsuranceCoefficient(BigDecimal insuredAmount);
}
