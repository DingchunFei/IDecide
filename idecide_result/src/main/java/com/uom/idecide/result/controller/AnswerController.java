package com.uom.idecide.result.controller;

import com.uom.idecide.result.pojo.Answer;
import com.uom.idecide.result.service.AnswerService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.POST,RequestMethod.GET}, origins="*")
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;


    @RequestMapping(method= RequestMethod.POST)
    public Result add(@RequestBody Answer answer) {
        answerService.add(answer);
        return new Result(true, StatusCode.OK, "inserting successfully","ActionPlan XXXX");
    }

    @RequestMapping(value = "/getResult/{userId}",method= RequestMethod.GET)
    public Result getAllResultByUserId(@PathVariable("userId") String userId) {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.getAllResultByUserId(userId));
    }

    @RequestMapping(value = "/getResult",method= RequestMethod.GET)
    public Result getAllResult() {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.getAllResult());
    }

    @RequestMapping(value = "/getResult/{userId}/{surveyId}", method= RequestMethod.GET)
    public Result getAllResultByUserIdAndSurveyId(
            @PathVariable("userId") String userId,
            @PathVariable("surveyId") String surveyId) {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.getAllResultByUserIdAndSurveyId(userId,surveyId));
    }

    @RequestMapping(value = "/getResult/{userId}/{surveyId}/{sectionId}",method= RequestMethod.GET)
    public Result getAllResultByUserIdAndSurveyIdAndSectionId(@PathVariable("userId") String userId, @PathVariable("surveyId") String surveyId, @PathVariable("sectionId") String sectionId) {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.getAllResultByUserIdAndSurveyIdAndSectionId(userId,surveyId,sectionId));
    }

    /**
     * about .csv
     */
    @RequestMapping(value = "/downloadResult",method= RequestMethod.GET)
    public Result downloadAllResult() {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.downloadAllResult());
    }

    @RequestMapping(value = "/downloadResult/byUserId/{userId}",method= RequestMethod.GET)
    public Result downloadAllResultByUserId(@PathVariable("userId") String userId) {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.downloadResultByUserId(userId));
    }

    @RequestMapping(value = "/downloadResult/bySurveyId/{surveyId}",method= RequestMethod.GET)
    public Result downloadAllResultBySurveyId(@PathVariable("surveyId") String surveyId) {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.downloadResultBySurveyId(surveyId));
    }

    @RequestMapping(value = "/downloadResult/{userId}/{surveyId}",method= RequestMethod.GET)
    public Result downloadAllResultByUserIdAndSurveyId(@PathVariable("userId") String userId,@PathVariable("surveyId") String surveyId) {
        return new Result(true, StatusCode.OK,"fetching successfully",answerService.downloadResultByUserIdAndSurveyId(userId,surveyId));
    }
}

