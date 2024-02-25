package ru.nsu.ecoroads.model.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DefaultValue {
    NO_ID(-1L);

    private final long value;
}
