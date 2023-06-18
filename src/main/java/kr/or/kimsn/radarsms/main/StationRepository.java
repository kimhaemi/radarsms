package kr.or.kimsn.radarsms.main;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<StationDto, Long> {

    List<StationDto> findAll();
}
