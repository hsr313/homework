package com.hsr.blog.controller;

import com.hsr.blog.repository.BlogRepository;
import com.hsr.blog.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private IArticleService articleService;

    @RequestMapping("/toBlogPage")
    public String toBlogPage(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
        model.addAttribute("articles", articleService.getArticleList(pageNum,pageSize));
        return "index";
    }
}
