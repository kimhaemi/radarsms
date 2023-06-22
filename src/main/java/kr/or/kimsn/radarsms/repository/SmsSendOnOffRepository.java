package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.SmsSendOnOffDto;

public interface SmsSendOnOffRepository extends JpaRepository<SmsSendOnOffDto, Long>{
    List<SmsSendOnOffDto> findAll();
}
