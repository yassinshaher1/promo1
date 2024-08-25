package org.promo.data.data;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Promo(
        @Id
        Integer promoId,
        String promoName,
        Integer dataQuota,
        Integer timeLimit,
        LocalDateTime createdAt
) {
}
