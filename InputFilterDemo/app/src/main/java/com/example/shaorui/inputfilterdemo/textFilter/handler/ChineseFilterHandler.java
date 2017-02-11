package com.example.shaorui.inputfilterdemo.textFilter.handler;

/**
 * Created by shaorui on 17-2-9.
 * 中文字符过滤器
 */

public class ChineseFilterHandler implements IFilterHandler {
    @Override
    public String getFilterRegexStr() {
        return "\\u4E00-\\u9FA5";
    }
}
