package com.game.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Profession {
    @Enumerated(EnumType.STRING)
    WARRIOR,
    @Enumerated(EnumType.STRING)
    ROGUE,
    @Enumerated(EnumType.STRING)
    SORCERER,
    @Enumerated(EnumType.STRING)
    CLERIC,
    @Enumerated(EnumType.STRING)
    PALADIN,
    @Enumerated(EnumType.STRING)
    NAZGUL,
    @Enumerated(EnumType.STRING)
    WARLOCK,
    @Enumerated(EnumType.STRING)
    DRUID
}
