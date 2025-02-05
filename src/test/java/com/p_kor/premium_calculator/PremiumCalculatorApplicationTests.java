package com.p_kor.premium_calculator;

import com.p_kor.premium_calculator.model.*;
import com.p_kor.premium_calculator.service.TypeRiskStrategyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PremiumCalculatorApplicationTests {
    private PremiumCalculator premiumCalculator;

    @BeforeEach
    void init() {
        TypeRiskStrategyService typeRiskStrategyService = new TypeRiskStrategyService();
        premiumCalculator = new PremiumCalculator(typeRiskStrategyService);
    }

    @Test
    void acceptanceTest1() {
        List<PolicySubObject> subObjects = List.of(
                new PolicySubObject("TV", new BigDecimal("100.0"), RiskType.FIRE),
                new PolicySubObject("Door bell", new BigDecimal("8.0"), RiskType.THEFT));
        List<PoliceObject> objects = List.of(
                new PoliceObject("Cottage", subObjects));
        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.APPROVED, objects);
        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(new BigDecimal("2.28"), premium, "Premium should be 2.28");
    }

    @Test
    void acceptanceTest2() {
        List<PolicySubObject> subObjectsFlat47 = List.of(
                new PolicySubObject("Air conditioner", new BigDecimal("500.0"), RiskType.FIRE));
        List<PolicySubObject> subObjectsHouse11 = List.of(
                new PolicySubObject("Oven", new BigDecimal("102.51"), RiskType.THEFT));

        List<PoliceObject> objects = new ArrayList<>();
        objects.add(new PoliceObject("Flat 47", subObjectsFlat47));
        objects.add(new PoliceObject("House 11", subObjectsHouse11));

        Policy policy = new Policy("LV20-02-100000-6", PolicyStatus.APPROVED, objects);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(new BigDecimal("17.13"), premium, "Premium should be 17.13");
    }

    @Test
    void acceptanceTest3() {
        List<PolicySubObject> subObjectsFlat47 = List.of(
                new PolicySubObject("Air conditioner", new BigDecimal("500.0"), RiskType.FLOOD));
        List<PolicySubObject> subObjectsHouse11 = List.of(
                new PolicySubObject("Oven", new BigDecimal("102.51"), RiskType.THEFT));

        List<PoliceObject> objects = new ArrayList<>();
        objects.add(new PoliceObject("Flat 47", subObjectsFlat47));
        objects.add(new PoliceObject("House 11", subObjectsHouse11));

        Policy policy = new Policy("LV20-02-100000-6", PolicyStatus.APPROVED, objects);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(new BigDecimal("17.13"), premium, "Premium should be 17.13");
    }
}
