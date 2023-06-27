package kr.or.kimsn.radarsms.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "watchdog_member")
public class MembersDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid") private Long id;
  
    @Column(name = "member_id")       private String memberId;
    @Column(name = "password")        private String password;
    @Column(name = "password_hint")   private String passwordHint;
    @Column(name = "password_answer") private String passwordAnswer;
    @Column(name = "member_name")     private String memberName;
    @Column(name = "member_email")    private String memberEmail;
    @Column(name = "member_phone")    private String memberPhone;
    @Column(name = "member_class")    private String memberClass;
    @Column(name = "member_org")      private String memberOrg;
    @Column(name = "member_dept")     private String memberDept;
    @Column(name = "member_pos")      private String memberPos;
    
    @UpdateTimestamp
    @Column(name = "member_join")
    private LocalDateTime memberJoin;
  
}
