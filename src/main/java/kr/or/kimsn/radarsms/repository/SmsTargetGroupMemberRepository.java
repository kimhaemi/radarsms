package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.or.kimsn.radarsms.dto.SmsTargetGroupMemberDto;

public interface SmsTargetGroupMemberRepository extends JpaRepository<SmsTargetGroupMemberDto, Long>{
    @Query(
        nativeQuery = true,
        value=
        "select \n"+
        "   T2.gid as gid\n" +
	    " , T1.mid as mid\n" +
	    " , T1.name as name\n" +
	    " , T1.organization as organization\n" +
	    " , T1.department as department\n" +
	    " , T1.position as position\n" +
	    " , T1.phone_num as phone_num\n" +
	    "from \n" +
		"  sms_target_member T1, \n" +
		"  sms_target_member_link T2 \n" +
	    "where 1=1\n" +
		"and T1.mid = T2.mid \n" +
        "and T1.activation = 1 \n" +
        "and T2.sms = 1 \n" +
        "and T2.gid > 1 \n" +
        "order by T2.gid, T1.name \n"
    )
    List<SmsTargetGroupMemberDto> getSmsTargetGroupMemberList();
}
