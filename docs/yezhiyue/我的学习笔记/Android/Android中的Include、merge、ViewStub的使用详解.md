

# Android中的Include、merge、ViewStub的使用详解

 - 开门见山，先来简单说说今天的三个主角的主要功能。include实现了布局文件的复用、merge可以减少复用过程中的嵌套层级、ViewGroup可以懒加载(也就是在特殊场景下可以提升页面加载的性能)。下面我们以代码示例的形式来给大家讲讲这三个哥们到底怎么用，而且使用过程中有哪些需要注意的点，防止大家在以后的使用中踩坑。

## ==一、Include的使用==
1. 使用方式
- 在需要复用的布局文件中使用include标签的layout属性来调用其他布局文件

    下面是我们的复用布局文件:activity_head.xml

```java
<LinearLayout xmlns:android#"http://schemas.android.com/apk/res/android"
  android:id#"@+id/linearLayout"
  android:layout_width#"match_parent"
  android:layout_height#"wrap_content"
  android:gravity#"center"
  android:orientation#"vertical">
  <TextView
    android:id#"@+id/textView1"
    android:layout_width#"wrap_content"
    android:layout_height#"wrap_content"
    android:layout_weight#"1"
    android:background#"#de627c"
    android:text#"文本框一"
    />
  <TextView
    android:id#"@+id/textView2"
    android:layout_width#"wrap_content"
    android:layout_height#"wrap_content"
    android:layout_weight#"1"
    android:background#"#561220"
    android:text#"文本框二"
    />
</LinearLayout>
```

    下面是调用复用模块的布局文件：activity_one.xml

```java
<LinearLayout xmlns:android#"http://schemas.android.com/apk/res/android"
  android:id#"@+id/linearLayout"
  android:layout_width#"match_parent"
  android:layout_height#"match_parent"
  android:gravity#"center"
  android:orientation#"vertical">
  <TextView
    android:id#"@+id/textView1"
    android:layout_width#"wrap_content"
    android:layout_height#"wrap_content"
    android:layout_margin#"2px"
    android:background#"#444444"
    android:gravity#"center"
    android:singleLine#"true"
    android:text#"文本框三" />
  <include layout#"@layout/activity_head"
    android:id#"@+id/include_layout"></include>
</LinearLayout>
```


![这里引入了额外的两个文本框](https://img-blog.csdnimg.cn/20200316231337638.gif#pic_center)
		这里引入了额外的两个文本框

2. 注意点：

- 如果复用布局文件的根布局管理器不是merge，那么如果给include标签设置id属性，这个id属性会覆盖掉复用模块的跟布局管理器的id属性。

    解决方案：
            1.直接通过include的id获取可复用文件的根布局管理器；
            2.不要给include设置id属性

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200316231446784.gif)

- 如果可复用模块中有组件的id值和调用者中的组件id有冲突，那么布局中先定义的组件id将覆盖后面的id

    在我们的示例中有两个id为textView1的组件，下面我们通过include所在的不同位置来演示这个注意点。
    最终的结果是不同的哪个组件在布局文件中事先定义，那么这个组件的id将覆盖另外一个组件的id

 情况一：我们看到 文本框三 的id覆盖了include引入的同id的文本框一。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200316232402416.gif)

 情况二：我们修改include的引入位置，可以看到include中的文本框一id优先级更高。
```java
<LinearLayout xmlns:android#"http://schemas.android.com/apk/res/android"
  android:id#"@+id/linearLayout"
  android:layout_width#"match_parent"
  android:layout_height#"match_parent"
  android:gravity#"center"
  android:orientation#"vertical">
  <include layout#"@layout/activity_head"
    android:id#"@+id/include_layout"></include>
  <TextView
    android:id#"@+id/textView1"
    android:layout_width#"wrap_content"
    android:layout_height#"wrap_content"
    android:layout_margin#"2px"
    android:background#"#444444"
    android:gravity#"center"
    android:singleLine#"true"
    android:text#"文本框三" />
</LinearLayout>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200316232553719.gif)

## ==二、merge的使用==
1. 使用方式

    merge标签是作为include标签的一种辅助扩展来使用的，也就是需要和include一起使用，它的主要作用是为了防止在引用布局文件时产生多余的布局嵌套。
    也就是减少嵌套层次，下面直接上代码大家应该就明白怎么使用了，我们把上次的activity_head.xml中的根布局标签LinearLayout替换为merge，
    其他配置不变,最终的效果和上面类似，但是实质上少了一个嵌套层级，也就是提升了性能。

```java
<merge xmlns:android#"http://schemas.android.com/apk/res/android">
  <TextView
    android:id#"@+id/textView1"
    android:layout_width#"wrap_content"
    android:layout_height#"wrap_content"
    android:layout_weight#"1"
    android:background#"#de627c"
    android:text#"文本框一" />
  <TextView
    android:id#"@+id/textView2"
    android:layout_width#"wrap_content"
    android:layout_height#"wrap_content"
    android:layout_weight#"1"
    android:background#"#561220"
    android:text#"文本框二" />
</merge>

```
可以看到效果和上面的示例类似，但是实质上减少了嵌套的层级。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200316232625136.gif#pic_center)

2. 注意点：

- 由于merge不是view.原ViewGroup的属性都失效(对merge标签设置的所有属性都是无效的),例如背景色都不能正常显示。大家试试其实就知道了。

## ==三、ViewStub的使用==
1. 使用方式

    ViewStub可以延时加载，也就是等到需要使用的时候在调用，这里就必须配合java代码的使用了。并且这里我就把
    其注意点也先直接说明了，方便演示。

- ViewStub标签不支持merge标签。因此这有可能导致加载出来的布局存在着多余的嵌套结构,开发中视情况而定。

- ViewStub的inflate只能被调用一次,第二次调用会抛出异常。

- 虽然ViewStub是不占用任何空间的，但是每个布局都必须要指定layout_width和layout_height属性，否则运行就会报错。


   		 1.下面是被ViewStub调用的布局文件。

```java
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

```

 	   2.下面是ViewStub的布局文件，需要指定其id、inflateId、layout属性。

```java
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
```

 	   3.下面是java实现的延时加载，注意这里只能加载一次，接下来会以两张动图来展示效果。

```java
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

```

情况一： 只加载一次ViewStub的正常情况,点击·按钮一的时候加载ViewStub，里面的两个按钮组件也就渲染出来了。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020031623274935.gif#pic_center)

情况二：因为ViewStub只能加载一次之后就会销毁，重复加载ViewStub就会导致异常问题。这里第一次按下按钮一加载ViewStub，第二次又按下按钮一，导致ViewStub在再次被加载，但是实际上ViewStub在第一次按钮一按下时已经被销毁了。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200316232759571.gif#pic_center)



