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