package com.kemisch.course.domain.enums;

public enum PaymentState {

    PENDING(1, "Pendente"),
    SETTLED(2, "Quitado"),
    CANCELED(3, "Cancelado");

    private Integer id;
    private String description;

    private PaymentState(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentState toEnum(Integer id) {
        if (id == null)
            return null;

        for (PaymentState x : PaymentState.values()) {
            if (id.equals(x.getId()))
                return x;
        }

        throw new IllegalArgumentException("Invalid id: " + id);
    }


}
