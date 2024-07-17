package com.appsWave.RahahlehApps.util;

import com.appsWave.RahahlehApps.dto.NewsDTO;
import com.appsWave.RahahlehApps.entities.News;
import com.appsWave.RahahlehApps.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class NewsUtility {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static News mapNewsDtoToNews(NewsDTO newsDTO){
        News news = new News();
        news.setDate(newsDTO.getDate().toString());
        news.setTitle(newsDTO.getTitle());
        news.setArabicTitle(newsDTO.getArabicTitle());
        news.setDescription(newsDTO.getDescription());
        news.setArabicDescription(newsDTO.getArabicDescription());
        news.setImageUrl(newsDTO.getImageUrl());
        return news;
    }
    public static NewsDTO mapNewsToNewsDto(News news){
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setDate(news.getDate());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setArabicTitle(news.getArabicTitle());
        newsDTO.setDescription(news.getDescription());
        newsDTO.setArabicDescription(news.getArabicDescription());
        newsDTO.setImageUrl(news.getImageUrl());
        return newsDTO;
    }
    public static User getLoggedInUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            return  ((User) principal);
        }
        return  null;
    }
}
