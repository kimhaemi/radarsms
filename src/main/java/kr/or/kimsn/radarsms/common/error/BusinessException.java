package kr.or.kimsn.radarsms.common.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

@Slf4j
public class BusinessException extends RuntimeException {

  private final ErrorCode errorCode;
  private final String messageCode;
  private final Object[] args;

  public BusinessException(ErrorCode errorCode) {
    if (StringUtils.isBlank(errorCode.getCode())) {
      log.warn("Wrong BusinessException usage! ErrorCode [{}] should be used with message, defined on message.properties in your service.",
          errorCode.name());
    }
    this.errorCode = errorCode;
    this.messageCode = null;
    this.args = null;
  }

  public BusinessException(ErrorCode errorCode, String messageCode) {
    this.errorCode = errorCode;
    this.messageCode = messageCode;
    this.args = null;
  }

  public BusinessException(ErrorCode errorCode, String messageCode, @Nullable Object[] args) {
    this.errorCode = errorCode;
    this.messageCode = messageCode;
    this.args = args;
  }

  public BusinessException(ErrorCode errorCode, Object[] args) {
    this.errorCode = errorCode;
    this.messageCode = errorCode.getCode();
    this.args = args;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public String getMessageCode() {
    return messageCode;
  }

  public Object[] getArgs() {
    return args;
  }
}
