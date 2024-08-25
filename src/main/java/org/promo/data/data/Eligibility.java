package org.promo.data.data;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Eligibility(
        @Id
        Integer eligibilityId,
        Integer userId,
        Integer promoId,
        String status,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer dataConsumed,
        LocalDateTime createdAt
) {
        public static Eligibility UpdateStatus(Eligibility eligibility,String status){
                return new Eligibility(eligibility.eligibilityId(),eligibility.userId(),eligibility.promoId(),status,LocalDateTime.now(),eligibility.endTime(),eligibility.dataConsumed(),eligibility.createdAt());
        }
}
