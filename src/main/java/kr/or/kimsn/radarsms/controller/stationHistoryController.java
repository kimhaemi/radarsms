package kr.or.kimsn.radarsms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 과거자료 검색
 */
@Controller
public class stationHistoryController {
    //조회
    @GetMapping("/station/hist/{name}")
    public String getStation(@PathVariable("name") String name ){
        System.out.println(name);
        return "views/station/stationHistory";
    }
}
