package kr.or.kimsn.radarsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kimsn.radarsms.dto.AppTemplateCodeDto;

public interface AppTemplateCodeRepository extends JpaRepository<AppTemplateCodeDto, String> {
    
}
