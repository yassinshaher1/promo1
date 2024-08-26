package org.promo.data.data;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Prize(
        @Id
        Integer prizeId,
        Integer eligibilityId,
        String prizeStatus,
        LocalDateTime grantedAt
) {
}
