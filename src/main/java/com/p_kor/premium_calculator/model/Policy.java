package com.p_kor.premium_calculator.model;

import java.util.Collection;

public record Policy(String number, PolicyStatus status, Collection<PoliceObject> objects) {
}
