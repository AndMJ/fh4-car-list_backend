package com.api.fh4carlist.Repositorys.user;

import com.api.fh4carlist.Models.user.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface userRepository extends JpaRepository<userModel, UUID> {
    //get just the user data, with no cars owned
    //@Query(value = "SELECT id,name,email,password FROM userModel WHERE id = ?1")
    //public Optional<Object> getUserData(UUID id);
}
