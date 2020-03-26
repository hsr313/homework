package com.hsr.blog.repository;

import com.hsr.blog.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Integer> {

}
