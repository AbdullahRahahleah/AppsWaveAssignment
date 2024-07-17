package com.appsWave.RahahlehApps.tasks;

import com.appsWave.RahahlehApps.entities.News;
import com.appsWave.RahahlehApps.repositorty.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RemoveOldNewsTask {

    private final NewsRepository newsRepository;

    /**
     * run the task every 0 second, 0 miniute 0 hours (which means midnight), * day of month * month and ? means any day
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void softDeleteExceedsPublishDate(){
        List<News> allNews = newsRepository.findAllActiveNews();
        for(News news:allNews){
            if(news.getDate() != null && LocalDate.now().isAfter(news.getDateAsLocalDate())){
                news.setDeleted(true);
                newsRepository.save(news);
            }
        }
    }
}
