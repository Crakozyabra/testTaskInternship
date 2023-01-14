package com.game.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Race {
    @Enumerated(EnumType.STRING)
    HUMAN,
    @Enumerated(EnumType.STRING)
    DWARF,
    @Enumerated(EnumType.STRING)
    ELF,
    @Enumerated(EnumType.STRING)
    GIANT,
    @Enumerated(EnumType.STRING)
    ORC,
    @Enumerated(EnumType.STRING)
    TROLL,
    @Enumerated(EnumType.STRING)
    HOBBIT
}