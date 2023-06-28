package kr.or.kimsn.radarsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.StationStatusDto;

public interface StationStatusRepository extends JpaRepository<StationStatusDto, String>{
    
}
