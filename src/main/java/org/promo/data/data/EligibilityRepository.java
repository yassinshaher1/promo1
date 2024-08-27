package org.promo.data.data;

import org.promo.data.services.EligibilityStatus;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EligibilityRepository extends CrudRepository<Eligibility, String>{// add the <eligibility, String>

    @Query("INSERT INTO eligibility (user_id, promo_id, status, start_time, end_time, data_consumed, created_at) " +
            "VALUES (:userId, :promoId, :status, :startTime, :endTime, :dataConsumed, :createdAt)")
    Optional<Eligibility> insertEligibility(
            Integer userId,
            Integer promoId,
            String status,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Integer dataConsumed,
            LocalDateTime createdAt);


    @Query("SELECT * FROM eligibility WHERE user_id = :userId")
    Optional<Eligibility> getEligibilityWithUserId(Integer userId);

    @Query("SELECT data_consumed FROM eligibility WHERE promo_id = :promoId LIMIT 1")
    Integer dataConsumedByPromoId(Integer promoId);

    @Query("SELECT eligibility_id FROM eligibility WHERE user_id= :userId ORDER BY created_at DESC LIMIT 1")
    Integer latestEligibilityIdByUserId(Integer userId);

    @Modifying
    @Query("UPDATE eligibility SET status = :status WHERE eligibility_id = :eligibilityId")
    void updateEligibilityStatus(EligibilityStatus status, Integer eligibilityId);


}
