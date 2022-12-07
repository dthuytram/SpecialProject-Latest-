package com.tramdt.service;


import com.tramdt.dto.NewsDto;
import com.tramdt.model.News;

import com.tramdt.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface INewsService {

    void createNews(NewsDto newsDto);
    List<News> findAll();
    void editNews(NewsDto newsDto);
    Page<News> findAllNews(Pageable pageable);
    void deleteNewsById(Long id);
    News findById(Long id);
    List<News> getAllNewsNotPagination();



}
