package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "receive_condition_criteria")
public class ReceiveConditionCriteriaDto {
    
    @Id
    private String code;
    private String name;
    private String criterion;
    private String comment;
}
