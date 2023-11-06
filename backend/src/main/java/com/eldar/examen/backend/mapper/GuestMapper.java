package com.eldar.examen.backend.mapper;

import java.time.LocalDate;

public interface GuestMapper {
    Long getId();
    String getFirstName();
    String getLastName();
    String getDni();

    LocalDate getBirthDate();
}
