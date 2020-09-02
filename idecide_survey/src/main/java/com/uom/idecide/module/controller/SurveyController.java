package com.uom.idecide.module.controller;


import com.alibaba.fastjson.JSONObject;
import com.uom.idecide.module.pojo.Survey;
import com.uom.idecide.module.service.SurveyService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

@RestController
@CrossOrigin
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(value = "/allSurveyId",method= RequestMethod.GET)
    public Result getAllSurveyId(){
        return new Result(true, StatusCode.OK,"fetching successfully",surveyService.findAllByJsonStrNotNull());
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result add(@RequestBody JSONObject jsonParam) {
        String surveyId = String.valueOf(idWorker.nextId());
        //String surveyId = (String) jsonParam.get("surveyId");
        String surveyTitle = (String) jsonParam.get("surveyTitle");
        String surveyVersion = (String) jsonParam.get("surveyVersion");
        String surveyIntroduction = (String) jsonParam.get("surveyIntroduction");
        String surveyImageName = (String) jsonParam.get("surveyImageName");

        //if(surveyId==null) return new Result(true, StatusCode.ERROR,"Insert fail, please contain the correct survey Id");

        Survey survey = new Survey(surveyId, surveyTitle,surveyVersion,surveyIntroduction,surveyImageName,jsonParam.toJSONString());
        surveyService.save(survey);
        return new Result(true, StatusCode.OK,"Inserting successfully");
    }

    @RequestMapping(value="/{surveyId}",method= RequestMethod.GET)
    public Result findById(@PathVariable("surveyId") String id){
        return new Result(true, StatusCode.OK,"fetching successfully",surveyService.findById(id));
    }


    @RequestMapping(method = RequestMethod.PUT,
            produces = "application/json;charset=UTF-8")
    public Result updateById(@RequestBody JSONObject jsonParam) {
        String surveyId = (String) jsonParam.get("surveyId");
        String surveyTitle = (String) jsonParam.get("surveyTitle");
        String surveyVersion = (String) jsonParam.get("surveyVersion");
        String surveyIntroduction = (String) jsonParam.get("surveyIntroduction");
        String surveyImageName = (String) jsonParam.get("surveyImageName");

        if(surveyId==null) return new Result(true, StatusCode.ERROR,"update fail, please contain the correct survey Id");

        Survey survey = new Survey(surveyId, surveyTitle,surveyVersion,surveyIntroduction,surveyImageName,jsonParam.toJSONString());
        surveyService.save(survey);
        return new Result(true, StatusCode.OK,"updating successfully");
    }

    @RequestMapping(value="/{surveyId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("surveyId") String id){
        surveyService.deleteById(id);
        return new Result(true, StatusCode.OK,"delete the survey successfully");
    }


/*    @RequestMapping(value="/actionPlan/{surveyId}",method = RequestMethod.GET)
    public Result getAllActionsBySurveyId(@PathVariable("surveyId") String id){
        return new Result(true, StatusCode.OK,"fetching successfully",surveyService.getAllActionsBySurveyId(id));
    }

    @RequestMapping(value="/actionPlan/{surveyId}/{actionSeverity}",method = RequestMethod.GET)
    public ActionPlan getBySurveyIdAndActionSeverity(@PathVariable("surveyId") String surveyId,@PathVariable("actionSeverity") int actionSeverity){
        return surveyService.getBySurveyIdAndActionSeverity(surveyId,actionSeverity);
    }

    @RequestMapping(value="/countActionPlan/{surveyId}",method = RequestMethod.GET)
    public int countActionPlanBySurveyId(@PathVariable("surveyId") String surveyId){
        return surveyService.countBySurveyId(surveyId);
    }*/
}

