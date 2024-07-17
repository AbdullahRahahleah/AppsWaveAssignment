package com.appsWave.RahahlehApps.dto;

import com.appsWave.RahahlehApps.util.ValidLocalDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewsDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String arabicTitle;

    @NotBlank
    private String description;

    @NotBlank
    private String arabicDescription;

    @ValidLocalDate
    private String date;

    @NotBlank
    private String imageUrl;

}
