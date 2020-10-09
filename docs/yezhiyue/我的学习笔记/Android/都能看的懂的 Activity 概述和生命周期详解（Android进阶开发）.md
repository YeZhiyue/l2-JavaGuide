# Activity 基本

## 目录

- 概述
- 创建Activity
- Activity的生命周期概述
- Activity的生命周期详解

## 一、概述

- **应用场景**：Activity 是一个应用组件，用户可与其提供的屏幕进行`交互`，以执行拨打电话、拍摄照片、发送电子邮件或查看地图等操作。 每个 Activity 都会获得一个用于绘制其用户界面的窗口。窗口通常会充满屏幕，但也可小于屏幕并浮动在其他窗口之上。
- **堆栈机制**：一个应用通常由多个彼此松散联系的 Activity 组成。 一般会指定应用中的某个 Activity 为“主”Activity，即首次启动应用时呈现给用户的那个 Activity。 而且每个 Activity 均可启动另一个 Activity，以便执行不同的操作。 每次`新 Activity 启动时，前一 Activity 便会停止`，但系统会在堆栈（“返回栈”）中保留该 Activity。 当新 Activity 启动时，系统会将其`推送到返回栈上`，并取得用户焦点。 返回栈遵循基本的`“后进先出”堆栈机制`，因此，当用户完成当前 Activity 并`按“返回”按钮时，系统会从堆栈中将其弹出（并销毁）`，然后恢复前一 Activity。 （任务和返回栈文档中对返回栈有更详细的阐述。）
- **回调机制**：当一个 Activity 因某个新 Activity 启动而停止时，系统会通过该 Activity 的生命周期回调方法通知其这一状态变化。Activity 因状态变化—系统是创建 Activity、停止 Activity、恢复 Activity 还是销毁 Activity— 而收到的回调方法可能有若干种，每一种回调都会为您提供执行与该状态变化相应的特定操作的机会。 例如，停止时，您的 Activity 应释放任何大型对象，例如网络或数据库连接。 当 Activity 恢复时，您可以重新获取所需资源，并恢复执行中断的操作。 这些状态转变都是 Activity 生命周期的一部分。

## 二、创建Activity

1. 创建布局文件

> 这个没什么好说的，下面就是一个最普通的布局了。

```java
    //activity_one.xml 创建
    <LinearLayout xmlns:android#"http://schemas.android.com/apk/res/android"
      android:id#"@+id/linearLayout"
      android:layout_width#"match_parent"
      android:layout_height#"match_parent"
      android:gravity#"center"
      android:orientation#"vertical">
          <TextView
              android:id#"@+id/textView"
              android:layout_width#"wrap_content"
              android:layout_height#"wrap_content"
              android:layout_margin#"2px"
              android:text#"文本框"
            />
    </LinearLayout>
```

2. 创建Activity的类

> onCreate() 您必须实现此方法。系统会在创建您的 Activity 时调用此方法。您应该在实现内初始化 Activity
> 的必需组件。 最重要的是，您必须在此方法内调用setContentView()，以定义 Activity 用户界面的布局。


```java
    //创建你的Activity类需要继承自Activity或者其子类
    public class MyActivity1 extends AppCompatActivity {

      @Override//一个Activity中必须要实现onCreate方法
      protected void onCreate(Bundle savedInstanceState) {
        //这个方法是必须要写的
        super.onCreate(savedInstanceState);
        //这里需要指定你的布局文件activity_one.xml
        setContentView(R.layout.activity_one);
      }
    }
```

3. 在AndroidManifest.xml中声明Activity

> AndroidManifest.xml也就是我们当前模块的清单文件，只有在清单文件中声明您的
> Activity，这样系统才能知道有这么一个Activity的存在，才可以访问这个Activity。

```java
      <!--这里设置过滤器，将这个Activity设置为你的程序入口-->
      <intent-filter>
        <action android:name#"android.intent.action.MAIN" />

        <category android:name#"android.intent.category.LAUNCHER" />
      </intent-filter>
    </activity>
```

4. 启动调试，这里就不演示了，只要启动你的模拟器即可

## 三、Activity的生命周期概述

1. 我们先上代码和图片，让大家看看Activity的一生到底是怎么样的

> 关于图中需要实现说明几个`关键词`：
> 
> 1.`entire lifetime`：表示一个最简单的完整的Activity生命周期会按照如下顺序回调：onCreate -> onStart > onResume -> onPause -> onStop -> onDestroy。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317104511221.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)
> 2.`visible lifetime`：当执行onStart回调方法时，Activity开始被用户所见（也就是说，onCreate时用户是看不到此Activity的，那用户看到的是哪个？当然是此Activity之前的那个Activity），一直到onStop之前，此阶段Activity都是被用户可见，称之为visible lifetime。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317104549593.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)
> 3.`foreground lifetime`：当执行到onResume回调方法时，Activity可以响应用户交互，一直到onPause方法之前，此阶段Activity称之为foreground lifetime。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020031710460154.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)



```java
public class MyActivity1 extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_one);
    // The activity is being created.
  }
  @Override
  protected void onStart() {
    super.onStart();
    // The activity is about to become visible.
  }
  @Override
  protected void onResume() {
    super.onResume();
    // The activity has become visible (it is now "resumed").
  }
  @Override
  protected void onPause() {
    super.onPause();
    // Another activity is taking focus (this activity is about to be "paused").
  }
  @Override
  protected void onStop() {
    super.onStop();
    // The activity is no longer visible (it is now "stopped")
  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    // The activity is about to be destroyed.
  }
}
```

2. 我们来举几个实际的例子，让大家更能生动的理解Activity的生命周期

- 下面我们直接展示具体例子：我们假设现在有`两个Activity，别是A和B`，然后现在Activity A上有一个`按钮可以跳转到Activity B`，我们就以这个场景来按照顺序做演示。

- 1.已经启动了Activity A，现在点击A中的按钮，跳转到Activity B中，这时候我们看看两个Activity的生命周期是怎么样的。

> `回调顺序`：onPause -> B:onCreate -> B:onStart -> B:onResume -> A:onStop。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317104726707.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)

- 2.这时候B正在工作运转，然后我们点击手机自带的Back返回键，来看看这个生命周期的运转

> `回调顺序`：B:onPause -> A:onRestart -> A:onStart -> A:onResume -> B:onStop
> -> B:onDestroy。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317104735927.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)

- 3.这时候B已经被销毁了，我们在A的界面上，然后我们尝试短暂按下手机的Home键


> `回调顺序`：A:onPause -> A:onStop。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317105329463.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)
> - 我们可以清楚的看到我们平时对Activity的操作中Activity生命周期运转的不同情况，也就是可以大致`总结`为
> - 1.Activity之间的跳转会将前一个Activity压入堆栈
> - 2.Back键的跳转会销毁当前Activity
> - 3.Home键的跳转会不会销毁当前Activity

## 四、Activity的生命周期详解

1. 首先我们直接上一张表来看看Activity生命周期更加`详细`的信息
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317105910910.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)
2. 接着我们来说说管理Activity生命周期的几个`技巧`

- 技巧一：使用 `onPause() `向存储设备写入至关重要的持久性数据（例如用户编辑）。

> (onPause()、onStop() 和 onDestroy())。由于 onPause() 是这三个方法中的第一个，因此Activity 创建后，onPause() 必定成为最后调用的方法，然后才能终止进程 — 如果系统在紧急情况下必须恢复内存，则可能不会调用onStop() 和 onDestroy()。因此，您应该使用 onPause() 向存储设备写入至关重要的持久性数据（例如用户编辑）。不过，您应该对 onPause()调用期间必须保留的信息有所选择，因为该方法中的任何阻止过程都会妨碍向下一个 Activity 的转变并拖慢用户体验。

- 技巧二：通过`onSaveInstanceState()`方法来更好的保护Activity的状态数据，防止特殊情况下重要数据的丢失(如系统因为内存不足强行销毁Activity)

>当 Activity 暂停或停止时，Activity 的状态会得到保留。 确实如此，因为当 Activity 暂停或停止时，Activity 对象仍保留在内存中 — 有关其成员和当前状态的所有信息仍处于活动状态。 因此，用户在 Activity 内所做的任何更改都会得到保留，这样一来，当 Activity 返回前台（当它“继续”）时，这些更改仍然存在。

>不过，当系统为了恢复内存而销毁某项 Activity 时，Activity 对象也会被销毁，因此系统在继续 Activity 时根本无法让其状态保持完好，而是必须在用户返回 Activity 时重建 Activity 对象。但用户并不知道系统销毁 Activity 后又对其进行了重建，因此他们很可能认为 Activity 状态毫无变化。 在这种情况下，您可以实现另一个回调方法对有关 Activity 状态的信息进行保存，以确保有关 Activity 状态的重要信息得到保留：onSaveInstanceState()。

> 系统会先调用 onSaveInstanceState()，然后再使 Activity 变得易于销毁。系统会向该方法传递一个 Bundle，您可以在其中使用 putString() 和putInt() 等方法以名称-值对形式保存有关 Activity 状态的信息。然后，如果系统终止您的应用进程，并且用户返回您的 Activity，则系统会重建该 Activity，并将 Bundle 同时传递给 onCreate() 和 onRestoreInstanceState()。您可以使用上述任一方法从 Bundle 提取您保存的状态并恢复该 Activity 状态。如果没有状态信息需要恢复，则传递给您的 Bundle 是空值（如果是首次创建该 Activity，就会出现这种情况）。

3. 处理配置变更（例如手机旋转）

- 说明：有些设备配置可能会在运行时发生变化（例如屏幕方向、键盘可用性及语言）。 发生此类变化时，Android 会重建运行中的 Activity（系统调用onDestroy()，然后立即调用 onCreate()）。此行为旨在通过利用您提供的备用资源（例如适用于不同屏幕方向和屏幕尺寸的不同布局）自动重新加载您的应用来帮助它适应新配置。

-	 正如上文所述，处理此类重启的最佳方法是利用onSaveInstanceState() 和 onRestoreInstanceState()（或 onCreate()）保存并恢复 Activity 的状态。

- 接下来我通过一段代码来展示如何通过onSaveInstanceState() 来实现状态的保存


```java
import android.app.Activity;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MyActivity1 extends Activity {

  private static final String TAG = "Activity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_one);
    //方式一：通过onCreate来获取之前的状态信息
    // 这里需要检查是否正在重新创建一个以前销毁的实例
    if (savedInstanceState != null) {
      // 从已保存状态恢复成员的值
      String string = savedInstanceState.getString(TAG);
      Log.i(TAG, "通过onCreate获取屏幕旋转之前状态"+string);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    // 保存用户自定义的状态
    savedInstanceState.putString(TAG, "hello");
    // 调用父类交给系统处理，这样系统能保存视图层次结构状态
    super.onSaveInstanceState(savedInstanceState);
  }

  //方式二：onRestoreInstanceState()来获取之前的状态信息
  //系统onRestoreInstanceState()只有在存在保存状态的情况下才会恢复，因此您不需要检查是否Bundle为空
  public void onRestoreInstanceState(Bundle savedInstanceState) {
    // 总是调用超类，以便它可以恢复视图层次超级
    super.onRestoreInstanceState(savedInstanceState);
    // 从已保存的实例中恢复状态成员
    String string = savedInstanceState.getString(TAG);
    Log.i(TAG, "通过onRestoreInstanceState获取屏幕旋转之前状态："+string);
  }
}
```


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317125539391.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200317125404382.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)

这里着重说明一下一些`注意点`：
	

> 1、如果是用户自动按下`返回键`，或程序`调用finish()`退出程序，是`不会触发`onSaveInstanceState()和onRestoreInstanceState()的。
> 		2、每次用户`旋转屏幕`时，您的Activity将被破坏并重新创建。当屏幕改变方向时，系统会破坏并重新创建前台Activity，因为屏幕配置已更改，您的Activity可能需要加载替代资源（例如布局）。即会执行onSaveInstanceState()和onRestoreInstanceState()的。


4. 协调 Activity之间的切换

>当一个 Activity 启动另一个 Activity 时，它们都会体验到生命周期转变。第一个 Activity 暂停并停止（但如果它在后台仍然可见，则不会停止）时，同时系统会创建另一个 Activity。 如果这些 Activity 共用保存到磁盘或其他地方的数据，必须了解的是，在创建第二个 Activity 前，第一个 Activity 不会完全停止。更确切地说，启动第二个 Activity 的过程与停止第一个 Activity 的过程存在重叠。

>生命周期回调的顺序经过明确定义，当两个 Activity 位于同一进程，并且由一个 Activity 启动另一个 Activity 时，其定义尤其明确。 以下是当 Activity A 启动 Activity B 时一系列操作的发生顺序：

>Activity A 的 onPause() 方法执行。
Activity B 的 onCreate()、onStart() 和 onResume() 方法依次执行。（Activity B 现在具有用户焦点。）
然后，如果 Activity A 在屏幕上不再可见，则其 onStop() 方法执行。
您可以利用这种可预测的生命周期回调顺序管理从一个 Activity 到另一个 Activity 的信息转变。 例如，如果您必须在第一个 Activity 停止时向数据库写入数据，以便下一个 Activity 能够读取该数据，则应在 onPause() 而不是 onStop() 执行期间向数据库写入数据。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200205185656320.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70#pic_center)
