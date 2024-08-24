package org.promo.data.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface usersRepository extends CrudRepository<users, String> {// add the <users, String>

    @Query("SELECT * FROM users WHERE msisdn = :msisdn LIMIT 1")
    Optional<users> selectByMsisdn(@Param("msisdn") String msisdn);

//    LocalDateTime time = LocalDateTime.now();
    @Query("INSERT INTO users (msisdn, created_at)\n" +
            "VALUES (:msisdn, :created_at)\n")
    Optional<users> insertUser(@Param("msisdn") String msisdn, @Param("createdAt") LocalDateTime createdAt);

    @Query("SELECT user_id FROM users WHERE msisdn = :msisdn")
    Integer getIdWithMsisdn(@Param("msisdn")String msisdn);

}
