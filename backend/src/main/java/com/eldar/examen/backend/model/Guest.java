package com.eldar.examen.backend.model;

import com.eldar.examen.backend.dto.CreateGuestRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "guest_table")
@Entity
public class Guest extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String dni;

    @Column
    private LocalDate birthDate;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean confirmed;


    public Guest(CreateGuestRequestDTO requestDTO) {
        this.firstName = requestDTO.firstName();
        this.lastName = requestDTO.lastName();
        this.dni = requestDTO.dni();
        this.birthDate = requestDTO.birthDate();
        this.confirmed = false;
    }

    public Guest() {

    }
}
