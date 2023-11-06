package com.eldar.examen.backend.restController;

import com.eldar.examen.backend.dto.GuestDTO;
import com.eldar.examen.backend.dto.CreateGuestRequestDTO;
import com.eldar.examen.backend.dto.UpdateGuestRequestDTO;
import com.eldar.examen.backend.model.Guest;
import com.eldar.examen.backend.service.GuestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/guest")
public class GuestRestController {

    private final GuestService guestService;

    @GetMapping("/all")
    public ResponseEntity<List<GuestDTO>> findAll() {
        log.info("GuestRestController.findAll");
        return ResponseEntity.ok(guestService.findAll());
    }

    @GetMapping("/confirmed")
    public ResponseEntity<List<GuestDTO>> getAllConfirmed() {
        log.info("GuestRestController.getAllConfirmed");
        return ResponseEntity.ok(guestService.getAllByConfirmation(true));
    }

    @GetMapping("/unconfirmed")
    public ResponseEntity<List<GuestDTO>> getAllUnconfirmed() {
        log.info("GuestRestController.getAllUnconfirmed");
        return ResponseEntity.ok(guestService.getAllByConfirmation(false));
    }

    @PostMapping("/create")
    public ResponseEntity<Guest> createGuest(@RequestBody @Validated CreateGuestRequestDTO requestDTO) {
        log.info("GuestRestController.createGuest - request {} ", requestDTO.toString());
        return ResponseEntity.ok(guestService.createGuest(requestDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> editGuest(@RequestBody @Validated List<UpdateGuestRequestDTO> requestDTO) {
        log.info("GuestRestController.editGuest - size ({})", requestDTO.size());
        return ResponseEntity.ok(guestService.editGuest(requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteGuest(@PathVariable Long id) {
        log.info("GuestRestController.deleteGuest - id ({})", id);
        return ResponseEntity.ok(guestService.deleteGuest(id));
    }

}
