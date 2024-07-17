package com.appsWave.RahahlehApps.controller;


import com.appsWave.RahahlehApps.dto.ApproveDTO;
import com.appsWave.RahahlehApps.dto.NewsDTO;
import com.appsWave.RahahlehApps.entities.News;
import com.appsWave.RahahlehApps.entities.Role;
import com.appsWave.RahahlehApps.entities.Status;
import com.appsWave.RahahlehApps.entities.User;
import com.appsWave.RahahlehApps.repositorty.NewsRepository;
import com.appsWave.RahahlehApps.util.NewsUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 1- Here I define different ways to validate user authirty:
 *      A. at read method, I do read the logged in user then I checked the user role.
 *      B. for delete, I define two different methods, one for normal user and other for other users,
 *          by configuration the security configuration (url).
 * 2- I used post to write new object and put to approve news (update object).
 *
 */

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsRepository newsRepository;


    @GetMapping("/read")
    public ResponseEntity<NewsDTO> read(@RequestBody Map<String, String> body) throws Exception {
        News news = newsRepository.findActiveNewsByTitle(body.get("title")).orElseThrow(()-> new Exception("invalid title"));
        User user = NewsUtility.getLoggedInUser();
        if(user != null && user.getRole() == Role.NORMAL){
            if(!news.getStatus().equals(Status.APPROVED))
                throw new Exception("This title not in the approved status");
        }
        return ResponseEntity.ok(NewsUtility.mapNewsToNewsDto(news));
    }

    @PostMapping("/write")
    public ResponseEntity<NewsDTO> write(@Valid @RequestBody NewsDTO newsDTO){
        News news = NewsUtility.mapNewsDtoToNews(newsDTO);
        news.setDeleted(false);
        news.setStatus(Status.PENDING);
        NewsDTO savedNews = NewsUtility.mapNewsToNewsDto(newsRepository.save(news));
        return ResponseEntity.ok(savedNews);
    }

    @PutMapping("/approve")
    public ResponseEntity<String> approve(@RequestBody ApproveDTO approveDTO) throws Exception {
        News news = newsRepository.findActiveNewsByTitle(approveDTO.getTitle()).orElseThrow(()->new Exception("Invalid title "));
        news.setStatus(Status.APPROVED);
        newsRepository.save(news);
        return ResponseEntity.ok(approveDTO.getTitle() +" Approved");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, String> body) throws Exception {
        String title = body.get("title");
        News news = newsRepository.findActiveNewsByTitle(title).orElse(null);
        if(news ==null)
            throw new Exception("invalid title");

        if(news.getStatus().equals(Status.APPROVED))
            throw new Exception("You can not delete news in approved phase, ask admin to use forceDelete");

        newsRepository.delete(news);
        return ResponseEntity.ok("deleted "+title);
    }


    @PostMapping("/forceDelete")
    public ResponseEntity<String> forceDelete(@RequestBody Map<String, String> body) throws Exception {
        String title = body.get("title");
        News news = newsRepository.findActiveNewsByTitle(title).orElse(null);

        if(news ==null)
            throw new Exception("invalid title");

        newsRepository.delete(news);
        return ResponseEntity.ok("deleted "+title);
    }

}
