package cn.young.im.springboot.starter.adapter.config.parse.handler;

import cn.young.im.springboot.starter.adapter.config.parse.IConfigParseHandler;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.BeanUtils;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Json 配置解析
 * @date 2023/12/16
 */
public class JsonConfigParseHandler implements IConfigParseHandler {
    /**
     * 解析
     */
    @Override
    public void parse(String content, Object bean) {
        Object obj = JSON.parseObject(content, bean.getClass());
        // 对原来的对象进行赋值
        BeanUtils.copyProperties(obj, bean);
    }
}
