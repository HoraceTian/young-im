package cn.young.im.plugin.privilege;

import cn.young.im.plugin.api.dto.response.YoungPluginResult;
import lombok.Data;


/**
 * @ClassName PrivilegePluginResult
 * @Description
 * @date 2023/11/30 10:44
 * @Author yanceysong
 * @Version 1.0
 */
@Data
public class PrivilegePluginResult implements YoungPluginResult {
    private Integer appId;
    private String tips;
}
