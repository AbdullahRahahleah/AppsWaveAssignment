package com.appsWave.RahahlehApps.repositorty;

import com.appsWave.RahahlehApps.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News,Long> {
    @Query("select n from News n where n.title = :title and n.isDeleted=false")
    Optional<News> findActiveNewsByTitle(@Param("title") String title);

    @Query("select n from News n where n.isDeleted =false")
    List<News> findAllActiveNews();
}
