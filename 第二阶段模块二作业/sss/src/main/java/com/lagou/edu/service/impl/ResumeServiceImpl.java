package com.lagou.edu.service.impl;

import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public List<Resume> queryResumeList() throws Exception {
        return resumeDao.findAll();
    }

    @Override
    public void saveOrUpdate(Resume resume) {
        resumeDao.save(resume);
    }

    @Override
    public String delete(long id) {
        resumeDao.deleteById(id);
        return "The data has been deleted";
    }

    @Override
    public Resume findOne(long id) {
        Optional<Resume> optional =  resumeDao.findById(id);
        Resume resume = optional.get();
        return resume;
    }
}
