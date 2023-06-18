package kr.or.kimsn.radarsms.main;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private MainRepository menuRepository;

    @Autowired
    private StationRepository stationRepository;

    // 메뉴조회
    public List<MainDto> getMenuList(){
        return menuRepository.findAll();
    }

    // 지점조회
    public List<StationDto> getStationList(){
        return stationRepository.findAll();
    }
    
    //메뉴저장
    @Transactional
    public MainDto addMenuDto(MainDto menuDto) throws Exception {
        try {
            // menuDto = menuRepository.save(menuDto);    
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        
        return menuDto;
    }
}
