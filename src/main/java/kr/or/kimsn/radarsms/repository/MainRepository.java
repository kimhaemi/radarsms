package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.MainDto;

public interface MainRepository extends JpaRepository<MainDto, Long> {

    List<MainDto> findAll();

}
