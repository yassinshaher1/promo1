package org.promo.data.data;

import java.time.LocalDateTime;

public record Prize(
        Integer prizeId,
        Integer eligibilityId,
        String prizeStatus,
        LocalDateTime grantedAt
) {
}
