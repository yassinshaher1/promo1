package org.promo.data.data;

import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

public record prize(
        Integer prizeId,
        Integer eligibilityId,
        String prizeStatus,
        LocalDateTime grantedAt
) {
}
