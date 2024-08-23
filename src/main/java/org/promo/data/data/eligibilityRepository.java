package org.promo.data.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface eligibilityRepository extends CrudRepository<eligibility, String>{// add the <eligibility, String>

    @Query("INSERT INTO eligibility (user_id, promo_id, status, start_time, end_time, data_consumed, created_at\n" +
            "VALUES (:user_id, :promo_id, :status, :start_time, :end_time, :data_consumed, :created_at")
    Optional<eligibility> insertEligibility(Integer userId,
                                            Integer promoId,
                                            String status,
                                            LocalDateTime startTime,
                                            LocalDateTime endTime,
                                            Integer dataConsumed,
                                            LocalDateTime createdAt);

    @Query("SELECT * FROM eligibility WHERE user_id = : user_id")
    Optional<eligibility> getEligibilityWithUserId(Integer userId);


}
