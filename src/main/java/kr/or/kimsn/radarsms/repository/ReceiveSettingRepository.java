package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.ReceiveSettingDto;

public interface ReceiveSettingRepository extends JpaRepository<ReceiveSettingDto, String> {
    
    List<ReceiveSettingDto> findByDataKind(String dataKind);

    List<ReceiveSettingDto> findByDataKindAndPermittedWatch(String dataKind, Integer permittedWatch);

    List<ReceiveSettingDto> findByOrderByDataKindDescPermittedWatchDesc();


}
