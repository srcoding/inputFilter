package com.example.shaorui.inputfilterdemo.textFilter.handler;

/**
 * Created by shaorui on 17-2-9.
 * 英文大小写字符
 */

public class EnglishFilterHandler implements IFilterHandler {
    @Override
    public String getFilterRegexStr() {
        return "a-zA-Z";
    }
}
