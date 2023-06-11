package kr.or.kimsn.radarsms.station;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 지점별 감시
 */
@Controller
public class StationController {

    // @Autowired
    // private HistoricalDataService historicalDataService;

    //조회
    @GetMapping("/station/{name}")
    public String getStation(@PathVariable("name") String name ){
        System.out.println(name);
        return "views/station/station";
    }

    //저장

    //수정

}
