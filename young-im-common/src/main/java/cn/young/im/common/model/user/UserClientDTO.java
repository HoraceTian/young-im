package cn.young.im.common.model.user;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName UserClientDTO
 * @Description
 * @date 2023/11/15 15:17
 * @Author yanceysong
 * @Version 1.0
 */
@Data
@ToString
public class UserClientDTO {
    private Integer appId;
    private String userId;
    private Integer clientType;
    private String imei;
}
