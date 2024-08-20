package org.promo.data.data;

import java.time.LocalDateTime;

public record promo(
        Integer promoId,
        String promoName,
        Integer dataQuota,
        Integer timeLimit,
        LocalDateTime createdAt
) {
}
