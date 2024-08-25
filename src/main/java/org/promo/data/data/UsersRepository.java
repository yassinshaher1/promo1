package org.promo.data.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, String> {// add the <users, String>

    @Query("SELECT * FROM users WHERE msisdn = :msisdn LIMIT 1")
    Optional<Users> selectByMsisdn(String msisdn);

//    LocalDateTime time = LocalDateTime.now();
    @Query("INSERT INTO users (msisdn, created_at) VALUES (:msisdn, :createdAt)")
    void insertUser(String msisdn, LocalDateTime createdAt);

    @Query("SELECT user_id FROM users WHERE msisdn = :msisdn")
    Integer getIdWithMsisdn(String msisdn);

}
