package cn.young.im.springboot.starter.adapter.config.parse;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 配置解析处理器
 * @date 2023/12/16
 */
public interface IConfigParseHandler {

    /**
     * 解析
     */
    void parse(String content, Object bean);
}
