package org.promo.data.data;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record eligibility(
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
}
