package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.pojo.User;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    /**
     * Spring容器和SpringMVC容器是有层次的（父子容器）
     * Spring容器：service对象+dao对象
     * SpringMVC容器：controller对象，，，，可以引用到Spring容器中的对象
     */


    @Autowired
    private ResumeService resumeService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Resume>  queryAll() throws Exception {
        return resumeService.queryResumeList();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session){
        String username = user.getUsername();
        String password = user.getPassword();
        if(username!=null&&username.equals("admin")&&password!=null&&password.equals("admin")){
            //将用户对象添加到Session中
            session.setAttribute("USER_SESSION",user);
            return "home";
        }
        model.addAttribute("msg","用户名或密码错误，请重新登录！");
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //清除session
        session.invalidate();
        return "redirect:login";
    }

    @RequestMapping(value = "home")
    public String toHome(){
        return "home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLoginPage(){
        return "login";
    }


    /**
     * 新增或修改请求接口
     */
    @RequestMapping("/addOrSave")
    public String addOrSave(@RequestBody Resume resume){
        resumeService.saveOrUpdate(resume);
        return "home";
    }

    /**
     * 根据id获取某个resume
     */
    @RequestMapping("/findOne")
    public @ResponseBody Resume findOne(@RequestParam("id") Integer id){
        System.out.println(id);
        return resumeService.findOne(id);
    }

    /**
     * 根据id删除
     */
    @RequestMapping("/deleteById")
    public  @ResponseBody String deleteById(@RequestParam("id") Integer id){
        System.out.println(id);
        return resumeService.delete(id);
    }
}
