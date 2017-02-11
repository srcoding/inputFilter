package com.example.shaorui.inputfilterdemo.textFilter.handler;

/**
 * Created by shaorui on 17-2-9.
 * 标点符号过滤器
 */

public class PunctuationFilterHandler implements IFilterHandler {
    @Override
    public String getFilterRegexStr() {
        return "\\p{P}";
    }
}
