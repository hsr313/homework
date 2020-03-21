package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotation.LagouSecurity;
import com.lagou.edu.mvcframework.annotations.LagouAutowired;
import com.lagou.edu.mvcframework.annotations.LagouController;
import com.lagou.edu.mvcframework.annotations.LagouRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@LagouController
@LagouRequestMapping("/demo")
public class DemoController {


    @LagouAutowired
    private IDemoService demoService;


    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param name
     * @return
     */
    @LagouSecurity({"Jack","Rose"})
    @LagouRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response,String name) {
        return demoService.get(name);
    }

    @LagouSecurity({"Tom","Jerry"})
    @LagouRequestMapping("/search")
    public String search(HttpServletRequest request, HttpServletResponse response,String name) {
        return demoService.get(name);
    }
}