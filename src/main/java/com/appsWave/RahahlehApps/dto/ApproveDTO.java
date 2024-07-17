package com.appsWave.RahahlehApps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ApproveDTO {
    @NotEmpty
    private String title;
}
