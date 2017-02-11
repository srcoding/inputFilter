package com.example.shaorui.inputfilterdemo.textFilter.handler;

/**
 * Created by shaorui on 17-2-9.
 * 小数点过滤器
 */

public class DecimalPointFilterHandler implements IFilterHandler {
    @Override
    public String getFilterRegexStr() {
        return ".";
    }
}
