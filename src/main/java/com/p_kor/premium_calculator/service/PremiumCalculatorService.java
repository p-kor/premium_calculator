package com.p_kor.premium_calculator.service;

import com.p_kor.premium_calculator.model.Policy;
import com.p_kor.premium_calculator.model.RiskType;
import com.p_kor.premium_calculator.model.PolicySubObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PremiumCalculatorService {

    private final EnumMap<RiskType, TypeRiskStrategy> riskStrategies;

    public PremiumCalculatorService() {
        riskStrategies = new EnumMap<>(RiskType.class);
        riskStrategies.put(RiskType.FIRE, new FireTypeRiskStrategyImp());
        riskStrategies.put(RiskType.THEFT, new TheftTypeRiskStrategyImp());
        riskStrategies.put(RiskType.FLOOD, new FloodTypeRiskStrategyImp());
    }

    public BigDecimal getRiskInsuranceCoefficient(RiskType riskType, BigDecimal insuredAmount) {
        return riskStrategies.get(riskType).getRiskInsuranceCoefficient(insuredAmount);
    }

    public BigDecimal calculatePremiumByRiskType(RiskType riskType, BigDecimal insuredAmount) {
        return getRiskInsuranceCoefficient(riskType, insuredAmount).multiply(insuredAmount);
    }

    public Map<RiskType, BigDecimal> getInsuredTotalAmountsByRiskType(Policy policy) {
        return policy.objects().stream()
                .flatMap(object -> object.subObjects().stream())
                .collect(Collectors.groupingBy(
                        PolicySubObject::riskType,
                        () -> new EnumMap<>(RiskType.class),
                        Collectors.reducing(BigDecimal.ZERO, PolicySubObject::insuredAmount, BigDecimal::add)));
    }

    public BigDecimal calculatePolicyPremium(Policy policy) {
        return getInsuredTotalAmountsByRiskType(policy).entrySet().stream()
                .map(entry -> calculatePremiumByRiskType(entry.getKey(), entry.getValue()))
                .reduce(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), BigDecimal::add);
    }

}
