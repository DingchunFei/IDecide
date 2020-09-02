/*
package com.uom.idecide.result.feignClinet;

import com.uom.idecide.result.pojo.ActionPlan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("IDECIDE-SURVEY1")
public interface SurveyClient {

    @RequestMapping(value="/survey/actionPlan/{surveyId}/{actionSeverity}",method = RequestMethod.GET)
    public ActionPlan getBySurveyIdAndActionSeverity(@PathVariable("surveyId") String surveyId, @PathVariable("actionSeverity") int actionSeverity);

    @RequestMapping(value="/survey/countActionPlan/{surveyId}",method = RequestMethod.GET)
    public int countActionPlanBySurveyId(@PathVariable("surveyId") String surveyId);
}
*/
