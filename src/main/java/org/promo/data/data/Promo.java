package org.promo.data.data;

import java.time.LocalDateTime;

public record Promo(
        Integer promoId,
        String promoName,
        Integer dataQuota,
        Integer timeLimit,
        LocalDateTime createdAt
) {
}
