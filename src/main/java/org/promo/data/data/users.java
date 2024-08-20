package org.promo.data.data;

import java.time.LocalDateTime;

public record users(
        Integer userId,
        String msisdn,
        LocalDateTime createdAt
) {
}
