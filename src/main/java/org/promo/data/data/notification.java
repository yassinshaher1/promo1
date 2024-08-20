package org.promo.data.data;

import java.time.LocalDateTime;

public record notification(
    Integer notificationId,
    Integer userId,
    String message,
    String status,
    LocalDateTime sentAt
) {
}
