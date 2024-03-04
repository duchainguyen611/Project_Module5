package com.ra.model.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckOutInfor {
    @NotBlank(message = "Enter Receive Name")
    private String receiveName;
    @NotBlank(message = "Enter Phone")
    private String receivePhone;
    @NotBlank(message = "Enter Address")
    private String receiveAddress;
    @NotBlank(message = "Enter Note")
    private String note;
}
