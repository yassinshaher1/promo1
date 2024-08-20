package org.promo.data.data;

import java.time.LocalDateTime;

public record eligibility(
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
