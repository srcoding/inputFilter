package com.example.shaorui.inputfilterdemo.textFilter.handler;

/**
 * Created by shaorui on 17-2-9.
 * 中划线Handler
 */

public class LineThroughFilterHandler implements IFilterHandler {
    @Override
    public String getFilterRegexStr() {
        return "-";
    }
}
