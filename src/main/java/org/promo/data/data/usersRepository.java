package org.promo.data.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends CrudRepository<users, String> {// add the <users, String>

    @Query("SELECT * FROM users WHERE msisdn = : msisdn LIMIT 1")
    Optional<users> selectByMsisdn(String msisdn);

}
