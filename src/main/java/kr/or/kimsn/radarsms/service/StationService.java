package kr.or.kimsn.radarsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kimsn.radarsms.dto.StationDto;
import kr.or.kimsn.radarsms.repository.StationRepository;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository; 
    
    //지점별 감시
    public List<StationDto> getStationDetail (String siteCd) {
        return stationRepository.findBySiteCd(siteCd);
    }
}
