package com.p_kor.premium_calculator.model;

import java.util.Collection;

public record PoliceObject(String name, Collection<PolicySubObject> subObjects) {
}
