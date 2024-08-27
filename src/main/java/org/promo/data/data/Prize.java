package org.promo.data.data;

import org.promo.data.services.PrizeStatus;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Prize(
        @Id
        Integer prizeId,
        Integer eligibilityId,
        PrizeStatus prizeStatus,
        LocalDateTime grantedAt
) {
}
