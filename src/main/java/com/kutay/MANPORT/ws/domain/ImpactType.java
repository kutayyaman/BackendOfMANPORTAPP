package com.kutay.MANPORT.ws.domain;

public enum ImpactType {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int impactCode;

    private ImpactType(int impactCode) {
        this.impactCode = impactCode;
    }

    public int getImpactCode() {
        return this.impactCode;
    }
}
