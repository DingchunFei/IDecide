package com.uom.idecide.result.service;


import com.uom.idecide.result.dao.AnswerDao;
import com.uom.idecide.result.pojo.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerDao AnswerDao;

    public void add(Answer answer) {
        AnswerDao.save(answer);
    }
}
