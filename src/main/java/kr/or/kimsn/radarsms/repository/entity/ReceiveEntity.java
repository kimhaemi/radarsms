package kr.or.kimsn.radarsms.repository.entity;

public class ReceiveEntity {
    private ReceiveEntity() {
    }

    public interface SmsSetRcEntity {
        String getSite();
        String getDataKind();
        String getNameKr();
        String getDataType();
        String getDataName();
        String getSmsSendActivation();
    }

}
