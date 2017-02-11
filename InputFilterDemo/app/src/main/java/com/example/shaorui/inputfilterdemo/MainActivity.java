package com.example.shaorui.inputfilterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

import com.example.shaorui.inputfilterdemo.textFilter.InputFilterHelper;
import com.example.shaorui.inputfilterdemo.textFilter.handler.ChineseFilterHandler;
import com.example.shaorui.inputfilterdemo.textFilter.handler.DecimalPointFilterHandler;
import com.example.shaorui.inputfilterdemo.textFilter.handler.EnglishFilterHandler;
import com.example.shaorui.inputfilterdemo.textFilter.handler.LineThroughFilterHandler;
import com.example.shaorui.inputfilterdemo.textFilter.handler.NumberFilterHandler;
import com.example.shaorui.inputfilterdemo.textFilter.handler.PunctuationFilterHandler;

public class MainActivity extends AppCompatActivity {
    /**
     * 只能输入中文，英文，数字和标点符号
     */
    private EditText mEt1;
    private InputFilter[] mInputFilters1;

    /**
     * 钱：只能输入数字和‘.’
     */
    private EditText mEt2;
    private InputFilter[] mInputFilters2;

    /**
     * 电话号码：限制长度为11，只能输入数字和‘-’
     */
    private EditText mEt3;
    private InputFilter[] mInputFilters3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInputFilters();
        initView();
    }

    private void initInputFilters() {
        InputFilterHelper.Builder builder1 = new InputFilterHelper.Builder()
                .addHandler(new ChineseFilterHandler())
                .addHandler(new EnglishFilterHandler())
                .addHandler(new NumberFilterHandler())
                .addHandler(new PunctuationFilterHandler());
        mInputFilters1 = InputFilterHelper.build(builder1).genFilters();

        InputFilterHelper.Builder builder2 = new InputFilterHelper.Builder()
                .addHandler(new NumberFilterHandler())
                .addHandler(new DecimalPointFilterHandler());
        mInputFilters2 = InputFilterHelper.build(builder2).genFilters();

        InputFilterHelper.Builder builder3 = new InputFilterHelper.Builder()
                .addHandler(new NumberFilterHandler())
                .addHandler(new LineThroughFilterHandler())
                .setInputTextLimitLength(11);
        mInputFilters3 = InputFilterHelper.build(builder3).genFilters();
    }

    private void initView() {
        mEt1 = (EditText) findViewById(R.id.et_1);
        mEt1.setFilters(mInputFilters1);

        mEt2 = (EditText) findViewById(R.id.et_2);
        mEt2.setFilters(mInputFilters2);

        mEt3 = (EditText) findViewById(R.id.et_3);
        mEt3.setFilters(mInputFilters3);
    }


}
