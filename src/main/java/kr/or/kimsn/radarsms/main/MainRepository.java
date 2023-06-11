package kr.or.kimsn.radarsms.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<MainDto, Long> {

    List<MainDto> findAll();

}
