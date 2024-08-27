package org.promo.data.data;

import org.promo.data.services.EligibilityStatus;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Eligibility(
        @Id
        Integer eligibilityId,
        Integer userId,
        Integer promoId,
        EligibilityStatus status,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Integer dataConsumed,
        LocalDateTime createdAt
) {
        public static Eligibility UpdateStatus(Eligibility eligibility,EligibilityStatus status){
                return new Eligibility(eligibility.eligibilityId(),eligibility.userId(),eligibility.promoId(),status,LocalDateTime.now(),eligibility.endTime(),eligibility.dataConsumed(),eligibility.createdAt());
        }
}
