package com.vxtech.capela.enums;

public enum TipoSanguineoEnum {

    A_POSITIVO("A_POSITIVO"),
    A_NEGATIVO("A_NEGATIVO"),
    B_POSITIVO("B_POSITIVO"),
    B_NEGATIVO("B_NEGATIVO"),
    AB_POSITIVO("AB_POSITIVO"),
    AB_NEGATIVO("AB_NEGATIVO"),
    O_POSITIVO("O_POSITIVO"),
    O_NEGATIVO("O_NEGATIVO");

    public final String type;

    private TipoSanguineoEnum(String type) {
        this.type = type;
    }
}
