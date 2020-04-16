package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> queryResumeList() throws Exception;

    //增或改
    void saveOrUpdate(Resume resume);

    //删除
    String delete(long id);

    Resume findOne(long id);
}
