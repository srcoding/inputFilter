package com.example.shaorui.inputfilterdemo.textFilter.handler;

/**
 * Created by shaorui on 17-2-9.
 */

public interface IFilterHandler {
    /**
     * 获取支持的过滤正则表达式
     * @return 支持输入的正则表达式
     */
    String getFilterRegexStr();
}
