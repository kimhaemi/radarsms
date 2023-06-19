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
	private String menu_name;
	private String path; 
	private Boolean status; 
	private int depth;
	private String order; 
	private String created_at;
	private String updated_at;

}
