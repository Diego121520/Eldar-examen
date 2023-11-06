package com.eldar.examen.backend.dto;

import lombok.NonNull;

import java.time.LocalDate;


public record CreateGuestRequestDTO(
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String dni,
        @NonNull LocalDate birthDate
) {
}
