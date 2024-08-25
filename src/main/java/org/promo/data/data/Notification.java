package org.promo.data.data;

import java.time.LocalDateTime;

public record Notification(
    Integer notificationId,
    Integer userId,
    String message,
    String status,
    LocalDateTime sentAt
) {
}
