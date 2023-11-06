package com.eldar.examen.backend.service.impl;

import com.eldar.examen.backend.dto.GuestDTO;
import com.eldar.examen.backend.dto.CreateGuestRequestDTO;
import com.eldar.examen.backend.dto.UpdateGuestRequestDTO;
import com.eldar.examen.backend.mapper.GuestMapper;
import com.eldar.examen.backend.model.Guest;
import com.eldar.examen.backend.persistence.repository.GuestRepository;
import com.eldar.examen.backend.service.GuestService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Override
    public List<GuestDTO> findAll() {

        List<GuestMapper> guestMappers = guestRepository.getAll();

        return getGuestMapDTO(guestMappers);
    }

    private List<GuestDTO> getGuestMapDTO(List<GuestMapper> guestMappers) {
        return guestMappers.stream()
                .map(guestMapper -> new GuestDTO(
                        guestMapper.getId(),
                        guestMapper.getFirstName(),
                        guestMapper.getLastName(),
                        guestMapper.getDni(),
                        guestMapper.getBirthDate()
                ))
                .toList();
    }

    @Override
    public List<GuestDTO> getAllByConfirmation(Boolean confirmation) {

        List<GuestMapper> guestMappers = guestRepository.getAllByConfirmed(confirmation);

        return getGuestMapDTO(guestMappers);
    }


    @Override
    public Guest createGuest(CreateGuestRequestDTO requestDTO) {
        Guest guest = new Guest(requestDTO);

        return guestRepository.save(guest);
    }

    @Override
    public Boolean editGuest(List<UpdateGuestRequestDTO> requestDTO) {
        List<Guest> guestList = new ArrayList<>();

        requestDTO.forEach(request -> {

            Guest guest = guestRepository.findById(request.id()).orElseThrow();

            if (StringUtils.isNotBlank(request.firstName())) {
                guest.setFirstName(request.firstName());
            }
            if (StringUtils.isNotBlank(request.lastName())) {
                guest.setLastName(request.lastName());
            }
            if (StringUtils.isNotBlank(request.dni())) {
                guest.setDni(request.dni());
            }
            if(request.birthDate() != null){
                guest.setBirthDate(request.birthDate());
            }
            if(request.confirmed() != null) {
                guest.setConfirmed(request.confirmed());
            }

            guestList.add(guest);
        });

        guestRepository.saveAll(guestList);

        return true;
    }

    @Override
    public Boolean deleteGuest(Long id) {

        Guest guest = guestRepository.findById(id).orElseThrow();

        guestRepository.delete(guest);

        return true;
    }

}
