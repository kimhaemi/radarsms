package kr.or.kimsn.radarsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.or.kimsn.radarsms.dto.SmsAgentDto;

public interface SmsAgentRepository extends JpaRepository<SmsAgentDto, Long>{
    @Query(
        nativeQuery = true,
        value=
		"SELECT COUNT(*) AS CNT \n" +
		" FROM \n" +
		"  (select A.MSGKEY\n" +
		"       , A.PHONE\n" +
		"       , A.STATUS\n" +
		"       , A.REQDATE\n" +
		"       , A.MSG\n" +
		"       , 'MMS' AS TYPE\n" +
		"  from sms_agent_mms A\n" +
		"  where A.REQDATE > DATE_SUB(now(), INTERVAL 7 DAY)\n" +
		"  union\n" +
		"  select b.MSGKEY\n" +
		"       , b.PHONE\n" +
		"       , b.STATUS\n" +
		"       , b.REQDATE\n" +
		"       , b.MSG\n" +
		"       , 'SMS' AS TYPE\n" +
		"  from sms_agent_sms B\n" +
		"where B.REQDATE > DATE_SUB(now(), INTERVAL 7 DAY)\n" +
		") T1"
    )
    public Integer findByTotalCount();

	@Query(
        nativeQuery = true,
        value=
		"SELECT\n" +
		"       row_number() over(order by msgkey desc) as num \n" +
		"     , T1.MSGKEY as msgkey\n" +
		"     , T1.PHONE as phone\n" +
		"     , T1.STATUS as status\n" +
		"     , T1.REQDATE as reqdate\n" +
		"     , T1.MSG as msg\n" +
		"     , T1.TYPE as type\n" +
		" FROM \n" +
		"  (select A.MSGKEY\n" +
		"       , A.PHONE\n" +
		"       , A.STATUS\n" +
		"       , A.REQDATE\n" +
		"       , A.MSG\n" +
		"       , 'MMS' AS TYPE\n" +
		"  from sms_agent_mms A\n" +
		"  where A.REQDATE > DATE_SUB(now(), INTERVAL 7 DAY)\n" +
		"  union\n" +
		"  select b.MSGKEY\n" +
		"       , b.PHONE\n" +
		"       , b.STATUS\n" +
		"       , b.REQDATE\n" +
		"       , b.MSG\n" +
		"       , 'SMS' AS TYPE\n" +
		"  from sms_agent_sms B\n" +
		"where B.REQDATE > DATE_SUB(now(), INTERVAL 7 DAY)\n" +
		") T1\n" +
		" ORDER BY T1.REQDATE DESC, T1.MSGKEY DESC\n" +
		" LIMIT :limitStart, :pageSize\n"
    )
	public List<SmsAgentDto> findByPage(@Param("limitStart") Long limitStart, @Param("pageSize") Long pageSize);
}
