# 目录

- 一、Intent概述
- 二、Activity匹配接收隐式Intent的匹配规则
- 三、隐式Intent的配置方法
- 四、常用的Inten隐式设置示例
- 五、Intent简单应用示例

## 一、Intent概述
1. 定义

Intent被译作意图，通过Intent，你的程序可以向Android表达某种请求或者意愿，Android会根据意愿的内容选择适当的组件来完成请求。

2. 显示Intent与隐式Intent

- 显示Intent与隐式Intent

```java
//1.表示显示Intent，也就是直接指定目标Activity
Intent intent = new Intent();
//1.1 通过ComponentName来指定
ComponentName componentName = new ComponentName("com.sample.demo3", "com.sample.demo3.MainActivity");
intent.setComponent(componentName);
//1.2 通过ClassName来指定
intent.setClassName(this, "com.sample.demo3.MainActivity");
intent.setClass(this, intent.getClass());

startActivity(intent);
//2.表示隐式Intent，也就是不直接指定目标Activity
Intent intent = new Intent();
intent.setAction(Intent.ACTION_NEW);
startActivity(intent);
```

## 二、Activity匹配接收隐式Intent的匹配规则

1. 示例

- 我们需要在清单文件的<intent-filter>中建立Activity和隐式Intent的匹配关系。例如下面是一个入口Activity匹配隐式Intent的示例。

```java
<activity
    android:name=".MainActivity"
    android:label="@string/app_name" >
    //进行匹配规则的制定
    <!-- 这个活动是主条目，应该出现在app启动器 -->
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />//ACTION_MAIN动作表示这是主入口点，不期望任何intent数据。
        <category android:name="android.intent.category.LAUNCHER" />//CATEGORY_LAUNCHER类别表示该活动的图标应该放置在系统的应用程序启动器中。如果元素没有指定带有图标的图标，那么系统将使用来自元素的图标。
    </intent-filter>
</activity>
```

```java
    <action>
        在name属性中声明接收的意图操作。也就是name值指定了可以接收的Action属性。
    <data>
        表明接收的数据类型，使用一个或多个属性指定数据URI的各个方面(scheme, host, port, path)和MIME类型。
    <category>
        在name属性中声明接受的intent类别。
```

2. Action：指定Activity可以执行的动作

Action标识用来说明这个Activity可以执行哪些动作，Activity在<intent-filter>标签下可以通过<data>来指定action属性，然后去匹配含有该action属性的Intent。

- 一些常用的示例如下

```java
ACTION_CALL activity 启动一个电话.
ACTION_EDIT activity 显示用户编辑的数据.
ACTION_MAIN activity 作为Task中第一个Activity启动
ACTION_SYNC activity 同步手机与数据服务器上的数据.
ACTION_BATTERY_LOW broadcast receiver 电池电量过低警告.
ACTION_HEADSET_PLUG broadcast receiver 插拔耳机警告
ACTION_SCREEN_ON broadcast receiver 屏幕变亮警告.
ACTION_TIMEZONE_CHANGED broadcast receiver 改变时区警告.
```

- 注意一：一条<intent-filter>元素至少应该包含一个<action>，否则任何Intent请求都不能和该<intent-filter>匹配；
- 注意二：如果<intent-filter>中没有包含任何Action类型，那么无论什么Intent请求都无法和这条<intent-filter>匹配。反之，如果Intent请求中没有设定Action类型，那么只要<intent-filter>中包含有Action类型，这个Intent请求就将顺利地通过<intent-filter>的行为测试。

3. Category：于指定当前动作（Action）被执行的环境

- 定义：指定这个activity在哪个环境中才能被激活。不属于这个环境的，不能被激活。

```java
　　　  CATEGORY_DEFAULT：Android系统中默认的执行方式，按照普通Activity的执行方式执行。表示所有intent都可以激活它　

　　　　CATEGORY_HOME：设置该组件为Home Activity。

　　　　CATEGORY_PREFERENCE：设置该组件为Preference。　

　　　　CATEGORY_LAUNCHER：设置该组件为在当前应用程序启动器中优先级最高的Activity，通常为入口ACTION_MAIN配合使用。　

　　　　CATEGORY_BROWSABLE：设置该组件可以使用浏览器启动。表示该activity只能用来浏览网页。　

　　　　CATEGORY_GADGET：设置该组件可以内嵌到另外的Activity中。
```

- 注意：如果该activity想要通过隐式intent方式激活，那么不能没有任何category设置，至少包含一个android.intent.category.DEFAULT

3. Data  指定了执行时要操作的数据

- 目标<data/>标签中包含了以下几种子元素，他们定义了url的匹配规则：

android:scheme 匹配url中的前缀，除了“http”、“https”、“tel”...之外，我们可以定义自己的前缀
android:host 匹配url中的主机名部分，如“google.com”，如果定义为“*”则表示任意主机名
android:port 匹配url中的端口
android:path 匹配url中的路径

```java
<activity android:name=".TargetActivity">
<intent-filter>
	<action android:name="com.scott.intent.action.TARGET"/>
	<category android:name="android.intent.category.DEFAULT"/>
	<data android:scheme="scott" android:host="com.scott.intent.data" android:port="7788" android:path="/target"/>
    //配置接收的数据MIME类型
    <data android:mimeType="application/vnd.google.panorama360+jpg"/>
    <data android:mimeType="image/*"/>
    <data android:mimeType="video/*"/>
    <data android:mimeType="text/plain"/>
</intent-filter>
</activity>
```

## 三、隐式Intent的配置方法

1. 设置Intent的Action属性

- 注意：一个activity可以有多个action，只要有一个匹配就可以被启动。

```java
//1.设置示例一
Intent intent = new Intent("com.scott.intent.action.TARGET");
startActivity(intent);
//2.设置示例二
intent.setAction(Intent.ACTION_VIEW);
intent.setAction(Intent.ACTION_SEND);
```

2. 设置Intent的Category属性

- 一个intent对象可以有任意个category。intent类定义了许多category常数，这里就不做演示了。

```java
//1.基本方法
addCategory()方法为一个intent对象增加一个category,
removeCategory删除一个category,
getCategories()获取intent所有的category.

//2.一些添加示例
//目标活动允许web浏览器自己启动，以显示链接引用的数据，如图像或电子邮件消息。
intent.addCategory(Intent.CATEGORY_BROWSABLE);
//活动是任务的初始活动，列在系统的应用程序启动程序中。
intent.addCategory(Intent.CATEGORY_LAUNCHER);
```

3. 设置Intent的Data属性

有两种设置Intent的Data属性的方式。

- 方式一：通过Intent的构造函数

```java
//1.方法
public Intent(String action, Uri uri) {
    mAction = action;
    mData = uri;
}
//2.示例
Intent intent = new Intent("com.scott.intent.action.name");
intent.setData(Uri.parse("scheme://host:port/parth"));
startActivity(intent);
```

- 方式二：通过setData()方法进行设置

```java
//示例
Intent intent = new Intent("com.scott.intent.action.name");
//要仅设置数据URI，请调用setData()。要仅设置MIME类型，请调用setType()。如果需要，可以使用setDataAndType()显式地设置。
intent.setData(Uri.parse("scheme://host:port/parth"));
intent.setType("");
intent.setDataAndType();
startActivity(intent);
```

```java
<activity android:name=".TargetActivity">
	<intent-filter>
		<action android:name="com.scott.intent.action.TARGET"/>
		<category android:name="android.intent.category.DEFAULT"/>
		<data android:scheme="scott" android:host="com.scott.intent.data" android:port="7788" android:path="/target"/>
	</intent-filter>
</activity>
不过有时候对path限定死了也不太好，比如我们有这样的url：（scott://com.scott.intent.data:7788/target/hello）（scott://com.scott.intent.data:7788/target/hi）

这个时候该怎么办呢？我们需要使用另外一个元素：android:pathPrefix，表示路径前缀。
我们把android:path="/target"修改为android:pathPrefix="/target"，然后就可以满足以上的要求了。
```

4. 设置Intent的Extra属性

这个参数不参与匹配activity，而仅作为额外数据传送到另一个activity中，接收的activity可以将其取出来。这些信息并不是激活这个activity所必须的。也就是说激活某个activity与否只上action、data、catagory有关，与extras无关。而extras用来传递附加信息，诸如用户名，用户密码什么的。

- 下面直接写上示例就清楚了

```java
//发送方
Intent intent = new Intent("com.scott.intent.action.TARGET");
Bundle bundle = new Bundle();
bundle.putInt("id", 0);
bundle.putString("name", "scott");
intent.putExtras(bundle);
startActivity(intent);
//接收方
Bundle bundle = intent.getExtras();
int id = bundle.getInt("id");
String name = bundle.getString("name");
```

## 四、常用的Inten隐式设置示例

```java
// 调用浏览器
Uri webViewUri = Uri.parse("http://blog.csdn.net/zuolongsnail");
Intent intent = new Intent(Intent.ACTION_VIEW, webViewUri);

// 调用地图
Uri mapUri = Uri.parse("geo:100,100");
Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);

// 播放mp3
Uri playUri = Uri.parse("file:///sdcard/test.mp3");
Intent intent = new Intent(Intent.ACTION_VIEW, playUri);
intent.setDataAndType(playUri, "audio/mp3");

// 调用拨打电话
Uri dialUri = Uri.parse("tel:10086");
Intent intent = new Intent(Intent.ACTION_DIAL, dialUri);
// 直接拨打电话，需要加上权限<uses-permission id="android.permission.CALL_PHONE" />
Uri callUri = Uri.parse("tel:10086");
Intent intent = new Intent(Intent.ACTION_CALL, callUri);

// 调用发邮件（这里要事先配置好的系统Email，否则是调不出发邮件界面的）
Uri emailUri = Uri.parse("mailto:zuolongsnail@163.com");
Intent intent = new Intent(Intent.ACTION_SENDTO, emailUri);
// 直接发邮件
Intent intent = new Intent(Intent.ACTION_SEND);
String[] tos = { "zuolongsnail@gmail.com" };
String[] ccs = { "zuolongsnail@163.com" };
intent.putExtra(Intent.EXTRA_EMAIL, tos);
intent.putExtra(Intent.EXTRA_CC, ccs);
intent.putExtra(Intent.EXTRA_TEXT, "the email text");
intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
intent.setType("text/plain");
Intent.createChooser(intent, "Choose Email Client");

// 发短信
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.putExtra("sms_body", "the sms text");
intent.setType("vnd.android-dir/mms-sms");
// 直接发短信
Uri smsToUri = Uri.parse("smsto:10086");
Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
intent.putExtra("sms_body", "the sms text");
// 发彩信
Uri mmsUri = Uri.parse("content://media/external/images/media/23");
Intent intent = new Intent(Intent.ACTION_SEND);
intent.putExtra("sms_body", "the sms text");
intent.putExtra(Intent.EXTRA_STREAM, mmsUri);
intent.setType("image/png");

// 卸载应用
Uri uninstallUri = Uri.fromParts("package", "com.app.test", null);
Intent intent = new Intent(Intent.ACTION_DELETE, uninstallUri);
// 安装应用
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setDataAndType(Uri.fromFile(new File("/sdcard/test.apk"), "application/vnd.android.package-archive");

// 在Android Market中查找应用
Uri uri = Uri.parse("market://search?q=愤怒的小鸟");
Intent intent = new Intent(Intent.ACTION_VIEW, uri);
```

## 五、Intent简单应用示例

### 一、使用包含预定义动作的隐式Intent

```java
//1.清单文件配置
<activity
    android:name=".SecondActivity"
    android:label="@string/title_activity_second" >
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
//2.配置隐式Intent
Intent intent = new Intent();
intent.setAction(Intent.ACTION_VIEW);
startActivity(intent);
```


### 二、使用自定义动作的隐式Intent

```java
//1.在上例的基础上，更改AndroidManifest.xml，为SecondActivity自定义一个action name
 <activity
     android:name=".SecondActivity"
     android:label="@string/title_activity_second" >
     <intent-filter>
         <action android:name="test_action" />

         <category android:name="android.intent.category.DEFAULT" />
     </intent-filter>
 </activity>
//2.隐式Intent跳转，设置Action为test_action的自定义值
Intent intent = new Intent();
intent.setAction("test_action");
startActivity(intent);
```

### 三、使用Intent打开网页

```java
Intent intent = new Intent();
intent.setAction(Intent.ACTION_VIEW);
intent.setData(Uri.parse("http://www.baidu.com"));
startActivity(intent);
```

### 四、下载程序的实现

```java
//下载程序的显示Intent使用
Intent downloadIntent = new Intent(this, DownloadService.class);
downloadIntent.setData(Uri.parse(fileUrl));// The fileUrl is a string URL, such as "http://www.example.com/image.png"
startService(downloadIntent);
```

### 五、共享数据，并且可以指定我共享数据的类型

```java
Intent sendIntent = new Intent();
//与其他人共享内容
sendIntent.setAction(Intent.ACTION_SEND);
//添加额外的内容来指定要共享的内容
sendIntent.putExtra(Intent.EXTRA_TEXT, "处理我的Intent指定数据的Activity打开了");
//指定我发送的数据内容的数据格式
sendIntent.setType("text/plain");
//用于验证是否有应用可以响应Intent请求，如果没有就不打开应用
if (sendIntent.resolveActivity(getPackageManager()) != null) {
    startActivity(sendIntent);
}
```

### 六、Intent使用串烧

```java
Intent intent = new Intent();
switch (Integer.parseInt(editText.getText().toString())) {
    case 1:
        //在本Activity中打开联系人名称为1的面板，可以进行编辑
        intent.setAction(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("https://www.baidu.com"));
//                    intent.setData(Uri.parse("content://contacts/people/1"));
        intent.setData(Uri.parse("https://www.csdn.net/"));
        break;
    case 2:
        //显示电话拨号与人填写
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("content://contacts/people/1"));
        break;
    case 3:
        //编辑标识符为“1”的人的信息。
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("content://contacts/people/1"));
        break;
    case 4:
        //显示电话拨号与给定的数字填写
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:123"));
        break;
    case 5:
        //编辑标识符为“1”的人的信息。
        intent.setAction(Intent.ACTION_EDIT);
        intent.setData(Uri.parse("content://contacts/people/1"));
        break;
    case 6:
        //显示用户可以浏览的人员列表。
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("content://contacts/people/"));
        break;
    /************************Category:********************************/
    case 8:
        //它应该作为顶级应用程序出现在启动程序中,其实就是程序启动入口
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        break;
    case 9:
        //它应该包含在用户可以对数据执行的可选操作列表中
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        break;
    /************************其他补充:********************************/
    case 10:
        //启动主屏幕
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        break;
    case 11:
        //显示人们的电话号码列表
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("item/phone");//vnd.android.cursor.item/phone
        break;
    case 12:
        //显示所有用户数据
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        break;
    case 13:
        //显示所有notes的列表。“记事本/便笺簿” 目前无效
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("content://com.google.provider.NotePad/notes"));
        break;
    default:
        //保持每次重新开启app之后进入的主界面都是固定的页面(默认情况下会在每次app重启之后显示关闭之前的页面)
        intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
        break;
}
startActivity(intent);
```

### 结语
##### 如果大家我的文章讲的不够好，大家可以参考大神的文章：[https://blog.csdn.net/harvic880925/article/details/38399723](https://blog.csdn.net/harvic880925/article/details/38399723)

直到这里，差不多Intent的使用方法已经介绍完毕了，他的作用就是发送信息。可以指定固定的接受者或者可以设置匹配规则，这个就是Intent的使用本质了。如果大家喜欢的话，希望...

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020032115075898.jpeg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMjMwMDA3,size_16,color_FFFFFF,t_70)
