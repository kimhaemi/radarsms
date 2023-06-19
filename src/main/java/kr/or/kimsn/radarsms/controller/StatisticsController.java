package kr.or.kimsn.radarsms.controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 통계
 */
@Controller
public class StatisticsController {

    // @Autowired
    // private HistoricalDataService historicalDataService;

    //조회
    @GetMapping("/stat/{name}")
    public String getStation(@PathVariable("name") String name ){
        System.out.println(name);
        return "views/statistics/statistics";
    }

    //저장

    //수정

}
