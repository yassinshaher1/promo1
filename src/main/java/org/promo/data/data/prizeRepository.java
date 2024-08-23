package org.promo.data.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface prizeRepository extends CrudRepository<prize, String>{

    @Query("INSERT INTO prize (eligibility_id, prize_status, granted_at)\n" +
            "VALUES (:eligibility_id, :prize_status, :granted_at")
    void insertPrize(Integer eligibility_id, String prizeStatus, LocalDateTime grantedAt);
}

