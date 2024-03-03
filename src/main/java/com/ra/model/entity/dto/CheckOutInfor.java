package com.ra.model.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckOutInfor {
    private String receiveName;
    private String receivePhone;
    private String receiveAddress;
    private String note;
}
