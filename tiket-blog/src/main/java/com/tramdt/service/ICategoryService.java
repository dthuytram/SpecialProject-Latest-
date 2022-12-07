package com.tramdt.service;
import com.tramdt.model.Category;
import com.tramdt.model.News;
import java.util.List;

public interface ICategoryService {

    List<Category> findAllCategory();
}
