package com.example.shaorui.inputfilterdemo.textFilter.handler;

/**
 * Created by shaorui on 17-2-9.
 * 数字
 */

public class NumberFilterHandler implements IFilterHandler {
    @Override
    public String getFilterRegexStr() {
        return "0-9";
    }
}
