package com.api.fh4carlist.Repositorys;

import com.api.fh4carlist.Models.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface userRepository extends JpaRepository<userModel, UUID> {
}
