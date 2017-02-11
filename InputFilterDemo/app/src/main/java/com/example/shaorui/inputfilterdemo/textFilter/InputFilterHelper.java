package com.example.shaorui.inputfilterdemo.textFilter;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import com.example.shaorui.inputfilterdemo.textFilter.handler.IFilterHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shaorui on 17-2-9.
 * 输入框输入正则限制帮助类
 * InputFilterHelper.Builder builder = new InputFilterHelper.Builder();
 * builder.setInputTextLimitLength(40)
 * .addHandler(new PunctuationFilterHandler())
 * .addHandler(new ChineseFilterHandler())
 * .addHandler(new EnglishFilterHandler())
 * .addHandler(new NumberFilterHandler());
 * mEditText.setFilters(InputFilterHelper.build(builder).genFilters());
 */

public class InputFilterHelper {
    private Pattern mFilterPattern;

    /**
     * 限制输入框输入的字符数
     */
    private InputFilter.LengthFilter mLengthFilter = null;

    private InputFilterHelper(Builder builder) {
        List<IFilterHandler> filterHandlerList = new ArrayList<>();
        if (builder != null && builder.filterHandlerList.size() > 0) {
            filterHandlerList.addAll(builder.filterHandlerList);
            mLengthFilter = builder.lengthFilter;
        }
        mFilterPattern = genFilterPattern(filterHandlerList);
    }

    public static InputFilterHelper build(Builder builder) {
        return new InputFilterHelper(builder);
    }

    /**
     * 生成EditText的filters
     * @return 返回InputFilter数组，供EditText使用个{@link android.widget.EditText#setFilters(InputFilter[])}
     */
    public InputFilter[] genFilters() {
        List<InputFilter> inputFilterList = new ArrayList<>();
        if (mLengthFilter != null) {
            inputFilterList.add(mLengthFilter);
        }
        inputFilterList.add(new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String s = innerFilter(source.toString());
                //输入汉字时，拼音、笔画等属于Spanned，需要返回SpannedString，当输入完成后，才能生成一个汉字
                //否则，会将拼音也展示在EditText中
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(s);
                    TextUtils.copySpansFrom((Spanned) source, start, end, null, sp, 0);
                    return sp;
                } else {
                    return s;
                }
            }
        });
        return inputFilterList.toArray(new InputFilter[inputFilterList.size()]);
    }

    private String innerFilter(String source) {
        Matcher matcher = mFilterPattern.matcher(source);
        return matcher.replaceAll("").trim();
    }

    /**
     * 生成Pattern对象
     * 该Pattern对象支持的正则与filterHandlerList支持的正则相反，
     * 目的是可以通过{@link Matcher#replaceAll(String)}方法将不在filterHandlerList支持范围的字符都过滤调
     * @param filterHandlerList 所有支持的正则List
     * @return Pattern对象
     */
    private Pattern genFilterPattern(List<IFilterHandler> filterHandlerList) {
        StringBuilder regexSb = new StringBuilder("[^");
        for (IFilterHandler filterHandler : filterHandlerList) {
            regexSb.append(filterHandler.getFilterRegexStr());
        }
        regexSb.append("]");
        return Pattern.compile(regexSb.toString());
    }


    public static class Builder {
        List<IFilterHandler> filterHandlerList = new ArrayList<>();
        private InputFilter.LengthFilter lengthFilter;

        /**
         * 添加Handler
         * @param filterHandler
         * @return
         */
        public Builder addHandler(IFilterHandler filterHandler) {
            if (filterHandler != null && !TextUtils.isEmpty(filterHandler.getFilterRegexStr())) {
                this.filterHandlerList.add(filterHandler);
            }
            return this;
        }

        /**
         * 输入框文字长度限制
         * @param textLength 输入最大字符数
         * @return
         */
        public Builder setInputTextLimitLength(int textLength) {
            if (textLength > 0) {
                lengthFilter = new InputFilter.LengthFilter(textLength);
            }
            return this;
        }
    }
}
