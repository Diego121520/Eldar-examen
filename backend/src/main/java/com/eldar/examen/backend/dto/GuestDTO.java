package com.eldar.examen.backend.dto;

import lombok.Data;

import java.time.LocalDate;

public record GuestDTO (
         Long id,

         String firstName,

         String lastName,

         String dni,

         LocalDate birthDate
) {


}
