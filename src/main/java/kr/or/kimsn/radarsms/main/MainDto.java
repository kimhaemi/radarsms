package kr.or.kimsn.radarsms.main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "menu")
public class MainDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String menudepth;
	private String menu1; 
	private String menu2; 
	private String menu3; 
	private Boolean status;
	private String created_at;
	private String updated_at;

}
