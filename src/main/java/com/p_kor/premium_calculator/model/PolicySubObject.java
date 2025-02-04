package com.p_kor.premium_calculator.model;

import java.math.BigDecimal;

public record PolicySubObject(String name, BigDecimal insuredAmount, RiskType riskType) {
}
