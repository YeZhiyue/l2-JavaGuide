# `Android开发DEMO大全（一）`


> 第一部分链接：[https://blog.csdn.net/qq_43230007/article/details/105819406](https://blog.csdn.net/qq_43230007/article/details/105819406)



- 布局管理器
- 普通UI组件
- 高级UI组件
- 事件、手势
- 资源文件
- 提示框、对话框、通知、广播
- 绘图、动画
- 音频、视频、摄像头

> 第二部分链接：[https://blog.csdn.net/qq_43230007/article/details/105819638](https://blog.csdn.net/qq_43230007/article/details/105819638)


- 数据存储
- Handler消息处理
- 传感器
- 地图定位
- actionBar
- Intent
- fragment
- Activity
- Service
- Broadcast
- ContentProvider
- 其他

## `布局管理器`

### frameLayout帧式布局

```java
<FrameLayout
    android:id="@+id/frameLayout"
    android:layout_width="match_parent"

    android:layout_height="match_parent"
    android:foreground="@drawable/yellow"
    android:foregroundGravity="center"
    >
    <!--
        帧式布局管理器：UI组件&&管理器属性：设置前景图和前景图位置
        android:foreground="@mipmap/a1"
        android:foregroundGravity="center"
          说明：
            1.越是后面定义的UI组件在图层的前面
            2.管理器的前景图一定在最前面
        注意：布局管理器中设置前景图相对位置的属性android:foregroundGravity="center"时，那么图片的部分可能会
            消失，这个时候需要fill属性显示整张图片。
    -->
    <!--
        foregroundGravity&&layout_gravity && gravity的属性值
        1.基本属性
        top	将对象放在其容器的顶部，不改变其大小.
        bottom	将对象放在其容器的底部，不改变其大小.
        left	将对象放在其容器的左侧，不改变其大小.
        right	将对象放在其容器的右侧，不改变其大小.
        2.多方向对齐
        center	横纵居中，不改变其大小.
        center_vertical	纵向居中，不改变其大小. 
        center_horizontal	横向居中，不改变其大小. 
        3.填充
        fill	必要的时候增加对象的横纵向大小，以完全充满其容器.
        fill_vertical	必要的时候增加对象的纵向大小，以完全充满其容器. 
        fill_horizontal	必要的时候增加对象的横向大小，以完全充满其容器. 
        4.附加属性
        clip_vertical 附加选项，用于按照容器的边来剪切对象的顶部和/或底部的内容. 剪切基于其纵向对齐设置：顶部对齐时，剪切底部；底部对齐时剪切顶部；除此之外剪切顶部和底部.
        clip_horizontal 附加选项，用于按照容器的边来剪切对象的左侧和/或右侧的内容. 剪切基于其横向对齐设置：左侧对齐时，剪切右侧；右侧对齐时剪切左侧；除此之外剪切左侧和右侧.
    -->
    <TextView
        android:id="@+id/t1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:foreground="@drawable/yellow"
        android:foregroundGravity="fill"

        android:text="文本框一"
        android:textSize="20dp" />

    <TextView
        android:id="@+id/t2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:foreground="@drawable/yellow"
        android:foregroundGravity="fill"

        android:text="文本框二"
        android:textSize="20dp" />
</FrameLayout>


```

### gridLayout网格布局

```java
<!--
    管理器属性：设置哪一行不显示、设置那几行手动伸缩、设置那几行可以自动收缩
    注意：这些属性值都是从0开始，也就是表格从下标0开始计数
    android:collapseColumns="3"
    android:stretchColumns="0,1,2"
    android:shrinkColumns="0,1"
-->
<!--
注意：
    1 表格、网格布局管理器器需要设置组件的wight权重属性，否则界面布局的比较难看
    2 如果需要在表格、网格布局管理器中嵌套其他管理器，那么注意被嵌套的管理器的android:layout_width="match_parent"属性的设置
-->
<GridLayout
    android:id="@+id/gridLayout"

    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:rowCount="$rowNum$"
    android:columnCount="$columCount$"
    android:gravity="top"
    android:orientation="horizontal"
    >
    <!--
            UI组件属性：
            1.指定组件在容器的第几列、跨几列、占有剩余列空间权重
            android:layout_column="0"
            android:layout_columnSpan="2"
            android:layout_columnWeight="1"
            2.指定组件在容器的第几行、跨几行、占有剩余行空间权重
            android:layout_row="1"
            android:layout_rowSpan="1"
            android:layout_rowWeight="1"
            3.设置文本内容在容器中位置
            android:layout_gravity="center"
    -->

    <TextView
        android:id="@+id/t1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_column="0"
        android:layout_columnSpan="1"
        android:layout_columnWeight="1"
        android:layout_gravity="center"
        android:background="#de627c"

        android:text="文本框一"
        android:textSize="20dp" />

    <TextView
        android:id="@+id/t2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_column="1"
        android:layout_columnSpan="1"
        android:layout_columnWeight="1"
        android:layout_gravity="center"
        android:background="#de627c"
        android:text="文本框二"
        android:textSize="20dp" />

    <TextView
        android:id="@+id/t3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_column="2"
        android:layout_columnWeight="1"
        android:background="#de627c"
        android:layout_gravity="center"

        android:text="文本框三"
        android:textSize="20dp" />
</GridLayout>
```

### linearLayout线性布局

```java
<!--    &lt;!&ndash;
    1.设置子组件的水平或者竖直排列、设置子组件相对于容器的相对位置
    android:orientation="vertical"
         vertical：一行一个组件  horizontal：一列一个组件
    android:gravity="center"
        -->
    <LinearLayout
        android:id="@+id/linearLayout"
        android:gravity="center"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
    >
        <!--
            UI组件特殊属性：设置占有剩余空间权重
            android:layout_weight="1"
        -->
        <TextView
            android:id="@+id/t1"
            android:text="文本框一"
            android:textSize="20dp"
            android:layout_weight="1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#de627c"

            />
        <TextView
            android:text="文本框二"
            android:textSize="20dp"
            android:layout_weight="1"
            android:id="@+id/t2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#561220"

            />
        <TextView
            android:id="@+id/t3"
            android:text="文本框三"
            android:textSize="20dp"
            android:layout_weight="1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#300f16"

            />
    </LinearLayout>
```

### relativeLayout相对布局

```java
<!--    &lt;!&ndash;
        1.外边距配置
        android:paddingBottom="@dimen/activity_vertical_margin"
        android:paddingLeft="@dimen/activity_horizontal_margin"
        android:paddingRight="@dimen/activity_horizontal_margin"
        android:paddingTop="@dimen/activity_vertical_margin"
        2.配置子组件的相对布局、配置哪些子组件不受gravity属性影响
        android:gravity="center"
        android:ignoreGravity="@id/t1"-->
<RelativeLayout
    android:id="@+id/relativeLayout"
    android:layout_width="match_parent"

    android:layout_height="match_parent"
    android:gravity="center"
    android:ignoreGravity="@id/relativeLayout">
    <!--
            1.组件间相对位置。可以在布局管理器和UI组件中设置。
            android:layout_above="@+id/t2"
            android:layout_below="@+id/t2"
            android:layout_toLeftOf="@+id/t2"
            android:layout_toRightOf="@+id/t2"
            2.依次是相对于管理器水平居中、中心、垂直居中。指定组件和布局管理器的相对位置，依次是位于布局管理器的
            android:layout_centerHorizontal="true"
            android:layout_centerInParent="true"
            android:layout_centerVertical="true"
            3.组件间对齐，指定组件与其他组件的上、下、左、右之一对其
            android:layout_alignTop="@+id/t2"
            android:layout_alignBottom="@+id/t2"
            android:layout_alignStart="@+id/t2"
            android:layout_alignEnd="@+id/t2"
            4.和管理器对齐，指定组件与布局管理器哪边对齐
            android:layout_alignParentTop="true"
            android:layout_alignParentBottom="true"
            android:layout_alignParentStart="true"
            android:layout_alignParentEnd="true"
        &ndash;&gt;-->
    <TextView
        android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:layout_toLeftOf="@+id/textView2"
        android:background="#de627c"
        android:gravity="center"
        android:singleLine="true"
        android:text="文本框1"

        android:textSize="14pt" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:background="#de627c"
        android:gravity="center"
        android:singleLine="true"
        android:text="文本框2"

        android:textSize="14pt" />

    <TextView
        android:id="@+id/textView3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:layout_toRightOf="@+id/textView2"
        android:background="#de627c"
        android:gravity="center"
        android:singleLine="true"
        android:text="文本框3"

        android:textSize="14pt" />
</RelativeLayout>
```

### tableLayout 表格布局

```java
<!--
    管理器属性：设置哪一行不显示、设置那几行手动伸缩、设置那几行可以自动收缩
    注意：这些属性值都是从0开始，也就是表格从下标0开始计数
    android:collapseColumns="3"
    android:stretchColumns="0,1,2"
    android:shrinkColumns="0,1"
-->
<!--
注意：
    1 表格、网格布局管理器器需要设置组件的wight权重属性，否则界面布局的比较难看
    2 如果需要在表格、网格布局管理器中嵌套其他管理器，那么注意被嵌套的管理器的android:layout_width="match_parent"属性的设置
-->
<TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/tableLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:collapseColumns="3"
    android:gravity="top"


    android:shrinkColumns="0,1"
    android:stretchColumns="0,1,2">
    <!--
            UI组件属性：指定组件在第几列、指定UI组件跨几列
            注意：下标仍然从0开始
            android:layout_column="0"
            android:layout_span="1"
    -->
    <TableRow
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/t1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_column="0"
            android:layout_margin="2dp"
            android:layout_span="1"
            android:background="#de627c"
            android:text="文本框一"
            android:textSize="20dp" />

        <TextView
            android:id="@+id/t2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_column="1"
            android:layout_margin="2dp"
            android:layout_span="1"
            android:background="#de627c"

            android:text="文本框二"
            android:textSize="20dp" />

        <TextView
            android:id="@+id/t3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_column="2"
            android:layout_margin="2dp"
            android:layout_span="1"
            android:background="#de627c"

            android:text="文本框三"
            android:textSize="20dp" />
    </TableRow>
</TableLayout>


```

## `普通UI组件`

### button

```java
    <!-- android:onClick="javaClick":添加按钮事件，需要java程序支持-->
    <Button
        android:id="@+id/button"
        android:background="#444444"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_margin="2px"
        android:text="按钮"
        />
```

### checkbox

```java
    <CheckBox
            android:id="@+id/checkBox1"
            android:background="#444444"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:gravity="center"
            android:singleLine="true"
            android:text="复选框1"
            android:textSize="14pt"
            />
            <CheckBox
            android:id="@+id/checkBox2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:gravity="center"
            android:singleLine="true"
            android:text="复选框2"
            android:textSize="14pt"
            />

        <CheckBox
            android:id="@+id/checkBox3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:gravity="center"
            android:singleLine="true"
            android:text="复选框3"
            android:textSize="14pt" />
```

### chronometer

```java
    <Chronometer
        android:id="@+id/chronometer"
        android:background="#444444"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_margin="2pt" />
```

### datePicker

```java
    <DatePicker
        android:id="@+id/datePicker"
        android:background="#444444"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
    ></DatePicker>
```

### editText

```java
    <!--
            编辑框组件 EditText
            background:设置背景图片
                 @drawable/img3
            hint:表示编辑框提示信息
                请输入名称
            inputType：表示输入文本类型，密码、普通文本、数字
                password、number
            drawableLeft|Start|Right|End|Top:表示编辑框上(下左右)侧的小图片|图标
                @drawable/img3 @mipmap/img3
            lines：设置可以输入多行
                3 4 ...
        -->
    <EditText
        android:id="@+id/editText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:background="#444444"
        android:layout_columnWeight="1"
        android:layout_rowWeight="1"
        android:padding="2dp"
        android:singleLine="false"
        android:hint="编辑框"
        android:textSize="14pt" />
```

### imageButton

```java
    <ImageButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scaleType="fitCenter"
        android:src="@drawable/yellow" />
```

### radioGroup

```java
    <!--
            android:orientation="horizontal":RadioGroup属性，
                设置按钮水平或者垂直排列 RadioGroup属性，设置按钮水平或者垂直排列
            android:checked="true":表示是否选中
            -->
    <RadioGroup
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <RadioButton
            android:id="@+id/radioButton1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:checked="true"

            android:singleLine="true"
            android:text="单选按钮1"
            android:background="#444444"
            android:textSize="14pt" />

        <RadioButton
            android:id="@+id/radioButton2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:singleLine="true"
            android:background="#444444"
            android:text="单选按钮2"
            android:textSize="14pt" />

        <RadioButton
            android:id="@+id/radioButton3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:background="#444444"
            android:singleLine="true"
            android:text="单选按钮3"
            android:textSize="14pt" />
```

### textView

```java
    <!--android:singleLine="true"：设置是否支持多行，false是支持多行-->
    <!--
        android:orientation="horizontal":RadioGroup属性，
            设置按钮水平或者垂直排列 RadioGroup属性，设置按钮水平或者垂直排列
        android:checked="true":表示是否选中
            -->
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:background="#444444"
        android:gravity="center"
        android:singleLine="true"
        android:text="文本框"
        android:textSize="14pt" />
```

### timePicker

```java
      <TimePicker
              android:id="@+id/timePicker"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_gravity="center"
              android:layout_margin="2px"
              android:background="#444444"></TimePicker>
```

### 普通UI组件案例

### chronometer 60秒计时

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：实现60s的计时功能
 *
 *流程：
 *    1.获取计时器组件
 *    2.设置起始时间
 *    3.设置时间格式
 *    4.开启计时器
 *    5.设置计时器监听
 *注意：需要在布局管理器中添加Chronometer组件
 *
 *方法：
 */
//设置全屏显示
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
//获取计时器组件
Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
//起始时间设置
chronometer.setBase(SystemClock.elapsedRealtime());
//时间格式设置
chronometer.setFormat("%s");
//开启计时器
chronometer.start();
//监听器设置
chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
    @Override
    public void onChronometerTick(Chronometer chronometer) {
        //计时时间设置为60秒
        if (SystemClock.elapsedRealtime() - chronometer.getBase() >= 60000) {
            //停止计时器
            chronometer.stop();
        }else{
            Toast.makeText(MainActivity.this,"计时器正在工作", Toast.LENGTH_SHORT).show();
        }
    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### DatePicker日期选择器

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：DatePicker日期选择器
 *
 *流程：
 *    1.布局，获取日期选择器组件、获取日历类
 *    2.设置日期选择器的监听
 *    3.监听时间的修改
 *注意：
 *方法：
 */
/************************方法段:2类变量********************************/
int year, month, day;
/************************方法段:onCreate()********************************/
//1.日期选择器获取
DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);    //通过ID获取布局日期选择器
//2.获取日期类，用于获取当前日期的年月日
Calendar calendar = Calendar.getInstance();
year = calendar.get(Calendar.YEAR);
month = calendar.get(Calendar.MONTH);
day = calendar.get(Calendar.DAY_OF_MONTH);
//3.初始化日期选择器，并且在初始化时指定监听器
datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
    @Override
    //前三个参数指的是改变后的日期时间
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //改变年的参数.
        MainActivity.this.year = year;
        //改变月的参数
        MainActivity.this.month = monthOfYear;
        //改变日的参数
        MainActivity.this.day = dayOfMonth;
        //通过消息框显示日期
        show(year, monthOfYear, dayOfMonth);
    }
});
/************************方法段:show()********************************/
private void show(int year, int monthOfYear, int dayOfMonth) {
    //获取选择器设置的日期字符串
    String str = year + "年" + monthOfYear + 1 + "月" + dayOfMonth + "日";
    //控制台打印：将选择的日期显示出来
    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### timePicker时间选择器

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：TimePicker时间选择器
 *
 *流程：
 *    1.布局，获取时间选择器，获取日历类获取时间
 *    2.设置时间监听
 */
/************************方法段:2类变量********************************/
int hour, minute;
/************************方法段:onCreate()********************************/
//1.获取布局时间选择器
TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
//2.设置参数值为true 表示时间就变为24小时制
timePicker.setIs24HourView(true);
//3.获取日历对象，进而获取小时，分钟
Calendar calendar = Calendar.getInstance();
hour = calendar.get(Calendar.HOUR_OF_DAY);
minute = calendar.get(Calendar.MINUTE);
//4.时间选择器设置监听器，时间改变就改变时间选择器的时间
timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        //改变小时、分钟后的重新为时间选择器设置参数
        MainActivity.this.hour = hourOfDay;
        MainActivity.this.minute = minute;
        //通过消息框显示选择的时间
        show(hourOfDay, minute);
    }

    private void show(int hourOfDay, int minute) {
        //获取选择器设置的时间
        String str = hourOfDay + "时" + minute + "分";
        //显示消息提示框
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

## `高级UI组件`

### gridView网格视图

```java
    <!--android:numColumns="4":设置网格列数-->
    <GridView
        android:id="@+id/gridView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:horizontalSpacing="3dp"
        android:numColumns="2"
        android:verticalSpacing="3dp"></GridView>
```

### scrollView（horizontalScrollView）滚动视图

```java
    <!--注意：滚动条下仅仅允许有一个UI组件，如果要放置多个需要布局管理器配合使用-->
    <ScrollView
        android:id="@+id/scrollView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#444444"
        android:layout_margin="2dp">

        <EditText
            android:id="@+id/editText"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="2px"
            android:padding="2dp"
            android:singleLine="false"
            android:hint="编辑框"
            android:textSize="240pt" />
    </ScrollView>

    <!--注意：滚动条下仅仅允许有一个UI组件，如果要放置多个需要布局管理器配合使用-->
        <HorizontalScrollView
            android:id="@+id/horizontalScrollView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2dp">
            <TextView
                android:id="@+id/textView"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:background="#4444"
                android:hint="编辑框"
                android:textSize="10pt" />
        </HorizontalScrollView>
```

### imagesSwitcher图片选择器

```java
    <!--android:layout_gravity="center_vertical":设置其中图片垂直居中-->
    <ImageSwitcher
        android:id="@+id/imageSwitcher"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_margin="2px"></ImageSwitcher>
```

### imageView图片视图

```java
    <!--
            图像视图：设置图片缩放
            android:scaleType="fitXY"
                fitXY 表示占满整个容器 matrix 表时等比例缩放并且占满整个屏幕
            2.设置图片是否允许系统自动调整，接着设置图片最大宽度和高度。这两个属性通常配合使用
            android:adjustViewBounds="true"
                true表示允许系统自动调整
            android:maxWidth="30dp"
            android:maxHeight="30dp"
            3.设置图片表面着色，通常需要设置透明度
            android:tint="#70dc3c3c"
        -->
        <ImageView
            android:id="@+id/imageView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:src="@drawable/yellow"
            android:scaleType="fitXY"
            android:adjustViewBounds="true"
            android:maxWidth="30dp"
            android:maxHeight="30dp"
            android:tint="#70dc3c3c"
            />
```

### listView列表视图

```java
    <!-- android:entries="@array/ctype"：引入数组资源文件作为列表项-->
    <ListView
        android:id="@+id/listView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2pt"></ListView>
```

### progressBar进度条

```java
<!--
        进度条：类型设置、最大长度、当前长度
        style="@style/Widget.AppCompat.ProgressBar.Horizontal"
            方式一：根据主题设置进度条，根据主题的改变而改变
                android:attr/progressBar.Horizontal|Large|Small 根据主题切换进度条样式
            方式二：固定的属性
                @android:style/Widget.ProgressBar.Horizontal|Large|Small 依次是条状、大圆、小圆
        android:max="100"
        android:progress="50"
-->
    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        style="@style/Widget.AppCompat.ProgressBar.Horizontal"
        android:max="100"
        android:progress="50"
        />
```

### ratingBar星选条

```java
    <!--
            星级评分条：星星个数，每次点亮或熄灭的星星数、星星的初始化数、星星是否可以改变
            android:numStars="5"
            android:stepSize="1"
            android:rating="3"
            android:isIndicator="false"
        -->
        <RatingBar
            android:id="@+id/ratingBar"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2px"
            android:numStars="5"
            android:stepSize="1"
            android:rating="3"
            android:background="#444444"
            android:isIndicator="false"
            />

//            int accessibilityLiveRegion = ratingBar3.getAccessibilityLiveRegion();
//            int accessibilityTraversalBefore = ratingBar3.getAccessibilityTraversalBefore();
//            int accessibilityTraversalAfter = ratingBar3.getAccessibilityTraversalAfter();
//            Toast.makeText(MyActivity3_0.this,accessibilityLiveRegion+""+accessibilityTraversalBefore+""+accessibilityTraversalAfter,Toast.LENGTH_SHORT).show();
//获取星选条数据
//获取总星数
int numStars = ratingBar3.getNumStars();
//获取当前星星数
float rating = ratingBar3.getRating();
//获取修改星星步长
float stepSize = ratingBar3.getStepSize();
//获取是否可以修改星星数的权限
boolean indicator = ratingBar3.isIndicator();
```

### seekBar拖动条

```java
    <!--
            拖动条：类型设置、最大长度、当前长度、拖动条的图标
            style="@style/Widget.AppCompat.ProgressBar.Horizontal"
                方式一：根据主题设置进度条，根据主题的改变而改变
                    android:attr/progressBar.Horizontal|Large|Small 根据主题切换进度条样式
                方式二：固定的属性
                    @android:style/Widget.ProgressBar.Horizontal|Large|Small 依次是条状、大圆、小圆
            android:max="100"
            android:progress="50"
            android:thumb="@mipmap/qq1"
        -->
    <SeekBar
        android:id="@+id/seekBar"
        style="@style/Widget.AppCompat.ProgressBar.Horizontal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:max="100"
        android:progress="50"
        android:thumb="@drawable/yellow" />
```

### spinner

```java
    <!-- android:entries="@array/ctype"：引入数组资源文件作为列表项-->
    <Spinner
        android:id="@+id/spinner"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2pt"
        ></Spinner>
```

### 选项卡

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：选项卡的实现
 *
 *流程：
 *    1.获取xml中TabHost对象
 *    2.初始化TabHost组件
 *    3.声明并实例化一个LayoutInflater对象
 *    4.添加标签名称和对应的布局管理器文件
 *注意：这里没有把第三个标签页文件写出来
 */

   //1.获取xml中TabHost对象
    TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
    //2.初始化TabHost组件
    tabHost.setup();
    //3.声明并实例化一个LayoutInflater对象
    LayoutInflater inflater = LayoutInflater.from(this);
    //R.layout.tab1后边的 tab1 表示的是新建的标签页布局文件的名称
    inflater.inflate(R.layout.tab1, tabHost.getTabContentView());
    inflater.inflate(R.layout.tab2,tabHost.getTabContentView());
    inflater.inflate(R.layout.tab3,tabHost.getTabContentView());
    //4.下面方法中三个参数：
    //      无关紧要值、标签页标题、新建的标签页中布局管理器的id
    //实质上就是设置在FrameLayout的布局当中
    tabHost.addTab(tabHost.newTabSpec("tab1")
    .setIndicator("标签页1")
    .setContent(R.id.linearlayoutTab1));//添加第一个标签页
    tabHost.addTab(tabHost.newTabSpec("tab2")
    .setIndicator("标签页2")
    .setContent(R.id.linearlayoutTab2));//添加第二个标签页
    tabHost.addTab(tabHost.newTabSpec("tab3")
    .setIndicator("标签页3")
    .setContent(R.id.linearlayoutTab3));//添加第二个标签页
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:activity_main.xml********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：选项卡主布局文件
 *
 *流程：
 *    1.添加TabHost选项卡的管理器
 *    2.添加子管理器管理标题头 LinearLayout、TabWidget、FrameLayout
 *注意：这里的布局管理器比较特殊；下面的布局比较特殊
 */
        <?xml version="1.0" encoding="utf-8"?>
        <TabHost xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@android:id/tabhost"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context="com.wanli.demo.MainActivity">
        <!--通常设置为线性布局-->
        <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TabWidget
        android:id="@android:id/tabs"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

        <FrameLayout
        android:id="@android:id/tabcontent"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></FrameLayout>
        </LinearLayout>
        </TabHost>

/**----------------------------END：2布局文件:activity_main.xml------------------------------------**/
/************************2布局文件:选项卡标签页********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：选项卡布局所需要的标签页
*/
        <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/linearlayoutTab1"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:layout_gravity="left"
        android:singleLine="true"
        android:text="文本一"
        android:textSize="14pt" />
        </LinearLayout>
/**----------------------------END：2布局文件:选项卡标签页------------------------------------**/
/************************2布局文件:选项卡标签页2********************************/
/************************制定时间：2020/3/6 0006********************************/
        <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/linearlayoutTab2"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2px"
        android:layout_gravity="center"
        android:singleLine="true"
        android:text="文本二"
        android:textSize="14pt" />
        </LinearLayout>
/**----------------------------END：2布局文件:选项卡标签页2------------------------------------**/
```

### webView网页视图

```java
package com.wanli.webviewdemo;
/************************WebView:WebViewButtonActivity********************************/
/************************制定时间：2020/3/13 0013********************************/
/**
 *功能：实现网页的播放
 *
 *流程：
 *    1.加载网页资源
 *    2.设置JS交互
 *    3.其他交互设置
 *注意：这里仅仅只是实现了部分功能
 */
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;


/**
 * Function:
 * Create date on 15/11/26.
 *
 * @author Conquer
 * @version 1.0
 */
public class WebViewButtonActivity  extends AppCompatActivity {

    private WebView mWebView;

    public class TestJSEvent{
        //告诉如何让js调用
        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(WebViewButtonActivity.this, toast, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_button);
        mWebView = (WebView) findViewById(R.id.web_view);
        //功能五：开启调试状态，需要版本4.4
        WebView.setWebContentsDebuggingEnabled(true);
        //
        mWebView.loadUrl("http://www.baidu.com");
        HashMap<String,String> map = new HashMap<>();
        map.put("token","xxxxxx");
        map.put("my_header","header");

        //功能一：加载网上页面
        mWebView.loadUrl("http://www.baidu.com", map);
        //功能二：加载本地URL
        //mWebView.loadUrl("file:///android_asset/test.html");
        //功能三：和JS进行交互
        // 设置和js交互的能力
        mWebView.getSettings().setJavaScriptEnabled(true);
        // JS调用原生App
        mWebView.addJavascriptInterface(new TestJSEvent(), "TestApp");
        // 原生App调用JS，使得js代码被执行
        mWebView.loadUrl("javascript:javaCallJS('')");

        //功能六：设置cookie
        CookieManager cookieManager = CookieManager.getInstance();
        //设置允许cookie
        cookieManager.setAcceptCookie(true);
        //清除cookie
        cookieManager.setCookie("domain", "cookie");

        mWebView.setWebViewClient(new WebViewClient(){
            @Override//拦截界面，根据url信息进行逻辑处理
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 404页面
                if(url.contains("404")){
                    view.loadUrl("http://www.zhihu.com");
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override//页面刚刚开始加载的时候
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 页面开始：显示loading动画
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 页面开始：隐藏loading动画
                super.onPageFinished(view, url);
            }

            @Override//页面正在加载
            public void onLoadResource(WebView view, String url) {
                // url 替换，可以选择替换图片或者不加载图片
                if(url.contains("logo.img")){

                }
                super.onLoadResource(view, url);
            }

            @Override//拦截网页请求
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                // request        hybrid 离线网页
                return super.shouldInterceptRequest(view, request);
            }
        });

        mWebView.setWebChromeClient(new TestWebChromeClient());
    }

    public class TestWebChromeClient extends WebChromeClient {
        public TestWebChromeClient() {
            super();
        }

        @Override//功能七：显示进度
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            super.onShowCustomView(view, requestedOrientation, callback);
        }
    }

    @Override//功能三：历史记录
    //原先的功能是Activity返回时的动作，原本就是直接销毁当前Activity
    public void onBackPressed() {
        if(mWebView != null && mWebView.canGoBack()){
            //获取历史记录
//            WebBackForwardList webBackForwardList = mWebView.copyBackForwardList();
//            WebHistoryItem historyItem = webBackForwardList.getItemAtIndex(0);
//            String historyUrl = historyItem.getUrl();
            //下面分别是网页的后退、前进、设置后退前进步数、刷新
            mWebView.goBack();
            mWebView.goForward();
            mWebView.goBackOrForward(+1);
            mWebView.reload();
        } else {
            super.onBackPressed();
        }
    }
}
/**----------------------------END：WebView:WebViewButtonActivity------------------------------------**/
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical">

<WebView
android:id="@+id/web_view"
android:layout_width="match_parent"
android:layout_height="match_parent">
</WebView>
</LinearLayout>
```

### 高级UI组件使用示例

### gridView展示多图(xml配置适配器)

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：网格视图 的使用
 *
 *流程：
 *    1.布局管理器中添加GridView组件
 *    2.创建布局文件cell存放图片视图ImageView
 *    3.制作适配器SimpleAdapter
 *    4.添加适配器到网格视图
 *注意：需要配置适配器，布局文件cell配合(给cell中的ImageView添加ID属性)
 *
 *方法：
 */
/************************方法段:类变量********************************/
//图片资源数组
private Integer[] $pictureArray$ = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
        R.drawable.a4,};
/************************方法段:onCreate()********************************/
//获取布局文件中的GridView组件
GridView gridView = (GridView) findViewById(R.id.gridView);
//map:String代表适配器中的ImageView的id值，Object代表目标图片
List<Map<String, Object>> listItem = new ArrayList<>();
//循环就是为了将所有的图片放入ImageView显示器当中去
for (int i = 0; i < $pictureArray$.length; i++) {
    Map<String, Object> map = new HashMap<>();
    //通过自己配置的布局文件cell文件中的图片视图id来获取图片
    map.put("$imageAdapter$", $pictureArray$[i]);
    listItem.add(map);
}
//设置适配器，将我们做好的list，也就是存放了我们所有ImageView组件的集合放到适配器中
SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItem, R.layout.cell, new String[]{"$imageAdapter$"}, new int[]{R.id.$imageAdapter$});
gridView.setAdapter(simpleAdapter);
//列表视图监听
private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
    public void onItemClick(AdapterView parent, View v/*当前点击的视图*/, int position/*当前点击的位置*/, long id/*当前点击的行*/) {
        System.out.println(v.getId());
        System.out.println(position);
        System.out.println(id);
    }
};
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************布局文件:cell.xml********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：用于制作适配器
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：如果作为适配器的目标，cell的布局管理器的id值不是必须的，其组件需要必要的id值，
 *      类似于需要放到网格视图中的图片视图就需要为其制定一个id
 *
 *方法：
 */
<?xml version="1.0" encoding="utf-8"?>
<!--如果作为适配器的目标，cell的布局管理器的id值不是必须的，其组件需要必要的id值，
类似于需要放到网格视图中的图片视图就需要为其制定一个id-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="horizontal"
android:layout_width="match_parent"
android:layout_height="match_parent">
<!-- 存放头像-->
<ImageView
android:id="@+id/image"
android:paddingRight="10dp"
android:paddingTop="20dp"
android:paddingBottom="20dp"
android:adjustViewBounds="true"
android:background="#444444"
android:maxWidth="72dp"
android:maxHeight="72dp"
android:layout_height="wrap_content"
android:layout_width="wrap_content"/>
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:padding="10dp"
android:layout_gravity="center"
android:id="@+id/title"
        />
</LinearLayout>
/**----------------------------END：布局文件:cell.xml------------------------------------**/
```

### gridView展示多图(java配置适配器)

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：GridView通过java类设置适配器
 *
 *流程：
 *    1.创建适配器类ImageAdapter
 *    2.之后再Activity的GridView中添加该适配器对象
 */
/************************方法段:onCreate()********************************/

        GridView gridView = (GridView) findViewById(R.id.gridView);  //获取布局文件中的GridView组件
        gridView.setAdapter(new ImageAdapter(this));                //调用ImageAdapter
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************BaseAdapter:ImageAdapter********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：构建适配器对象
 *
 *流程：
 *    1.设置图片数组
 *    2.getView方法中设置视图中需要存放的组件
 *      配置这些组件的属性
 *    3.返回适配器对象
 *    4.
 *注意：可以为这些组件添加额外的属性
 */
        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.GridView;
        import android.widget.ImageView;

//创建ImageAdapter
        public class ImageAdapter extends BaseAdapter {
            private Context mContext;  //获取上下文
            private Integer[] $pictureArray$ = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
                    R.drawable.a4,};

            public ImageAdapter(Context c) {
                mContext = c;
            }

            @Override
            public int getCount() {
                return $pictureArray$.length;//图片数组的长度
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null) {        //判断传过来的值是否为空
                    imageView = new ImageView(mContext);  //创建ImageView组件
                    imageView.setLayoutParams(new GridView.LayoutParams(100, 90));   //为组件设置宽高
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);        //选择图片铺设方式

                } else {
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource($pictureArray$[position]);    //将获取图片放到ImageView组件中
                return imageView; //返回ImageView
            }
        }
/**----------------------------END：Activity:ImageAdapter------------------------------------**/

```

### imageSwitcher手指滑动切换图片

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：ImageSwitch实现手指滑动改变图片
 *
 *流程：
 *    1.获取ImageSwitch组件
 *    2.向ImageSwitch中添加内容
 *    3.为ImageSwitch添加触摸事件
 *注意：需要添加动画资源；需要为ImageSwitch添加图片；需要为ImageSwitch添加事件；
 */
/************************方法段:类变量********************************/
private int[] arrayPictures = new int[]{R.drawable.a1, R.drawable.a2, R.drawable.a3,
        R.drawable.a4
};// 声明并初始化一个保存要显示图像ID的数组
private ImageSwitcher imageSwitcher; // 声明一个图像切换器对象
//要显示的图片在图片数组中的Index
private int pictutureIndex;
//左右滑动时手指按下的X坐标
private float touchDownX;
//左右滑动时手指松开的X坐标
private float touchUpX;
/************************方法段:onCreate()********************************/
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏显示
imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher); // 获取图像切换器
//为ImageSwicher设置Factory，用来为ImageSwicher制造ImageView
imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
    @Override
    public View makeView() {
        ImageView imageView = new ImageView(MainActivity.this); // 实例化一个ImageView类的对象
        imageView.setImageResource(arrayPictures[pictutureIndex]);//根据id加载默认显示图片
        return imageView; // 返回imageView对象
    }
});
imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            //从左往右，看下一张
            if (touchUpX - touchDownX > 100) {
                //取得当前要看的图片的index
                pictutureIndex = pictutureIndex == 0 ? arrayPictures.length - 1 : pictutureIndex - 1;
                //设置图片切换的动画,R.anim.slide_in_left表示引入res/anim下的动画资源文件，如果没有的话需要自己配置xml配置文件
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_left));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_right));
                //设置当前要看的图片
                imageSwitcher.setImageResource(arrayPictures[pictutureIndex]);
                //从右往左，看上一张
            } else if (touchDownX - touchUpX > 100) {
                //取得当前要看的图片index
                pictutureIndex = pictutureIndex == arrayPictures.length - 1 ? 0 : pictutureIndex + 1;
                //设置切换动画
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_left));
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right));
                //设置要看的图片
                imageSwitcher.setImageResource(arrayPictures[pictutureIndex]);
            }
            return true;
        }
        return false;
    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### listView列表视图显示两个组件

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：ListView列表视图的单击事件监听
 *
 *流程：
 *    1.配置cell布局文件
 *    2.布局文件中添加ListView组件
 *    3.设置适配器，适配器中的每个单元添加2个组件
 *    4.设置列表项的单击事件的监听
 *注意：这里需要cell，并且设置两个组件，一个图片视图组件，一个文本框组件需要给这两个组件命名
 */
/************************方法段:类变量********************************/
//图片资源数组
        private Integer[] $pictureArray$ = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
                R.drawable.a4,};
//2.定义并初始化保存图片id的数组
        String[] $title$ = new String[]{"刘一", "陈二", "张三", "李四", "王五",
                "赵六", "孙七", "周八", "吴九"};
/************************方法段:onCreate()********************************/
//获取列表视图
        ListView listview = (ListView) findViewById(R.id.listView);
//map中String为cell中的组件id，Object是对应组件中的内容(图片、文本...)
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
//通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < $pictureArray$.length; i++) {
            // 实例化Map对象，每次创建一个map对象并且这里仅仅存入两个数据
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("$imageAdapter$", $pictureArray$[i]);
            map.put("$textAdapter$", $title$[i]);
            // 将map对象添加到List集合中
            listItems.add(map);
        }
// 创建SimpleAdapter
//参数： 数组名、res/layout下的资源文件名、cell中两个组件id、res/layout下的资源文件中两个组件的id
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.cell, new String[]{"$textAdapter$", "$imageAdapter$"}, new int[]{
                R.id.$textAdapter$, R.id.$imageAdapter$});

// 将适配器与ListView关联
        listview.setAdapter(adapter);
//注册单击事件监听器
//列表视图监听
private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
    public void onItemClick(AdapterView parent, View v/*当前点击的视图*/, int position/*当前点击的位置*/, long id/*当前点击的行*/) {
        System.out.println(v.getId());
        System.out.println(position);
        System.out.println(id);
    }
};
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************布局文件:cell.xml********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：配置适配器
 *注意：组件的id设置
 *
 *方法：
 */
        /*
        <?xml version="1.0" encoding="utf-8"?>
        <!--如果作为适配器的目标，cell的布局管理器的id值不是必须的，其组件需要必要的id值，
        类似于需要放到网格视图中的图片视图就需要为其制定一个id-->
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="horizontal"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <!-- 存放头像-->
        <ImageView
        android:id="@+id/imageAdapter"
        android:paddingRight="10dp"
        android:paddingTop="20dp"
        android:paddingBottom="20dp"
        android:adjustViewBounds="true"
        android:background="#444444"
        android:maxWidth="72dp"
        android:maxHeight="72dp"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"/>
        <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="10dp"
        android:layout_gravity="center"
        android:id="@+id/textAdapter"
                />
        </LinearLayout>*/
/**----------------------------END：布局文件:cell.xml------------------------------------**/

```

### progressBar进度条模拟

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：通过线程模拟更新进度条
 *流程：
 *    1.获取进度条，设置进度条进度数值标志
 *    2.设置Handler对象，处理Message
 *    3.开启线程模拟进度条更新，生成随机数进行标志位的更新实现模拟进度条更新
 *注意：这里仅仅只是一个进度条更新的模拟
 */
/************************方法段:类变量********************************/
//进度实时更新数值
private int progressStatus = 0;
ProgressBar progressBar;
Handler handler;
/************************方法段:onCreate()********************************/
//设置全屏显示
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);


//进度条对象获取
progressBar = (ProgressBar) findViewById(R.id.progressBar);
//处理器对象获取
handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 0x111) {
            //通过标志更新进度
            progressBar.setProgress(progressStatus);
        } else {
            //悬浮消息提示
            Toast.makeText(MainActivity.this, "耗时操作已经完成", Toast.LENGTH_SHORT).show();
            //设置进度条不显示，并且不占用空间
            //progressBar.setVisibility(View.GONE);
        }
    }
};
new Thread(new Runnable() {
    public void run() {
        while (true) {
            //获取耗时操作完成的百分比
            progressStatus = doWork();
            Message m = new Message();
            if (progressStatus < 100) {
                m.what = 0x111;
                //发送信息
                handler.sendMessage(m);
            } else {
                m.what = 0x110;
                //发送消息
                handler.sendMessage(m);
                break;
            }
        }
    }

    //模拟一个耗时操作，返回值是整形的完成进度
    private int doWork() {
        //改变完成进度
        progressStatus += Math.random() * 10;
        try {
            //线程休眠200毫秒
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //返回新的进度
        return progressStatus;
    }
}).start();
//开启一个线程

/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### ratingBar星选条的信息获取

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：RatingBar星选条的信息获取
 *
 *流程：
 *    1.获取组件
 *    2.添加事件
 *    3.获取星选条数据
 *注意：注意星选条无法直接添加单击事件，需要通过其他组件辅助实现事件。注意如何获取星选条的信息即可。
 */
/************************方法段:类变量********************************/
RatingBar ratingbar;
/************************方法段:onCreate()********************************/
ratingbar = (RatingBar) findViewById(R.id.ratingBar);    //获取星级评分条
Button button = (Button) findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int result = ratingbar.getProgress();            //获取进度
        float rating = ratingbar.getRating();            //获取等级
        float step = ratingbar.getStepSize();            //获取每次最少要改变多少个星级
        Toast.makeText(MainActivity.this, "step=" + step + " result=" + result + " rating=" + rating, Toast.LENGTH_SHORT).show();
    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### scrollView滚动视图

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：ScrollView拖动的组件
 *注意：这个视图需要注意的就是他的布局，里面需要通过嵌套布局管理器添加多个组件
 */
/************************方法段:onCreate()********************************/
LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout);//获取布局管理器
//1.创建一个新的布局管理器
LinearLayout linearLayout2 = new LinearLayout(MainActivity.this);
//设置为纵向排列
linearLayout2.setOrientation(LinearLayout.VERTICAL);
//2.创建滚动视图组件
ScrollView scrollView = new ScrollView(MainActivity.this);
//3.默认布局中添加滚动视图组件
linearLayout.addView(scrollView);
//4.滚动视图组件中添加新建布局
scrollView.addView(linearLayout2);
//5.创建ImageView组件
ImageView imageView = new ImageView(MainActivity.this);
//ImagView添加图片
imageView.setImageResource(R.mipmap.advise);
//6.创建TextView组件
TextView textView = new TextView(MainActivity.this);
//TextView添加文字
textView.setText(R.string.cidian);
//7.新建布局中添加ImageView组件
linearLayout2.addView(imageView);
//8.新建布局中添加TextView组件
linearLayout2.addView(textView);
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### seekBar拖动条(拖动条组件控制图片透明度实现)

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：SeekBar 拖动条组件
 *
 *流程：实现拖动条SeekBar控制图片的透明度
 *    1.获取组件
 *    2.注册事件
 *    3.实现改变图片透明度的方法
 *注意：注意这里对API的等级有要求，需要API16
 *方法：// 动态改变图片的透明度 image.setImageAlpha(progress);
 */
/************************方法段:类变量********************************/
ImageView image;          //定义图片
SeekBar seekBar;          //定义拖动条
/************************方法段:onCreate()********************************/
image = (ImageView) findViewById(R.id.imageView);    //获取图片
seekBar = (SeekBar) findViewById(R.id.seekBar);    //获取拖动条
//为拖动条设置监听事件
seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    // 当拖动条的滑块位置发生改变时触发该方法
    public void onProgressChanged(SeekBar arg0, int progress,
                                  boolean fromUser) {
        // 动态改变图片的透明度
        image.setImageAlpha(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar bar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar bar) {
    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### spinner下拉列表(Spinner下拉列表菜单选中事件)

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：Spinner下拉列表
 *
 *流程：
 *    1.布局，配置数组资源
 *    2.创建下拉列表的适配器
 *    3.为下拉列表注册菜单选项单击事件
 *注意：注意需要为适配器指定数组资源
 *方法：适配器方法比较特殊，和之前的网格视图和图片选择器不太一样
 */
Spinner spinner = (Spinner) findViewById(R.id.spinner);   //获取下拉列表

//方法一
//        String[] stringResource=new String[]{"全部","电影","图书","唱片","小事","用户","小组","群聊","游戏","活动"};
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,stringResource);
//方法二
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.stringResource/*表示引用的数组资源*/, android.R.layout.simple_dropdown_item_1line);//创建一个适配器
// 为适配器设置列表框下拉时的选项样式
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// 将适配器与选择列表框关联
spinner.setAdapter(adapter);
//为下拉列表创建监听事件
spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String result = parent.getItemAtPosition(position).toString();    //获取选择项的值
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show(); //显示被选中的值
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### 适配器java简单示例

```java
/************************BaseAdapter:ImageAdapter********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：构建适配器对象
 *
 *流程：
 *    1.设置图片数组
 *    2.getView方法中设置视图中需要存放的组件
 *      配置这些组件的属性
 *    3.返回适配器对象
 *    4.
 *注意：可以为这些组件添加额外的属性
 */
        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.GridView;
        import android.widget.ImageView;

//创建ImageAdapter
        public class ImageAdapter extends BaseAdapter {
            private Context mContext;  //获取上下文
            private Integer[] $pictureArray$ = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
                    R.drawable.a4,};

            public ImageAdapter(Context c) {
                mContext = c;
            }

            @Override
            public int getCount() {
                return $pictureArray$.length;//图片数组的长度
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null) {        //判断传过来的值是否为空
                    imageView = new ImageView(mContext);  //创建ImageView组件
                    imageView.setLayoutParams(new GridView.LayoutParams(100, 90));   //为组件设置宽高
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);        //选择图片铺设方式

                } else {
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource($pictureArray$[position]);    //将获取图片放到ImageView组件中
                return imageView; //返回ImageView
            }
        }
/**----------------------------END：Activity:ImageAdapter------------------------------------**/

```

### viewStub懒加载的使用

```java
/************************Activity:MyActivity********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：ViewStub的懒加载使用
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：ViewStub只能加载一次，再次加载会抛出异常
 *
 *方法：
 */
public class MyActivity1 extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_one);
    Button btnNext #findViewById(R.id.button);
    btnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // 这里调用的是inflate方法，当然，也可以调用setVisibility方法（但是不建议这么做）
        // 只能点击一次加载视图按钮，因为inflate只能被调用一次。调用完成ViewStub被销毁
        // 如果再次点击按钮，会抛出异常"ViewStub must have a non-null ViewGroup viewParent"
        ViewStub viewStub # findViewById(R.id.viewStub);
        Log.i("TAG", viewStub.toString());
        if(viewStub !# null){
          viewStub.inflate();
          //这里注意ViewStub只是一个容器，所以在其显示后，其中的view就是在Activity中展示，所以直接findViewByid即可
          Button btnOne #findViewById(R.id.button2);
          Button btnTwo #findViewById(R.id.button3);
          btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(MyActivity1.this,"点击了第一个ViewStub按钮",Toast.LENGTH_LONG).show();
            }
          });
          btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(MyActivity1.this,"点击了第二个ViewStub按钮",Toast.LENGTH_LONG).show();
            }
          });
        }
      }
    });
  }
}
/**----------------------------END：Activity:MyActivity------------------------------------**/
/************************2布局文件:layout********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：ViewStub的布局
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：ViewStub中的属性设置需要注意，而且其中不能调用含有merge标签的布局文件
 */
<LinearLayout xmlns:android#"http://schemas.android.com/apk/res/android"
    android:layout_width#"match_parent"
    android:layout_height#"match_parent"
    android:gravity#"center"
    android:orientation#"vertical">
<Button
    android:id#"@+id/button"
        android:background#"#444444"
        android:layout_width#"wrap_content"
        android:layout_height#"wrap_content"
        android:layout_gravity#"center"
        android:layout_margin#"2px"
        android:text#"按钮一"
        />
<!--这里需要指定layout属性-->
<ViewStub
    android:id#"@+id/viewStub"
        android:inflatedId#"@id/linearLayout"
        android:layout_width#"match_parent"
        android:layout_height#"wrap_content"
        android:layout#"@layout/activity_head"></ViewStub>
</LinearLayout>


/**----------------------------END：2布局文件:layout------------------------------------**/
/************************2布局文件:layout********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：用于复用的布局文件
 */
<LinearLayout xmlns:android#"http://schemas.android.com/apk/res/android"
    android:id#"@+id/linearLayout"
    android:layout_width#"match_parent"
    android:layout_height#"match_parent"
    android:gravity#"center"
    android:orientation#"vertical">
<Button
    android:id#"@+id/button2"
        android:layout_width#"wrap_content"
        android:layout_height#"wrap_content"
        android:layout_margin#"2px"
        android:layout_gravity#"center"
        android:background#"#444444"
        android:text#"按钮二" />
<Button
    android:id#"@+id/button3"
        android:background#"#444444"
        android:layout_width#"wrap_content"
        android:layout_height#"wrap_content"
        android:layout_gravity#"center"
        android:layout_margin#"2px"
        android:text#"按钮三"
        />
</LinearLayout>



/**----------------------------END：2布局文件:layout------------------------------------**/


```

### widget窗口小部件示例

```java
package com.wanli.mywidget;
/************************Brodcast:WidgetDemo********************************/
/************************制定时间：2020/3/13 0013********************************/
/**
 *功能：窗口小部件
 *
 *流程：
 *    1.继承AppWidgetProvider
 *    2.设置广播接收时的更新请求动作
 *    3.设置更新
 */
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
public class WidgetDemo extends AppWidgetProvider {

    public static final String WIDGET_BUTTON_ACTION = "widget_button_action";

    @Override//设置接收信息后处理的动作，实质就是广播
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent != null && TextUtils.equals(intent.getAction(),WIDGET_BUTTON_ACTION )){
            Log.i(WIDGET_BUTTON_ACTION, "be clicked");
            //设置更新时的动作
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);
            remoteViews.setTextViewText(R.id.widget_text_view, "be clicked");
            remoteViews.setTextColor(R.id.widget_text_view, Color.RED);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, WidgetDemo.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    @Override//更新的时候
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //更新布局文件
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);
        //Intent是最后需要通过广播的方式发送出去
        Intent intent = new Intent();
        intent.setClass(context, WidgetDemo.class);
        intent.setAction(WIDGET_BUTTON_ACTION);
        //未来要执行的动作
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        remoteViews.setOnClickPendingIntent(R.id.widget_button,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
    }
}
/**----------------------------END：Brodcast:WidgetDemo------------------------------------**/

/************************2布局文件:WidgetDemo********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：实现小部件的属性设置和指定其布局内容
 *注意：需要指定额外的布局文件
 */
<?xml version="1.0" encoding="utf-8"?>
<!--    android:initialLayout="@layout/activity_my_widget"
表示初始化的时候指定的布局文件
android:widgetCategory="home_screen"
表示显示在主屏幕上，也可以设置为显示在锁屏上面
        -->
<appwidget-provider
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:initialLayout="@layout/activity_main"
android:minHeight="140dp"
android:minWidth="140dp"
android:updatePeriodMillis="20000"
android:widgetCategory="home_screen"
        >
</appwidget-provider>
/**----------------------------END：2布局文件:WidgetDemo------------------------------------**/

/************************2布局文件:AndroidManifest********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：配置小部件广播
 *注意：注意其action属性的设置
 */
<receiver android:name=".WidgetDemo">
<intent-filter>
<!--监测widget的更新-->
<action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
</intent-filter>
<meta-data
android:name="android.appwidget.provider"
android:resource="@layout/widget_setting"></meta-data>
</receiver>
/**----------------------------END：2布局文件:AndroidManifest------------------------------------**/

```

## `事件、手势`

### viewFlipper手势组件

```java
        <ViewFlipper
        android:id="@+id/flipper"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
                >
        </ViewFlipper>
```

### 事件 物理按键事件，双击返回键退出app

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：物理按键监听 连续按下返回键退出程序
 *
 *流程：
 *    1.需要设置一个间隔时间标志位，第一次按下肯定是不会退出程序的，因为起始标志位状态为0,；接着第一次按下之后
 *      就有可能在下一次按下时退出程序。如果下一次按下时间超过2秒，那么不退出程序，更新标志位。如果下一次按下时间
 *      少于2秒，那么就直接退出程序。
 *    2.实现物理按键需要重写方法onKeyDown()
 *注意：方法的重写不要忘记；物理按键不需要我们额外定义组件。
 */
/************************方法段:2类变量********************************/
private long exitTime = 0;
/************************方法段:onKeyDown()********************************/
@Override
public boolean onKeyDown ( int keyCode, KeyEvent event){
    if (keyCode == KeyEvent.KEYCODE_BACK) {
        exit();
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
/************************方法段:exit()自定义方法，实现程序逻辑退出********************************/

public void exit () {
    if ((System.currentTimeMillis() - exitTime) > 2000) {
        Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        exitTime = System.currentTimeMillis();
    } else {
        finish();
        System.exit(0);
    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### 事件 编辑框监听

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：添加编辑框文本编辑监听事件
 */
/********************************** onCreate *************************************************/
final EditText editText = (EditText) findViewById(R.id.editText);
editText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //如果文本框有输入，那么给出提示信息
        if (editText.length() > 0) {
            Toast.makeText(MainActivity.this,"文本正在输入",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### 事件 长按、单击、触摸

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：依次是 单机事件、长按事件、触摸事件
 *注意：长按事件默认是2秒之后触发；触摸事件包括按下与松开两个动作
 */
/************************方法段:2类变量********************************/
View.OnClickListener click = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "单机事件触发了！", Toast.LENGTH_SHORT).show();
    }
};
//长按事件实现类
View.OnLongClickListener longClick = new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(MainActivity.this, "长按事件触发了！", Toast.LENGTH_SHORT).show();
        //下面两句固定写法，获取menu
        registerForContextMenu(v);
        openContextMenu(v);
        return false;
    }
};
//触摸事件实现类
View.OnTouchListener touchListener = new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(MainActivity.this, "按下触摸事件触发了！", Toast.LENGTH_SHORT).show();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Toast.makeText(MainActivity.this, "抬起触摸事件触发了！", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
};

//额外内容，通过方法重写生成菜单栏组件
@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    menu.add("收藏");
    menu.add("举报");
}
/************************方法段:onCreate()********************************/
Button button = (Button) findViewById(R.id.button); //获取ImageView组件
Button button1 = (Button) findViewById(R.id.button1); //获取ImageView组件
//button组件注册单击事件
button.setOnClickListener(click);
//button组件注册长按事件
button.setOnLongClickListener(longClick);
//button1组件注册触摸事件(为了效果演示)
button1.setOnTouchListener(touchListener);
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### 手势 案例一滑动-淡入淡出

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：
 *
 *流程：
 *  1.第一步：让MainActivity实现 GestureDetector.OnGestureListener接口并且实现其中方法
 *  2.第二步：定义一个全局的手势检测器
 *  3.第三步：将要显示的图片加载到ViewFlipper中，并且初始化动画数组
 *  4.第四步：在onFile()方法中通过触摸事件的X坐标判断是想做滑动还是向右滑动，并且为其设置动画
 *  5.第五步：将Activity上的触摸事件交给GestureDetector处理
 *注意：
 *
 *方法：
 */

/*
public class MainActivity extends Activity implements GestureDetector.OnGestureListener {
    //第一步：让MainActivity实现 GestureDetector.OnGestureListener接口并且实现其中方法
    Animation[] animation = new Animation[4];
    private Integer[] picture = {R.mipmap.advise, R.mipmap.advise1, R.mipmap.advise2,
            R.mipmap.alarm,};
    final int distance = 50;
    ViewFlipper viewFlipper;
    //第二步：定义一个全局的手势检测器
    GestureDetector detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector = new GestureDetector(MainActivity.this, this);
        //第三步：将要显示的图片加载到ViewFlipper中，并且初始化动画数组
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        for (int i = 0; i < picture.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(picture[i]);
            viewFlipper.addView(imageView);
        }
        animation[0] = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        animation[1] = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        animation[2] = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        animation[3] = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //第四步：在onFile()方法中通过触摸事件的X坐标判断是想做滑动还是向右滑动，并且为其设置动画
        //从右向左滑动
        if (e1.getX() - e2.getX() > distance) {
            viewFlipper.setInAnimation(animation[2]);
            viewFlipper.setOutAnimation(animation[1]);
            viewFlipper.showPrevious();
            return true;
        } else if (e2.getX() - e1.getX() > distance) {
            viewFlipper.setInAnimation(animation[0]);
            viewFlipper.setOutAnimation(animation[3]);
            viewFlipper.showNext();
            return true;
        }
        return false;
    }
    //第五步：将Activity上的触摸事件交给GestureDetector处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }
}
*/
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:activity_main.xml********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：手势功能实现需要的组件

 */
<ViewFlipper
android:id="@+id/flipper"
android:layout_width="match_parent"
android:layout_height="match_parent"></ViewFlipper>
/**----------------------------END：2布局文件:activity_main.xml------------------------------------**/

```

### 手势 自定义手势输入法实现

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：
 *
 *流程：
 *    1.加载手势文件gestures
 *    2.配置手势组件gesture
 *    3.为手势添加监听
 *    4.通过词典获取手势中的文字信息
 *注意：需要创建raw目录，然后放置自己的手势文件；需要额外添加组件  <android.gesture.GestureOverlayView
 *
 *方法：
 */

public class MainActivity extends Activity implements GestureOverlayView.OnGesturePerformedListener {
    //手势文件对象声明
    private GestureLibrary library;
    //编辑框组件声明
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载手势文件
        library = GestureLibraries.fromRawResource
                (MainActivity.this, R.raw.gestures);
        //获取编辑框
        editText = (EditText) findViewById(R.id.editText);
        // 如果加载失败则退出
        if (!library.load()) {
            finish();
        }
        //获取手势组件
        GestureOverlayView gestureOverlayView = (GestureOverlayView) findViewById(R.id.gesture);
        gestureOverlayView.setGestureColor(Color.BLACK);
        gestureOverlayView.setFadeOffset(1000);
        // 增加事件监听器
        gestureOverlayView.addOnGesturePerformedListener(this);
    }
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        // 获得全部预测结果
        ArrayList<Prediction> gestures = library.recognize(gesture);
        // 保存当前预测的索引号
        int index = 0;
        // 保存当前预测的得分
        double score = 0.0;
        // 获得最佳匹配结果
        for (int i = 0; i < gestures.size(); i++) {
            // 获得一个预测结果
            Prediction result = gestures.get(i);
            if (result.score > score) {
                index = i;
                score = result.score;
            }
        }
        // 获得编辑框中已经包含的文本
        String text = editText.getText().toString();
        // 获得最佳匹配
        text += gestures.get(index).name;
        // 更新编辑框
        editText.setText(text);
    }
}

/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:activity_main.xml********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：自定义手势所需要的布局文件
 * 注意：需要添加<android.gesture.GestureOverlayView 标签
 */
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="@drawable/beijing"
tools:context="com.mingrisoft.MainActivity">
<EditText
android:id="@+id/editText"
android:layout_width="200dp"
android:layout_height="wrap_content"
android:layout_marginLeft="40dp"
android:layout_marginTop="190dp"/>
<android.gesture.GestureOverlayView
android:id="@+id/gesture"
android:layout_width="320dp"
android:layout_height="180dp"
android:layout_alignParentBottom="true"
android:gestureStrokeType="multiple"
        >
</android.gesture.GestureOverlayView>
</RelativeLayout>


/**----------------------------END：2布局文件:activity_main.xml------------------------------------**/
```

## `资源文件`

### 9-patch.xml

```java
<?xml version="1.0" encoding="utf-8"?>
<!--dither：表示是否允许抖动-->
<nine-patch xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@drawable/myninepatch"
    android:dither="false" />
```

### bitmap.xml

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
XML位图是在XML中定义的指向位图文件的资源。效果是原始位图文件的别名。XML可以为位图指定额外的属性，比如抖动和平铺。
<?xml version="1.0" encoding="utf-8"?>
<bitmap
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@[package:]drawable/drawable_resource"
    android:antialias=["true" | "false"]//抗锯齿
    android:dither=["true" | "false"]//抖动
    android:filter=["true" | "false"]//是否使得图片收缩或者拉伸更加平滑
    android:gravity=["top" | "bottom" | "left" | "right" | "center_vertical" |//图形在容器中的位置
                      "fill_vertical" | "center_horizontal" | "fill_horizontal" |
                      "center" | "fill" | "clip_vertical" | "clip_horizontal"]
    android:mipMap=["true" | "false"]//mipmap提示是否可用
    android:tileMode=["disabled" | "clamp" | "repeat" | "mirror"] //定义平铺模式。当启用平铺模式时，位图将重复。当启用平铺模式时，重力将被忽略。
    />
-->
<bitmap xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@drawable/icon"
    android:tileMode="repeat" />
<!--
The filename is used as the resource ID.
直接在drawable目录下定义
调用方式和普通图片文件相同
-->
```

### bool

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <bool name="screen_small">true</bool>
    <bool name="adjust_view_bounds">true</bool>
</resources>
XML file saved at res/values-small/bools.xml:

Resources res = getResources();
boolean screenIsSmall = res.getBoolean(R.bool.screen_small);

<ImageView
    android:layout_height="fill_parent"
    android:layout_width="fill_parent"
    android:src="@drawable/logo"
    android:adjustViewBounds="@bool/adjust_view_bounds" />
```

### clip drawable 剪切

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
它根据该可绘制对象的当前级别剪辑另一个可绘制对象。您可以根据级别控制子drawable在宽度和
高度上被裁剪的程度，以及控制它在整个容器中的位置的重力。最常用来实现进度条之类的东西。
<?xml version="1.0" encoding="utf-8"?>
<clip
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/drawable_resource"
    android:clipOrientation=["horizontal" | "vertical"]
    android:gravity=["top" | "bottom" | "left" | "right" | "center_vertical" |
                     "fill_vertical" | "center_horizontal" | "fill_horizontal" |
                     "center" | "fill" | "clip_vertical" | "clip_horizontal"] />
-->
<clip xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/a1"
    android:clipOrientation="horizontal"
    android:gravity="left" />

<!--
<ImageView
    android:id="@+id/image"
    android:background="@drawable/clip"
    android:layout_height="wrap_content"
    android:layout_width="wrap_content" />

ImageView imageview = (ImageView) findViewById(R.id.image);
Drawable drawable = imageview.getBackground();
if (drawable instanceof CLipDrawable) {
    ((ClipDrawable)drawable).setLevel(drawable.getLevel() + 1000);
}
-->
```

### color

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
   <color name="opaque_red">#f00</color>
   <color name="translucent_red">#80ff0000</color>
</resources>
XML file saved at res/values/colors.xml:
Resources res = getResources();
int color = res.getColor(R.color.opaque_red);

<TextView
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:textColor="@color/translucent_red"
    android:text="Hello"/>
```

### dimension 尺寸资源

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="textview_height">25dp</dimen>
    <dimen name="textview_width">150dp</dimen>
    <dimen name="ball_radius">30dp</dimen>
    <dimen name="font_size">16sp</dimen>
</resources>
XML file saved at res/values/dimens.xml:

Resources res = getResources();
float fontSize = res.getDimension(R.dimen.font_size);

<TextView
    android:layout_height="@dimen/textview_height"
    android:layout_width="@dimen/textview_width"
    android:textSize="@dimen/font_size"/>

```

### id

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <item type="id" name="button_ok" />
    <item type="id" name="dialog_exit" />
</resources>

XML file saved at res/values/ids.xml:

<Button android:id="@id/button_ok"
    style="@style/button_style" />

R.id.dialog_exit
```

### insert drawable 插入位图，用于填充空缺

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
在XML中定义的一个绘图项，它将另一个绘图项插入指定的距
离。当视图需要比视图实际边界小的背景时，这是很有用的。
<?xml version="1.0" encoding="utf-8"?>
<inset
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/drawable_resource"
    android:insetTop="dimension"
    android:insetRight="dimension"
    android:insetBottom="dimension"
    android:insetLeft="dimension" />
-->
<inset xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/background"
    android:insetTop="10dp"
    android:insetLeft="10dp" />


```

### integer

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <integer name="max_speed">75</integer>
    <integer name="min_speed">5</integer>
</resources>
XML file saved at res/values/integers.xml:

Resources res = getResources();
int maxSpeed = res.getInteger(R.integer.max_speed);
```

### integer array

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <integer-array name="bits">
        <item>4</item>
        <item>8</item>
        <item>16</item>
        <item>32</item>
    </integer-array>
</resources>

XML file saved at res/values/integers.xml:

Resources res = getResources();
int[] bits = res.getIntArray(R.array.bits);
```

### layer list 有层次的位图视图

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
LayerDrawable是一个管理其他drawable数组的drawable对象。
列表中的每个drawable是按照列表的顺序绘制的——列表中的
最后一个drawable是在顶部绘制的。

<?xml version="1.0" encoding="utf-8"?>
<layer-list
    xmlns:android="http://schemas.android.com/apk/res/android" >
    <item
        android:drawable="@[package:]drawable/drawable_resource"//获取绘制资源
        android:id="@[+][package:]id/resource_name"//该item资源的唯一标识
        android:top="dimension"//顶部偏移量，以px为单位
        android:right="dimension"
        android:bottom="dimension"
        android:left="dimension"
        />
</layer-list>
默认情况下，所有可绘制项都按比例缩放以适应包含视图的大小。因此，把你的图像放
在一个层列表的不同位置可能会增加视图的大小和一些图像缩放适当。为了避免缩放列
表中的项，可以在<item>元素中使用<bitmap>元素来指定drawable，并将重力定义为不
缩放的值，比如“center”。例如，下面的<item>定义了一个可缩放以适应其容器视图
的项:
-->
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item>
        <bitmap android:src="@drawable/android_red"
            android:gravity="center" />
    </item>
    <item android:top="10dp" android:left="10dp">
        <bitmap android:src="@drawable/android_green"
            android:gravity="center" />
    </item>
    <item android:top="20dp" android:left="20dp">
        <bitmap android:src="@drawable/android_blue"
            android:gravity="center" />
    </item>
</layer-list>

<!--
<ImageView
    android:layout_height="wrap_content"
    android:layout_width="wrap_content"
    android:src="@drawable/layers" />
-->
```

### state list 有状态的位图

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
StateListDrawable是在XML中定义的可绘制对象，它使用几个不同的图像来表示相同的
图形，具体取决于对象的状态。例如，按钮小部件可以存在于几种不同的状态之一(按下、
聚焦或都不存在)，并且，使用状态列表drawable，您可以为每种状态提供不同的背景图像。
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android"
    android:constantSize=["true" | "false"]
    android:dither=["true" | "false"]
    android:variablePadding=["true" | "false"] >
    <item
        android:drawable="@[package:]drawable/drawable_resource"
        android:state_pressed=["true" | "false"]
        android:state_focused=["true" | "false"]
        android:state_hovered=["true" | "false"]
        android:state_selected=["true" | "false"]
        android:state_checkable=["true" | "false"]
        android:state_checked=["true" | "false"]
        android:state_enabled=["true" | "false"]
        android:state_activated=["true" | "false"]
        android:state_window_focused=["true" | "false"] />
</selector>

-->
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
        android:drawable="@drawable/button_pressed" /> <!-- pressed -->
    <item android:state_focused="true"
        android:drawable="@drawable/button_focused" /> <!-- focused -->
    <item android:state_hovered="true"
        android:drawable="@drawable/button_focused" /> <!-- hovered -->
    <item android:drawable="@drawable/button_normal" /> <!-- default -->
</selector>

<!--
XML file saved at res/drawable/button.xml:
<Button
android:layout_height="wrap_content"
android:layout_width="wrap_content"
android:background="@drawable/button" />
-->
```

### transition drawable 切换位图淡入淡出效果

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
它管理若干个可画图形，每个可画图形都有一个最大值。使用setLevel()设置
drawable的level值将加载level列表中的drawable资源，该资源的android:ma
xLevel值大于或等于传递给方法的值。
<?xml version="1.0" encoding="utf-8"?>
<level-list
    xmlns:android="http://schemas.android.com/apk/res/android" >
    <item
        android:drawable="@drawable/drawable_resource"
        android:maxLevel="integer"
        android:minLevel="integer" />
</level-list>

-->
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
        android:drawable="@drawable/button_pressed" /> <!-- pressed -->
    <item android:state_focused="true"
        android:drawable="@drawable/button_focused" /> <!-- focused -->
    <item android:state_hovered="true"
        android:drawable="@drawable/button_focused" /> <!-- hovered -->
    <item android:drawable="@drawable/button_normal" /> <!-- default -->
</selector>

<!--
Once this is applied to a View, the level can be changed with setLevel() or setImageLevel().
-->
```

### typed array drawable

```java
在XML中定义的类型。您可以使用它来创建其他资源的数组，例如drawables。注意，
这个数组并不要求是同构的，所以您可以创建一个混合资源类型的数组，但是您必
须知道数据类型在数组中的什么位置，这样您就可以使用TypedArray的get…()方
法正确地获取每个项目。

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <array name="icons">
        <item>@drawable/home</item>
        <item>@drawable/settings</item>
        <item>@drawable/logout</item>
    </array>
    <array name="colors">
        <item>#FFFF0000</item>
        <item>#FF00FF00</item>
        <item>#FF0000FF</item>
    </array>
</resources>

XML file saved at res/values/arrays.xml:

Resources res = getResources();
TypedArray icons = res.obtainTypedArray(R.array.icons);
Drawable drawable = icons.getDrawable(0);

TypedArray colors = res.obtainTypedArray(R.array.colors);
int color = colors.getColor(0,0);
```

### anim 平移

```java
<?xml version="1.0" encoding="utf-8"?><set xmlns:android="http://schemas.android.com/apk/res/android">
<!--
        1.设置平移的起始点的X轴和Y轴
        android:fromXDelta="0"
        android:fromYDelta="0"
        2.设置平移的结束点的X轴和Y轴
        android:toXDelta="300"
        android:toYDelta="300"
        3.设置持续时间为2秒
        android:duration="2000"
        -->
            <!-- 动画资源的使用Java代码：
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.anim_demo);
        image.startAnimation(hyperspaceJump);-->
<translate
    android:fromXDelta="0"
    android:fromYDelta="0"
    android:toXDelta="300"
    android:toYDelta="300"
    android:duration="2000" />
</set>
```

### anim 旋转

```java
    <?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<!--
        1.设置从0度开始旋转
        android:fromDegrees="0"
        2.设置结束旋转位置为360度
        android:toDegrees="360"
        3.设置旋转中心轴为X、Y轴的50%
                android:pivotX="50%"
        android:pivotY="50%"
        4.设置持续时间为2秒
        android:duration="2000"/>
        -->
    <!-- 动画资源的使用Java代码：
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.anim_demo);
        image.startAnimation(hyperspaceJump);-->
<rotate
    android:fromDegrees="0"
    android:toDegrees="360"
    android:pivotX="50%"
    android:pivotY="50%"
    android:duration="2000"/>
</set>
```

### anim 淡入

```java
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<alpha android:fromAlpha="0"
android:toAlpha="1"
android:duration="4000"/>
    <!-- 动画资源的使用Java代码：
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.anim_demo);
        image.startAnimation(hyperspaceJump);-->
</set>

```

### anim 缩放

```java
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<!--
        1.设置开始比例和原图比例为1:1
android:fromXScale="1.0"
android:fromYScale="1.0"
2.设置结束比例和原图比例为0:1，也就是消失
android:toXScale="0.0"
android:toYScale="0.0"
3.设置旋转中心轴为X、Y轴的50%
        android:pivotX="50%"
android:pivotY="50%"
4.设置持续时间为2秒
android:duration="2000"
        -->
    <!-- 动画资源的使用Java代码：
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.anim_demo);
        image.startAnimation(hyperspaceJump);-->
<scale
android:fromXScale="1.0"
android:fromYScale="1.0"
android:pivotX="50%"
android:pivotY="50%"
android:toXScale="0.0"
android:toYScale="0.0"
android:duration="2000"
        ></scale>
</set>
```

### array资源

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <integer-array name="bgcolor">
        <item>0xBBE24A83</item>
        <item>0xBB318AD6</item>
        <item>0xBBD73943</item>
        <item>0xBBE69A08</item>
        <item>0xBBBD9663</item>
        <item>0xBBD45ABC</item>
        <item>0xBB4AA6D6</item>
        <item>0xBB8064D2</item>
        <item>0xBBF7A81E</item>
    </integer-array>
    <string-array name="word">
        <item>微信</item>
        <item>通讯录</item>
        <item>QQ</item>
        <item>相机</item>
        <item>时钟</item>
        <item>备忘录</item>
        <item>音乐</item>
        <item>互联网</item>
        <item>邮件</item>
    </string-array>
</resources>
```

### color state list(颜色状态列表)

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
ColorStateList是一个可以在XML中定义的对象，它可以作为一种颜色应
用，但实际上会根据应用它的视图对象的状态改变颜色。
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
    <item
        android:color="hex_color"
        android:state_pressed=["true" | "false"]
        android:state_focused=["true" | "false"]
        android:state_selected=["true" | "false"]
        android:state_checkable=["true" | "false"]
        android:state_checked=["true" | "false"]
        android:state_enabled=["true" | "false"]
        android:state_window_focused=["true" | "false"] //如果应用程序窗口有焦点
        />
</selector>
注意：默认属性需要放在最后，因为状态匹配是从上到下的
-->
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
        android:color="#ffff0000"/> <!-- pressed -->
    <item android:state_focused="true"
        android:color="#ff0000ff"/> <!-- focused -->
    <item android:color="#ff000000"/> <!-- default -->
</selector>
<!--
XML file saved at res/color/button_text.xml:
<Button
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="@string/button_text"
    android:textColor="@color/button_text" />
-->
```

### font bundled(捆绑字体，需要字体文件)

```java
<?xml version="1.0" encoding="utf-8"?>
<!--
<?xml version="1.0" encoding="utf-8"?>
<font-family>
  <font
    android:font="@[package:]font/font_to_include"//res/font/filename.ttf (.ttf, .ttc, .otf, or .xml)
    android:fontStyle=["normal" | "italic"]//定义字体样式。
    android:fontWeight="weight_value" />//字体权重，100和900之间(包括100和900)
</font-family>
-->
<font-family xmlns:android="http://schemas.android.com/apk/res/android">
    <font
        android:fontStyle="normal"
        android:fontWeight="400"
        android:font="@font/lobster_regular" />
    <font
        android:fontStyle="italic"
        android:fontWeight="400"
        android:font="@font/lobster_italic" />
</font-family>

<!--
<?xml version="1.0" encoding="utf-8"?>
<EditText
    android:fontFamily="@font/lobster"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="Hello, World!" />
-->
```

### font download(可下载字体)

```java
<!--
可下载的字体资源定义了可在应用程序中使用的自定义字体。该字体在应用程序本身中不可用;而是从字体提供程序检索字体。
<?xml version="1.0" encoding="utf-8"?>
<font-family
    android:fontProviderAuthority="authority"//定义字体请求的字体提供程序的权限。
    android:fontProviderPackage="package"//用于请求的字体提供程序的包名。这用于验证提供者的标识。
    android:fontProviderQuery="query"//字体的字符串查询
    android:fontProviderCerts="@[package:]array/array_resource"//定义用于签署此提供程序的证书的哈希值集。此选项用于验证提供程序的身份，仅当提供程序不是系统映像的一部分时才需要此选项。该值可以指向单个列表(字符串数组资源)或列表列表(数组资源)，其中每个列表表示签名散列的一个集合。有关这些值，请参阅字体提供程序的文档。
    />
-->
<?xml version="1.0" encoding="utf-8"?>
<font-family xmlns:android="http://schemas.android.com/apk/res/android"
    android:fontProviderAuthority="com.example.fontprovider.authority"
    android:fontProviderPackage="com.example.fontprovider"
    android:fontProviderQuery="Lobster"
    android:fontProviderCerts="@array/certs">
</font-family>

<!--
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string-array name="certs">
      <item>MIIEqDCCA5CgAwIBAgIJA071MA0GCSqGSIb3DQEBBAUAMIGUMQsww...</item>
    </string-array>
</resources>

<?xml version="1.0" encoding="utf-8"?>
<EditText
    android:fontFamily="@font/lobster"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="Hello, World!" />
-->
```

### font资源

```java
<resources>
    <!-- Default screen margins, per the Android Design guidelines. -->
    <dimen name="activity_horizontal_margin">16dp</dimen>
    <dimen name="activity_vertical_margin">16dp</dimen>
    <dimen name="style_title">20sp</dimen>
    <dimen name="margin">10dp</dimen>
    <dimen name="margin_top">50dp</dimen>
    <dimen name="text_margin_top">30dp</dimen>
</resources>
```

### menu资源

```java
<!--
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item //一个菜单项
    android:id="@[+][package:]id/resource_name"
          android:title="string" //表单项内容
          android:titleCondensed="string"//字符串资源。作为字符串资源或原始字符串的压缩标题。此标题用于正常标题过长的情况。
          android:icon="@[package:]drawable/drawable_resource_name"//表单项图标
          android:onClick="method name"//尽量少用，容易混淆
          android:showAsAction=["ifRoom" 只有在应用程序栏中有空间时才放置此项目。如果标记为“ifRoom”的所有项都没有空间，则orderInCategory值最低的项将显示为操作，其余项将显示在overflow菜单中。
                                | "never"永远不要把这个项目在应用程序栏。相反，在应用程序栏的溢出菜单中列出项目。
                                | "withText"还包括带有动作项的标题文本(由android:title定义)。您可以通过使用管道|将这些值与其他值之一一起作为标志集包含进来。
                                | "always"总是把这个项目在应用程序栏。避免使用此选项，除非该项目总是出现在操作栏中是至关重要的。将多个项设置为始终显示为操作项可能导致它们与app栏中的其他UI重叠。
                                | "collapseActionView"与此操作项相关联的操作视图(由android:actionLayout或android:actionViewClass声明)是可折叠的。
                                ]//该项目何时以及如何在应用程序栏中显示为操作项。
          android:actionLayout="@[package:]layout/layout_resource_name"
          android:actionViewClass="class name"//For example, "android.widget.SearchView" to use SearchView as an action view.
          android:actionProviderClass="class name"
          android:alphabeticShortcut="string"//字母快捷键
          android:alphabeticModifiers=["META" | "CTRL" | "ALT" | "SHIFT" | "SYM" | "FUNCTION"]//菜单项的字母快捷方式的修饰符。默认值对应于控制键。
          android:numericShortcut="string"//数字快捷键
          android:numericModifiers=["META" | "CTRL" | "ALT" | "SHIFT" | "SYM" | "FUNCTION"]
          android:checkable=["true" | "false"]
          android:visible=["true" | "false"]
          android:enabled=["true" | "false"]
          android:menuCategory=["container" | "system" | "secondary" | "alternative"]//定义常量项的优先级
          android:orderInCategory="integer"//在一个组中，项目的“重要性”的顺序。
          />
    <group//菜单组
    android:id="@[+][package:]id/resource name"
           android:checkableBehavior=["none" | "all" | "single"]
           android:visible=["true" | "false"]
           android:enabled=["true" | "false"]
           android:menuCategory=["container" | "system" | "secondary" | "alternative"]
           android:orderInCategory="integer" >
        <item />
    </group>
    <item >
        <menu>
          <item />
        </menu>
    </item>
</menu>
-->
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/item1"
        android:icon="@drawable/group_item1_icon"
        android:title="@string/item1"
        app:showAsAction="ifRoom|withText" />
    <group android:id="@+id/group">
        <item
            android:id="@+id/group_item1"
            android:icon="@drawable/group_item1_icon"
            android:onClick="onGroupItemClick"
            android:title="@string/group_item1" />
        <item
            android:id="@+id/group_item2"
            android:icon="@drawable/group_item2_icon"
            android:onClick="onGroupItemClick"
            android:title="@string/group_item2" />
    </group>
    <item
        android:id="@+id/submenu"
        android:title="@string/submenu_title"
        app:showAsAction="ifRoom|withText">
        <menu>
            <item
                android:id="@+id/submenu_item1"
                android:title="@string/submenu_item1" />
        </menu>
    </item>
</menu>
men
    <!--
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.example_menu, menu);
    return true;
}

public void onGroupItemClick(MenuItem item) {
    // One of the group items (using the onClick attribute) was clicked
    // The item parameter passed here indicates which item it is
    // All other menu item clicks are handled by onOptionsItemSelected()
}
-->
```

### string资源

```java
<resources>
    <string name="string_resource1"><b>加粗</b><i>斜体</i><u>下划线</u><font face="微软雅黑" color="red">font设置字体</font></string>
    <string name="string_resource2">Hello, %1$s! You have %2$d new messages</string>
    <!--格式化的字符串获取：String text = getString(R.string.welcome_messages, username, mailCount);-->

    <string-array name="planets_array">
        <item>Mercury</item>
        <item>Venus</item>
        <item>Earth</item>
        <item>Mars</item>
        <!--需要在java中使用
        Resources res = getResources();
        String[] planets = res.getStringArray(R.array.planets_array);
        for (String planet : planets) {
            System.out.println(planet);
        }
        -->
    </string-array>
    <plurals name="numberOfSongsAvailable">
        <!--quantity可用的属性：["zero" | "one" | "two" | "few" | "many" | "other"]-->
        <item quantity="one">Znaleziono %d piosenkę.</item>
        <item quantity="few">Znaleziono %d piosenki.</item>
        <item quantity="other">Znaleziono %d piosenek.</item>
    </plurals>
</resources>

```

### style 资源

```java
<!--
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style
        name="style_name"
        parent="@[package:]style/style_to_inherit" //指定其父元素，可以继承这个父元素的属性
        >
        <item//为样式定义单个属性。必须是<style>元素的子元素。
            name="[package:]style_property_name"
            >style_value</item>
    </style>
</resources>
注意:样式是使用name属性中提供的值(而不是XML文件的名称)引用的简单资源。因此，可以在一个XML文件中，在一个<resources>元素下，将样式资源与其他简单资源组合在一起。
-->

<resources>
    <style name="CustomText" parent="@style/Text">
        <item name="android:textSize">20sp</item>
        <item name="android:textColor">#008</item>
    </style>
    <!--
    主题资源两种引入方式：
        方式一.在java中引入主题
            在onCreate()方法之后引入主题，setTheme(R.style.自定义主题名称)
        方式二.在AndroidManifest.xml中引入自定义主题
            <application
            android:allowBackup="true"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:supportsRtl="true"
            android:theme="@style/AppTheme">
    -->
    <style name="自定义主题名称" parent="@style/AppTheme" >
        <item name="android:name">false</item>
        <item name="android:windowBackground">@drawable/选择自定义主题背景</item>
    </style>
</resources>
    <!--
<?xml version="1.0" encoding="utf-8"?>
<EditText
    style="@style/CustomText"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="Hello, World!" />
-->
```

### 上下文菜单

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：上下文菜单功能实现，就是屏幕中间的菜单
 *
 *流程：
 *    1.获取文本框组件
 *    2.为文本框组件添加上下文菜单的事件
 *    3.编辑事件处理逻辑，为表单项添加处理逻辑
 *注意：需要指定menu资源，其中的资源配置需要注意；还有菜单项事件触发需要2秒；
 *方法： registerForContextMenu(introduce); 为组件注册上下文菜单事件
 */
/************************方法段:2类变量********************************/
TextView introduce;
/************************方法段:onCreate()********************************/
//获取介绍TextView组件
$introduce$ = (TextView) findViewById(R.id.textView);
//为文本框注册上下文菜单
registerForContextMenu($introduce$);
/************************方法段:onCreateContextMenu()********************************/
@Override
//创建上下文菜单菜单项逻辑处理
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    //实例化一个MenuInflater对象
    MenuInflater inflater = new MenuInflater(this);
    //解析菜单文件
    inflater.inflate(R.menu.menu, menu);
    @Override//菜单的选项监听器
    public boolean onContextItemSelected(MenuItem item) {
        //获取menu项中的选项id
        switch (item.getItemId()) {
            //选中介绍文字菜单中的复制时
            case R.id.$menu1$:
                Toast.makeText(MainActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                break;
            //选中介绍文字菜单中的收藏时
            case R.id.$menu2$:
                Toast.makeText(MainActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

### 状态列表资源

```java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--通过组件聚焦状态决定组件文本颜色-->
    <item android:state_focused="true" android:color="#f60"/>
    <item android:state_focused="false" android:color="#0a0"/>
    <!--通过组件可用状态决定组件背景图-->
    <item android:state_focused="true" android:drawable="图片名称1"/>
    <item android:state_focused="false" android:drawable="图片名称2"/>
</selector>
```

### 组件样式

```java
    <!--下面是子父类继承样式示例，子样式的样式属性优先级较高-->
    <style name="自定义组件父样式">
        <item name="android:layout_gravity">center_horizontal</item>
        <item name="android:textStyle">bold</item>
        <item name="android:textSize">30sp</item>
        <item name="android:textColor">#F60</item>
    </style>
    <style name="自定义组件子样式" parent="自定义组件父样式">
        <item name="android:textSize">18sp</item>
        <item name="android:textColor">@color/选择颜色样式</item>
    </style>
```

### 选项菜单

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：选项菜单 点击按钮触发，实现菜单项的罗列
 *
 *流程：
 *    1.创建选项菜单，需要指定菜单资源文件
 *    2.为选项菜单注册事件，编写菜单项逻辑编码
 *注意：这个选项菜单不需要添加组件来触发，这个菜单自带一个菜单组件样式。
 *      这个的菜单的实现很简单，只需要重写其中方法添加菜单项事件即可。
 *方法：onOptionsItemSelected
 */
/************************方法段:选项菜单需要重写的方法********************************/
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    //实例化一个MenuInflater对象
    MenuInflater menuInflater = new MenuInflater(this);
    //解析菜单资源文件
    menuInflater.inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    //获取选中菜单id
    switch (item.getItemId()) {
        //通过选中id跳转指定页面
        case R.id.menu1:
            Toast.makeText(MainActivity.this,"选中了菜单项一",Toast.LENGTH_SHORT).show();
            break;
        //通过选中id跳转指定页面
        case R.id.menu2:
            Toast.makeText(MainActivity.this,"选中了菜单项二",Toast.LENGTH_SHORT).show();
            break;
    }
    return super.onOptionsItemSelected(item);
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
```

## `提示框、对话框、通知、广播`

### toast

```java
Toast.makeText($ClassName$.this,"$Content$",Toast.LENGTH_SHORT).show();
```

### 对话框  4种对话框种类

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：对话框实现
 *
 *流程：
 *    1.显示带取消、确定按钮的对话框
 *    2.显示带列表的对话框
 *    3.显示带单选列表项的对话框
 *    4.显示带多选列表项的对话框
 *注意：对话框的实现比较死板，主要设置其中的属性样式
 */

/************************方法段:2类变量********************************/
private boolean[] checkedItems;
private String[] items;
/************************方法段:onCreate()********************************/
// 一、为“显示带取消、确定按钮的对话框”按钮添加单击事件监听器
// 一、为“显示带取消、确定按钮的对话框”按钮添加单击事件监听器
// 一、为“显示带取消、确定按钮的对话框”按钮添加单击事件监听器
// 获取按钮
Button button1 = (Button) findViewById(R.id.button1);
button1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //创建对话框对象
        //设置对话框的图标
        //设置对话框的标题
        //设置要显示的内容
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setIcon(R.drawable.advise);
        alertDialog.setTitle("乔布斯:");
        alertDialog.setMessage("活着就是为了改变世界，难道还有其他原因吗？");
        //添加取消按钮
        //添加确定按钮
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您单击了否按钮", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您单击了是按钮 ", Toast.LENGTH_SHORT).show();
            }
        });
        //显示对话框
        alertDialog.show();
    }
});
// 二、获取“显示带列表的对话框”按钮
// 二、获取“显示带列表的对话框”按钮
// 二、获取“显示带列表的对话框”按钮
Button button2 = (Button) findViewById(R.id.button2);
button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //创建名言字符串数组
        final String[] items = new String[]{"当你有使命，它会让你更专注", "要么出众，要么出局", "活着就是为了改变世界",
                "求知若饥，虚心若愚"};
        //创建对话框对象
        //设置对话框的图标
        //设置对话框的标题
        //设置要显示的内容
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.advise1);
        builder.setTitle("请选择你喜欢的名言：");
        //添加列表项
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,
                        "您选择了" + items[which], Toast.LENGTH_SHORT).show();

            }
        });
        // 创建对话框并显示
        builder.create().show();
    }
});
// 三、获取“显示带单选列表项的对话框”按钮
// 三、获取“显示带单选列表项的对话框”按钮
// 三、获取“显示带单选列表项的对话框”按钮
Button button3 = (Button) findViewById(R.id.button3);
button3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //创建名字字符串数组
        final String[] items = new String[]{"扎克伯格", "乔布斯", "拉里.埃里森", "安迪.鲁宾", "马云"};
        // 显示带单选列表项的对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //设置对话框的图标
        builder.setIcon(R.drawable.advise2);
        //设置对话框的标题
        builder.setTitle("如果让你选择，你最想做哪一个：");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //显示选择结果
                Toast.makeText(MainActivity.this,
                        "您选择了" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        //添加确定按钮
        builder.setPositiveButton("确定", null);
        // 创建对话框并显示
        builder.create().show();
    }
});
// 四、获取“显示带多选列表项的对话框”按钮
// 四、获取“显示带多选列表项的对话框”按钮
// 四、获取“显示带多选列表项的对话框”按钮
Button button4 = (Button) findViewById(R.id.button4);
button4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //记录各列表项的状态
        checkedItems = new boolean[]{false, true, false, true, false};
        //各列表项要显示的内容
        items = new String[]{"开心消消乐", "球球大作战", "欢乐斗地主", "梦幻西游", "超级玛丽"};
        // 显示带单选列表项的对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //设置对话框的图标
        builder.setIcon(R.drawable.advise2);
        //设置对话框标题
        builder.setTitle("请选择您喜爱的游戏：");
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //改变被操作列表项的状态
                checkedItems[which] = isChecked;

            }
        });
        //为对话框添加“确定”按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for (int i = 0; i < checkedItems.length; i++) {
                    //当选项被选择时
                    if (checkedItems[i]) {
                        //将选项的内容添加到result中
                        result += items[i] + "、";
                    }
                }
                //当result不为空时，通过消息提示框显示选择的结果
                if (!"".equals(result)) {
                    //去掉最后面添加的“、”号
                    result = result.substring(0, result.length() - 1);
                    Toast.makeText(MainActivity.this,
                            "您选择了[ " + result + " ]", Toast.LENGTH_LONG).show();
                }

            }
        });
        // 创建对话框并显示
        builder.create().show();
    }
});
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### 通知java的实现

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：发送通知，并且在通知信息中添加事件
 *
 *流程：
 *    1.获取通知对象，设置其属性。图标、内容、时间
 *    2.设置点击通知时处理的事件
 *    3.发送通知的动作设置
 */

/************************方法段:2类变量********************************/
final int NOTIFYID = 0x123;
//第一步：通知属性设置
//获取通知管理器，用于发送通知
final NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
Notification.Builder notification = new Notification.Builder(this); // 创建一个Notification对象
// 设置打开该通知，该通知自动消失
notification.setAutoCancel(true);
// 设置通知的图标
// 设置通知内容的标题
// 设置通知内容
notification.setSmallIcon(R.mipmap.advise);
notification.setContentTitle("奖励百万红包！！！");
notification.setContentText("点击查看详情！");
//设置使用系统默认的声音、默认震动
notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
//设置发送时间
notification.setWhen(System.currentTimeMillis());

//第二步：通知的事件设置(这里是点击实现跳转Activity)
// 创建一个启动其他Activity的Intent
Intent intent = new Intent(MainActivity.this, 其他的Activity类名称MainActivity3.class);
PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
//设置通知栏点击跳转
notification.setContentIntent(pi);
//第三步：通知信息的发送
notificationManager.notify(NOTIFYID, notification.build());
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### 闹钟 由Android提供实现

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：闹钟的实现
 *
 *流程：
 *    1.布局界面
 *    2.创建闹钟提醒内容的AlarmActivity类
 *    3.设置闹钟，创建Intent对象准备转移界面，并且系统会自动处理闹钟指令
 *注意：需要一个定时器组件设置TimePicker；需要对闹钟时间到了的时候做出一些
 *
 *方法：
 */
import Activity;
import AlarmManager;
import PendingIntent;
import Context;
import Intent;
import Bundle;
import View;
import Button;
import TimePicker;
import Toast;

import Calendar;

public class MainActivity extends Activity {
    TimePicker timepicker; // 时间拾取器
    Calendar calendar; // 日历对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar=Calendar.getInstance();                  //获取日历对象
        timepicker = (TimePicker) findViewById(R.id.timePicker1);            // 获取时间拾取组件
        timepicker.setIs24HourView(true);                            // 设置使用24小时制
        Button button1 = (Button) findViewById(R.id.button1); // 获取“设置闹钟”按钮
        // 为“设置闹钟”按钮添加单击事件监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个Intent对象，作为我们的闹钟
                // 获取显示闹钟的PendingIntent对象，这对象是对Intent对象的描述，用来处理即将发生的事情的
                //注意下面这两句
                //注意下面这两句
                //注意下面这两句
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                // 获取AlarmManager对象
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                calendar.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());    // 设置闹钟的小时数
                calendar.set(Calendar.MINUTE, timepicker.getCurrentMinute());        // 设置闹钟的分钟数
                calendar.set(Calendar.SECOND,0);                                      // 设置闹钟的秒数
                // 设置一个闹钟
                alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pendingIntent);
                Toast.makeText(MainActivity.this, "闹钟设置成功", Toast.LENGTH_SHORT)
                        .show();                                    // 显示一个消息提示
            }
        });

    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************Activity:AlarmActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：闹钟响应事件的动作
 *流程：
 *    1.获取对话框对象
 *    2.设置属性
 *    3.弹出对话框
 */
import AlertDialog;
import DialogInterface;
import Bundle;
import AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setIcon(R.drawable.alarm);//设置对话框的图标
        alert.setTitle("传递正能量：");//设置对话框的标题
        alert.setMessage("要么出众，要么出局");//设置要显示的内容
        //添加确定按钮
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        alert.show();  // 显示对话框

    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/

/************************2布局文件:activity_main.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：布局，获取TimePicker
 */
<TimePicker
android:id="@+id/timePicker1"
android:layout_width="wrap_content"
android:layout_height="wrap_content" />
<Button
android:id="@+id/button1"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_alignParentBottom="true"
android:layout_centerHorizontal="true"
android:text="设置闹钟" />
/**----------------------------END：2布局文件:activity_main.xml------------------------------------**/

```

## `绘图、动画`

### view 绘制位图，本地图片绘制

```java
package com.wanli.demo3;
/************************View:MyView********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：绘制图片 通过BitmapFactory类或者Bitmap类实现
 *
 *流程：
 *    1.通过图片文件创建或者通过输入流创建BitmapFactory对象
 *    2.把准备好的图片资源放到sdcard目录下，这张图片会保存在storage/0918-2E19目录下面
 *    3.创建画笔，获取存储路径，获取位图对象，绘制图片
 *注意：注意添加View还是需要在设置权限<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
 *方法：Environment.getExternalStorageDirectory() + "/图片名称.png";
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //第一步：定义画笔
        Paint paint = new Paint();
        //第二步：获取图片路径
        String path = Environment.getExternalStorageDirectory() + "/图片名称.png";
        //第三步：获取位图对象，绘制图片
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        canvas.drawBitmap(bitmap,0,0,paint);

        //第四步：获取图片的部分内容
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 23, 89, 150, 168);//新的位图对象获取1
        canvas.drawBitmap(bitmap1,270,50,paint);
    }
}
/**----------------------------END：View:MyView------------------------------------**/

```

### view 绘制几何图形、文本、路径

```java
/************************View:$ClassName$********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：绘制 几何图形、文本、路径
 *
 *流程：
 *    1.获取画笔
 *    2.设置画笔属性
 *    3.绘制图像
 *    4.绘制文本
 *注意：需要在Activity中引入自定义的
 *      ViewFrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
 *      frameLayout.addView(new $ClassName$(this));
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.view.View;

public class MyView/*你创建的绘画类名称*/ extends View {


    public MyView(Context context) {
        super(context);
    }
    /*
    流程说明：包括三大块内容
        1.绘制图形
        2.绘制文本
        3.绘制图像
        4.绘制路径
        4.最后要在Activity中添加这个绘画类
    //        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
    //        relativeLayout.addView(new MyView(this));
    */
    @Override
    protected void onDraw(Canvas canvas/*画布对象*/) {
        super.onDraw(canvas);
        //画笔对象，设置画笔颜色、设置填充方式、使用抗锯齿功能
        Paint paint=new Paint();
        paint.setColor(0xff660);
        paint.setStyle(Paint.Style.FILL/*STROKE表示描边*/);
        paint.setAntiAlias(true);
        //1.绘制图形演示
        //填充满
        canvas.drawRect(10, 10, 280, 150, paint);

        //2.绘制文本
        //设置文字对齐方式、设置文字大小
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(12);
        //开始绘画文本，设置文本内容，绘制的xy坐标，选择绘制的画笔
        canvas.drawText("canvas绘画文本一",175,160,paint);
        canvas.drawText("canvas绘画文本二",175,160,paint);

        //3.绘制位图
        //获取图片文件路径
        String path= Environment.getExternalStorageDirectory()+"/从本地拖取的图片.png";
        //位图绘制方式一：通过BitmapFactory类绘制
        //获取位图对象并且进行图片绘制
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        canvas.drawBitmap(bitmap,0,0,paint);
        //位图绘制方式二：通过Bitmap类绘制
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 23, 89, 150, 168);
        canvas.drawBitmap(bitmap2,270,50,paint);
       /*
            位图对象类：
                1.BitmapFactory类
                     通过图片文件创建
                     通过输入流创建
                    方法：
                    1.decodeFile() 通过路径创建
                    2.decodeResource() 通过资源ID创建
                    3.decodeStream() 通过输入流创建
                2.Bitmap类
                    挖去一块图像创建
                    将源位图缩放生成
                    使用颜色数组创建
                    方法：
                    1.createBitmap() 根据重载形式创建对应的Bitmap对象
                    2.compress() 压缩Bitmap对象并保存到文件输出流
                    3.createScaledBitmap() 将源位图缩放并创建行的Bitmap对象
        */
        //4.绘制路径
        Path pathDraw = new Path();
        //参数是x,y,半径，绘制方向
        pathDraw.addCircle(150,150,50,Path.Direction.CW/*CCW表示逆时针绘制*/);
        //绘制路径的两种实现方式
        //方式一：绘制路径
        canvas.drawPath(pathDraw,paint);
        //方式二：绘制文本
        canvas.drawTextOnPath("通过Path类绘制了一条路径",pathDraw,0,0,paint);

        /*
            Path类：创建路径
            创建路径方法：
                1.addArc() 圆弧
                1.addCircle() 圆形
                1.addRect() 长方形
                1.addRoundRect() 圆角长方形
            显示路径方法：通过Canvas类实现
                1 drawPath()      绘制路径
                1 drawTextOnPath() 绘制文本
        */


    }
}
/**----------------------------END：View:$ClassName$------------------------------------**/

```

### 机器人绘制java

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
/**
 *功能：机器人绘制
 */
class MyPaint extends View {
    public MyPaint(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();//默认设置创建一个画笔
        paint.setAntiAlias(true);//使用抗锯齿功能
        paint.setColor(0xFFA4C739);//设置画笔的颜色为绿色
        //绘制机器人的头
        RectF rectf_head=new RectF(10, 10, 100, 100);
        rectf_head.offset(90, 20);
        canvas.drawArc(rectf_head, -10, -160, false, paint);//绘制弧
        //绘制眼睛
        paint.setColor(Color.WHITE);//设置画笔的颜色为白色
        canvas.drawCircle(125, 53, 4, paint);//绘制圆
        canvas.drawCircle(165, 53, 4, paint);//绘制圆
        paint.setColor(0xFFA4C739);//设置画笔的颜色为绿色
        //绘制天线
        paint.setStrokeWidth(2);//设置笔触的宽度
        canvas.drawLine(110, 15, 125, 35, paint);//绘制线
        canvas.drawLine(180, 15, 165, 35, paint);//绘制线
        //绘制身体
        canvas.drawRect(100, 75, 190, 150, paint);//绘制矩形
        RectF rectf_body=new RectF(100,140,190,160);
        canvas.drawRoundRect(rectf_body, 10, 10, paint);//绘制圆角矩形
        //绘制胳膊
        RectF rectf_arm=new RectF(75,75,95,140);
        canvas.drawRoundRect(rectf_arm, 10, 10, paint);//绘制左侧的胳膊
        rectf_arm.offset(120, 0);//设置在X轴上偏移120像素
        canvas.drawRoundRect(rectf_arm, 10, 10, paint);//绘制右侧的胳膊
        //绘制腿
        RectF rectf_leg=new RectF(115,150,135,200);
        canvas.drawRoundRect(rectf_leg, 10, 10, paint);//绘制左侧的腿
        rectf_leg.offset(40, 0);//设置在X轴上偏移40像素
        canvas.drawRoundRect(rectf_leg, 10, 10, paint);//绘制右侧的腿
    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### 补间动画 平移、旋转、淡入、缩放

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：补间动画 的平移动画实现
 *
 *流程：
 *    1.获取图像
 *    2.获取动画对象
 *    3.获取anim的平移动画资源
 *    4.设置图像动作
 *注意：实现这个动画的步骤很简单，需要的就是你动画资源的准备
 */
ImgeView imageView=(ImgeView)findViewById(R.id.imageView);
//设置平移动画资源并且开启动画
Animation animation = AnimationUtils.loadAnimation($ClassName$.this, R.anim.$animDir$);
//开启动画
imageView.startAnimation(animation);
/**----------------------------END：Activity:------------------------------------**/
/************************anim资源:anim平移********************************/
/************************制定时间：2020/3/7 0007********************************/
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<!--
        1.设置平移的起始点的X轴和Y轴
android:fromXDelta="0"
android:fromYDelta="0"
2.设置平移的结束点的X轴和Y轴
android:toXDelta="300"
android:toYDelta="300"
3.设置持续时间为2秒
android:duration="2000"
        -->
<translate
android:fromXDelta="0"
android:fromYDelta="0"
android:toXDelta="300"
android:toYDelta="300"
android:duration="2000"
        />
</set>
/**----------------------------END：2:MainActivity------------------------------------**/
/************************anim资源:anim 旋转********************************/
/************************制定时间：2020/3/7 0007********************************/
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<!--
        1.设置从0度开始旋转
android:fromDegrees="0"
2.设置结束旋转位置为360度
android:toDegrees="360"
3.设置旋转中心轴为X、Y轴的50%
        android:pivotX="50%"
android:pivotY="50%"
4.设置持续时间为2秒
android:duration="2000"/>
        -->
<rotate
android:fromDegrees="0"
android:toDegrees="360"
android:pivotX="50%"
android:pivotY="50%"
android:duration="2000"/>
</set>
/**----------------------------END：2:MainActivity------------------------------------**/
/************************资源文件:淡入********************************/
/************************制定时间：2020/3/7 0007********************************/
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<alpha android:fromAlpha="0"
android:toAlpha="1"
android:duration="4000"/>
</set>
/**----------------------------END：资源文件:淡入------------------------------------**/

/************************anim资源:缩放********************************/
/************************制定时间：2020/3/7 0007********************************/
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<!--
        1.设置开始比例和原图比例为1:1
android:fromXScale="1.0"
android:fromYScale="1.0"
2.设置结束比例和原图比例为0:1，也就是消失
android:toXScale="0.0"
android:toYScale="0.0"
3.设置旋转中心轴为X、Y轴的50%
        android:pivotX="50%"
android:pivotY="50%"
4.设置持续时间为2秒
android:duration="2000"
        -->
<scale
android:fromXScale="1.0"
android:fromYScale="1.0"
android:pivotX="50%"
android:pivotY="50%"
android:toXScale="0.0"
android:toYScale="0.0"
android:duration="2000"
        ></scale>
</set>
/**----------------------------END：anim资源:缩放------------------------------------**/

```

### 补间动画 手指滑动切换图片

```java
package com.wanli.demo3;


/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：实现动画切换
 *
 *流程：
 *    1.布局文件中添加ViewFlipper，动画资源准备
 *    2.ViewFlipper中添加图片查看器组件
 *    3.重写触摸方法，实现动画效果
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    ViewFlipper flipper; //定义ViewFlipper
    GestureDetector detector; //定义手势检测器
    Animation[] animation = new Animation[4];//定义动画数组，为ViewFlipper指定切换动画
    final int distance = 50; //定义手势动作两点之间最小距离
    //定义图片数组
    private int[] images = new int[]{R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06, R.drawable.img07, R.drawable.img08,
            R.drawable.img09,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector = new GestureDetector(this, this); //创建手势检测器
        flipper = (ViewFlipper) findViewById(R.id.flipper); //获取ViewFlipper
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView); //加载图片
        }
        //初始化动画数组
        animation[0] = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_in);     //淡入动画
        animation[1] = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_out);      //淡出动画
        animation[2] = AnimationUtils.loadAnimation(this, R.anim.anim_scale_in);      //放大进入动画
        animation[3] = AnimationUtils.loadAnimation(this, R.anim.anim_scale_out);  //缩小退出动画
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        /*
        如果第一个触点事件的X坐标到第二个触点事件的X坐标的距离超过distance就是从右向左滑动
         */
        if (e1.getX() - e2.getX() > distance) {
            //为flipper设置切换的动画效果
            flipper.setInAnimation(animation[0]);
            flipper.setOutAnimation(animation[1]);
            flipper.showPrevious();
            return true;
            /*
            如果第二个触点事件的X坐标到第一个触点事件的X坐标的距离超过distance就是从左向右滑动
             */
        } else if (e2.getX() - e1.getX() > distance) {
            //为flipper设置切换的动画
            flipper.setInAnimation(animation[2]);
            flipper.setOutAnimation(animation[3]);
            flipper.showNext();
            return true;
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将该Activity上的触碰事件交给GestureDetector处理
        return detector.onTouchEvent(event);
    }
}
/**----------------------------END：Activity:------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
<ViewFlipper
android:id="@+id/flipper"
android:layout_width="match_parent"
android:layout_height="match_parent">

</ViewFlipper>
/**----------------------------END：2布局文件:layout.xml------------------------------------**/
/************************anim资源:淡入淡出********************************/
/************************制定时间：2020/3/7 0007********************************/
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<!--
        1.设置从0度开始旋转
android:fromDegrees="0"
2.设置结束旋转位置为360度
android:toDegrees="360"
3.设置旋转中心轴为X、Y轴的50%
        android:pivotX="50%"
android:pivotY="50%"
4.设置持续时间为2秒
android:duration="2000"/>
        -->
<rotate
android:fromDegrees="0"
android:toDegrees="360"
android:pivotX="50%"
android:pivotY="50%"
android:duration="2000"/>
</set>
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
<scale
android:fromXScale="1.0"
android:fromYScale="1.0"
android:pivotX="50%"
android:pivotY="50%"
android:toXScale="0.0"
android:toYScale="0.0"
android:duration="2000"
        ></scale>
</set>
/**----------------------------END：anim资源:淡入淡出------------------------------------**/

```

### 逐帧动画java

```java
package com.wanli.demo3;


/************************Activity:********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：逐帧动画的实现
 *
 *流程：
 *    1.获取布局管理器对象，对象中需要引入anim文件
 *    2.获取AnimationDrawable对象
 *    3.实现动画的播放
 *注意：需要anim动画资源，并且需要多张图片；注意anim资源的引入是在布局文件中引入的
 *       android:background="@anim/逐帧动画资源文件名"
 */
/************************方法段:onCreate()********************************/
private boolean flag = true;
LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linearLayout); //获取布局管理器
//获取AnimationDrawable对象
final AnimationDrawable anim= (AnimationDrawable) linearLayout.getBackground();
if(flag){
    anim.start(); //开始播放动画
    flag=false;
}else {
    anim.stop();  //停止播放动画
    flag=true;
}
/**----------------------------END：Activity:------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：引入逐帧动画资源
 *注意：注意需要通过background属性引入anim资源
 *     android:background="@anim/逐帧动画资源文件名"
 */
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/linearLayout"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="@anim/逐帧动画资源文件名"
android:gravity="center"
android:orientation="horizontal"
tools:context="com.wanli.demo.MainActivity">
</LinearLayout>
/**----------------------------END：2布局文件:layout.xml------------------------------------**/
/************************anim资源:逐帧动画********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：提供多张图片，实现逐帧动画
 *注意：注意需要引入多张图片
 */
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android">
<item android:drawable="@drawable/img001" android:duration="60"/>
<item android:drawable="@drawable/img002" android:duration="60"/>
<item android:drawable="@drawable/img003" android:duration="60"/>
<item android:drawable="@drawable/img004" android:duration="60"/>
<item android:drawable="@drawable/img005" android:duration="60"/>
<item android:drawable="@drawable/img006" android:duration="60"/>
</animation-list>
/**----------------------------END：anim资源:------------------------------------**/

```


## 音频、视频、摄像头

### 拍照 预览+拍照

```java
package com.wanli.demo3;

/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：拍照 预览、存储
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：引入import android.os.Environment;还需要添加手机和配置文件相关权限
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    //定义相机对象
    //定义非预览状态
    private Camera camera;
    private boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏显示
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (!Environment.getExternalStorageState().equals(
                //判断手机是否安装SD卡
                Environment.MEDIA_MOUNTED)) {
            // 提示安装SD卡
            Toast.makeText(this, "请安装SD卡！", Toast.LENGTH_SHORT).show();
        }

        // 获取SurfaceView组件，用于显示相机预览
        SurfaceView sv = (SurfaceView) findViewById(R.id.surfaceView);
        //获取SurfaceHolder对象
        final SurfaceHolder sh = sv.getHolder();
        // 设置该SurfaceHolder自己不维护缓冲
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //获取“预览”按钮
        ImageButton preview = (ImageButton) findViewById(R.id.preview);
        //获取“拍照”按钮
        ImageButton takePicture = (ImageButton) findViewById(R.id.takephoto);


        preview.setOnClickListener(new View.OnClickListener() {
            //实现相机预览功能
            @Override
            public void onClick(View v) {
                // 如果相机为非预览模式，则打开相机
                if (!isPreview) {
                    // 打开相机
                    camera = Camera.open();
                    //设置为预览状态
                    isPreview = true;
                }
                try {
                    // 设置用于显示预览的SurfaceView
                    camera.setPreviewDisplay(sh);
                    //获取相机参数
                    Camera.Parameters parameters = camera.getParameters();
                    //指定图片为JPEG图片
                    parameters.setPictureFormat(PixelFormat.JPEG);
                    //设置图片的质量
                    parameters.set("jpeg-quality", 80);
                    //重新设置相机参数
                    camera.setParameters(parameters);
                    //开始预览
                    camera.startPreview();
                    //设置自动对焦
                    camera.autoFocus(null);
                } catch (IOException e) {
                    //输出异常信息
                    e.printStackTrace();
                }
            }
        });

        //实现相机拍照功能
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机不为空
                if (camera != null) {
                    //进行拍照
                    camera.takePicture(null, null, jpeg);
                }
            }
        });
    }

    //实现将照片保存到系统图库中
    final Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        //照片回调函数
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 根据拍照所得的数据创建位图
            final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
                    data.length);
            //停止预览
            camera.stopPreview();
            //设置为非预览状态
            isPreview = false;
            //获取sd卡根目录
            File appDir = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/");
            //如果该目录不存在
            if (!appDir.exists()) {
                //就创建该目录
                appDir.mkdir();

            }
            //将获取当前系统时间设置为照片名称
            String fileName = System.currentTimeMillis() + ".jpg";
            //创建文件对象
            File file = new File(appDir, fileName);
            try {  //保存拍到的图片
                //创建一个文件输出流对象
                FileOutputStream fos = new FileOutputStream(file);
                //将图片内容压缩为JPEG格式输出到输出流对象中
                bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                //将缓冲区中的数据全部写出到输出流中
                fos.flush();
                //关闭文件输出流对象
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //将照片插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(MainActivity.this.getContentResolver(),
                        file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // 最后通知图库更新
            MainActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + "")));
            Toast.makeText(MainActivity.this, "照片保存至：" + file, Toast.LENGTH_LONG).show();
            //调用重新预览resetCamera()方法
            resetCamera();
        }
    };

    private void resetCamera() {
        //创建resetCamera()方法，实现重新预览功能
        //如果为非预览模式
        if (!isPreview) {
            //开启预览
            camera.startPreview();
            isPreview = true;
        }
    }

    @Override
    protected void onPause() {
        //当暂停Activity时，停止预览并释放资源
        //如果相机不为空
        if (camera != null) {
            //停止预览
            camera.stopPreview();
            //释放资源
            camera.release();
        }
        super.onPause();
    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:layout。xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：拍照的实现
 *注意：需要添加SurfaceView组件
 */

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.mingrisoft.MainActivity">
<!--SurfaceView组件,主要用于显示摄像头预览的-->
<SurfaceView
android:id="@+id/surfaceView"
android:layout_width="match_parent"
android:layout_height="match_parent" />
<!--拍照按钮-->
<ImageButton
android:id="@+id/takephoto"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="right|top"
android:layout_marginTop="@dimen/margin_top"
android:background="@color/btn_background"
android:src="@drawable/camera" />
<!--预览按钮-->
<ImageButton
android:id="@+id/preview"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="right|bottom"
android:layout_marginBottom="@dimen/margin_bottom"
android:background="@color/btn_background"
android:src="@drawable/preview" />
</FrameLayout>

/**----------------------------END：2布局文件:layout。xml------------------------------------**/



```

### 视屏播放 MediaPlayer+SurfaceView播放视频的实现

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：MediaPlayer和SurfaceView配合视频播放
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：需要对手机设置app内存访问权限；还要配置AndroidManifest.xml的内存访问权限
 *
 *方法：
 */
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {
    //设置3个播放控制按钮
    //设置视频状态标志位
    //设置MediaPlayer和SurfaceView的对象
    private Button playButton, pauseButton, stopButton;
    private boolean noPlay = true;
    MediaPlayer mediaPlayer;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        //surfaceHolder是用来将MediaPlayer和SurfaceView进行关联的
        surfaceHolder = surfaceView.getHolder();
        //获取MediaPlayer并且设置其属性
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //设置完成播放视频监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
            }
        });
        //三个监听按钮
        playButton = (Button) findViewById(R.id.play);
        pauseButton = (Button) findViewById(R.id.pause);
        stopButton = (Button) findViewById(R.id.stop);
        //设置事件
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noPlay) {
                    play();
                } else {
                    mediaPlayer.start();
                }

            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    noPlay = true;
                }
            }
        });


    }
    //设置播放状态
    public void play() {
        mediaPlayer.reset();
        mediaPlayer.setDisplay(surfaceHolder);
        try {
            //设置要播放的视频
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/video.mp4");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
    //设置关闭后释放资源
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *注意：添加SurfaceView组件
 */
<SurfaceView
android:id="@+id/surfaceView"
android:layout_weigh="10"
android:layout_width="wrap_content"
android:layout_height="wrap_content" />
<LinearLayout
android:id="@+id/linearLayout"
android:gravity="center"
android:orientation="vertical"
android:layout_width="match_parent"
android:layout_height="match_parent"
        >
<Button
android:id="@+id/play"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="center"
android:layout_margin="2px"
android:text="按钮"
android:textSize="10pt"
        />
<Button
android:id="@+id/pause"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="center"
android:layout_margin="2px"
android:text="按钮"
android:textSize="10pt"
        />
<Button
android:id="@+id/stop"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="center"
android:layout_margin="2px"
android:text="按钮"
android:textSize="10pt"
        />
</LinearLayout>
/**----------------------------END：2布局文件:layout.xml------------------------------------**/

```

### 视屏播放 SurfaceView的实现

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：视屏播放 SurfaceView的实现
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：手机和配置文件权限设置
 */
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //获取VideoView组件
        VideoView video = (VideoView) findViewById(R.id.video);
        //指定模拟器SD卡上要播放的视频文件
        File file = new File(Environment.getExternalStorageDirectory() + "/video.mp4");
        //创建android.widget.MediaController对象，控制视频的播放
        MediaController mc = new MediaController(MainActivity.this);
        //实现视频的播放功能

        //判断要播放的视频文件是否存在
        if (file.exists()) {
            //指定要播放的视频
            video.setVideoPath(file.getAbsolutePath());
            //设置VideoView与MediaController相关联
            video.setMediaController(mc);
            //让VideoView获得焦点
            video.requestFocus();
            try {
                //开始播放视频
                video.start();
            } catch (Exception e) {
                //输出异常信息
                e.printStackTrace();
            }

            //为VideoView添加完成事件监听器，实现视频播放结束后的提示信息
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //弹出消息提示框显示播放完毕
                    Toast.makeText(MainActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //弹出消息提示框提示文件不存在
            Toast.makeText(this, "要播放的视频文件不存在", Toast.LENGTH_SHORT).show();
        }

    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *注意：VideoView的添加
 */
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.mingrisoft.mingrisoft.MainActivity">

<VideoView
android:id="@+id/video"
android:layout_width="match_parent"
android:layout_height="match_parent" />
</RelativeLayout>
/**----------------------------END：2布局文件:layout.xml------------------------------------**/

```

### 视频录制

```java
/************************Activity:PlayVideoActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：实现跳转播放视频
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：权限设置
 *
 *方法：
 */
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
public class PlayVideoActivity extends Activity {
    private SurfaceView surfaceView;         // 定义SurfaceView对象
    private ImageButton play, pause, stop;     // 定义播放、暂停和停止按钮
    private MediaPlayer mediaPlayer;        // 定义MediaPlayer对象
    private SurfaceHolder surfaceHolder;    // 定义SurfaceHolder对象
    private boolean noPlay = true;          //定义播放状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        play = (ImageButton) findViewById(R.id.play);                  // 获取播放按钮对象
        pause = (ImageButton) findViewById(R.id.pause);                // 获取暂停按钮对象
        stop = (ImageButton) findViewById(R.id.stop);                  // 获取停止按钮对象
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);  //获取SurfaceView
        surfaceHolder = surfaceView.getHolder();                     //获取SurfaceHolder
        pause.setEnabled(false);                                      //设置暂停按钮不可用
        stop.setEnabled(false);                                       //设置停止按钮不可用
        /**
         * 实现播放与继续播放功能
         */
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noPlay) {                   //如果没有播放视频
                    play();                     //调用实现播放功能的play()方法
                    noPlay = false;            //设置播放状态为正在播放
                } else {
                    mediaPlayer.start();       //继续播放视频
                }
            }
        });
        /**
         * 实现暂停功能
         */
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) { //如果视频处于播放状态
                    mediaPlayer.pause();       //暂停视频的播放
                }
            }
        });
        /**
         * 实现停止功能
         */
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {   //如果视频处于播放状态
                    mediaPlayer.stop();          // 停止播放
                    mediaPlayer.release();       // Activity销毁时停止播放，释放资源。
                    noPlay = true;               //设置播放状态为没有播放
                    pause.setEnabled(false);     // 设置“暂停”按钮不可用
                    stop.setEnabled(false);      //设置“停止”按钮不可用
                }
            }
        });

    }

    /**
     * 创建play()方法，在该方法中实现视频的播放功能
     */
    public void play() {
        mediaPlayer = new MediaPlayer();                               //创建MediaPlayer对象
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);    //设置多媒体的类型
        mediaPlayer.setDisplay(surfaceHolder);                      //设置显示视频的SurfaceHolder
        try {
            // 模拟器的SD卡上的视频文件
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/Myvideo/video.mp4");
            mediaPlayer.prepare();    // 预加载
        } catch (Exception e) {       //输出异常信息
            e.printStackTrace();
        }
        mediaPlayer.start(); // 开始播放
        pause.setEnabled(true);    // 设置“暂停”按钮可用
        stop.setEnabled(true);     //设置“停止”按钮可用
        // 为MediaPlayer对象添加完成事件监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(PlayVideoActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 当前Activity销毁时，停止正在播放的视频，并释放MediaPlayer所占用的资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {          //如果MediaPlayer不为空
            if (mediaPlayer.isPlaying()) {  //如果处于播放状态
                mediaPlayer.stop();         // 停止播放视频
            }
            // Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
            mediaPlayer.release();
        }
    }
}
/**----------------------------END：Activity:PlayVideoActivity------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：SurfaceView的添加
 *
 *方法：
 */
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.mingrisoft.MainActivity">
<!--SurfaceView组件,主要用于显示摄像头预览的-->
<SurfaceView
android:id="@+id/surfaceView"
android:layout_width="match_parent"
android:layout_height="match_parent" />
<!--拍照按钮-->
<ImageButton
android:id="@+id/takephoto"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="right|top"
android:layout_marginTop="@dimen/margin_top"
android:background="@color/btn_background"
android:src="@drawable/camera" />
<!--预览按钮-->
<ImageButton
android:id="@+id/preview"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="right|bottom"
android:layout_marginBottom="@dimen/margin_bottom"
android:background="@color/btn_background"
android:src="@drawable/preview" />
</FrameLayout>

/**----------------------------END：2布局文件:layout.xml------------------------------------**/

```

### 音频 MediaPlayer长音乐播放暂停

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：音乐播放和暂停
 *
 *流程：
 *    1.创建音频资源
 *    2.获取该MediaPlayer对象
 *    3.进行按键监听
 *注意：权限设置
 */

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    //定义MediaPlayer对象
    //定义是否暂停
    //定义要播放的音频文件
    private MediaPlayer player;
    private File file;
    private boolean isPause = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //获取“播放/暂停”按钮
        //获取“停止”按钮
        final ImageButton btn_play = (ImageButton) findViewById(R.id.btn_play);
        final ImageButton btn_stop = (ImageButton) findViewById(R.id.btn_stop);
        /*
          创建MediaPlayer对象的方式
            1.create(Context context,int resid) 从资源文件中加载音频
            1.create(Context context,Uri uri) 根据指定的URI装载指定的音频
            1.无参的构造方法创建：setDataSource() prepare()
         */
        //获取音频对象的方式一
        //MediaPlayer.create(this,R.raw.音频文件名)
        //获取音频对象的方式二
        //获取要播放的音频文件
        file = new File("/sdcard/music.mp3");
        //player = MediaPlayer.create(this, Uri.parse(file.getAbsolutePath()));

        //判断音频文件是否存在
        if (file.exists()) {
            //创建MediaPlayer对象,并解析要播放的音频文件
            player = MediaPlayer.create(this, Uri.parse(file.getAbsolutePath()));
        } else {
            //提示音频文件不存在
            Toast.makeText(MainActivity.this, "要播放的音频文件不存在！", Toast.LENGTH_SHORT).show();
            return;
        }

        //为MediaPlayer添加完成事件监听器，实现当音频播放完毕后，重新开始播放音频
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //调用play()方法，实现播放功能
                play();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            //实现继续播放与暂停播放
            @Override
            public void onClick(View v) {
                //如果音频处于播放状态
                if (player.isPlaying() && !isPause) {
                    //暂停播放
                    //设置为暂停状态
                    //更换为播放图标
                    player.pause();
                    isPause = true;
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.play, null));
                } else {
                    //继续播放
                    // 更换为暂停图标
                    //设置为播放状态
                    player.start();
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.pause, null));
                    isPause = false;
                }
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            //单击停止按钮，实现停止播放音频
            @Override
            public void onClick(View v) {
                //停止播放
                //更换为播放图标
                player.stop();
                btn_play.setImageDrawable(getResources().getDrawable(R.drawable.play, null));
            }
        });
    }

    private void play() {
        //创建play()，实现音频播放功能
        try {
            //重置MediaPlayer对象
            player.reset();
            //重新设置要播放的音频
            player.setDataSource(file.getAbsolutePath());
            //预加载音频
            player.prepare();
            //开始播放
            player.start();
        } catch (Exception e) {
            //输出异常信息
            e.printStackTrace();
        }
    }
    //Activity的销毁方法
    @Override
    protected void onDestroy() {
        //当前Activity销毁时,停止正在播放的音频,并释放MediaPlayer所占用的资源
        //如果音频处于播放状态
        if (player.isPlaying()) {
            //停止音频的播放
            player.stop();
        }
        //释放资源
        player.release();
        super.onDestroy();
    }

}
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### 音频 SoundPool短音乐播放

```java
package com.wanli.demo3;
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：SoundPool对象实现音频播放
 *
 *流程：
 *    1.创建音频名称视图
 *    2.配置音效属性，然后创建SoundPool对象并且设置相关属性
 *    3.保存音频到map视图中
 *    4.创建适配器和ListView关联（本例采用的是列表视图）
 *注意：
 *
 *方法：
 */
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取列表视图
        ListView listview = (ListView) findViewById(R.id.listView);

        // 定义并初始化保存列表项文字的数组
        String[] title = new String[]{"布谷鸟叫声", "风铃声", "门铃声", "电话声", "鸟叫声",
                "水流声", "公鸡叫声"};
        //创建一个list集合
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

        //通过for循环将列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < title.length; i++) {
            // 实例化Map对象
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", title[i]);
            // 将map对象添加到List集合中
            listItems.add(map);
        }

        //设置音效相关属性
        AudioAttributes attr = new AudioAttributes.Builder()
                // 设置音效使用场景，这里是媒体场景(还有游戏的、通知的)
                .setUsage(AudioAttributes.USAGE_GAME)
                        // 设置音效的类型为音乐类型
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        // 创建SoundPool对象
        final SoundPool soundpool = new SoundPool.Builder()
                // 设置音效池的属性
                .setAudioAttributes(attr)
                        // 设置最多可容纳10个音频流，
                .setMaxStreams(10)
                .build();

        //创建HashMap对象,将要音频流保存到HashMap对象中
        final HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();
        soundmap.put(0, soundpool.load(this, R.raw.cuckoo, 1));
        soundmap.put(1, soundpool.load(this, R.raw.chimes, 1));
        soundmap.put(2, soundpool.load(this, R.raw.notify, 1));
        soundmap.put(3, soundpool.load(this, R.raw.ringout, 1));
        soundmap.put(4, soundpool.load(this, R.raw.bird, 1));
        soundmap.put(5, soundpool.load(this, R.raw.water, 1));
        soundmap.put(6, soundpool.load(this, R.raw.cock, 1));

        //创建SimpleAdapter适配器并将适配器与ListView关联
        // 创建SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.main, new String[]{"name",}, new int[]{
                R.id.title});
        // 将适配器与ListView关联
        listview.setAdapter(adapter);

        //为ListView设置单击事件监听器，为每个选项设置所对应要播放的音频
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选项的值
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                //后面四个参数依次是：左声道最大音量、右声道最大音量、左声道最小音量、右声道最小音量、循环播放次数(0表示不循环)
                soundpool.play(soundmap.get(position), 1, 1, 0, 0, 1);  //播放所选音频
            }
        });

    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：
 *注意：
 *方法：
 */
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.mingrisoft.MainActivity">
<ListView
android:id="@+id/listView"
android:layout_width="match_parent"
android:layout_height="match_parent">
</ListView>
</RelativeLayout>
/**----------------------------END：2布局文件:layout.xml------------------------------------**/
/************************适配器:列表视图********************************/
/************************制定时间：2020/3/7 0007********************************/
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical"
android:layout_width="match_parent"
android:layout_height="match_parent">
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:padding="10dp"
android:layout_gravity="center"
android:id="@+id/title"
android:textSize="@dimen/text_size"
        />
</LinearLayout>
/**----------------------------END：适配器:列表视图------------------------------------**/


```

