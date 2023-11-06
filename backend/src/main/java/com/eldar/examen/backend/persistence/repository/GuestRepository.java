package com.eldar.examen.backend.persistence.repository;

import com.eldar.examen.backend.mapper.GuestMapper;
import com.eldar.examen.backend.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query("select g from Guest g")
    List<GuestMapper> getAll();
    List<GuestMapper> getAllByConfirmed(@Param("confirmed") Boolean confirmed);

}
