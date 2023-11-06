package com.eldar.examen.backend.dto;

import lombok.NonNull;

import java.time.LocalDate;

public record UpdateGuestRequestDTO (

        @NonNull Long id,
        String firstName,
         String lastName,
        String dni,
         LocalDate birthDate,
        Boolean confirmed
) {
}
