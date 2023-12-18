package cn.young.im.springboot.starter.adapter.config.callback;

/**
 * @ClassName EmptyConfigRefreshCallBack
 * @Description 空的回调方法
 * @date 2023/12/18 11:47
 * @Author yanceysong
 * @Version 1.0
 */
public class EmptyConfigRefreshCallBack implements ConfigRefreshCallBack {
    /**
     * 当配置刷新以后没有特殊的设置回调方法
     * 会走当前空的回调实现
     */
    @Override
    public void refresh() {
    }
}
