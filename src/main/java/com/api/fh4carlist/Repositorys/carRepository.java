package com.api.fh4carlist.Repositorys;

import com.api.fh4carlist.Models.carModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface carRepository extends JpaRepository<carModel, UUID> {
    public boolean existsByInGameID(String inGameID);
}
