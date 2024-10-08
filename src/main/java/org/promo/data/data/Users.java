package org.promo.data.data;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Users(
        @Id
        Integer userId,
        String msisdn,
        LocalDateTime createdAt
) {
}
