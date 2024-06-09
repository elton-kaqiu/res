package com.eltonkaqiu.resbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PENDING("Pending"), CANCELED("Canceled"), COMPLETED("Completed");
    private final String status;
}
