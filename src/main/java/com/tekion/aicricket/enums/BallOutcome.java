package com.tekion.aicricket.enums;

public enum BallOutcome {
    NORUN(0l), ONE(1l), TWO(2l), THREE(3l), FOUR(4l), SIX(6l), WICKET(0l);
    private final Long value;

    BallOutcome(Long intValue) {
        this.value = intValue;
    }

    public Long getValue() {
        return value;
    }
}