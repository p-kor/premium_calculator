package com.p_kor.premium_calculator.service;

import com.p_kor.premium_calculator.model.RiskType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.EnumMap;

@Component
public class TypeRiskStrategyService {

    private final EnumMap<RiskType, TypeRiskStrategy> riskStrategies;

    public TypeRiskStrategyService() {
        riskStrategies = new EnumMap<>(RiskType.class);
        riskStrategies.put(RiskType.FIRE, new FireTypeRiskStrategyImp());
        riskStrategies.put(RiskType.THEFT, new TheftTypeRiskStrategyImp());
    }

    public BigDecimal getRiskInsuranceCoefficient(RiskType riskType, BigDecimal insuredAmount) {
        return riskStrategies.get(riskType).getRiskInsuranceCoefficient(insuredAmount);
    }
}
