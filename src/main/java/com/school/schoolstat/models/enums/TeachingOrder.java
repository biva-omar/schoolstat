package com.school.schoolstat.models.enums;

public enum TeachingOrder {
    PUBLIC(1),
    PRIVE_LAIC(2),
    PRIVE_CATHOLIQUE(3),
    PRIVE_FRANCO_ARABE(4),
    PRIVE_PROTESTANT(5),

    LENGTH(5);

    

    private final int value;

    TeachingOrder(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
