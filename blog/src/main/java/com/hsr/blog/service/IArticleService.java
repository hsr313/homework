package com.hsr.blog.service;

import com.hsr.blog.pojo.Article;
import org.springframework.data.domain.Page;

public interface IArticleService {
    public Page<Article> getArticleList(int pageNum, int pageSize);
}
