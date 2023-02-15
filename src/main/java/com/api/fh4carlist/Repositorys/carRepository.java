package com.api.fh4carlist.Repositorys;

import com.api.fh4carlist.Models.carModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface carRepository extends JpaRepository<carModel, UUID> {
}
