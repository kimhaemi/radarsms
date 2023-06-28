package kr.or.kimsn.radarsms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.MenuDto;
import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.repository.MenuRepository;
import kr.or.kimsn.radarsms.repository.StationRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private StationRepository stationRepository;

    // 메뉴조회
    public List<MenuDto> getMenuList(){
        return menuRepository.findAll();
    }

    // 지점조회
    public List<StationDto> getStationList(){
        return stationRepository.findByOrderBySortOrder();
    }
    
    //메뉴저장
    @Transactional
    public MenuDto addMenuDto(MenuDto menuDto) throws Exception {
        try {
            // menuDto = menuRepository.save(menuDto);    
        } catch (Exception e) {
            System.out.println("insert error : " +e);
        }
        
        return menuDto;
    }
}
