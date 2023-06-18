package kr.or.kimsn.radarsms.station;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
/**
 * 지점별 감시
 */
@Controller
public class StationController {

    // @Autowired
    // private HistoricalDataService historicalDataService;

    //조회
    @GetMapping("/station/{name}")
    public ModelAndView getStation(@PathVariable("name") String name ){
        ModelAndView mav = new ModelAndView();
        System.out.println("ffsdfsdfsdfsdfsdfsd");
        System.out.println(name);
        mav.setViewName("views/station/station");
        return mav;
    }

    //저장

    //수정

}
