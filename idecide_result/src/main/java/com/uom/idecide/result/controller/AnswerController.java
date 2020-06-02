package com.uom.idecide.result.controller;

import com.uom.idecide.result.pojo.Answer;
import com.uom.idecide.result.service.AnswerService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(method= RequestMethod.POST)
    public Result add(@RequestBody Answer answer) {
        answerService.add(answer);
        return new Result(true, StatusCode.OK,"inserting successfully","Result Received, Action Plan: XXXXX");
    }

}

