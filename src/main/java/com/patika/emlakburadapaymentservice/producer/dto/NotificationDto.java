package com.patika.emlakburadapaymentservice.producer.dto;

import com.patika.emlakburadapaymentservice.producer.dto.enums.NotificationType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDto {

    private NotificationType notificationType;
    private Long packageId;
    private Long userId;
}
