package cn.young.im.springboot.starter.adapter.config.callback;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 配置刷新回调
 * @date 2023/12/16
 */
@FunctionalInterface
public interface ConfigRefreshCallBack {

    /**
     * 配置刷新 Callback
     */
    void refresh();
}
