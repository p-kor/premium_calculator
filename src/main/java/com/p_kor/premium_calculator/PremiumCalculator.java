package com.p_kor.premium_calculator;

import com.p_kor.premium_calculator.model.Policy;
import com.p_kor.premium_calculator.model.PolicySubObject;
import com.p_kor.premium_calculator.model.RiskType;
import com.p_kor.premium_calculator.service.TypeRiskStrategyService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PremiumCalculator {

    private final TypeRiskStrategyService typeRiskStrategyService;

    public PremiumCalculator(TypeRiskStrategyService typeRiskStrategyService) {
        this.typeRiskStrategyService = typeRiskStrategyService;
    }

    public BigDecimal calculate(Policy policy) {
        Map<RiskType, BigDecimal> amounts = policy.objects().stream()
                .flatMap(object -> object.subObjects().stream())
                .collect(Collectors.groupingBy(
                        PolicySubObject::riskType,
                        () -> new EnumMap<>(RiskType.class),
                        Collectors.reducing(BigDecimal.ZERO, PolicySubObject::insuredAmount, BigDecimal::add)));

        BigDecimal premium = amounts.entrySet().stream()
                .map(entry -> entry.getValue().multiply(typeRiskStrategyService.getRiskInsuranceCoefficient(entry.getKey(), entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return premium.setScale(2, RoundingMode.HALF_UP);
    }
}
