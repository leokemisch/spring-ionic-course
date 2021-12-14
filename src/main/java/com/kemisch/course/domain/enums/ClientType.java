package com.kemisch.course.domain.enums;

public enum ClientType {
    PHYSICAL_STAFF(1, "Pessoa Física"),
    LEGAL_PERSON(2, "Pessoa Jurídica");

    private Integer id;
    private String description;

    private ClientType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static ClientType toEnum(Integer id) {
        if (id == null)
            return null;

        for (ClientType x : ClientType.values()) {
            if (id.equals(x.getId()))
                return x;
        }

        throw new IllegalArgumentException("Invalid id: " + id);
    }
}
