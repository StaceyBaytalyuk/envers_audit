package org.example.dao.audit;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AuditAction {
    CREATE((byte) 0, "Created"),
    EDIT((byte) 1, "Edited"),
    DELETE((byte) 2, "Deleted");

    private final byte code;
    private final String name;

    AuditAction(byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public static AuditAction getActionByCode(@NotNull Byte code) {
        return Arrays.stream(AuditAction.values())
                .filter(a -> code == a.code)
                .findFirst().orElseThrow();
    }

}
