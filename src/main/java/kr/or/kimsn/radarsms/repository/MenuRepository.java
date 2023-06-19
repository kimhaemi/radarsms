package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.MenuDto;

public interface MenuRepository extends JpaRepository<MenuDto, Long> {

    List<MenuDto> findAll();

}
