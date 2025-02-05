package com.p_kor.premium_calculator;

import com.p_kor.premium_calculator.model.*;
import com.p_kor.premium_calculator.service.TypeRiskStrategyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PremiumCalculatorApplicationTests {

    @Autowired
    private TypeRiskStrategyService typeRiskStrategyService;

    @Autowired
    private PremiumCalculator premiumCalculator;

    @Test
    void acceptanceTest1() {
        BigDecimal EXPECTED_RESULT = new BigDecimal("2.28");

        List<PolicySubObject> subObjects = List.of(
                new PolicySubObject("TV", new BigDecimal("100.0"), RiskType.FIRE),
                new PolicySubObject("Door bell", new BigDecimal("8.0"), RiskType.THEFT));

        List<PoliceObject> objects = List.of(
                new PoliceObject("Cottage", subObjects));

        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.APPROVED, objects);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(EXPECTED_RESULT, premium, "Premium should be 2.28");
    }

    @Test
    void acceptanceTest2() {
        BigDecimal EXPECTED_RESULT = new BigDecimal("17.13");

        List<PolicySubObject> subObjectsFlat47 = List.of(
                new PolicySubObject("Air conditioner", new BigDecimal("500.0"), RiskType.FIRE));
        List<PolicySubObject> subObjectsHouse11 = List.of(
                new PolicySubObject("Oven", new BigDecimal("102.51"), RiskType.THEFT));

        List<PoliceObject> objects = new ArrayList<>();
        objects.add(new PoliceObject("Flat 47", subObjectsFlat47));
        objects.add(new PoliceObject("House 11", subObjectsHouse11));

        Policy policy = new Policy("LV20-02-100000-6", PolicyStatus.APPROVED, objects);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(EXPECTED_RESULT, premium, "Premium should be 17.13");
    }

    @Test
    void acceptanceTest3() {
        BigDecimal EXPECTED_RESULT = new BigDecimal("17.13");

        List<PolicySubObject> subObjectsFlat47 = List.of(
                new PolicySubObject("Air conditioner", new BigDecimal("500.0"), RiskType.FLOOD));
        List<PolicySubObject> subObjectsHouse11 = List.of(
                new PolicySubObject("Oven", new BigDecimal("102.51"), RiskType.THEFT));

        List<PoliceObject> objects = new ArrayList<>();
        objects.add(new PoliceObject("Flat 47", subObjectsFlat47));
        objects.add(new PoliceObject("House 11", subObjectsHouse11));

        Policy policy = new Policy("#@$@!-1", PolicyStatus.REGISTERED, objects);

        BigDecimal premium = premiumCalculator.calculate(policy);
        assertEquals(EXPECTED_RESULT, premium, "Premium should be 17.13");
    }
}
