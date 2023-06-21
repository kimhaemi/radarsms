package kr.or.kimsn.radarsms.dto;

import javax.persistence.Entity;

// import java.util.ArrayList;
// import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String roles;
    private String provider;
    private String provider_id;
    // private String updated_at;
    // private String created_at;

    // public List<String> getUserList(){
    //     if(this.username.isEmpty()){
    //         return Array.asList(this);
    //     }
    //     return new ArrayList<>();
    // }
}
