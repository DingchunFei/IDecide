/*
package com.uom.idecide.module.controller;

import com.alibaba.fastjson.JSONObject;
import com.uom.idecide.module.pojo.Module;
import com.uom.idecide.module.pojo.Survey;
import com.uom.idecide.module.service.ModuleService;
import com.uom.idecide.module.service.SurveyService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/survey")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private SurveyService surveyService;

    @RequestMapping(method= RequestMethod.POST)
    public Result add(@RequestBody Module module) {
        moduleService.add(module);
        return new Result(true, StatusCode.OK,"Inserting successfully");
    }


    @RequestMapping(value = "/allSurveyId",method= RequestMethod.GET)
    public Result getAllSurveyId(){
        List<Survey> surveyIdList = surveyService.findAllByJsonStrNotNull();
        for (Survey survey:surveyIdList){
            System.out.println(survey);
        }
        return null;
    }

    @RequestMapping(value = "/json/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getByJSON(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());

        String surveyId = (String) jsonParam.get("surveyId");
        String surveyTitle = (String) jsonParam.get("surveyTitle");
        String surveyVersion = (String) jsonParam.get("surveyVersion");
        String surveyIntroduction = (String) jsonParam.get("surveyIntroduction");
        if(surveyId==null) return new Result(true, StatusCode.ERROR,"Insert fail, please contain the correct survey Id");

        Survey survey = new Survey(surveyId, surveyTitle,surveyVersion,surveyIntroduction,jsonParam.toJSONString());
        surveyService.save(survey);
//        // 将获取的json数据封装一层，然后在给返回
//        JSONObject result = new JSONObject();
//        result.put("msg", "ok");
//        result.put("method", "json");
//        result.put("data", jsonParam);


        return null;
    }



    @RequestMapping(method= RequestMethod.PUT)
    public Result updateById(@RequestBody Module module) {
        moduleService.updateById(module);
        return new Result(true, StatusCode.OK,"updating successfully");
    }

    @RequestMapping(value="/{moduleId}",method= RequestMethod.GET)
    public Result findById(@PathVariable("moduleId") String id){
        return new Result(true, StatusCode.OK,"finding successfully",moduleService.findById(id));
    }

    @RequestMapping(value="/{moduleId}",method= RequestMethod.DELETE)
    public Result deleteById(@PathVariable("moduleId") String id){
        moduleService.deleteById(id);
        return new Result(true, StatusCode.OK,"deleting successfully");
    }

    @RequestMapping(value = "/findAllByActiveTrue", method = RequestMethod.GET)
    public Result findAllByActiveTrue(){
        return new Result(true, StatusCode.OK,"fetching successfully", moduleService.findAllByActiveTrue());
    }
}

*/
