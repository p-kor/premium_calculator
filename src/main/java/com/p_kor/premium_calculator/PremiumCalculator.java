package com.p_kor.premium_calculator;

import com.p_kor.premium_calculator.model.Policy;
import com.p_kor.premium_calculator.service.PremiumCalculatorService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PremiumCalculator {

    private final PremiumCalculatorService premiumCalculatorService;

    public PremiumCalculator(PremiumCalculatorService typeRiskStrategyService) {
        this.premiumCalculatorService = typeRiskStrategyService;
    }

    public BigDecimal calculate(Policy policy) {
        return premiumCalculatorService.calculatePolicyPremium(policy);
    }
}
