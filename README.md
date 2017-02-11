# inputFilter
如果你想对项目中EditText输入的内容有所限制，那么InputFilterHelper可以帮到你。  

InputFilterHelper将每一种输入限制分割开来，如中文、英文、数字、标点符号、“-”、“.”等，并且可以他们随意组合起来限制EditText的输入。

InputFilterHelper采用Builder的设计模式，用法及其简单：  

```java
InputFilterHelper.Builder builder = new InputFilterHelper.Builder()
                .addHandler(new ChineseFilterHandler())//允许输入中文
                .addHandler(new EnglishFilterHandler())//允许输入英文
                .addHandler(new NumberFilterHandler())//允许输入数字
                .addHandler(new PunctuationFilterHandler())//允许输入标点符号
                .addHandler(new DecimalPointFilterHandler())//允许输入小数点"."
                .addHandler(new LineThroughFilterHandler())//允许输入中划线"-"
                .setInputTextLimitLength(11);//设置最大字符数
        InputFilter[] inputFilters = InputFilterHelper.build(builder).genFilters();
       
editText.setFilters(inputFilters)
```  

采用Handler的方式来添加对EditText的输入限制，使得输入限制的扩展也非常简单,实现可参考NumberFilterHandler:  

```java
public class NumberFilterHandler implements IFilterHandler {
    @Override
    public String getFilterRegexStr() {
        return "0-9";//返回的是输入限制的正则匹配，该例是允许输入数字，因此返回的是数字的正则匹配表达式
    }
}
```