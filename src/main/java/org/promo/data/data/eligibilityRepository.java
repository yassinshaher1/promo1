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

    @Query("SELECT data_consumed FROM eligibility WHERE promo_id = :promo_id LIMIT 1")
    Integer dataConsumedByPromoId(Integer promoId);

    @Query("SELECT eligibility_id FROM eligibility WHERE user_id= :user_id ORDER BY created_at DESC LIMIT 1")
    Integer latestEligibilityIdByUserId(Integer userId);

    @Query("UPDATE eligibility SET status = :status WHERE eligibility_id = :eligibility_id")
    void updateEligibilityStatus(String Status,Integer eligibilityId);


}
