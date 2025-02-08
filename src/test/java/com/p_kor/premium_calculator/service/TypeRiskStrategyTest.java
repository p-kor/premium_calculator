package com.p_kor.premium_calculator.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TypeRiskStrategyTest {
    private TypeRiskStrategy strategy;

    @Test
    void testFireTypeRiskCoefficients() {
        final BigDecimal BASE_AMOUNT = new BigDecimal("100.0");
        final BigDecimal EXPECTED_COEFFICIENT_BASE_AMOUNT = new BigDecimal("0.014");
        final BigDecimal OVER_AMOUNT = new BigDecimal("101.0");
        final BigDecimal EXPECTED_COEFFICIENT_OVER_AMOUNT = new BigDecimal("0.024");

        strategy = new FireTypeRiskStrategyImp();

        assertAll(
                () -> assertEquals(EXPECTED_COEFFICIENT_BASE_AMOUNT,
                        strategy.getRiskInsuranceCoefficient(BASE_AMOUNT),
                        "Base coefficient should be " + EXPECTED_COEFFICIENT_BASE_AMOUNT),
                () -> assertEquals(EXPECTED_COEFFICIENT_OVER_AMOUNT,
                        strategy.getRiskInsuranceCoefficient(OVER_AMOUNT),
                        "Over coefficient should be " + EXPECTED_COEFFICIENT_OVER_AMOUNT));
    }

    @Test
    void testTheftTypeRiskCoefficients() {
        final BigDecimal BASE_AMOUNT = new BigDecimal("14.99");
        final BigDecimal EXPECTED_COEFFICIENT_BASE_AMOUNT = new BigDecimal("0.11");
        final BigDecimal OVER_AMOUNT = new BigDecimal("15.0");
        final BigDecimal EXPECTED_COEFFICIENT_OVER_AMOUNT = new BigDecimal("0.05");

        strategy = new TheftTypeRiskStrategyImp();

        assertAll(
                () -> assertEquals(EXPECTED_COEFFICIENT_BASE_AMOUNT,
                        strategy.getRiskInsuranceCoefficient(BASE_AMOUNT),
                        "Base coefficient should be " + EXPECTED_COEFFICIENT_BASE_AMOUNT),
                () -> assertEquals(EXPECTED_COEFFICIENT_OVER_AMOUNT,
                        strategy.getRiskInsuranceCoefficient(OVER_AMOUNT),
                        "Over coefficient should be " + EXPECTED_COEFFICIENT_OVER_AMOUNT));
    }

    @Test
    void testFloodTypeRiskCoefficients() {
        final BigDecimal BASE_AMOUNT = new BigDecimal("100.0");
        final BigDecimal EXPECTED_COEFFICIENT_BASE_AMOUNT = new BigDecimal("0.014");
        final BigDecimal OVER_AMOUNT = new BigDecimal("101.0");
        final BigDecimal EXPECTED_COEFFICIENT_OVER_AMOUNT = new BigDecimal("0.024");

        strategy = new FloodTypeRiskStrategyImp();

        assertAll(
                () -> assertEquals(EXPECTED_COEFFICIENT_BASE_AMOUNT,
                        strategy.getRiskInsuranceCoefficient(BASE_AMOUNT),
                        "Base coefficient should be " + EXPECTED_COEFFICIENT_BASE_AMOUNT),
                () -> assertEquals(EXPECTED_COEFFICIENT_OVER_AMOUNT,
                        strategy.getRiskInsuranceCoefficient(OVER_AMOUNT),
                        "Over coefficient should be " + EXPECTED_COEFFICIENT_OVER_AMOUNT));
    }

}