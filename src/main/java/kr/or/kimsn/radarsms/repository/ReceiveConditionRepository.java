package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.or.kimsn.radarsms.dto.ReceiveConditionDto;
import kr.or.kimsn.radarsms.dto.ReceiveDto;

public interface ReceiveConditionRepository extends JpaRepository<ReceiveConditionDto, String> {

    List<ReceiveConditionDto> findBySiteAndDataType(String site, String data_type);

    @Query(value="select \n" +
            "T1.data_kind, \n" +
            "T1.site, T2.name_kr,\n" +
            "T1.data_type, T3.data_name,\n" +
            "T1.sms_send_activation\n" +
            "from receive_condition T1\n" +
            "left outer join station_rdr T2 \n" +
            "on T1.site = T2.site_cd \n" +
            "join receive_setting T3 \n" +
            "where 1=1 \n" +
            "and T1.data_kind=T3.data_kind \n" +
            "and T1.data_type=T3.data_type \n" +
            "order by \n" +
            "data_kind desc, name_kr asc, data_type asc")
    List<ReceiveDto> findReceiveConditionStationRdrReceiveSetting();

    
}
