# `Android开发DEMO大全（二）`


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

## 数据存储

### SharedPreference的全套使用

```java
/************************SharedPreferences:MyActivityShar********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：SharedPreferences的使用
 *
 *流程：
 *    1.增加数据
 *    2.修改数据 直接在此存入相同key达到修改数据的目的
 *    3.删除数据 意思的Edit对象中的操作，删除文件还是需要IO流
 *    4.查询数据
 * 注意：/data/data/user/0/com.wanli.demo4/
 */
// 获得SharedPreferences,并创建文件名称为"mrsoft"
final SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);
// 获得Editor对象
final SharedPreferences.Editor editor = sp.edit();
//功能一：增删改
//存储数据
editor.putString("username", "数据内容");
//        editor.putStringSet("setDate",new HashSet<String>());
//        editor.remove("username");//移除某个数据
//        editor.clear();//清空存储
//提交信息
editor.commit();
//功能二：获取数据
//        sp.contains("key");
//        Map<String, ?> all = sp.getAll();
//        sp.getString("key");
//功能三：设置事件
//        sp.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
//            @Override
//            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//
//            }
//        });
/**----------------------------END：Activity:MyActivityShar------------------------------------**/

```

### sqlite（用于java操作数据库的演示）

```java
/************************Activity:MyActivityShar********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：数据库的CRUD，事务，
 *注意：
 *      1.注意原生数据库操作方法sqLiteDatabase.execSQL，execSQL
 *      2.事务的操作注意，如果需要执行的操作有很多，那么可以分成几次做，可以让数据库有空隙去执行其他线程的数据库操作
 *      3.ORM操作
 *      4.注意变量的提取，如果用到相同次数达到2次以上的，通常将其提取出来
 *方法：
 */

public class MyActivityShar extends AppCompatActivity {


    public static final String TAG = "849";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my1);
        //性能：因为是IO操作，非常影响性能，推荐创建一个线程到后台操作
        DatebaseHeleper datebaseHeleper = new DatebaseHeleper(this, "demo1.db", null, 2);
        SQLiteDatabase sqLiteDatabase = datebaseHeleper.getReadableDatabase();
        //Add:
        //制作内容
        ContentValues contentValues = new ContentValues();
<<<<<<< HEAD
        contentValues.put(DatebaseHeleper.USERNAME, "叶之越");
=======
        contentValues.put(DatebaseHeleper.USERNAME, "yzy");
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
        contentValues.put(DatebaseHeleper.PASSWORD, "0000");
        //更新数据
        sqLiteDatabase.insert("demo1.db", null, contentValues);

        //query:
        //游标，用于查询数据，返回查询结果的集合
        //具体参数自己进入源码查看
        Cursor cursor = sqLiteDatabase.query(DatebaseHeleper.USER_TABLE,null,null,null,null,null,null);
        //根据游标属性来读取查询数据
        if (cursor.moveToFirst()){
            int count = cursor.getCount();
            for (int len=0;len<count;len++){
                String userName = cursor.getString(cursor.getColumnIndexOrThrow(DatebaseHeleper.USERNAME));
                String passWord = cursor.getString(cursor.getColumnIndexOrThrow(DatebaseHeleper.PASSWORD));
                Log.i(TAG, len+":"+userName+"->"+passWord);
            }
        }

        //Delete：
        String whereCLauseString="username=?";
        String[] whereArgsString={"张三","李四"};
        sqLiteDatabase.delete(DatebaseHeleper.USER_TABLE,whereCLauseString,whereArgsString);

        //Update:
        ContentValues contentValues2 = new ContentValues();
        //更新的内容
        contentValues2.put(DatebaseHeleper.PASSWORD,"1234");
        //更新的条件判断
        String whereClauseString="username=?";
        String[] whereArgsString2 ={"张三","李四"};
        sqLiteDatabase.update(DatebaseHeleper.USER_TABLE, contentValues2, whereClauseString, whereArgsString2);


        //原始语句，可以执行任何操作，并且效率更高
        sqLiteDatabase.execSQL("insert into user(username,password) values('name1','password1')");

        //事务：
        sqLiteDatabase.beginTransaction();
        try{
            //...这里是要执行的数据库操作
            //注意，如果需要执行的操作有很多，那么可以分成几次做，可以让数据库有空隙去执行其他线程的数据库操作
            //一旦开启事务，那么当前数据库是会被锁定的，这里需要注意
            //提交事务
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            //关闭事务
            sqLiteDatabase.endTransaction();
        }
    }
}
/**----------------------------END：Activity:MyActivityShar------------------------------------**/
/************************SQLiteOpenHelper:DatebaseHeleper********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：实现数据库的初始化，创建数据库表
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatebaseHeleper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "user";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    public DatebaseHeleper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建你的表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE + "(" + USERNAME + " varchar(20) not null," + PASSWORD + " varchar(60) not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    }
}
/**----------------------------END：SQLiteOpenHelper:DatebaseHeleper------------------------------------**/

```

### 内部存储 IO流(内部存储javaIO流，文件的创建于读取)

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：内部存储
 *
 *流程：
 *    1.指定写入的文件权限
 *    2.指定输入内容
 *    3.读入文件
 *注意：流程说明：先输出内部存储文件，然后在读进来，
 *        内部存储文件位置： data/data/<包名(com.mingrisoft)>/files 目录下的文件，这些文件就是内部存储
 *方法：
 */
//参数：输出流参数说明的如下
//path|指定文件名称不包括拓展名
//mode|指定访问读写权限，MODE_PRIVATE 表示只能是本应用读写 MODE_MULTI_PROCESS 表示外部应用也可以读写
try (FileOutputStream fos = openFileOutput("memo", MODE_PRIVATE);
     FileInputStream fis = openFileInput("memo")) {
    //保存文本信息
    fos.write("输入流写入文本".getBytes());
    //清除缓存
    fos.flush();

    //保存数据的数组
    byte[] buffer = new byte[fis.available()];
    //从输入流中读取数据
    fis.read(buffer);
    Log.i("是否读取到文件日志", new String(buffer));
} catch (IOException e) {
    Toast.makeText(MainActivity.this, "出现了异常", Toast.LENGTH_SHORT).show();
    Log.i("IO流", "文件流异常");
}
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### 外部存储 IO流 注意文件存储位置(外部存储javaIO流，文件的创建于读取)

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：外部存储
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：文件保存位置：storage/emulated/带数字的目录；
 *
 *方法：
 */
//设置存储sd卡根目录
File file = new File(Environment.getExternalStorageDirectory(), "YeZhiyue.text");
Log.i("tag标志", file.getAbsolutePath());

try (FileOutputStream fos = new FileOutputStream(file);
     FileInputStream fis = new FileInputStream(file)) {
    //保存文本信息
    fos.write("输入流写入文本".getBytes());
    //清除缓存
    fos.flush();

    //保存数据的数组
    byte[] buffer = new byte[fis.available()/*读取一次输入流能够读取的字节个数*/];
    //从输入流中读取数据
    fis.read(buffer);
    Log.i("是否读取到文件日志", new String(buffer));
} catch (IOException e) {
    Toast.makeText(MainActivity.this, "出现了异常", Toast.LENGTH_SHORT).show();
    Log.i("IO流", "文件流异常");
}

/**----------------------------END：Activity:------------------------------------**/

```

### 文件存储拓展(各种文件路径的获取、各种文件的特殊读取方式)

```java
/************************Activity:MyActivityShar********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：文件的读取，路径、Stream
 */
/************************读取各种类型的文件:********************************/

//情况一：读取assert目录下面的文件，这个目录下面可以有多个层级，但是res目录下的目录只能有一个层级
//外部存储
//读文件列表
String[] list = getAssets().list("image/");
//读图片
InputStream inputStream = getResources().getAssets().open("test.html");
getAssets().open("image/dog.jpg");
Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
new ImageView(this).setImageBitmap(bitmap);
//读音频
AssetFileDescriptor assetFileDescriptor = getAssets().openFd("libai.mp3");
MediaPlayer mediaPlayer = new MediaPlayer();
mediaPlayer.reset();
mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());

//情况二：读取资源目录下边的文件
//读取raw下面的文件,和assert读取的区别就是raw目录下面的资源是有索引的
InputStream is = getResources().openRawResource(R.raw.libai);

//情况三：读取sd卡下面的文件
//读取sd卡的文件
//        File sdDir = new File("/sdcard/test/a.txt");//不推荐使用，因为有些手机的sd卡的目录名称是不相同的
File sdDir = Environment.getExternalStorageDirectory().getAbsoluteFile();

/************************获取文件对象或者文件路径:********************************/
//情况一：基本的文件路径获取
//        getCacheDir()///data/user/0/com.wanli.demo4/cache
//        getFilesDir()///data/user/0/com.wanli.demo4/files
//        getObbDir()///storage/emulated/0/Android/obb/com.wanli.demo4
//        getDatabasePath("db")///data/user/0/com.wanli.demo4/databases/db 不管又没有这个数据库，都会获得这个文件
//        Environment.get...  这个可以直接获取很多不同的目录，可以尝试

//情况二：res目录下的资源文件读取
//读取raw下面的文件,和assert读取的区别就是raw目录下面的资源是有索引的
//注意出来的对象是不同的，可以是流、图片对象、字符串、整形值
InputStream is = getResources().openRawResource(R.raw.libai);
Drawable drawable = getDrawable();
String string = getString();
int color = getColor();

//情况三：获取sd卡的文件路径
//读取sd卡的文件
//        File sdDir = new File("/sdcard/test/a.txt");//不推荐使用，因为有些手机的sd卡的目录名称是不相同的
File sdDir = Environment.getExternalStorageDirectory().getAbsoluteFile();
/**----------------------------END：Activity:MyActivityShar------------------------------------**/

```

## Handler消息处理

### 5种work线程中实现UI线程的方式示例

```java
/************************Activity:ThreadActivityYehziyue********************************/
/************************制定时间：2020/3/15 0015********************************/
/**
 *功能：5种work线程中实现UI线程的方式示例
 *注意：我们的主线程中是不允许work线程中直接进行UI组件的操作，这时候就需要特殊的处理
 */

public class ThreadActivityYehziyue extends Activity  {

  public static final String TAG = "线程日志";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // UI/Main线程
    setContentView(R.layout.activity_thread);
    final Button button=(Button)findViewById(R.id.button);

    new Thread(new Runnable() {
      @Override
      public void run() {
        //Activity's methods,only UI thread can user,the work thread need depend this UI method to work.Otherwise the work thread will throw Exception.\
        //First way:Use work thread by runOnUiThread method provided by Activity Interface
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            Log.i(TAG, "Work线程");
          }
        });
        //Second way:Use work thread by post method
        button.post(new Runnable() {
          @Override
          public void run() {
            Log.i(TAG, "实现工作线程中执行UI线程的方式二");
          }
        });
        //Third way:Use work thread by postDelay method
        button.postDelayed(new Runnable() {
          @Override
          public void run() {
            Log.i(TAG, "实现工作线程中执行UI线程的方式三");
          }
        },2000);

        //Forth way:Use Handler and Looper
        new Handler(Looper.getMainLooper()).post(new Runnable() {
          @Override
          public void run() {
            Log.i(TAG, "实现工作线程中执行UI线程的方式四");
          }
        });
      }
    }).start();
    //Fifth way:Use AsyncTask class to
    /**
     * Work can divided as 4 steps:通常就按照下面4个步骤来使用这个类
     *  onPreExecute
     *  doInBackground
     *  onProgressUpdate
     *  onPostExecute
     * 注意：这个类适合于短时间的线程处理，耗时的线程处理不推荐使用这个线程处理
     */
    //有主线程和工作线程的工作之分，main and work thread to realize work thread
    class TestTask extends AsyncTask<Integer,Integer,String>/*这里的泛型根据需求来定义*/{

      @Override//UI thread
      protected void onPreExecute() {
        super.onPreExecute();
      }

      @Override//Work Thread
      //
      protected String doInBackground(Integer... integers) {
        return null;
      }

      @Override//UI thread
      protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
      }

      @Override//UI thread
      protected void onPostExecute(String s) {
        super.onPostExecute(s);
      }

    }
  }
}
/**----------------------------END：Activity:ThreadActivityYehziyue------------------------------------**/

```

### handler 主线程实现

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：Handler主线程实现
 *
 *流程：
 *    1.创建Handler对象
 *    2.设置message
 *    3.执行线程任务
 *注意：主线程实现和子线程的实现方式有所区别。主线程不需要额外创建Looper对象
 *
 *方法：
 */

public class MainActivity extends Activity {

    Message message1 = Message.obtain();       //获得消息对象
    Message message2 = Message.obtain();       //获得消息对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /*
       Message如何携带数据：
            通过它的属性来实现的：
            整形 arg1 arg2
            Object类型 obj
            replyTo 发送到何处
            what 自定义的消息代码
        */
        //设置消息代码,发送两条不同的消息
        message1.what = 0x01;
        handler.sendMessage(message1);
        message2.what = 0x02;
        handler.sendMessage(message2);
        //发送不带消息体的数据
        handler.sendEmptyMessage(0x03);
    }

    Handler handler = new Handler() {  //创建android.os.Handler对象
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x01) {  //如果接收到的是发送的标记消息
                Log.i("tag标志", "0x01");

            } else if (msg.what == 0x02) {
                Log.i("tag标志", "0x02");

            } else if (msg.what == 0x03) {
                Log.i("tag标志", "0x03");

            }
            //如果需要循环的发送消息，加上下面两句
//                message1=handler.obtainMessage(0x01);
//                handler.sendMessageDelayed(message1, 3000);  //延迟3秒发送消息                //如果需要不停的发送消息，加上下面一句
//                message1=handler.obtainMessage(0x02);
//                handler.sendMessageDelayed(message2, 3000);  //延迟3秒发送消息
        }
    };
}
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### handler 子线程实现

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：Handler子线程实现
 *
 *流程：
 *    1.创建Handler对象，需要额外配置Looper对象
 *    2.设置message
 *    3.执行线程任务
 *注意：与主线程的创建不同，需要额外配置Looper对象
 *      Looper.loop();//启动Looper,这句一定要放在handler.sendMessage的下面
 *方法：
 */
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread1 implements Runnable {
    Message message;
    Message message2;

    @Override
    public void run() {
        //第一步：初始化Looper对象、Handler对象
        Looper.prepare();
        //第二步：消息设置Message
        message = handler.obtainMessage();
        message2 = handler.obtainMessage();
        message.what = 0x01;//设置Message的what属性的值
        message2.what = 0x02;//设置Message的what属性的值
        handler.sendMessage(message);//发送消息
        handler2.sendMessage(message2);
        Looper.loop();//启动Looper,这句一定要放在handler.sendMessage的下面
    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Log.i("Looper", String.valueOf(msg.what));
            System.out.println("线程正在执行");
            message = handler.obtainMessage(0x01);
            handler.sendMessageDelayed(message, 3000);  //延迟3秒发送消息                //如果需要不停的发送消息，加上下面一句
        }
    };
    Handler handler2 = new Handler() {
        public void handleMessage(Message msg) {
            Log.i("Looper", String.valueOf(msg.what));
            System.out.println("线程2正在执行");
            message2 = handler2.obtainMessage(0x02);
            handler2.sendMessageDelayed(message2, 3000);  //延迟3秒发送消息                //如果需要不停的发送消息，加上下面一句
        }
    };
}
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### 轮播的实现 Handler+Message

```java

/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：布局文件中组件添加
 *
 *方法：
 */
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
public class MainActivity extends Activity {
    final int FLAG_MSG = 0x001;    //定义要发送的消息代码
    private ViewFlipper flipper;   //定义ViewFlipper
    private Message message;        //声明消息对象
    //定义图片数组
    private int[] images = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8};
    private Animation[] animation = new Animation[2];  //定义动画数组，为ViewFlipper指定切换动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);  //获取ViewFlipper
        for (int i = 0; i < images.length; i++) {      //遍历图片数组中的图片
            ImageView imageView = new ImageView(this);  //创建ImageView对象
            imageView.setImageResource(images[i]);  //将遍历的图片保存在ImageView中
            flipper.addView(imageView);             //加载图片
        }
        //初始化动画数组
        animation[0] = AnimationUtils.loadAnimation(this, R.anim.slide_in_right); //右侧平移进入动画
        animation[1] = AnimationUtils.loadAnimation(this, R.anim.slide_out_left); //左侧平移退出动画
        flipper.setInAnimation(animation[0]);   //为flipper设置图片进入动画效果
        flipper.setOutAnimation(animation[1]);  //为flipper设置图片退出动画效果

        message=Message.obtain();       //获得消息对象
        message.what=FLAG_MSG;  //设置消息代码
        handler.sendMessage(message); //发送消息
    }


    Handler handler = new Handler() {  //创建android.os.Handler对象
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == FLAG_MSG) {  //如果接收到的是发送的标记消息
                flipper.showPrevious();                  //示下一张图片
            }
            message=handler.obtainMessage(FLAG_MSG);   //获取要发送的消息
            handler.sendMessageDelayed(message, 3000);  //延迟3秒发送消息
        }
    };
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="@drawable/bg"
tools:context="com.mingrisoft.MainActivity">

<ViewFlipper
android:id="@+id/viewFlipper"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_marginTop="@dimen/margin_top"/>
</RelativeLayout>

/**----------------------------END：Activity:MainActivity------------------------------------**/


```

## 传感器

### 光线传感器，重力传感器java

```java
/***********************SensorEventListener：MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：光线传感器，重力传感器
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：
 *
 *方法：需要继承implements SensorEventListener
 */
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    //传感器输出信息的编辑框
    //定义传感器管理
    EditText textGRAVITY, textLIGHT;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取传感器管理
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE/*Context.SENSOR_SERVICE*/);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //为重力传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)/*指定传感器类型*/,
                SensorManager.SENSOR_DELAY_GAME/*指定传感器的频率*/);
        //为光线传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        //取消注册监听器
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        //取消注册监听器
        sensorManager.unregisterListener(this);
        super.onStop();
    }


    @Override//获取传感器的值
    public void onSensorChanged(SensorEvent event) {
        //重力传感器获取X、Y、Z三轴的输出信息
        float[] values = event.values;
        //获取传感器类型
        int sensorType = event.sensor.getType();
        StringBuilder stringBuilder = null;
        //根据传感器类型进行不同的操作
        switch (sensorType) {
            case Sensor.TYPE_GRAVITY:
                stringBuilder = new StringBuilder();
                stringBuilder.append("X轴横向重力值:");
                stringBuilder.append(values[0]);
                stringBuilder.append("\nY轴纵向重力值:");
                stringBuilder.append(values[1]);
                stringBuilder.append("\nZ轴向上重力值:");
                stringBuilder.append(values[2]);
                textGRAVITY.setText(stringBuilder.toString());
                break;
            case Sensor.TYPE_LIGHT:
                stringBuilder = new StringBuilder();
                stringBuilder.append("光的强度值:");
                stringBuilder.append(values[0]);
                textLIGHT.setText(stringBuilder.toString());
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/


```

### 加速度传感器java摇红包

```java
/************************SensorEventListener：MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：加速度传感器：微信摇红包
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：
 *
 *方法：
 */

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;  //定义传感器管理器
    private Vibrator vibrator;            //定义振动器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  // 获取传感器管理器
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);  //获取振动器服务

    }

    @Override
    protected void onResume() {
        super.onResume();
        //为加速度传感器注册监听器
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;  //获取传感器X、Y、Z三个轴的输出信息
        int sensorType = event.sensor.getType();  // 获取传感器类型
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {  //如果是加速度传感器
            //X轴输出信息>15,Y轴输出信息>15,Z轴输出信息>20
            if (values[0] > 15 || values[1] > 15 || values[2] > 20) {
                Toast.makeText(MainActivity.this, "摇一摇", Toast.LENGTH_SHORT).show();
                //创建AlertDialog.Builder对象
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setView(R.layout.packet);     //添加布局文件
                alertDialog.show();                        //显示alertDialog
                vibrator.vibrate(500);                    //设置振动器频率
                sensorManager.unregisterListener(this);  //取消注册监听器
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### 方向传感器java==磁场传感器+加速度传感器

```java
/************************SensorEventListener：MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：方向传感器==磁场传感器+加速度传感器
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：
 *
 *方法：
 */

public class SpiritlevelView extends View implements SpiritlevelView {
    /*
    执行流程：磁场传感器+加速度传感器实现方向传感器
        方向小球开发步骤：
            1.创建自定义View，并且实现SnesorEventListener接口
            2.布局界面
            3.为磁场和加速度传感器注册监听器，并获取传感器的取值
            4.获取z、x、y轴的旋转角度，并绘制小球
    */
/*
    获取方向传感器的步骤：
        1.获取加速度传感器和磁场传感器的实例
        2.注册监听器
        3.onSensorChanged()获取加速度、磁场传感器的值->传入数据到 getRotationMatrix()中获取R数组(加速度数据、磁场数据)
        4.获取所需要的旋转弧度数据 SensorManager.getOrientation()
            注意：这里获取的是弧度而不是角度，1弧度==圆周弧长为r  1角度==1度(共360度)
                我们可以通过Math.toDegrees()方法进行弧度到角度的转换
*/
    private Bitmap bubble;         // 定义水平仪中的小蓝球位图
    private int MAX_ANGLE = 30;   // 定义水平仪最大倾斜角，超过该角度，小蓝球将直接位于边界
    private int bubbleX, bubbleY; // 定义水平仪中小蓝球的X、Y坐标

    public SpiritlevelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bubble = BitmapFactory   // 加载小蓝球图片
                .decodeResource(getResources(), R.drawable.bubble);

        SensorManager sensorManager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);   // 获取传感器管理
        sensorManager.registerListener(this,  //为磁场传感器注册监听器
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this,  //为加速度传感器注册监听器
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    float[] accelerometerValues = new float[3];   //创建加速度传感器Z轴、X轴、Y轴取值数组
    float[] magneticValues = new float[3];          //创建磁场传感器Z轴、X轴、Y轴取值数组

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {          //如果当前为加速度传感器
            accelerometerValues = event.values.clone();                    //将取出的值放到加速度传感器取值数组中
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {  //如果当前为磁场传感器
            magneticValues = event.values.clone();                         //将取出的值放到磁场传感器取值数组中
        }

        float[] R = new float[9];                                           //创建存放旋转数据的取值数组
        float[] values = new float[3];                                        //创建存放方向数据的取值数组
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
        SensorManager.getOrientation(R, values);                              //获取方向Z轴、X轴、Y轴信息值

        float xAngle = (float) Math.toDegrees(values[1]);  // 获取与X轴的夹角
        float yAngle = (float) Math.toDegrees(values[2]);   // 获取与Y轴的夹角
        getPosition(xAngle,yAngle); //获取小蓝球的位置坐标
        super.postInvalidate();  // 刷新界面
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bubble, bubbleX, bubbleY, null);   // 根据小蓝球坐标绘制小蓝球
    }
    //根据x轴和y轴的旋转角度确定小蓝球的位置
    private  void getPosition(float xAngle,float yAngle){
        // 小蓝球位于中间时（水平仪完全水平），小蓝球的X、Y坐标
        int x = (super.getWidth() - bubble.getWidth()) / 2;
        int y = (super.getHeight() - bubble.getHeight()) / 2;
        /********控制小球的X轴位置******/

        if (Math.abs(yAngle) <= MAX_ANGLE) {   // 如果Y轴的倾斜角度还在最大角度之内
            // 根据Y轴的倾斜角度计算X坐标的变化值（倾斜角度越大，X坐标变化越大）
            int deltaX = (int) ((super.getWidth() - bubble.getWidth()) / 2 * yAngle / MAX_ANGLE);
            x -= deltaX;
        } else if (yAngle > MAX_ANGLE) {  // 如果Y轴的倾斜角度已经大于MAX_ANGLE，小蓝球在最左边
            x = 0;
        } else {  // 如果与Y轴的倾斜角已经小于负的MAX_ANGLE，小蓝球在最右边
            x = super.getWidth() - bubble.getWidth();
        }
        /********控制小球的Y轴位置******/
        if (Math.abs(xAngle) <= MAX_ANGLE) {  // 如果X轴的倾斜角度还在最大角度之内
            // 根据X轴的倾斜角度计算Y坐标的变化值（倾斜角度越大，Y坐标变化越大）
            int deltaY = (int) ((super.getHeight() - bubble.getHeight()) / 2 * xAngle / MAX_ANGLE);
            y += deltaY;
        } else if (xAngle > MAX_ANGLE) {  // 如果与X轴的倾斜角度已经大于MAX_ANGLE，小蓝球在最下边
            y = super.getHeight() - bubble.getHeight();
        } else {  // 如果X轴的倾斜角已经小于负的MAX_ANGLE，小蓝球在最上边
            y = 0;
        }
        //更新小蓝球的坐标
        bubbleX = x;
        bubbleY = y;
    }
}
/**----------------------------END：Activity:SpiritlevelView------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *方法：需要添加自定义组件com.mingrisoft.SpiritlevelView
 */

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="@drawable/back1"
tools:context="com.mingrisoft.MainActivity">

<com.mingrisoft.SpiritlevelView
android:layout_width="match_parent"
android:layout_height="match_parent"/>
</FrameLayout>

/**----------------------------END：2布局文件:layout.xml------------------------------------**/


```

### 磁力传感器java指南针

```java
/************************ SensorEventListener：PointerView********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：磁场传感器
 *
 *流程：
 1.创建自定义View，并且实现SensorEventListener接口
 2.布局界面
 3.为磁场传感器注册监听器，并获取传感器的取值
 4.根据X轴和Y轴的池昌强度绘制指针java类成员变量段
 *注意：
 *
 *方法：
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;
public class PointerView extends View implements SensorEventListener {
    //定义指针位图
    private Bitmap pointer = null;
    //定义传感器三轴的输出信息
    private float[] allValue;
    //定义传感器管理
    private SensorManager sensorManager;

    public PointerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        pointer = BitmapFactory.decodeResource(super.getResources(),
                R.drawable.pointer); //获取要绘制的指针位图
        //获取传感器管理
        sensorManager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        //为磁场传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) { //如果是磁场传感器
            float value[] = event.values; //获取磁场传感器三轴的输出信息
            allValue = value; // 保存输出信息
            super.postInvalidate(); // 刷新界面
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint(); //创建画笔
        if (allValue != null) { //传感器三轴输出信息不为空
            float x = allValue[0]; //获取x轴坐标
            float y = allValue[1]; //获取y轴坐标
            // 重置绘图对象
            canvas.restore();
            // 以屏幕中心点作为旋转中心
            canvas.translate(super.getWidth() / 2, super.getHeight() / 2);
            // 判断y轴为0时的旋转角度
            if (y == 0 && x > 0) {
                canvas.rotate(90);    // 旋转角度为90度
            } else if (y == 0 && x < 0) {
                canvas.rotate(270);    // 旋转角度为270度
            } else {
                //通过三角函数tanh()方法计算旋转角度
                if (y >= 0) {
                    canvas.rotate((float) Math.tanh(x / y) * 90);
                } else {
                    canvas.rotate(180 + (float) Math.tanh(x / y) * 90);
                }
            }
        }
        //绘制指针
        canvas.drawBitmap(this.pointer, -this.pointer.getWidth() / 2,
                -this.pointer.getHeight() / 2, p);
    }
}
/**----------------------------END：Activity:PointerView------------------------------------**/
/************************2布局文件:layout.xml********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *注意：需要添加自定义视图
 */

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context="com.mingrisoft.MainActivity">

<ImageView
android:id="@+id/background"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_gravity="center"
android:src="@drawable/background"
        />

<com.mingrisoft.PointerView
android:layout_width="wrap_content"
android:layout_height="wrap_content"/>

</FrameLayout>

/**----------------------------END：2布局文件:PointerView------------------------------------**/

```

## 地图定位

### locationjava的基本使用

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/8 0008********************************/
/**
 *功能：
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：需要在配置manifests,并且手机中允许开启app定位权限获取LocationProvider的三种方式
 *
 *方法：
 */


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {

    Message message1 = Message.obtain();       //获得消息对象
    Message message2 = Message.obtain();       //获得消息对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        注意：
            1.获取所有可用的LocationProvider
                LocationManager对象的getAllProviders()方法获取
            2.通过名称获得LocationProvider
                LocationManager对象的getProvider()方法
            3.通过Criteria类获得LocationProvider

            4.获取定位数据
         */
        //方式一 获取所有可用的LocationProvider
        //1 获取location服务
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //2 获取系统所有的定位信息LocationProvider名称（可能包含gps\passive\network）
        List<String> providersNames = locationManager.getAllProviders();
        //3 打印获取的LocationProvider的名称
        //使用StringBuilder保存数据
        //遍历获取到的全部LocationProvider名称
        //显示LocationProvider名称
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<String> iterator = providersNames.iterator(); iterator.hasNext(); ) {
            stringBuilder.append(iterator.next() + "\n");
        }
        String locationMessage = stringBuilder.toString();
        Log.i("tag标志", "方式一 获取所有可用的LocationProvider：" + locationMessage);

        //方式二 通过名称获得LocationProvider
        //获取GPS定位
        LocationProvider providerGps = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        Log.i("tag标志", "方式二 通过名称获得LocationProvider：" + providerGps.getName());

        //方式三 通过Criteria类获得LocationProvider
        Criteria criteria = new Criteria();
        //不收费的、精度最准确的、耗电量最低的
        criteria.setCostAllowed(false);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        //获取LocationProvider的名称
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Log.i("tag标志", "方式三 通过Criteria类获得LocationProvider：" + bestProvider);

        //////////////////////////////4 获取定位数据/////////////////////////////////////////////////////
        //////////////////////////////4 获取定位数据/////////////////////////////////////////////////////
        //////////////////////////////4 获取定位数据/////////////////////////////////////////////////////
        //添加权限检查
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //设置每一秒获取一次location信息
//        <!--允许使用定位-->
//        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,      //GPS定位提供者
                1000,       //更新数据时间为1秒
                1,      //位置间隔为1米
                //位置监听器
                new LocationListener() {  //GPS定位信息发生改变时触发，用于更新位置信息

                    @Override
                    public void onLocationChanged(Location location) {
                        //GPS信息发生改变时，更新位置
                        locationUpdates(location);
                    }

                    @Override
                    //位置状态发生改变时触发
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    @Override
                    //定位提供者启动时触发
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    //定位提供者关闭时触发
                    public void onProviderDisabled(String provider) {
                    }
                });
        //从GPS获取最新的定位信息
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationUpdates(location);    //将最新的定位信息传递给创建的locationUpdates()方法中
    }

    public void locationUpdates(Location location) {  //获取指定的查询信息
        //如果location不为空时
        if (location != null) {
            StringBuilder stringBuilder = new StringBuilder();        //使用StringBuilder保存数据
            //获取经度、纬度、等属性值
            stringBuilder.append("您的位置信息：\n");
            stringBuilder.append("经度：");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append("\n纬度：");
            stringBuilder.append(location.getLatitude());
//            stringBuilder.append("\n精确度：");
//            stringBuilder.append(location.getAccuracy());
//            stringBuilder.append("\n高度：");
//            stringBuilder.append(location.getAltitude());
//            stringBuilder.append("\n方向：");
//            stringBuilder.append(location.getBearing());
//            stringBuilder.append("\n速度：");
//            stringBuilder.append(location.getSpeed());
//            stringBuilder.append("\n时间：");
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH mm ss");    //设置日期时间格式
//            stringBuilder.append(dateFormat.format(new Date(location.getTime())));

            //显示获取的信息
            Log.i("tag标志", stringBuilder.toString());
        } else {
            //否则输出空信息
            Log.i("tag标志", "没有获取到GPS信息");
        }
    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/


```

### 百度地图定位java

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/8 0008********************************/
/**
 *功能：
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：
 *
 *方法：
 */
/*
        定位到我的位置的步骤：
            1.开启定位图层 setMyLocationEnabled()方法，值为true
            2.构造定位数据 MyLocationData对象
            3.设置定位数据，并配置定位图层的信息
            4.关闭定位图层 setMyLocationEnabld()方法，值为false，关闭定位图层
        开发步骤：
            1.布局界面并且显示百度地图
            2.实时获取定位信息中的精度和纬度
            3.启用定位功能标记我的位置
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
public class MainActivity extends AppCompatActivity {

    private MapView mMapView;     // 定义百度地图组件
    private BaiduMap mBaiduMap;   // 定义百度地图对象
    private LocationClient mLocationClient;  //定义LocationClient
    private BDLocationListener myListener = new MyLocationListener();  //定义位置监听
    private boolean isFirstLoc = true;  //定义第一次启动
    private MyLocationConfiguration.LocationMode mCurrentMode;  //定义当前定位模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());   //初始化地图SDK
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.bmapview);  //获取地图组件
        mBaiduMap = mMapView.getMap();  //获取百度地图对象
        mLocationClient = new LocationClient(getApplicationContext());  //创建LocationClient类
        mLocationClient.registerLocationListener(myListener);   //注册监听函数
        initLocation();  //调用initLocation()方法，实现初始化定位
    }

    private void initLocation() {  //该方法实现初始化定位
        //创建LocationClientOption对象，用于设置定位方式
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");  //设置坐标类型
        option.setScanSpan(1000);      //1秒定位一次
        option.setOpenGps(true);      //打开GPS
        mLocationClient.setLocOption(option);  //保存定位参数与信息
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;  //设置定位模式
        //设置自定义定位图标
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_geo);
        //位置构造方式，将定位模式，定义图标添加其中
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);  //地图显示定位图标
    }

    public class MyLocationListener implements BDLocationListener {  //设置定位监听器
        @Override
        public void onReceiveLocation(BDLocation location) {
            //销毁后不再处理新接收的位置
            if (location == null || mMapView == null)
                return;
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {  //如果是第一次定位,就定位到以自己为中心
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude()); //获取用户当前经纬度
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);  //更新坐标位置
                mBaiduMap.animateMapStatus(u);                            //设置地图位置
                isFirstLoc = false;                                      //取消第一次定位
            }

        }
    }


    @Override
    protected void onStart() {  //地图定位与Activity生命周期绑定
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient.start();
    }


    @Override
    protected void onStop() {  //停止地图定位
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {  //销毁地图
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}
/**----------------------------END：Activity:MainActivity------------------------------------**/
/************************2布局文件:Layout.xml********************************/
/************************制定时间：2020/3/8 0008********************************/
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:paddingBottom="@dimen/activity_vertical_margin"
android:paddingLeft="@dimen/activity_horizontal_margin"
android:paddingRight="@dimen/activity_horizontal_margin"
android:paddingTop="@dimen/activity_vertical_margin"
tools:context="com.mingrisoft.MainActivity">
<com.baidu.mapapi.map.MapView
android:id="@+id/bmapview"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:clickable="true" />
</RelativeLayout>
/**----------------------------END：2布局文件:Layout.xml------------------------------------**/

```

## actionBar

### actionBar 的全面实现(基本囊括所用常见的ActionBar的应用)

```java
package com.wanli.thefourthweek;
/************************Activity:MyActivity********************************/
/************************制定时间：2020/3/18 0018********************************/
/**
 *功能：这里基本囊括了所有常用的ActionBar的设置
 *
 *流程：这里主要分为一些功能模块来说明
 *    1.ActionBar的基本属性设置
 *    2.ActionBar的item(按钮)项逻辑处理
 *    3.跳到父Activity的ActionBar导航实现
 *    4.ActionView监听设置
 *    5.AndroidManifest的子父Activity设置
 *    //进阶内容一
 *    6.解决overflow不显示的问题
 *    7.overflow图标样式设置(不是三个点)
 *    8.设置overflow菜单栏的item可以显示图标
 *    9.ActionBar菜单资源配置
 *    //进阶内容二
 *    10.Provider菜单项设置（可以使用系统的或者是自己的）
 *    11.ActionBar的Tab导航添加
 *注意：这里的Provider菜单项设置（可以使用系统的或者是自己的）模块的问题还未解决
 */
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.SearchView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity1);
    /**【模块：ActionBar的基本属性设置】**/
//    ActionBar actionBar = getActionBar();
    //获取ActionBar
    ActionBar actionBar = getSupportActionBar();
//    actionBar.hide();
//    actionBar.show();
    setTitle("天气");
    //设置ActionBar的标题不显示
    //getSupportActionBar().setDisplayShowTitleEnabled(false);
    //开启Navigate小箭头导航功能,注意，当前测试这个方法会出现未知异常，先注释了
//    actionBar.setDisplayHomeAsUpEnabled(true);
    /**【模块结束：ActionBar的基本属性设置】**/


    /**【模块：设置ActionBar的tab导航模式设置】**/
    //设置ActionBar的tab导航模式设置
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);  //隐藏标题栏
    actionBar.addTab(actionBar.newTab().setText("按钮对话"). //将标签页添加ActionBar中
        setTabListener(new MyTabListener4_0(this, MyFragment4_0.class)));
    actionBar.addTab(actionBar.newTab().setText("列表对话"). //将标签页添加ActionBar中
        setTabListener(new MyTabListener4_0(this, MyFragment4_1.class)));
    actionBar.addTab(actionBar.newTab().setText("单选对话").////将标签页添加ActionBar中
        setTabListener(new MyTabListener4_0(this, MyFragment4_2.class)));
    actionBar.addTab(actionBar.newTab().setText("多选对话").////将标签页添加ActionBar中
        setTabListener(new MyTabListener4_0(this, MyFragment4_3.class)));
    /**【模块结束：设置ActionBar的tab导航模式设置】**/

  }

  @Override//解析菜单文件
  public boolean onCreateOptionsMenu(Menu menu) {

    //MenuInflater的实例对象用于解析菜单资源文件的
    //进行解析
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu,menu);

    /**【模块：Provider菜单项设置】**/
//    MenuItem shareItem = menu.findItem(R.id.action_share);
//    ShareActionProvider provider = (ShareActionProvider) shareItem.getActionProvider();
//    provider.setShareIntent(getDefaultIntent());
//    private Intent getDefaultIntent() {
//      Intent intent = new Intent(Intent.ACTION_SEND);
//      intent.setType("image/*");
//      return intent;
//    }
    /**【模块结束：Provider菜单项设置】**/

    /**【模块：ActionView监听设置】**/
    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) searchItem.getActionView();
    //ActionView展开和合并的监听
    MenuItemCompat.setOnActionExpandListener(searchItem, new OnActionExpandListener() {
      @Override
      public boolean onMenuItemActionExpand(MenuItem item) {
        Log.d("TAG", "on expand");
        return true;
      }

      @Override
      public boolean onMenuItemActionCollapse(MenuItem item) {
        Log.d("TAG", "on collapse");
        return true;
      }
    });
/*    //在不同版本需要通过不同的方法进行兼容
      searchItem.setOnActionExpandListener(new OnActionExpandListener() {
      @Override
      public boolean onMenuItemActionExpand(MenuItem item) {
        Log.d("TAG", "on expand");
        return true;
      }

      @Override
      public boolean onMenuItemActionCollapse(MenuItem item) {
        Log.d("TAG", "on collapse");
        return true;
      }
     });*/
    /**【模块结束：ActionView监听设置】**/
    return super.onCreateOptionsMenu(menu);
}

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

      /**【模块：跳到父Activity的ActionBar导航实现】**/
      //说明：ActionBar导航箭头独有ID，为了实现和返回键不同的功能，也就是跳到父Activity，编码如下
      case android.R.id.home:
        //1.NavUtils.getParentActivityIntent()方法可以获取到跳转至父Activity的Intent
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        //2.如果父Activity和当前Activity是在同一个Task中的，则直接调用navigateUpTo()方法进行跳转
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
          TaskStackBuilder.create(this)
              .addNextIntentWithParentStack(upIntent)
              .startActivities();
        } else {
          //3.如果不是在同一个Task中的，则需要借助TaskStackBuilder来创建一个新的Task。
          upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          NavUtils.navigateUpTo(this, upIntent);
        }
        //实现方式二
        //判断父Activity是否存在，也就是通过是否为空来判断；
        // 如果不为空，也就是父Activity存在那么就设置向左的图标
        //这个图标也就是我们所说的ActionBar的层级导航
//        if (NavUtils.getParentActivityName(FriendsActivity.this) != null) {
//          //显示向左的箭头图标
//          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
        return true;
      /**【模块结束：跳到父Activity的ActionBar导航实现】**/

      /**【模块：ActionBar的item(按钮)项逻辑处理】**/
      case R.id.action_compose:
        Toast.makeText(this, "Compose", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.action_delete:
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.action_settings:
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        return true;
      /**【模块结束：ActionBar的item(按钮)项逻辑处理】**/
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  /**【模块：设置overflow菜单栏的item可以显示图标】**/
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    if (menu != null) {
      if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
        try {
          //MenuBuilder这个类的setOptionalIconsVisible方法来决定是否overflow的item可以显示图标，默认下是false，现在我们设置其为true
          Method m = menu.getClass().getDeclaredMethod(
              "setOptionalIconsVisible", Boolean.TYPE);
          m.setAccessible(true);
          m.invoke(menu, true);
        } catch (Exception e) {
          Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
        }
      }
    }
    return super.onPrepareOptionsMenu(menu);
  }
  /**【模块结束：设置overflow菜单栏的item可以显示图标】**/

  /**【模块：解决overflow不显示的问题】**/
  //由于谷歌的奇怪设定，造成了有物理menu按钮的手机会出现这一问题
  private void setOverflowShowingAlways() {
    //需要在OnCreate中调用这个方法
//    setOverflowShowingAlways();
    try {
      ViewConfiguration config = ViewConfiguration.get(this);
      Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
      menuKeyField.setAccessible(true);
      menuKeyField.setBoolean(config, false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**【模块结束：解决overflow不显示的问题】**/

}
/**【模块：overflow图标样式设置(不是三个点)】**/
/*
<resources>
    <!--
        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    -->
    <style name="AppBaseTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- 在style资源文件中进行修改 -->
        <item name="android:actionOverflowButtonStyle">@style/OverflowStyle</item>
    </style>
    <style name="OverflowStyle">
        <item name="android:src">@drawable/actionbar_add_icon</item>
    </style>
</resources>
*/
/**【模块结束：overflow图标样式设置(不是三个点)】**/
/**----------------------------END：Activity:MyActivity------------------------------------**/

/************************AndroidManifest:Activity********************************/
/************************制定时间：2020/3/18 0018********************************/
/**
 *功能：ActionBar
 */
/*
//为了实现ActionBar的返回导航需要设置其父Activity，下面演示了如何设置其父Activity
<activity
    android:name="com.example.actionbartest.MainActivity"//设置主题
    android:label="天气"//设置名称
    android:logo="@drawable/weather"//设置图标 >
    <meta-data
        android:name="android.support.PARENT_ACTIVITY"
        android:value="com.example.actionbartest.指定其父Activity" />
</activity>
 */
/**----------------------------END：AndroidManifest:Activity------------------------------------**/
/************************资源文件:menu********************************/
/************************制定时间：2020/3/18 0018********************************/
/**
 *功能：ActionBar的菜单资源
 */
/*
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  >

  <!--
  一、这边是设置普通ActionBar的按钮
  app:showAsAction="always" 设置，注意其前缀有点特殊
  1.aways 表示这个菜单项始终显示在ActionBar上
  2.collapseActionView
  3.ifRoom 表示如果ActionBar上有显示空间的话这个item就显示在ActionBar上
  4.never 表示这个item永远都不显示在ActionBar上
  5.withText 指定item必须显示到ActionBar上并且显示这个item的文本，但是如果空间不足会导致看不到这个item
          -->
  <item
    android:id="@+id/action_compose"
    android:icon="@drawable/pic1"
    app:showAsAction="always"
    android:title="@string/item" />
  <item
    android:id="@+id/action_delete"
    android:icon="@drawable/pic1"
    app:showAsAction="ifRoom"
    android:title="@string/item" />
  <item
    android:id="@+id/action_settings"
    android:icon="@drawable/pic1"
    app:showAsAction="never"
    android:title="@string/item" />
  <!--ActionView-->

  <!--
      二、ActionView的菜单项配置
      app:actionViewClass="android.support.v7.widget.SerchView"
      这个属性需要使用一个actionview的实现类来实现
      app:action-layout="@layout/img_message"
      这个属性需要指定一个布局文件
      -->
  <item
    android:id="@+id/action_search"
    app:actionViewClass="android.widget.SearchView"
    android:icon="@drawable/pic1"
    app:showAsAction="ifRoom|collapseActionView"
    android:title="搜索框" />
  <!--下面这个放大镜图标，可以实现单机弹出搜索框-->
  <item android:id="@+id/搜索图标"
    android:title="搜索放大镜"
    app:actionViewClass="android.widget.SearchView"
    app:showAsAction="never"></item>
  <!-- app:action-layout="@+layout/menu_image"
      这个属性加不上去，扎偶出问题所在
      -->
  <item android:id="@+id/通讯录"
    android:title="通讯录"
    app:showAsAction="ifRoom"></item>

  <item android:id="@+id/扫一扫"
    android:title="扫一扫"
    app:showAsAction="ifRoom"></item>
</menu>


 */

/**----------------------------END：资源文件:menu------------------------------------**/
/************************TabListener:MyTabListener4_0********************************/
/************************制定时间：2020/3/18 0018********************************/
/**
 *功能：实现ActionBar的Tab导航监听
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
public class MyTabListener4_0<T extends Fragment> implements ActionBar.TabListener {
  //定义Fragment
  //定义Activity
  //定义Class
  private Fragment mFragment;
  private final Activity mActivity;
  private final Class<T> mClass;
  private  String mTag;

  public MyTabListener4_0(Activity activity, Class<T> aClass) {
    //添加构造函数
    mActivity = activity;
    mClass = aClass;
  }

  public MyTabListener4_0(Activity activity, String tag, Class<T> clz) {
    this(activity,clz);
    mTag=tag;
  }
  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    //判断碎片是否初始化
    //如果没有初始化，将其初始化
    if (mFragment == null) {
      mFragment = Fragment.instantiate(mActivity, mClass.getName());
      ft.add(android.R.id.content/*安卓给我们提供的*/, mFragment/*我们需要放入的fragment对象*/, null);
    } else {
      //显示新画面
      ft.attach(mFragment);
    }
  }

  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    if (mFragment  != null) {
      //删除旧画面
      ft.detach(mFragment );
    }
  }

  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

  }
}
/**----------------------------END：TabListener:MyTabListener4_0------------------------------------**/
/************************资源文件:Style资源********************************/
/************************制定时间：2020/3/18 0018********************************/
/**
 *功能：配置ActionBar的基本常用样式
 *注意：可以通过子父继承进行优化
 */
/*
<resources>

<!-- Base application theme. -->
<style name="AppTheme" parent="@android:style/Widget.Holo.Light.ActionBar">
<!--指定overflow溢出菜单的样式-->
<item name="android:actionOverflowButtonStyle">@style/OverflowStyle</item>
<!--指定ActionBar和Tab的背景样式-->
<item name="android:actionBarStyle">@style/MyActionBar</item>
<!--指定ActionBar标题和Tab标题样式(附加Tab状态变化改变样式)-->
<item name="android:titleTextStyle">@style/MyActionBarTitleText</item>
<item name="android:actionBarTabTextStyle">@style/MyActionBarTabText</item>
</style>

<!--指定overflow溢出菜单的样式-->
<style name="OverflowStyle">
<item name="android:src">@drawable/pic1</item>
</style>

<!--指定ActionBar标题和Tab标题样式-->
<style name="MyActionBar" parent="@android:style/Widget.Holo.Light.ActionBar">
<item name="android:background">#f4842d</item><!--ActionBar背景样式-->
<item name="android:backgroundStacked">#d27026</item><!--Tab的背景样式-->
</style>

<!--指定ActionBar标题和Tab标题样式-->
<style name="MyActionBarTitleText" parent="@android:style/TextAppearance.Holo.Widget.ActionBar.Title">
<item name="android:textColor">#fff</item>
</style>
<style name="MyActionBarTabText" parent="@android:style/Widget.Holo.ActionBar.TabText">
<item name="android:textColor">#fff</item>
<!--状态变化样式-->
<item name="android:background">@drawable/actionbar_tab_indicator</item>
</style>
<!--
    状态文件(drawable目录下)：actionbar_tab_indicator.xml文件

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

<item  android:state_selected="false"
    android:state_pressed="false"
    android:drawable="@drawable/tab_unselected" />
<item android:state_selected="true"
    android:state_pressed="false"
    android:drawable="@drawable/tab_selected" />
<item android:state_selected="false"
    android:state_pressed="true"
    android:drawable="@drawable/tab_unselected_pressed" />
<item android:state_selected="true"
    android:state_pressed="true"
    android:drawable="@drawable/tab_selected_pressed" />
</selector>-->

</resources>
*/
/**----------------------------END：资源文件:Style资源------------------------------------**/

```

## Intent

### intent Action&&Category&&Date配置串烧

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：Intent的Action&&Category属性配合使用
 *
 *流程：
 *    1.创建Intent对象
 *    2.设置Action属性
 *    3.设置Category属性
 *    4.实现Intent调用系统的Activity资源
 *注意：案例中的Action属性通常需要Category或者data属性的配合
 */
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
/**----------------------------END：Activity:MainActivity------------------------------------**/

```

### intentxml过滤器Filter（

```java

<!--依次是设置action的过滤属性、设置category的过滤属性,设置的属性不会被过滤-->
<!--注意：通常配合action&&category或者action&&date属性来使用-->
<!--前者表示这个Activity用来指示数据的，后者表示这个Activity作为执行数据的默认动作-->
<action android:name="android.intent.action.VIEW"></action>
<category android:name="android.intent.category.DEFAULT"></category>
//表示应用程序入口
 <intent-filter>
     <action android:name="android.intent.action.MAIN" />
     <category android:name="android.intent.category.LAUNCHER" />
 </intent-filter>
 /*
 它声明了活动可以在notes目录上执行的操作。所支持的类型由<type>标记提供，
 其中vnd.android.cursor.dir/vnd.google。note是一个URI，可以从中检索到包
 含零个或多个条目的游标(vnd.android.cursor.dir)，该URI保存我们的记事本数
 据(vnd.google.note)。该活动允许用户查看或编辑数据目录(通过查看和编辑操
 作)，或者选择一个特定的通知并将其返回给调用者(通过选择操作)。还要注意这
 里提供的默认类别:这是上下文需要的。方法，以在未显式指定其组件名称时解析活动。
 */
 <intent-filter>
     <action android:name="android.intent.action.VIEW" />//表示可以查看
     <action android:name="android.intent.action.EDIT" />//表示可以编辑
     <action android:name="android.intent.action.PICK" />//表示提供选择
     <category android:name="android.intent.category.DEFAULT" />//提供的默认类别:这是上下文需要的
     <data android:mimeType="vnd.android.cursor.dir/vnd.google.note" />
 </intent-filter>

/*
此筛选器描述将用户选择的通知返回给调用者的能力，而不需要知道它来自何处。数据类型为vnd.android.cursor.item/vnd.google。note是一个URI，可以从中检索到一个包含我们的记事本数据的游标(vnd.android.cursor.item)。GET_CONTENT操作类似于PICK操作，其中活动将向其调用者返回用户选择的一段数据。然而，在这里，调用者指定他们想要的数据类型，而不是用户将从中选择的数据类型。
*/
 <intent-filter>
     <action android:name="android.intent.action.GET_CONTENT" />
     <category android:name="android.intent.category.DEFAULT" />
     <data android:mimeType="vnd.android.cursor.item/vnd.google.note" />
 </intent-filter>

<action android:name="android.intent.action.VIEW" />//查看
<action android:name="android.intent.action.EDIT" />//编辑
<action android:name="android.intent.action.PICK" />//选择
<action android:name="android.intent.action.GET_CONTENT" />//获取内容
<action android:name="android.intent.action.MAIN" />//表示应用主程序入口
<action android:name="android.intent.action.INSERT" />//插入新的数据
<category android:name="android.intent.category.DEFAULT" />//提供默认表示是上下文需要的
<category android:name="android.intent.category.LAUNCHER" />//罗列，通常用于应用程序入口的配置
<category android:name="android.intent.category.ALTERNATIVE" />
<category android:name="android.intent.category.SELECTED_ALTERNATIVE" />
<action android:name="com.android.notepad.action.EDIT_TITLE" />
<data android:mimeType="vnd.android.cursor.item/vnd.google.note" />//检索到一个包含我们的记事本数据的游标
<data android:mimeType="vnd.android.cursor.dir/vnd.google.note" />//该URI保存我们的记事本数据(vnd.google.note)。


/************************Standard Activity Actions********************************/
这些是Intent为启动活动(通常通过Context.startActivity(Intent))定义的当前标准动作。最重要的，也是使用最频繁的是ACTION_MAIN和ACTION_EDIT。
ACTION_MAIN  作为主要入口点，仅仅就是进入目标Activity
ACTION_VIEW  展示数据
ACTION_ATTACH_DATA 用来表示某段数据应该附加到其他地方。例如，可以将图像数据附加到联系人。由收件人决定应将资料夹附于何处;意图没有指定最终目的地。
    Input: getData() is URI of data to be attached. 返回Uri
ACTION_EDIT 编辑数据
ACTION_PICK 选择数据
ACTION_CHOOSER
ACTION_GET_CONTENT
ACTION_DIAL  直接操作活动，比如直接指定一个号码拨出去
ACTION_CALL
ACTION_SEND
ACTION_SENDTO
ACTION_ANSWER
ACTION_INSERT
ACTION_DELETE
ACTION_RUN
ACTION_SYNC
ACTION_PICK_ACTIVITY  根据给定的意图选择一个活动，返回所选的类。
ACTION_SEARCH
ACTION_WEB_SEARCH
ACTION_FACTORY_TEST
/************************Standard Broadcast Actions********************************/
这些是Intent为接收广播(通常通过上下文)定义的当前标准操作。registerReceiver(BroadcastReceiver、IntentFilter)或清单中的<receiver>标记)。

ACTION_TIME_TICK 每分钟发送一次，您不能通过清单中声明的组件接收此消息，只能通过显式地向Context.registerReceiver()注册它。
ACTION_TIME_CHANGED
ACTION_TIMEZONE_CHANGED
ACTION_BOOT_COMPLETED
ACTION_PACKAGE_ADDED
ACTION_PACKAGE_CHANGED
ACTION_PACKAGE_REMOVED
ACTION_PACKAGE_RESTARTED
ACTION_PACKAGE_DATA_CLEARED
ACTION_PACKAGES_SUSPENDED
ACTION_PACKAGES_UNSUSPENDED
ACTION_UID_REMOVED
ACTION_BATTERY_CHANGED
ACTION_POWER_CONNECTED
ACTION_POWER_DISCONNECTED
ACTION_SHUTDOWN
/************************Standard Categories********************************/
CATEGORY_DEFAULT  设置该活动是否应该是默认操作(中心按下)在数据块上执行的选项。设置此设置将对用户隐藏任何活动，而在对某些数据执行操作时不设置此设置。请注意，当启动一个动作时，通常在意图中不设置它——它用于包中指定的意图过滤器中。
CATEGORY_BROWSABLE
CATEGORY_TAB
CATEGORY_ALTERNATIVE
CATEGORY_SELECTED_ALTERNATIVE  设置该活动是否应被视为用户当前选择的数据的替代选择操作。
CATEGORY_LAUNCHER 应该显示在顶级启动程序中
CATEGORY_INFO 提供有关其所在包的信息;通常情况下，如果一个包不包含一个CATEGORY_LAUNCHER来为用户提供一个前门，而不必显示在所有应用程序列表中，那么就会使用此选项。
CATEGORY_HOME 指向这是home活动，这是设备启动时显示的第一个活动。
CATEGORY_PREFERENCE
CATEGORY_TEST
CATEGORY_CAR_DOCK
CATEGORY_DESK_DOCK
CATEGORY_LE_DESK_DOCK
CATEGORY_HE_DESK_DOCK
CATEGORY_CAR_MODE
CATEGORY_APP_MARKET
CATEGORY_VR_HOME
/************************Standard Extra Data********************************/
EXTRA_ALARM_COUNT
EXTRA_BCC
EXTRA_CC
EXTRA_CHANGED_COMPONENT_NAME
EXTRA_DATA_REMOVED
EXTRA_DOCK_STATE
EXTRA_DOCK_STATE_HE_DESK
EXTRA_DOCK_STATE_LE_DESK
EXTRA_DOCK_STATE_CAR
EXTRA_DOCK_STATE_DESK
EXTRA_DOCK_STATE_UNDOCKED
EXTRA_DONT_KILL_APP
EXTRA_EMAIL
EXTRA_INITIAL_INTENTS
EXTRA_INTENT
EXTRA_KEY_EVENT
EXTRA_ORIGINATING_URI
EXTRA_PHONE_NUMBER
EXTRA_REFERRER
EXTRA_REMOTE_INTENT_TOKEN
EXTRA_REPLACING
EXTRA_SHORTCUT_ICON
EXTRA_SHORTCUT_ICON_RESOURCE
EXTRA_SHORTCUT_INTENT
EXTRA_STREAM
EXTRA_SHORTCUT_NAME
EXTRA_SUBJECT
EXTRA_TEMPLATE
EXTRA_TEXT
EXTRA_TITLE
EXTRA_UID
```

## fragment

### fragment 全面解析&&最佳实践

```java
/*

生命周期：
onAttach：onAttach()在fragment与Activity关联之后调调查用。
onCreate：fragment初次创建时调用。尽管它看起来像是Activity的OnCreate()函数，但这个只是用来创建Fragment的。此时的Activity还没有创建完成，因为我们的Fragment也是Activity创建的一部分。所以如果你想在这里使用Activity中的一些资源，将会获取不到。比如：获取同一个Activity中其它Frament的控件实例。(代码如下：)，如果想要获得Activity相关联的资源，必须在onActivityCreated中获取。
onActivityCreated：在Activity的OnCreate()结束后，会调用此方法。所以到这里的时候，Activity已经创建完成！在这个函数中才可以使用Activity的所有资源。如果把下面的代码放在这里，获取到的btn_Try的值将不会再是空的！
onDestroy：当这个fragment不再使用时调用。需要注意的是，它即使经过了onDestroy()阶段，但仍然能从Activity中找到，因为它还没有Detach。
onDetach：Fragment生命周期中最后一个回调是onDetach()。调用它以后，Fragment就不再与Activity相绑定，它也不再拥有视图层次结构，它的所有资源都将被释放。
 */
/*
常用方法：

//一、开启事务管理
//注意：一个FragmentManager对象掌管着一个堆栈，多个的话就维护各自维护各自的一个堆栈
final FragmentManager manager = getSupportFragmentManager();
FragmentTransaction transaction = manager.beginTransaction();

//二、获取fragment对象
Fragment fragment = manager.findFragmentByTag("fragment2");
Fragment fragment = manager.findFragmentById(R.id.fragment);

//三、fragment的增删改
//注意：推荐 hide() add() 方法的配置使用，比较节约性能
//不希望保留用户数据用下面的操作
ft.remove(fragment);
ft.add(fragment);
ft.replace(fragment);//比较耗费资源，因为会把container中的fragment实例清空，不推荐
//希望保留用户编辑数据使用下面的操作
ft.hide(fragment);
ft.show(fragment);
//remove会销毁整个Fragment实例，而detach只是销毁视图结构，示例并不会被销毁
//如果当前Activity一直存在，那么在不希望保留用户操作的时候，你可以使用detach
ft.detach(fragment);//将view从UI中移除，但是fragment状态依然由FragmentManager维护，也就是依旧存在这个实例
ft.attach(fragment);//重建view视图

//四、提交操作，返回一个int值，表示提交后的栈中深度，从0开始
//注意：这个的使用容易出现异常，每个布局中需要将FragmentManager声明为全局变量才可以多次执行commit()方法
//原因：在一个布局文件中每次只能提交一次commit()，防止其多次提交导致状态改变
transaction.commit();

//五、压栈操作，可以指定tag
transaction.addToBackStack(String tag);

//六、出栈操作（popBackStackImmediate方法执行优先级更高），参数介绍
//id 表示commit()方法返回值，也就是提交后当前的栈中元素数量，从0开始
//flag 有两个值。
  //0 表示除了参数一指定这一层之上的所有层都退出栈，指定的这一层为栈顶层；
  //FragmentManager.POP_BACK_STACK_INCLUSIVE 表示连着参数一指定的这一层一起退出栈；
void popBackStack(int id, int flags);
void popBackStack(String name, int flags);
popBackStackImmediate()
popBackStackImmediate(String tag)
popBackStackImmediate(String tag, int flag)
popBackStackImmediate(int id, int flag)

//七、可已设置回退栈的回退监听 代码实现如下
//注意：1.需要在结束时关闭监听（Fragment的生命周期中通常在onDestory()中将监听器remove掉），否者会出现OOM问题；2.不同的Fragment包设置监听方式有微小差异
    fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {
      @Override
      public void onBackStackChanged() {
        Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
      }
    });
    manager.removeOnBackStackChangedListener(listener);//关闭监听
 */

/************************Activity:MyActivity1********************************/
/************************制定时间：2020/3/12 0012********************************/
/**
 *说明：fragment的比较详细的使用要点
 *
 *  1.基本使用方法（静态添加、动态添加、生命周期）
 *  2.管理Fragment（就是堆栈事务的管理，回滚监听的设置）
 *     注意；这里主要关注其中的方法的使用
 *  3.Fragment之间的参数传递
 *    1.方式一：通过在fragment类中定义接口
 *      我们可以在Activity中通过接口来获取fragment中的任意组件或者布局管理器从而进行布局。
 *    2.方式二：通过fragment实例进行参数传递
 *      可以获取fragment中定义的可见参数
 *  5.如何设置类似于Activity中回退onPressBack的效果？
 *    说明：由于fragment的实质就是一个组件，所以其是没有onPressBack方法的，具体实现还是需要通过fragment的生命周期来间接实现，可能需要借助内部接口类
 *  6.实现replace后fragment的状态保存
 *    1.方式一(不推荐)：把控件的属性在某个生命周期中保存到变量中，因为replace方法销毁的仅仅只是控件，但是其中的变量值是不会删除的
 *    2.方式二(不推荐)：为你需要保存状态的控件添加ID值
 *    3.方式三(推荐，代码如下)：在fragment创建视图的时候就直接保存整个视图View，然后下次调用时返回。实质上是方式一的强化版。
   *   private View rootView;
   *   public View onCreateView(LayoutInflater inflater, ViewGroup container,
   *       Bundle savedInstanceState) {
   *     return getPersistentView(inflater, container, savedInstanceState, R.layout.fragment1);
   *   }
   *   public View getPersistentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int layout) {
   *     if (rootView == null) {
   *       // Inflate the layout for this fragment
   *       rootView = inflater.inflate(layout, container,false);
   *     } else {
   *       ((ViewGroup) rootView.getParent()).removeView(rootView);
   *     }
   *     return rootView;
   *   }
 *   注意：因为要考虑Fragment的重复使用，所以必须降低Fragment与Activity的耦合，而且Fragment更不应该直接操作别的Fragment，毕竟Fragment操作应该由它的管理者Activity来决定。
 */

import MyFragment1.FOneBtnClickListener;
import MyFragment2.FTwoBtnClickListener;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;


public class MyActivity1 extends Activity implements FOneBtnClickListener, FTwoBtnClickListener {

  private MyFragment1 fragment1;
  private MyFragment2 fragment2;
  private MyFragment3 fragment3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_my1);

    fragment1 = new MyFragment1();
    FragmentManager fm = getFragmentManager();
    FragmentTransaction tx = fm.beginTransaction();
    tx.add(R.id.fragmentLayout, fragment1, "ONE");
    tx.commit();
  }

  /**
   * fragment解耦方式一
   */
  @Override
  public void onFOneBtnClick() {

    if (fragment2 == null) {
      fragment2 = new MyFragment2();
      //为什么可以将this作为参数传入，因为可以将this自动转换为其继承的接口的父类型
      fragment2.setfTwoBtnClickListener(this);
    }
    FragmentManager fm = getFragmentManager();
    FragmentTransaction tx = fm.beginTransaction();
    tx.replace(R.id.fragmentLayout, fragment2, "TWO");
    tx.addToBackStack(null);
    tx.commit();
  }

  /**
   * fragment解耦方式二
   */
  @Override
  public void onFTwoBtnClick() {
    if (fragment3 == null) {
      fragment3 = new MyFragment3();
    }
    FragmentManager fm = getFragmentManager();
    FragmentTransaction tx = fm.beginTransaction();
    tx.hide(fragment2);
    tx.add(R.id.fragmentLayout, fragment3, "THREE");
//         tx.replace(R.id.fragmentLayout, fragment3, "THREE");
    tx.addToBackStack(null);
    tx.commit();
  }
}
/**----------------------------END：Activity:MyActivity1------------------------------------**/

/************************Fragment:MyFragment1********************************/
/************************制定时间：2020/3/12 0012********************************/
/**
 *功能：单独的Fragment模块实现解耦并且可以注册监听
 *
 *流程：
 * 回调解耦逻辑，方式一
 *  1.宿主Activity继承fragment中内部接口
 *  2.宿主实现内部接口的方法
 *  3.Fragment注册监听
 *  4.监听中调用宿主的接口方法实现
 * 说明：1.为什么选择嵌套内部接口而不是内部类呢？
 *  因为接口中强制其宿主实现其中的方法，这样才使得宿主的继承有意义，这样也
 *  无形中规范了我们的代码，减少bug的出现。
 *       2.解耦解了什么？
 *  fragment中的事件处理不需要依赖于对其他fragment类的获取，直接在宿主中处理
 *注意：注意解耦细节
 */

import android.app.Fragment;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.view.ViewGroup;
    import android.widget.Button;

/**

 */
public class MyFragment1 extends Fragment implements OnClickListener {
  private Button mBtn;
  public interface FOneBtnClickListener {
    void onFOneBtnClick();
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_my_fragment1, container, false);
    mBtn = (Button) view.findViewById(R.id.button1);
    mBtn.setOnClickListener(this);
    return view;
  }
  /**
   * 交给宿主Activity处理，如果它希望处理
   */
  @Override
  public void onClick(View v) {
    //如果宿主Activity实现了这个内部接口，那么就表示我们的宿主重写了处理这个单击事件
    //宿主重写了onFOneBtnClick表示对单击事件的处理
    if (getActivity() instanceof FOneBtnClickListener) {
      ((FOneBtnClickListener) getActivity()).onFOneBtnClick();
    }
  }
}
/**----------------------------END：Fragment:MyFragment1------------------------------------**/

package com.wanli.demo4;
/************************Fragment:MyFragment2********************************/
/************************制定时间：2020/3/12 0012********************************/
/**
 *功能：Fragment模块解耦方式二
 *
 * 回调解耦方式二：相对于方式一更加繁琐
 *  这里就说说和方式一有什么区别：需要定义接口的类变量，不需要进行强制转换
 */

    import android.app.Fragment;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.view.ViewGroup;
    import android.widget.Button;
public class MyFragment2 extends Fragment implements OnClickListener {
  /**
   * 获取fragment中的组件，这个组件通过
   */

  private Button mBtn;

  private FTwoBtnClickListener fTwoBtnClickListener;

  public interface FTwoBtnClickListener {
    void onFTwoBtnClick();
  }

  //设置回调接口
  public void setfTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener) {
    this.fTwoBtnClickListener = fTwoBtnClickListener;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_my_fragment2, container, false);
    mBtn = (Button) view.findViewById(R.id.button2);
    mBtn.setOnClickListener(this);
    return view;
  }
  @Override
  public void onClick(View v) {
    if (fTwoBtnClickListener != null) {
      fTwoBtnClickListener.onFTwoBtnClick();
    }
  }
}
/**----------------------------END：Fragment:MyFragment2------------------------------------**/
<!--android:name="com.wanli.demo.DetailFragment"：指定java类类路径-->
<fragment
android:id="@+id/detialFragment"
android:layout_width="wrap_content"
android:layout_height="match_parent"
android:name="com.wanli.demo.fragment类名称指定"
android:layout_weight="1">
</fragment>
```

## Activity

### activity Activity接收返回的数据

```java
/************************Activity:$ClassName$********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：Activity接收返回的数据
 *
 *流程：
 *    1.创建Intent对象在单击事件中发送数据请求
 *    2.发送数据请求的时候会打开Intent指定的目的地
 *    3.重写接收返回数据的方法onActivityResult()
 *注意：这里注意Intent的方法使用；注意另外一个Activity中的数据处理方式
 */
        //获取选择头像按钮
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            //为按钮创建单机事件
            @Override
            public void onClick(View v) {
                //创建Intent对象
                Intent intent=new Intent($ClassName$.this,$Main2Activity$.class);
                //直接打开对应的Activity
                startActivityForResult(intent, 0x11);
            }
        });
/************************方法段:onActivityResult()接收返回数据，在Intent使用startActivityForResult(intent, 0x11);的方法的时候使用********************************/
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //根据requestCode&&resultCode两个标志位一起判断如何处理返回的数据
            if(requestCode==0x11 && resultCode==$0x12$){
                //获取传递的数据包，其中带着Int数据
                Bundle bundle=data.getExtras();
                //获取选择的头像ID
                int $ImageArray$=bundle.getInt("$ImageArray$");
                //获取布局文件中添加的ImageView组件
                ImageView iv=(ImageView)findViewById(R.id.imageView);
                //显示选择的头像
                iv.setImageResource($ImageArray$);
            }
/**----------------------------END：Activity:$ClassName$------------------------------------**/
/************************Activity:$Main2Activity$********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：接收其他Activity的数据请求，然后返回数据
 *
 *流程：
 *    1.布局中添加GridView，并且添加适配器
 *    2.创建Intent，绑定选中的视图的id数据
 *    3.最后需要关闭Activity finishu()
 *注意：这里需要注意resultCode的设置；还有这里的适配器的设置比较繁琐，可以
 *  优化一下为xml配合适配器的配置。
 */

            import android.content.Intent;
            import android.os.Bundle;
            import android.support.design.widget.FloatingActionButton;
            import android.support.design.widget.Snackbar;
            import android.support.v7.app.AppCompatActivity;
            import android.support.v7.widget.Toolbar;
            import android.view.View;
            import android.view.ViewGroup;
            import android.widget.AdapterView;
            import android.widget.BaseAdapter;
            import android.widget.GridView;
            import android.widget.ImageView;
            import android.widget.TextView;
            import android.widget.Toast;

            public class $Main2Activity$ extends AppCompatActivity {
                public int[] $ImageArray$ = new int[]{R.drawable.a1, R.drawable.a2,
                        R.drawable.a3, R.drawable.a4
                };   // 定义并初始化保存头像id的数组

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main2);
                    GridView gridview = (GridView) findViewById(R.id.gridView); // 获取GridView组件
                    BaseAdapter adapter=new BaseAdapter() {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            ImageView imageview;//声明ImageView的对象
                            if(convertView==null){
                                imageview=new ImageView($Main2Activity$.this);//实例化ImageView的对象
                                //////////////设置图像的宽度和高度///////////////////
                                imageview.setAdjustViewBounds(true);
                                imageview.setMaxWidth(158);
                                imageview.setMaxHeight(150);
                                ////////////////////////////////////////////////////
                                imageview.setPadding(5, 5, 5, 5);//设置ImageView的内边距
                            }else{
                                imageview=(ImageView)convertView;
                            }
                            imageview.setImageResource($ImageArray$[position]);//为ImageView设置要显示的图片
                            return imageview;//返回ImageView
                        }
                        // 功能：获得当前选项的ID
                        @Override
                        public long getItemId(int position) {
                            return position;
                        }
                        //功能：获得当前选项
                        @Override
                        public Object getItem(int position) {
                            return position;
                        }
                        //获得数量
                        @Override
                        public int getCount() {
                            return $ImageArray$.length;
                        }
                    };

                    // 将适配器与GridView关联
                    gridview.setAdapter(adapter);
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = getIntent();    //获取Intent对象
                            Bundle bundle = new Bundle();    //实例化要传递的数据包
                            bundle.putInt("$ImageArray$", $ImageArray$[position]);// 显示选中的图片
                            intent.putExtras(bundle);    //将数据包保存到intent中
                            setResult($0x12$, intent);    //设置返回的结果码，并返回调用该Activity的Activity
                            finish();        //关闭当前Activity
                        }
                    });
                }
            }
/**----------------------------END：Activity:$Main2Activity$------------------------------------**/



        }
    }


```

### activity onSaveInstanceState保存状态

```java
package com.example.studyforapi;

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
/************************Activity:MyActivity1********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：实现屏幕旋转时数据的保存
 *
 *流程：
 *    1.onSaveInstanceState的方法实现，直接使用其中的Bundle作为状态数据容器
 *    2.onRestoreInstanceState的方法实现，直接使用其中的Bundle作为获取状态数据的容器
 *    3.onCreate的savedInstanceState作为获取状态的容器
 *注意：注意一下Back键销毁的情况下并不会保存这些状态数据
 *
 *方法：
 */
public class MyActivity1 extends Activity {

  private static final String TAG = "Activity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_one);
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

  //系统onRestoreInstanceState()只有在存在保存状态的情况下才会恢复，因此您不需要检查是否Bundle为空
  public void onRestoreInstanceState(Bundle savedInstanceState) {
    // 总是调用超类，以便它可以恢复视图层次超级
    super.onRestoreInstanceState(savedInstanceState);
    // 从已保存的实例中恢复状态成员
    String string = savedInstanceState.getString(TAG);
    Log.i(TAG, "通过onRestoreInstanceState获取屏幕旋转之前状态："+string);
  }
}
/**----------------------------END：Activity:MyActivity1------------------------------------**/

```

### activity 数据传输

```java
/************************Activity:数据传输********************************/
/************************制定时间：2020/3/21 0021********************************/
/*
 *  关闭方法
 *  finish();
 *  刷新方法
 *  onCreate(null);
 */
//一、发送者
//设置传送目标Activity
//        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//        //创建Bundle对象存数据
//        Bundle bundle = new Bundle();
//        //bundle保存邮箱键值对
//        bundle.putCharSequence(,);
//        //Intent对象中添加Bundle对象
//        intent.putExtras(bundle);
//        //转到目标Activity
//        startActivity(intent);
//二、接收者
//        //获取Intent对象
//        Intent intent = getIntent();
//        //intent对象获取传递的Bundle信息 intent.getExtras()
//        Bundle bundle = intent.getExtras();
//        //获取信息 bundle.getString("email")
//        Toast.makeText(Main2Activity.this, bundle.getString(), Toast.LENGTH_SHORT).show();
/**----------------------------END：Activity:数据传输------------------------------------**/
```

## Service

### ServiceBound的IBind的实现

```java
/************************Service:LocalService********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：绑定服务的Binder类实现方式
 *流程：
 *  1.定义内部类LocalBinder实现与外界交互
 *  2.生命周期的代码编写。onCreate()、onBind、onUnBind、onDestroy。
 *  3.公共方法的书写，通常用于数据交互
 *注意：生命周期方法的调用依次为onCreate()、onBind、onUnBind、onDestroy。
 */
public class LocalService extends Service{
  private final static String TAG = "wzj";
  private int count;
  private boolean quit;

  //固定：定义这两个字段
  private Thread thread;
  private LocalBinder binder = new LocalBinder();

  //步骤一：创建类继承Binder，并且提供获取当前Service类对象的方法
  public class LocalBinder extends Binder {
    // 声明一个方法，getService。（提供给客户端调用）
    LocalService getService() {
      // 返回当前对象LocalService,这样我们就可在客户端端调用Service的公共方法了
      return LocalService.this;
    }
  }

  //步骤二：生命周期方法的书写，主要在onCreate中编码
  @Override
  public void onCreate() {
    super.onCreate();
    Log.i(TAG, "Service is invoke Created");
    thread = new Thread(new Runnable() {
      @Override
      public void run() {
        // 每间隔一秒count加1 ，直到quit为true。
        while (!quit) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          count++;
        }
      }
    });
    thread.start();
  }

  @Nullable
  @Override//绑定
  public IBinder onBind(Intent intent) {
    return binder;
  }

  @Override//解绑
  public boolean onUnbind(Intent intent) {
    Log.i(TAG, "Service is invoke onUnbind");
    return super.onUnbind(intent);
  }

  @Override//销毁
  public void onDestroy() {
    Log.i(TAG, "Service is invoke Destroyed");
    this.quit = true;
    super.onDestroy();
  }

  //第三步：公共方法的书写，通常用于数据交互
  public int getCount(){
    return count;
  }
}
/**-------------------------------Service:LocalService---------------------------**/

/************************Activity:BindActivity********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：通过IBinder实现与Service之间的通信
 *
 *流程：
 *    1.编写连接逻辑代码，处理连接成功和失败时的处理
 *    2.进行绑定、解绑、数据交互的编码
 */

public class BindActivity extends Activity {
  protected static final String TAG = "wzj";
  Button btnBind;
  Button btnUnBind;
  Button btnGetDatas;
  /**
   * ServiceConnection代表与服务的连接，它只有两个方法，
   * onServiceConnected和onServiceDisconnected，
   * 前者是在操作者在连接一个服务成功时被调用，而后者是在服务崩溃或被杀死导致的连接中断时被调用
   */
  //第一步：编写连接逻辑代码，处理连接成功和失败时的处理
  private ServiceConnection conn;
  private LocalService mService;
  conn = new ServiceConnection() {
    /**
     * 与服务器端交互的接口方法 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
     * 通过这个IBinder对象，实现宿主和Service的交互。
     * @param name ComponentName是一个封装了组件(Activity, Service, BroadcastReceiver, or ContentProvider)信息的类，如包名，组件描述等信息，较少使用该参数。
     * @param service 服务端返回的IBinder实现类对象
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      Log.d(TAG, "绑定成功调用：onServiceConnected");
      // 获取Binder
      LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
      mService = binder.getService();
    }
    /**
     * 当取消绑定的时候被回调。但正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
     * 例如内存的资源不足时这个方法才被自动调用。
     * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。注意:当客户端取消绑定时，系统“绝对不会”调用该方法。
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
      mService=null;
    }
  };
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bind);
    btnBind = (Button) findViewById(R.id.BindService);
    btnUnBind = (Button) findViewById(R.id.unBindService);
    btnGetDatas = (Button) findViewById(R.id.getServiceDatas);
    //第二步：进行绑定、解绑、数据交互的编码
    //创建绑定对象
    final Intent intent = new Intent(this, LocalService.class);
    // 开启绑定
    btnBind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d(TAG, "绑定调用：bindService");
        //调用绑定方法
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
      }
    });
    // 解除绑定
    btnUnBind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d(TAG, "解除绑定调用：unbindService");
        // 解除绑定
        if(mService!=null) {
          mService = null;
          unbindService(conn);
        }
      }
    });

    // 获取数据
    btnGetDatas.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mService != null) {
          // 通过绑定服务传递的Binder对象，获取Service暴露出来的数据

          Log.d(TAG, "从服务端获取数据：" + mService.getCount());
        } else {

          Log.d(TAG, "还没绑定呢，先绑定,无法从服务端获取数据");
        }
      }
    });



  }
}
/**----------------------------END：Activity:BindActivity------------------------------------**/

```

### ServiceBound的Messager的实现

```java
/************************Service:MessengerService********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：BundleService的Messager实现
 *
 *流程：
 *    1.//第一步：设置UI线程
 *    2.//第二步： Messenger进程的创建，并且传入handlerUI线程对象
 *    3.//第三步，生命周期的逻辑处理
 */

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {

  static final int MSG_SAY_HELLO = 1;
  private static final String TAG ="wzj" ;
  //第一步：设置UI线程
  //UI线程，用于接收从客户端发送过来的数据
  class IncomingHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case MSG_SAY_HELLO:
          Log.i(TAG, "thanks,Service had receiver message from client!");
          //回复客户端信息,该对象由客户端传递过来
          Messenger client=msg.replyTo;
          //获取回复信息的消息实体
          Message replyMsg=Message.obtain(null,MessengerService.MSG_SAY_HELLO);
          Bundle bundle=new Bundle();
          bundle.putString("reply","ok~,I had receiver message from you! ");
          replyMsg.setData(bundle);
          //向客户端发送消息
          try {
            client.send(replyMsg);
          } catch (RemoteException e) {
            e.printStackTrace();
          }

          break;
        default:
          super.handleMessage(msg);
      }
    }
  }

  //第二步： Messenger进程的创建，并且传入handlerUI线程对象
  final  Messenger mMessenger = new Messenger(new IncomingHandler());

  //第三步，生命周期的逻辑处理
  //绑定
  @Override
  public IBinder onBind(Intent intent) {
    Log.i(TAG, "Service is invoke onBind");
    return mMessenger.getBinder();
  }
}
/**----------------------------END：Service:MessengerService------------------------------------**/
/************************Activity:ActivityMessenger********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：
 *
 *流程：
 *    1.设置连接时的逻辑判断
 *    2.指定绑定生命周期
 *    3.设置数据交互
 */
    import android.app.Activity;
    import android.content.ComponentName;
    import android.content.Context;
    import android.content.Intent;
    import android.content.ServiceConnection;
    import android.os.Bundle;
    import android.os.Handler;
    import android.os.IBinder;
    import android.os.Message;
    import android.os.Messenger;
    import android.os.RemoteException;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;


public class ActivityMessenger extends Activity {
  //
  Messenger mService = null;

  boolean mBound;

  //第一步：指定连接动作
  private ServiceConnection mConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName className, IBinder service) {
      //固定写法
      mService = new Messenger(service);
      mBound = true;
    }

    public void onServiceDisconnected(ComponentName className) {
      // This is called when the connection with the service has been
      // unexpectedly disconnected -- that is, its process crashed.
      mService = null;
      mBound = false;
    }
  };

  public void sayHello(View v) {
    if (!mBound) return;
    // 创建与服务交互的消息实体Message
    Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
    //把接收服务器端的回复的Messenger通过Message的replyTo参数传递给服务端
    msg.replyTo=mRecevierReplyMsg;
    try {
      //发送消息
      mService.send(msg);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  //
  private Messenger mRecevierReplyMsg= new Messenger(new ReceiverReplyMsgHandler());
  private static class ReceiverReplyMsgHandler extends Handler{
    private static final String TAG = "zj";

    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        //接收服务端回复
        case MessengerService.MSG_SAY_HELLO:
          Log.i(TAG, "receiver message from service:"+msg.getData().getString("reply"));
          break;
        default:
          super.handleMessage(msg);
      }
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_messenager);
    Button bindService= (Button) findViewById(R.id.bindService);
    Button unbindService= (Button) findViewById(R.id.unbindService);
    Button sendMsg= (Button) findViewById(R.id.sendMsgToService);

    bindService.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("zj","onClick-->bindService");
        //第二步：指定绑定解绑动作
        //当前Activity绑定服务端
        bindService(new Intent(ActivityMessenger.this, MessengerService.class), mConnection,
            Context.BIND_AUTO_CREATE);
      }
    });
    //第三步设置动作
    //发送消息给服务端
    sendMsg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        sayHello(v);
      }
    });

    //第四步:解绑
    unbindService.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Unbind from the service
        if (mBound) {
          Log.d("zj","onClick-->unbindService");
          unbindService(mConnection);
          mBound = false;
        }
      }
    });
  }
}
/**----------------------------END：Activity:ActivityMessenger------------------------------------**/
    <!--android:process=":remote"代表该Service在单独的进程中创建-->
    <service android:name=".MessengerService"
android:process=":remote"
    />

```

### ServiceIntent 自动开启线程、自动销毁线程

```java
/****************** Service：MyIntentService ********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：IntentService,自动开启、关闭线程
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：这是IntentService，可以自动创建线程和自动关闭
 *      在Activity中添加这个：startService(new Intent(MainActivity.this,MyIntentService.this))
 *方法：
 */

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }
    //用于给工作的线程命名
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public IBinder onBind(Intent intent) {  //必须实现的绑定方法
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //IntentService在这个方法中自动开启线程了
        while (true) {
            synchronized (this) {
                try {
                    wait(1000);
                    Log.i("tag标志", "IntentService自动开启线程了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    //一、Service创建时执行的方法
    public void onCreate() {
    }


    @Override
    //二、主要执行的方法
    public int onStartCommand(Intent intent, int flags, int startId) { //实现音乐的播放

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    //三、Service销毁时的方法
    public void onDestroy() {
        //IntentService可以自动销毁
        Log.i("tag标志", "IntentService自动销毁了");
        super.onDestroy();
    }
}
/**----------------------------END：:MyIntentService------------------------------------**/


```

### ServiceStart Class的常规用法

```java
/************************Service:SimpleService********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：启动式服务示例，Start Service
 *注意：主要注意其中的onStartCommand方法的参数和返回值。
 *  当应用组件（如 Activity）通过调用 startService() 启动服务时，
 *  服务即处于“启动”状态。一旦启动，服务即可在后台无限期运行，即使
 *  启动服务的组件已被销毁也不受影响，除非手动调用才能停止服务， 已
 *  启动的服务通常是执行单一操作，而且不会将结果返回给调用方。
 */

public class SimpleService extends Service {

  private static final String TAG = "Service";

  /**
   * 绑定服务时才会调用 必须要实现的方法
   *
   * @param intent
   * @return
   */
  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  /**
   * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。 如果服务已在运行，则不会调用此方法。该方法只被调用一次
   */
  @Override
  public void onCreate() {
    System.out.println("onCreate invoke");
    Log.i(TAG, "onCreate invoke");
    super.onCreate();
  }

  /**
   * 每次通过startService()方法启动Service时都会被回调。
   *
   * @param intent 启动时，启动组件传递过来的Intent，如Activity可利用Intent封装所需要的参数并传递给Service
   * @param flags 表示启动请求时是否有额外数据，可选值有 0，START_FLAG_REDELIVERY，START_FLAG_RETRY，0代表没有
   * @param startId  指明当前服务的唯一ID，与stopSelfResult (int startId)配合使用，stopSelfResult 可以更安全地根据ID停止服务。
   * @return 它有三种可选值， START_STICKY，START_NOT_STICKY，START_REDELIVER_INTENT
   * START_STICKY
   *   当Service因内存不足而被系统kill后，一段时间后内存再次空闲时，系统将会尝试重新创建此Service，一旦创建成功后将回调onStartCommand方法，但其中的Intent将是null，除非有挂起的Intent，如pendingintent，这个状态下比较适用于不执行命令、但无限期运行并等待作业的媒体播放器或类似服务。
   *
   * START_NOT_STICKY
   *   当Service因内存不足而被系统kill后，即使系统内存再次空闲时，系统也不会尝试重新创建此Service。除非程序中再次调用startService启动此Service，这是最安全的选项，可以避免在不必要时以及应用能够轻松重启所有未完成的作业时运行服务。
   *
   * START_REDELIVER_INTENT
   *   当Service因内存不足而被系统kill后，则会重建服务，并通过传递给服务的最后一个 Intent 调用 onStartCommand()，任何挂起 Intent均依次传递。与START_STICKY不同的是，其中的传递的Intent将是非空，是最后一次调用startService中的intent。这个值适用于主动执行应该立即恢复的作业（例如下载文件）的服务。
   */
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.i(TAG, "onStartCommand invoke");
    return super.onStartCommand(intent, flags, startId);
  }

  /**
   * 服务销毁时的回调
   */
  @Override
  public void onDestroy() {
    Log.i(TAG, "onDestroy invoke");
    super.onDestroy();
  }
}
/**----------------------------END：Service:SimpleService------------------------------------**/
/************************Activity:MyActivity1********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：启动服务、关闭服务。StartService的应用场景
 */

public class MyActivity1 extends AppCompatActivity implements View.OnClickListener {

  private Button startBtn;
  private Button stopBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_one);
    startBtn= (Button) findViewById(R.id.startService);
    stopBtn= (Button) findViewById(R.id.stopService);
    startBtn.setOnClickListener(this);
    //断言
    assert stopBtn != null;
    stopBtn.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Intent it=new Intent(this, SimpleService.class);
    switch (v.getId()){
      case R.id.startService:
        //启动服务
        startService(it);
        break;
      case R.id.stopService:
        //关闭服务
        stopService(it);
        break;
    }
  }
}
/**----------------------------END：Activity:MyActivity1------------------------------------**/
<service android:name=".SimpleService" />

```

### ServiceStart 的通知栏的实现

```java
/************************Service:ForegroundService********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：实现前台Service，前台Service的实现
 *
 *流程：
 *    1.设置生命周期
 *    2.配置前台通知
 *注意：注意关闭前台通知不会结束Service，需要手动结束Service
 */
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import org.jetbrains.annotations.Nullable;

public class ForegroundService extends Service {

  /**
   * id不可设置为0,否则不能设置为前台service
   */
  private static final int NOTIFICATION_DOWNLOAD_PROGRESS_ID = 0x0001;

  private boolean isRemove=false;//是否需要移除

  public void createNotification(){
    //使用兼容版本
    NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
    //设置状态栏的通知图标
    builder.setSmallIcon(R.mipmap.ic_launcher);
    //设置通知栏横条的图标
    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background));
    //禁止用户点击删除按钮删除
    builder.setAutoCancel(false);
    //禁止滑动删除
    builder.setOngoing(true);
    //右上角的时间显示
    builder.setShowWhen(true);
    //设置通知栏的标题内容
    builder.setContentTitle("I am Foreground Service!!!");
    //创建通知
    Notification notification = builder.build();
    //重点：设置为前台服务
    startForeground(NOTIFICATION_DOWNLOAD_PROGRESS_ID,notification);
  }


  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    int i=intent.getExtras().getInt("cmd");
    if(i==0){
      if(!isRemove) {
        createNotification();
      }
      isRemove=true;
    }else {
      //移除前台服务
      if (isRemove) {
        stopForeground(true);
      }
      isRemove=false;
    }
    //该方法是用来从前台删除服务，此方法传入一个布尔值，指示是否也删除状态栏通知，true为删除。 注意该方法并不会停止服务。 但是，如果在服务正在前台运行时将其停止，则通知也会被删除。
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy() {
    //移除前台服务
    if (isRemove) {
      stopForeground(true);
    }
    isRemove=false;
    super.onDestroy();
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
/**----------------------------END：Service:ForegroundService------------------------------------**/
/************************Activity:ForegroundActivity********************************/
/************************制定时间：2020/3/17 0017********************************/
/**
 *功能：实现前台通知的实现
 *
 *流程：
 *    1.设置Service逻辑
 *注意：通过发送不同的数据控制通知的开关
 */

    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;

public class ForegroundActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_foreground);
    Button btnStart = (Button) findViewById(R.id.startForeground);
    Button btnStop = (Button) findViewById(R.id.stopForeground);
    final Intent intent = new Intent(this, ForegroundService.class);

    btnStart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        intent.putExtra("cmd", 0);//0,开启前台服务,1,关闭前台服务
        startService(intent);
      }
    });

    btnStop.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        intent.putExtra("cmd", 1);//0,开启前台服务,1,关闭前台服务
        startService(intent);
      }
    });
  }
}
/**----------------------------END：Activity:ForegroundActivity------------------------------------**/

```

## Broadcast

### broadCast 动态或者静态

```java
/************************Activity:SendBroadcastActivity********************************/
/************************制定时间：2020/3/13 0013********************************/
/**
 *功能：发送广播，这里主要演示动态发送广播，但是又静态配置的例子
 *
 *流程：
 *    1.获取广播对象
 *    2.设置广播过滤属性Action
 *    3.注册广播
 *    4.发送广播
 *    5.广播注册解除，也就是销毁广播
 *注意：注意广播不要乱用，虽然广播使用非常简单，但是对于程序的可读性来说有非常不好的影响
 *  动态广播最好在Activity 的 onResume()注册、onPause()注销。
 */
package com.wanli.demo4;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

public class SendBroadcastActivity extends Activity {

    public static final String COM_GEEKBAND_TEST_BROADCAST = "com.geekband.test.broadcast";
    //获取目标广播对象
    private TestBroadcastReceiver mTestBroadcastReceiver = new TestBroadcastReceiver();
    private Button mSendBroadcastButton;
    /**
     * 开启Activity的时候注册一个广播，并且设置广播监听器
     */
    @Override
    protected void onStart() {
        super.onStart();
        // 有一个intentFilter  动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        //通常设置为目标广播的类名
        intentFilter.addAction(COM_GEEKBAND_TEST_BROADCAST);
        registerReceiver(mTestBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my1);
        mSendBroadcastButton = (Button) findViewById(R.id.button2);
        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送广播
                Intent intent = new Intent();
                intent.setAction(COM_GEEKBAND_TEST_BROADCAST);
                intent.putExtra("toast", "this is my toast of broadcast");
                //方式一：发送普通广播，整个系统发送，比较浪费性能
                sendBroadcast(intent);
                //方式二：发送有序广播
                sendOrderedBroadcast(intent,"1");
                //方式三：发送本应用内的广播，比较推荐，性能更佳
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(SendBroadcastActivity.this);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

  @Override
  protected void onPause() {
    super.onPause();
    //销毁在onResume()方法中的广播
    unregisterReceiver(mBroadcastReceiver);
  }
}
/************************静态注册:********************************/
<receiver android:name=".MyBroadcastReceiver" >
<intent-filter>
<action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
</intent-filter>
<intent-filter>
<action android:name="android.intent.action.BOOT_COMPLETED" />
</intent-filter>
</receiver>
/**----------------------------END：Activity:SendBroadcastActivity------------------------------------**/
/************************Broadcast:TestBroadcastReceiver********************************/
/************************制定时间：2020/3/13 0013********************************/
/**
 *功能：广播的消息接收处理
 *
 *流程：
 *    1.重写onReceive方法处理接收到的消息
 *注意：注意标志位的逻辑判断
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
public class TestBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //  receive broadcast, handle data

        // > 10S ANR work thread
        if (intent != null) {
            if (TextUtils.equals(intent.getAction(), SendBroadcastActivity.COM_GEEKBAND_TEST_BROADCAST)) {
                String toastString = intent.getStringExtra("toast");
                Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
            }
        }
    }
}
/**----------------------------END：Broadcast:TestBroadcastReceiver------------------------------------**/
<receiver
android:enabled=["true" | "false"]
//此broadcastReceiver能否接收其他App的发出的广播
//默认值是由receiver中有无intent-filter决定的：如果有intent-filter，默认值为true，否则为false
android:exported=["true" | "false"]
android:icon="drawable resource"
android:label="string resource"
//继承BroadcastReceiver子类的类名
android:name=".mBroadcastReceiver"
//具有相应权限的广播发送者发送的广播才能被此BroadcastReceiver所接收；
android:permission="string"
//BroadcastReceiver运行所处的进程
//默认为app的进程，可以指定独立的进程
//注：Android四大基本组件都可以通过此属性指定自己的独立进程
android:process="string" >

//用于指定此广播接收器将接收的广播类型
//本示例中给出的是用于接收网络状态改变时发出的广播
 <intent-filter>
```

## ContentProvider

### contentProvider Utils（contentProvider 获取数据）

```java
/************************其他:ContentProvider Utils********************************/
/************************制定时间：2020/3/18 0018********************************/
/**
 *功能：ContentProvider的工具类
 *
 *流程：
 *    1.编辑Uri，获取Uri数据
 *    2.注册Uri，可以为Uri配置标志位，从而进行更加方便的Uri逻辑处理
 *    3.监听指定Uri的数据变化，做出逻辑判断
 */

//工具一：ContentUris类，操作 URI，核心方法有两个：withAppendedId（） &parseId（）
// withAppendedId（）作用：向URI追加一个id
Uri uri = Uri.parse("content://cn.scu.myprovider/user")
Uri resultUri = ContentUris.withAppendedId(uri, 7);
// 最终生成后的Uri为：content://cn.scu.myprovider/user/7

// parseId（）作用：从URL中获取ID
Uri uri = Uri.parse("content://cn.scu.myprovider/user/7")
long personid = ContentUris.parseId(uri);
//获取的结果为:7



//工具二：UriMatcher类，在ContentProvider 中注册URI，根据 URI 匹配 ContentProvider 中对应的数据表
// 步骤1：初始化UriMatcher对象
UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
//常量UriMatcher.NO_MATCH  = 不匹配任何路径的返回码
// 即初始化时不匹配任何东西

// 步骤2：在ContentProvider 中注册URI（addURI（））
int URI_CODE_a = 1；
int URI_CODE_b = 2；
matcher.addURI("cn.scu.myprovider", "user1", URI_CODE_a);
matcher.addURI("cn.scu.myprovider", "user2", URI_CODE_b);
// 若URI资源路径 = content://cn.scu.myprovider/user1 ，则返回注册码URI_CODE_a
// 若URI资源路径 = content://cn.scu.myprovider/user2 ，则返回注册码URI_CODE_b

// 步骤3：根据URI 匹配 URI_CODE，从而匹配ContentProvider中相应的资源（match（））
@Override
public String getType(Uri uri) {
  Uri uri = Uri.parse(" content://cn.scu.myprovider/user1");

  switch(matcher.match(uri)){
    // 根据URI匹配的返回码是URI_CODE_a
    // 即matcher.match(uri) == URI_CODE_a
    case URI_CODE_a:
      return tableNameUser1;
    // 如果根据URI匹配的返回码是URI_CODE_a，则返回ContentProvider中的名为tableNameUser1的表
    case URI_CODE_b:
      return tableNameUser2;
    // 如果根据URI匹配的返回码是URI_CODE_b，则返回ContentProvider中的名为tableNameUser2的表
  }
}


//工具三：ContentObserver类， 观察 Uri引起 ContentProvider 中的数据变化 & 通知外界（即访问该数据访问者）
// 步骤1：注册内容观察者ContentObserver
getContentResolver().registerContentObserver（uri）；
// 通过ContentResolver类进行注册，并指定需要观察的URI

// 步骤2：当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
public class UserContentProvider extends ContentProvider {
  public Uri insert(Uri uri, ContentValues values) {
    db.insert("user", "userid", values);
    getContext().getContentResolver().notifyChange(uri, null);
    // 通知访问者
  }
}

// 步骤3：解除观察者
getContentResolver().unregisterContentObserver（uri）；
// 同样需要通过ContentResolver类进行解除
/**----------------------------END：其他:Utils------------------------------------**/

```

### contentProvider 自定义（ContentProviderjava通讯录数据共享）

```java
package com.geekband.Test01.provider;

import com.geekband.Test01.database.DatabaseHelper;

/************************Activity:URIList********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：用于content个的单独罗列，程序的模块化
 */
public class URIList {

    public static final String CONTENT = "content://";
    public static final String AUTHORITY = "com.geekband.Test01";


    // content://com.geekband.Test01/user   1
    // content://com.geekband.Test01/book   2
    //用于系统来访问我们的表
    public static final String USER_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.USER_TABLE_NAME;
    public static final String BOOK_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.BOOK_TABLE_NAME;
}
/**----------------------------END：Activity:URIList------------------------------------**/

/************************Activity:URIList********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：实现不同应用之间的数据交互
 *
 *流程：
 *    1.注册URI，别人需要通过这个对我们的资源进行获取
 *    2.getTableName方法的创建，根据uri返回需要操作的文件或者数据表
 *    3.onCreate方法进行必要对象的初始化获取（通常需要初始化一个数据库类或者文件查找类）
 *    4.重写update方法
 *注意：其实质就是数据表和文件数据的获取，需要有文件或者数据表（通常需要实现额外的数据库查询类）
 */

package com.geekband.Test01.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.geekband.Test01.database.DatabaseHelper;
public class TestContentProvider extends ContentProvider {

    //
    private static UriMatcher sUriMatcher;
    public static final int URI_MATCH_USER = 1;
    public static final int URI_MATCH_BOOK = 2;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // content://com.geekband.Test01/user   1
        // content://com.geekband.Test01/book   2
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.USER_TABLE_NAME, URI_MATCH_USER);
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.BOOK_TABLE_NAME, URI_MATCH_BOOK);
    }
    private DatabaseHelper mDatabaseHelper;

    @Override//初始化必须的数据
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return false;
    }
    private String getTableName(Uri uri){
        int type = sUriMatcher.match(uri);
        String tableName = null;
        switch (type){
            case URI_MATCH_USER:
                tableName = DatabaseHelper.USER_TABLE_NAME;
                break;
            case URI_MATCH_BOOK:
                tableName = DatabaseHelper.BOOK_TABLE_NAME;
                break;
        }
        return tableName;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return -1;
        }
        int count = mDatabaseHelper.getWritableDatabase().update(tableName, values,selection,selectionArgs);
        return count;
    }
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return null;
        }
        Cursor cursor = mDatabaseHelper.getReadableDatabase()
                .query(tableName, projection,selection, selectionArgs,null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return null;
        }
        long id = mDatabaseHelper.getWritableDatabase().insert(tableName, null, values);

        // uri: content://com.geekband.Test01/user
        // content://com.geekband.Test01/user/id

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if(TextUtils.isEmpty(tableName)){
            return -1;
        }

        int count = mDatabaseHelper.getWritableDatabase().delete(tableName, selection, selectionArgs);
        return count;
    }
}

/**----------------------------END：Activity:URIList------------------------------------**/

/************************2布局文件:AndroidManifest********************************/
/************************制定时间：2020/3/14 0014********************************/
/**
 *功能：
 *
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：
 *
 *方法：
 */

<provider
android:authorities="com.geekband.Test01"
android:name=".provider.TestContentProvider"
android:enabled="true"
android:exported="true"
android:multiprocess="true"
        >
/**----------------------------END：2布局文件:AndroidManifest------------------------------------**/

```

### contentProvider 获取数据

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/7 0007********************************/
/**
 *功能：ContentProvider实现不同的程序之间(如获取通讯录联系人信息)实现数据共享的功能
 *      实现通讯录中联系人的获取
 *流程：
 *    1.
 *    2.
 *    3.
 *    4.
 *注意：
 *
 *方法：
 */
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String columns = ContactsContract.Contacts.DISPLAY_NAME; //希望获得姓名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.result); //获得布局文件中的TextView组件
        tv.setText(getQueryData()); //为TextView设置数据
    }

    private CharSequence getQueryData() { //创建getQueryData()方法，实现获取通讯录信息
        StringBuilder sb = new StringBuilder(); //用于保存字符串
        ContentResolver resolver = getContentResolver(); //获得ContentResolver对象
        //查询记录
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int displayNameIndex = cursor.getColumnIndex(columns); //获得姓名记录的索引值
        //迭代全部记录
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String displayName = cursor.getString(displayNameIndex);
            sb.append(displayName + "\n");
        }
        cursor.close(); //关闭Cursor
        return sb.toString(); //返回查询结果
    }
}


/**----------------------------END：Activity:MainActivity------------------------------------**/

```

## 其他

### AndroidManifest.xml配置大全

```java
/************************AndroidManifest********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：实现App整体属性的配置
 *
 *流程：
 *    1.第一部分 权限设置
 *    2.第二部分 入口Activity设置
 *    3.第三部分 子Activity设置
 *    4.第四部分 Service设置
 *    5.第五部分 其他一些设置
 *注意：这边包括了视频中讲述的基本配置
 *
 *方法：
 */

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.wanli.demo3">
/************************方法段:第一部分********************************/
<!--webView允许访问网络的权限-->
<uses-permission android:name="android.permission.INTERNET" />
<!--允许访问通讯录-->
<uses-permission android:name="android.permission.READ_CONTACTS" />
<!--设置访问sd卡的权限-->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<!--MediaPlayer+SurfaceView进行视频播放 需要进行设置-->
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMSE" />
<!--设置写sd卡的权限-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!--使用外部存储器权限-->
<uses-permission android:name="android.permission.CAMERA" />
<!--使用摄像头权限-->
<uses-permission android:name="android.permission.CAMERA" />
<!--加速度传感器使用：允许使用振动器-->
<uses-permission android:name="android.permission.VIBRATE"></uses-permission>
<!--允许使用定位-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
<!--百度地图API的权限设置-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
/************************方法段:第二部分********************************/
<application android:allowBackup="true"
        //设置app背景图
        android:icon="@mipmap/ic_launcher"
        //设置app标题
        android:label="第一张 Activity"
        android:supportsRtl="true"
        //设置app主题
        android:theme="@style/AppTheme.NoActionBar">

<activity android:name=".MainActivity"
Activity中设置的一款主题：android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"
>
//过滤器配置
<intent-filter>
//1 设置为主Activity
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />

//2 依次是设置action的过滤属性、设置category的过滤属性
<action android:name="android.intent.action.VIEW"></action>
<category android:name="android.intent.category.DEFAULT"></category>
</intent-filter>

//子父Activity配置
<meta-data
        //1 配置<meta-data>第一个属性name
        android:name="android.support.PARENT_ACTIVITY"
        //2 配置<meta-data>的value属性，指定其父Activity的名称
        android:value=".MainActivity" />
</activity>
/************************方法段:第三部分********************************/
<activity
android:name=".Main2Activity"
        android:label="第二张 Activity"
        android:theme="@style/AppTheme.NoActionBar"
        ></activity>
/************************方法段:第四部分********************************/
<service
android:name=".MyService"
        //是否可以和其他组件交互
        android:enabled="true"
        //该Service是否可以被实例化
        android:exported="true"
        >
</service>
/************************方法段:第五部分********************************/
<service android:name="com.baidu.location.f" android:enabled="true" android:process=":remote">
<intent-filter>
<action android:name="com.baidu.location.service_v3.7.3"></action>
</intent-filter>
</service>
//下面是需要放到布局管理器中的内容，需要配置自己的百度地图类
<com.baidu.mapapi.map.MapView android:id="@+id/bmapview" android:layout_width="match_parent" android:layout_height="match_parent" android:clickable="true" />
</application>
</manifest>
/**----------------------------END：AndroidManifest:------------------------------------**/
```

### simpleAdapter适配器

```java
/************************Activity:MainActivity********************************/
/************************制定时间：2020/3/6 0006********************************/
/**
 *功能：SimpleAdapter，适配器
 *
 *方法：
 */
/************************方法段:类变量********************************/
//图片资源数组
private Integer[] $pictureArray$ = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
        R.drawable.a4,};
/************************方法段:onCreate()********************************/
//map:String代表适配器中的ImageView的id值，Object代表目标图片
List<Map<String, Object>> $listItem$ = new ArrayList<>();
//循环就是为了将所有的图片放入ImageView显示器当中去
for (int i = 0; i < $pictureArray$.length; i++) {
    Map<String, Object> map = new HashMap<>();
    //通过自己配置的布局文件cell文件中的图片视图id来获取图片
    map.put("$imageAdapter$", $pictureArray$[i]);
    $listItem$.add(map);
}
//设置适配器，将我们做好的list，也就是存放了我们所有ImageView组件的集合放到适配器中
SimpleAdapter simpleAdapter = new SimpleAdapter(this, $listItem$, R.layout.$cell$, new String[]{"$imageAdapter$"}, new int[]{R.id.$imageAdapter$});
/**
 /************************方法段:类变量********************************/
//图片资源数组
private Integer[] pictureArray = {R.drawable.a1, R.drawable.a2, R.drawable.a3,
        R.drawable.a4,};
//2.定义并初始化保存图片id的数组
String[] title = new String[]{"刘一", "陈二", "张三", "李四", "王五",
        "赵六", "孙七", "周八", "吴九"};
/************************方法段:SimpleAdapter双组件********************************/
//map中String为cell中的组件id，Object是对应组件中的内容(图片、文本...)
List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
//通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
for (int i = 0; i < pictureArray.length; i++) {
    // 实例化Map对象，每次创建一个map对象并且这里仅仅存入两个数据
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("imageAdapter", pictureArray[i]);
    map.put("textAdapter", title[i]);
    // 将map对象添加到List集合中
    listItems.add(map);
}
// 创建SimpleAdapter
//参数： 数组名、res/layout下的资源文件名、cell中两个组件id、res/layout下的资源文件中两个组件的id
SimpleAdapter adapter = new SimpleAdapter(this, listItems,
        R.layout.cell, new String[]{"textAdapter", "imageAdapter"}, new int[]{
        R.id.textAdapter, R.id.imageAdapter});
 */
/**----------------------------END：Activity:MainActivity------------------------------------**/
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

```

### 大全 anim

```java

/************************动画资源讲解:属性动画********************************/
/************************制定时间：2020/3/11 0011********************************/
<set 容器标签，可以嵌套多个set标签
        //together:表示动画一起执行
        //sequentially:表示动画按照顺序执行
        android:ordering=["together" | "sequentially"]>
<objectAnimator 表示在特定时间内激活对象的特定属性。
        //Required，动画资源引用的唯一标识。必须通过调用loadAnimator()和调用setTarget()来扩展动画XML资源，以设置包含此属性的目标对象。
        android:propertyName="string"
        //动画持续的毫秒值。
        android:duration="int"
        //颜色值，动画属性开始的地方的值。如果没有指定，动画将从属性的get方法获得的值开始。
        android:valueFrom="float | int | color"
        //Required，颜色值，动画结束地方的值，通常用来设置透明度
        android:valueTo="float | int | color"
        //动画启动前的延时
        android:startOffset="int"
        //重复一个动画多少次。设置为“-1”无限重复或为正整数。例如，值“1”表示动画在初始运行后重复播放一次，因此动画总共播放两次。默认值是“0”，表示没有重复。
        android:repeatCount="int"
        //一个动画在动画结束时的行为。android:repeatCount必须设置为正整数或“-1”，这个属性才有效果。设置为“reverse”可以让动画在每次迭代时反向播放，或者设置为“repeat”可以让动画在每次开始时循环播放。
        android:repeatMode=["repeat" | "reverse"]
        //关键字。如果值是颜色，则不要指定此属性。动画框架自动处理颜色值
        android:valueType=["intType" | "floatType"]/>
<animator
android:duration="int"
        android:valueFrom="float | int | color"
        android:valueTo="float | int | color"
        android:startOffset="int"
        android:repeatCount="int"
        android:repeatMode=["repeat" | "reverse"]
        android:valueType=["intType" | "floatType"]/>
<set>
...
</set>
</set>
/************************例子:********************************/
<set android:ordering="sequentially">
<set>
<objectAnimator
android:propertyName="x"
        android:duration="500"
        android:valueTo="400"
        android:valueType="intType"/>
<objectAnimator
android:propertyName="y"
        android:duration="500"
        android:valueTo="300"
        android:valueType="intType"/>
</set>
<objectAnimator
android:propertyName="alpha"
        android:duration="500"
        android:valueTo="1f"/>
</set>

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(myContext,
        R.animator.property_animator);
        set.setTarget(myObject);
        set.start();
/**----------------------------END：动画资源讲解:------------------------------------**/



/************************动画资源讲解:视图动画->补间动画********************************/
/************************制定时间：2020/3/11 0011********************************/
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
        //内插器资源指定，可以自定义也可以使用系统自带的
        android:interpolator="@[package:]anim/interpolator_resource"
        //如果你想要在所有的子元素中共享相同的内插器，则为“true”。
        android:shareInterpolator=["true" | "false"] >
<alpha//透明度
android:fromAlpha="float"
        android:toAlpha="float" />
<scale//平移
android:fromXScale="float"
        //X尺寸偏移量，其中1.0表示没有变化
        android:toXScale="float"
        android:fromYScale="float"
        android:toYScale="float"
        //自由浮动。当对象缩放时，X坐标保持不变。
        android:pivotX="float"
        android:pivotY="float" />
<translate//大小的改变，垂直或水平运动。支持以下三种格式中的任何一种:从-100到100以“%”结尾的值，表示相对于自身的百分比;值从-100到100以“%p”结尾，表示相对于其父级的百分比;没有后缀的浮点值，表示绝对值。
android:fromXDelta="float"
        android:toXDelta="float"
        android:fromYDelta="float"
        android:toYDelta="float" />
<rotate//旋转
//起始角度位置，单位是度。
android:fromDegrees="float"
        android:toDegrees="float"
        //浮动或百分比。旋转中心的X坐标。表示为:相对于对象的左边缘的像素(例如“5”)，相对于对象的左边缘的百分比(例如“5%”)，或者相对于父容器的左边缘的百分比(例如“5%p”)。
        android:pivotX="float"
        android:pivotY="float" />
<set>
...
</set>
</set>
/************************例子:********************************/
<set xmlns:android="http://schemas.android.com/apk/res/android"
        android:shareInterpolator="false">
<scale
android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:fromXScale="1.0"
        android:toXScale="1.4"
        android:fromYScale="1.0"
        android:toYScale="0.6"
        android:pivotX="50%"
        android:pivotY="50%"
        android:fillAfter="false"
        android:duration="700" />
<set
android:interpolator="@android:anim/accelerate_interpolator"
        android:startOffset="700">
<scale
android:fromXScale="1.4"
        android:toXScale="0.0"
        android:fromYScale="0.6"
        android:toYScale="0.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="400" />
<rotate
android:fromDegrees="0"
        android:toDegrees="-45"
        android:toYScale="0.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="400" />
</set>
</set>

ImageView image = (ImageView) findViewById(R.id.image);
Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
image.startAnimation(hyperspaceJump);

/************************内插器资源补充:********************************/
Interpolator class	Resource ID
AccelerateDecelerateInterpolator	@android:anim/accelerate_decelerate_interpolator
AccelerateInterpolator	@android:anim/accelerate_interpolator
AnticipateInterpolator	@android:anim/anticipate_interpolator
AnticipateOvershootInterpolator	@android:anim/anticipate_overshoot_interpolator
BounceInterpolator	@android:anim/bounce_interpolator
CycleInterpolator	@android:anim/cycle_interpolator
DecelerateInterpolator	@android:anim/decelerate_interpolator
LinearInterpolator	@android:anim/linear_interpolator
OvershootInterpolator	@android:anim/overshoot_interpolator
/**----------------------------END：动画资源讲解:------------------------------------**/


/************************动画资源讲解:视图动画->逐帧动画********************************/
/************************制定时间：2020/3/11 0011********************************/
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
        //表示是否循环播放动画
        android:oneshot=["true" | "false"] >
<item
android:drawable="@[package:]drawable/drawable_resource_name"
        android:duration="integer" />
</animation-list>

/************************例子:********************************/
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
        android:oneshot="false">
<item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
<item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
<item android:drawable="@drawable/rocket_thrust3" android:duration="200" />
</animation-list>

ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
rocketImage.setBackgroundResource(R.drawable.rocket_thrust);

rocketAnimation = rocketImage.getBackground();
if (rocketAnimation instanceof Animatable) {
((Animatable)rocketAnimation).start();
}
/**----------------------------END：动画资源讲解:------------------------------------**/

```

### 大全 string

```java
/************************字符串资源:********************************/
/************************制定时间：2020/3/11 0011********************************/
/************************下面依次是单个字符串、字符串数组、:********************************/

<?xml version="1.0" encoding="utf-8"?>
<resources>
<string//一个字符串，它可以包含样式标签。该值可以是另一个字符串资源的引用。
        //资源的唯一标识id
name="string_name"
        >text_string</string>
<string-array//包含一个过着多个item标签
        name="string_array_name">
</resources>

//资源的引用注意了：R.array.string_array_name
<resources>
<string-array//包含一个过着多个item标签
        name="string_array_name">
<item//一个字符串，它可以包含样式标签。该值可以是另一个字符串资源的引用。
        >text_string</item>
</string-array>
</resources>


<?xml version="1.0" encoding="utf-8"?>
<resources>
<plurals//字符串的集合
name="plural_name">
<item//一个复数或单数的字符串。该值可以是另一个字符串资源的引用
quantity=["zero" | "one" | "two" | "few" | "many" | "other"]
        >text_string</item>
</plurals>
</resources>
/************************例子一:********************************/
<?xml version="1.0" encoding="utf-8"?>
<resources>
<string name="hello">Hello!</string>
</resources>

<TextView
android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="@string/hello" />

ing string = getString(R.string.hello);
/************************例子二:********************************/
<?xml version="1.0" encoding="utf-8"?>
<resources>
<string-array name="planets_array">
<item>Mercury</item>
<item>Venus</item>
<item>Earth</item>
<item>Mars</item>
</string-array>
</resources>

<?xml version="1.0" encoding="utf-8"?>
<resources>
<string-array name="planets_array">
<item>Mercury</item>
<item>Venus</item>
<item>Earth</item>
<item>Mars</item>
</string-array>
</resources>

Resources res = getResources();
String[] planets = res.getStringArray(R.array.planets_array);
/************************例子三:********************************/
<?xml version="1.0" encoding="utf-8"?>
<resources>
<plurals name="numberOfSongsAvailable">
<!--
        As a developer, you should always supply "one" and "other"
        strings. Your translators will know which strings are actually
        needed for their language. Always include %d in "one" because
        translators will need to use %d for languages where "one"
        doesn't mean 1 (as explained above).
        -->
<item quantity="one">%d song found.</item>
<item quantity="other">%d songs found.</item>
</plurals>
</resources>

<?xml version="1.0" encoding="utf-8"?>
<resources>
<plurals name="numberOfSongsAvailable">
<item quantity="one">Znaleziono %d piosenkę.</item>
<item quantity="few">Znaleziono %d piosenki.</item>
<item quantity="other">Znaleziono %d piosenek.</item>
</plurals>
</resources>

int count = getNumberOfSongsAvailable();
Resources res = getResources();
String songsFound = res.getQuantityString(R.plurals.numberOfSongsAvailable, count, count);

/**----------------------------END：字符串资源:------------------------------------**/


//Formatting strings
<string name="welcome_messages">Hello, %1$s! You have %2$d new messages.</string>
//在本例中，格式字符串有两个参数:%1$s是字符串，%2$d是小数。然后，通过调用getString(int, Object…)来格式化字符串。例如:
String text = getString(R.string.welcome_messages, username, mailCount);

//Styling with HTML markup
<?xml version="1.0" encoding="utf-8"?>
<resources>
<string name="welcome">Welcome to <b>Android</b>!</string>
</resources>
        The following HTML elements are supported:

        Bold: <b>, <em>
Italic: <i>, <cite>, <dfn>
25% larger text: <big>
20% smaller text: <small>
Setting font properties: <font face=”font_family“ color=”hex_color”>. Examples of possible font families include monospace, serif, and sans_serif.
        Setting a monospace font family: <tt>
Strikethrough: <s>, <strike>, <del>
Underline: <u>
Superscript: <sup>
Subscript: <sub>
Bullet points: <ul>, <li>
Line breaks: <br>
Division: <div>
CSS style: <span style=”color|background_color|text-decoration”>
        Paragraphs: <p dir=”rtl | ltr” style=”…”>




```

### 大全JAVA

```java
目录：
    1.组件篇
    2.适配器
    3.Intent交互
    4.Fragment
    5.菜单文件解析
    6.事件
    7.ActionBar
    8.对话框
/************************JAVA:组件篇********************************/
//1.创建组件、获取组件
LinearLayout linearLayout2 = new LinearLayout(MainActivity.this);
ScrollView scrollView = new ScrollView(MainActivity.this);
TextView email = (TextView) findViewById(R.id.email);
//2.组件属性设置
linearLayout2.setOrientation(LinearLayout.VERTICAL);
imageView.setImageResource(R.mipmap.advise)
textView.setText(R.string.cidian);
textView.setGravity(Gravity.START);设置权重
布局中设置的方向是horizontal，设置成LayoutParams(0,heignt,weight) 即width需要设置权重就在width处为0
布局中设置的方向是vertical，设置成LayoutParams(width,0,weight) 即heignt需要设置权重就在heignt处为0
LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1.0f);
LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1.0f,0);
textView.setLayoutParams(lp);
//3.组件嵌套设置
linearLayout2.addView(imageView);
linearLayout2.addView(textView);
//4.组件属性获取
((EditText) findViewById(R.id.et_email)).getText().toString();
//5.其他
finish();//关闭方法
onCreate(null);//刷新方法

/************************1JAVA:适配器********************************/
//方法一
String[] stringResource=new String[]{"全部","电影","图书","唱片","小事","用户","小组","群聊","游戏","活动"};
ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,stringResource);
//方法二
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
    this, R.array.stringResource/*表示引用的数组资源*/,
    android.R.layout.simple_dropdown_item_1line);//创建一个适配器
// 为适配器设置列表框下拉时的选项样式
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// 将适配器与选择列表框关联
spinner.setAdapter(adapter);

/************************1JAVA:Intent交互********************************/
/-----Intent获取和属性设置----/
Intent intent = new Intent(MainActivity.this/*本类名*/, Activity.class/*目标类名*/);
Intent intent = getIntent();//切换页面后获取Intent的方式
ComponentName componentName = new ComponentName("com.wanli.demo", "com.wanli.demo.MainActivity3");
intent.setComponent(componentName);
intent.setAction(intent.ACTION_MAIN);  //设置action动作属性
intent.addCategory(intent.CATEGORY_HOME); //设置categoty种类显示主屏幕
intent.setAction(intent.ACTION_DIAL); //调用拨号面板
intent.setData(Uri.parse("tel:13800138000")); //设置要拨打的号码
intent.setAction(intent.ACTION_SENDTO); //调用发送短信息
intent.setData(Uri.parse("smsto:5554")); //设置要发送的号码
intent.putExtra("sms_body", "Welcome to Android!"); //设置要发送的信息内容
/----最终Intent----/
startActivity(intent);//切换页面
startActivityForResult(intent, 0x11/*requestCode*/);//等待数据返回
setResult(0x12, intent);    //设置返回的结果码，并返回调用该Activity的Activity
finish();        //关闭当前Activity

Bundle bundle = new Bundle();    //准备添加数据
Bundle bundle = intent.getExtras();//获取Bundle对象中的数据
bundle.putInt("imageId", imageId[position]);
bundle.getString("email")
intent.putExtras(bundle);    //将数据包保存到intent中
intent.putExtra("sms_body", "Welcome to Android!"); //设置要发送的信息内容

/************************1JAVA:Fragment********************************/
FragmentManager fm = getFragmentManager();   // 获取Fragment
FragmentTransaction ft = fm.beginTransaction(); // 开启一个事务
Fragment fragment = null; //为fragmentragment初始化
fragment = new MyFragment(); //创建第一个Fragment
ft.replace(R.id.fragmentLayout,fragment); //替换Fragment
ft.commit(); //提交事务

/************************1JAVA:菜单文件解析********************************/
 //下面两句固定写法，获取menu；根据事件来执行方法
 registerForContextMenu(v);
 openContextMenu(v);
 @Override//解析菜单文件
 public boolean onCreateOptionsMenu (Menu menu){
     //MenuInflater的实例对象用于解析菜单资源文件的
     MenuInflater inflater = getMenuInflater();
     //进行解析
     inflater.inflate(R.menu.指定你的菜单资源, menu);
     return super.onCreateOptionsMenu(menu);
 }

  @Override//额外内容，直接通过方法重写生成菜单栏组件
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
      super.onCreateContextMenu(menu, v, menuInfo);
      menu.add("收藏");
      menu.add("举报");
  }

/************************1JAVA:事件********************************/
//单击事件
 View.OnClickListener click = new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Toast.makeText(MainActivity.this, "单机事件触发了！", Toast.LENGTH_SHORT).show();
     }
 };
 //长按事件实现类
 View.OnLongClickListener longClick=new View.OnLongClickListener(){
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
 View.OnTouchListener touchListener=new View.OnTouchListener(){
     @Override
     public boolean onTouch(View v, MotionEvent event) {
         if (event.getAction()==MotionEvent.ACTION_DOWN){
             Toast.makeText(MainActivity.this, "按下触摸事件触发了！", Toast.LENGTH_SHORT).show();
         }
         if (event.getAction()==MotionEvent.ACTION_UP){
             Toast.makeText(MainActivity.this, "抬起触摸事件触发了！", Toast.LENGTH_SHORT).show();
         }
         return true;
     }
 };

/************************1JAVA:ActionBar********************************/
ActionBar actionBar = getActionBar();
//设置ActionBar的隐藏和显示
actionBar.hide();
actionBar.show();
//设置ActionBar的标题不显示
getSupportActionBar().setDisplayShowTitleEnabled(false)

/************************1JAVA:对话框********************************/
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

        flipper = (ViewFlipper) findViewById(R.id.flipper); //获取ViewFlipper
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            flipper.addView(imageView); //加载图片
        }
```

### 大全XML

```java
<!--
布局管理器
    相对布局管理器 RelativeLayout
    线性布局管理器 LinearLayout
    帧式布局管理器 FrameLayout
    表格布局管理器 TableLayout
    网格布局管理器 GridLayout
普通UI组件
        1.文本框组件 TextView
        2.编辑框组件 EditText
        3.普通按钮 Button
        4.图片按钮 ImageButton
        5.单选按钮 RadioGroup RadioButton
        6.复选框 CheckBox
        7.日期选择器 DatePicker | 时间选择器 TimePicker | 计时器 Chronometer
高级UI组件
    一、进度条 ProgressBar
    二、拖动条 SeekBar
    三、星级评分条
    四、图像视图 ImageView
    五、图像切换器 ImageSwitcher
    六、网格视图 GridView
    七、下拉列表框  Spinner
    八、列表视图 ListView
    九、滚动视图 ScrollView(竖直方向滚动) HorizontalScrollView(垂直方向上滚动)
    十、选项卡
-->

<!--
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

<!--
按钮、文本框、编辑框、单选按钮、复选按钮、图片按钮(比较特殊，没有text属性)
    //配置和其父容器关系
    android:layout_width="match_parent|warp_content"
    android:layout_height="match_parent|warp_content"
    //配置文本属性
    text
        授权并登录 @string/demo1
    textSize
        20dp
    textColor
        # ffffff
    padding：文本框UI组件和文本的内边距
        10dp
    textScaleX:设置文本间的间距，值是浮点数
        3.0
    lineSpacingExtra：设置文本间的行间距
        2dp
-->

        设置水平滚动条的drawable（如颜色）：android:scrollbarThumbHorizontal
        设置垂直滚动条的drawable（如颜色）：android:scrollbarThumbVertical
        设置水平滚动条背景（轨迹）的色drawable（如颜色）：android:scrollbarTrackHorizontal
        设定滚动条宽度：android:scrollbarSize
        ScrollView滚动条不显示：android:scrollbars="none"
        ScrollView滚动条恒显示：android:fadeScrollbars="false"
```











