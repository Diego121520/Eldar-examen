package com.eldar.examen.backend.service;

import com.eldar.examen.backend.dto.GuestDTO;
import com.eldar.examen.backend.dto.CreateGuestRequestDTO;
import com.eldar.examen.backend.dto.UpdateGuestRequestDTO;
import com.eldar.examen.backend.model.Guest;

import java.util.List;

public interface GuestService {

    List<GuestDTO> findAll();

    List<GuestDTO> getAllByConfirmation(Boolean confirmation);

    Guest createGuest(CreateGuestRequestDTO requestDTO);


    Boolean editGuest(List<UpdateGuestRequestDTO> requestDTO);

    Boolean deleteGuest(Long id);
}


