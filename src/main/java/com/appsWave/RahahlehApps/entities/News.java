package com.appsWave.RahahlehApps.entities;

import com.appsWave.RahahlehApps.util.NewsUtility;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;

    private String arabicTitle;

    private String description;

    private String arabicDescription;

    private String date;

    private String imageUrl;

    private Status status;
    @Column(nullable = false)
    private boolean isDeleted;

    public LocalDate getDateAsLocalDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(NewsUtility.DATE_PATTERN);

        // Parse the string to a LocalDate object
        LocalDate date = LocalDate.parse(getDate(), formatter);
        return date;
    }
}
