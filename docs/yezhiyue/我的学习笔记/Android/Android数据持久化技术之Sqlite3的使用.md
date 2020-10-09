https://blog.csdn.net/qq_43230007/article/details/105005060

# ==目录==

- 一、Sqlite简单的使用
- 二、Sqlite表创建
- 三、Sqlite表升级
- 四、Sqlite的存储数据（insert）
- 五、Sqlite更新和删除（update、delete）
- 六、Sqlite查询（query）
- 七、Sqlite聚合函数使用(count、sum、max、min、average)

> 说明：这些都是Sqlite中比较传统的用法，使用起来比较麻烦。博主推荐使用LitPal会大大方便sqlite的数据库操作，如果有兴趣的小伙伴可参考下面两篇文章。
> 
> 文章一（简短叙述）：[https://blog.csdn.net/qq_43230007/article/details/105005060](https://blog.csdn.net/qq_43230007/article/details/105005060)
> 文章二（详细述说原理）：[https://blog.csdn.net/sinyu890807/category_9262963.html](https://blog.csdn.net/sinyu890807/category_9262963.html)

## `一、Sqlite简单的使用`

1. 进入adb目录，输入 adb shell

2. 进入data/data目录，然后进入指定的包路径 如:com.example.provider

3. 进入databases目录，然后 ls 查看数据库

4. 选择(或者创建)一个数据库进入 sqlite3 {databaseName}

5. 一些简单常用的命令

- .table 查询当前数据库下面的所有表
- .help 查看帮助
- pragma table_info(TABLE_NAME) 查询选中表的字段属性，等价于MySql中的desc {tableName}命令
- .mode line 切换显示模式

6. 一些常用Sql的CRUD命令，学过SQL的应该都会(CRUD)

- create table {tableName}(id integer,name text);
- insert into {tabeName}[(...)] values(...);
- delete from {tableName} where {条件};
- update {tableName} set {...} where {条件};
- drop table {table_name};

## `二、Sqlite表创建`

1. 需要创建一个继承自SQLiteOpenHelper的类，然后重写其中的onCreate方法并且创建一张表

```java
public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String CREATE_NEWS = "create table news ("
			+ "id integer primary key autoincrement, "
			+ "title text, "
			+ "content text, "
			+ "publishdate integer,"
			+ "commentcount integer)";

	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_NEWS);
	}
    ...
}
```

2. 通常我们在Activity类中会通过这个方法进行数据库的初始化

```java
//1.数据库初始化操作，参数一是context，参数二是数库库名称，参数三是当前版本version
SQLiteOpenHelper dbHelper = new MySQLiteHelper(this, "demo.db", null, 1);
//2.获取数据库操作类对象SQLiteDatabase，通过这个对象我们就可以进行CRUD操作了
SQLiteDatabase db = dbHelper.getWritableDatabase();
```

## `三、Sqlite表升级`

- 场景：当数据库的表的数量或者一些列字段需要变更的时候，我们需要对数据库进行版本迭代，实现方式如下

1. 传统方法的升级逻辑通常是通过重写SQLiteOpenHelper中的onUpgrade方法来根据版本迭代逻辑进行更新

- 为何不在onCreate方法中进行更新，因为该方法仅仅在软件第一次启动时执行一次，是不会再执行第二次的。

- 在Android机制中数据库更新(通常指数据表的增加，表的字段的改变)逻辑通常是依赖于版本更迭的

```java
public class MySQLiteHelper extends SQLiteOpenHelper {

	......

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_NEWS);
		db.execSQL(CREATE_COMMENT);
	}

	@Override//根据newVersion的迭代逻辑进行数据库更新
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
			db.execSQL(CREATE_COMMENT);
		default:
		}
	}

}
```

```java
//进行版本迭代，需要更改版本号。第三个参数变为2
SQLiteOpenHelper dbHelper = new MySQLiteHelper(this, "demo.db", null, 2);
SQLiteDatabase db = dbHelper.getWritableDatabase();
```

- 注意：Sqlite语法中没有直接删除列的操作，通常无视需要删除的列（如果需要删除需要绕弯）

## `四、Sqlite的存储数据（insert）`

1. insert方法

```java
//方法，第一个参数是表名，第二个参数通常都用不到，直接传null，第三个参数则是一个封装了待存储数据的ContentValues对象
//返回该行插入的数据id
public long insert(String table, String nullColumnHack, ContentValues values)
//示例
//其中，调用ContentValues的put()方法来添加待存储数据，put()方法接收两个参数，第一个参数是数据库表中对应的列名，第二个参数就是要存储的值
SQLiteDatabase db = dbHelper.getWritableDatabase();
ContentValues values = new ContentValues();
values.put("title", "这是一条新闻标题");
values.put("content", "这是一条新闻内容");
values.put("publishdate", System.currentTimeMillis());
long id = db.insert("news", null, values);
```

## `五、Sqlite更新和删除（update、delete）`

1. update()方法的使用

```java
//update()方法接收四个参数，第一个参数是表名，第二个参数是一个封装了待修改数据的ContentValues对象，第三和第四个参数用于指定修改哪些行，对应了SQL语句中的where部分。
public int update(String table, ContentValues values, String whereClause, String[] whereArgs)
//示例
//update news set title='今日iPhone6发布' where id=2;
SQLiteDatabase db = dbHelper.getWritableDatabase();
ContentValues values = new ContentValues();
values.put("title", "今日iPhone6发布");
db.update("news", values, "id = ?", new String[] {"2"});
```

2. delete()方法

```java
//delete()方法接收三个参数，第一个参数同样是表名，第二和第三个参数用于指定删除哪些行，对应了SQL语句中的where部分。
public int delete(String table, String whereClause, String[] whereArgs)
//delete from news where commentcount=0;
SQLiteDatabase db = dbHelper.getWritableDatabase();
db.delete("news", "commentcount = ?", new String[] {"0"});
```

## `六、Sqlite查询（query）`


```java
//1. 通过原生方法进行查询
//rawQuery()方法接收两个参数，第一个参数接收的就是一个SQL字符串，第二个参数是用于替换SQL语句中占位符（?）的字符串数组。rawQuery()方法返回一个Cursor对象，所有查询到的数据都是封闭在这个对象当中的，我们只要一一取出就可以了。
public Cursor rawQuery(String sql, String[] selectionArgs)
//2. 通过重载方法进行查询，重载形式还有很多
//第二个参数用于指定去查询哪几列，如果不指定则默认查询所有列。第三、第四个参数用于去约束查询某一行或某几行的数据，不指定则默认是查询所有行的数据。第五个参数用于指定需要去group by的列，不指定则表示不对查询结果进行group by操作。第六个参数用于对group by之后的数据进行进一步的过滤，不指定则表示不进行过滤。第七个参数用于指定查询结果的排序方式，不指定则表示使用默认的排序方式。
public Cursor query(String table, String[] columns, String selection,
            String[] selectionArgs, String groupBy, String having,
            String orderBy)
//示例
SQLiteDatabase db = dbHelper.getWritableDatabase();
Cursor cursor = db.query("news", null, "commentcount>?", new String[]{"0"}, null, null, null);
List<News> newsList = new ArrayList<News>();
if (cursor != null && cursor.moveToFirst()) {
	do {
		int id = cursor.getInt(cursor.getColumnIndex("id"));
		String title = cursor.getString(cursor.getColumnIndex("title"));
		String content = cursor.getString(cursor.getColumnIndex("content"));
		Date publishDate = new Date(cursor.getLong(cursor.getColumnIndex("publishdate")));
		int commentCount = cursor.getInt(cursor.getColumnIndex("commentcount"));
		News news = new News();
		news.setId(id);
		news.setTitle(title);
		news.setContent(content);
		news.setPublishDate(publishDate);
		news.setCommentCount(commentCount);
		newsList.add(news);
	} while (cursor.moveToNext());
}

```

## `七、Sqlite聚合函数使用(count、sum、max、min、average)`

- 虽说是聚合函数，但它的用法其实和传统的查询还是差不多的，即仍然使用的是select语句。但是在select语句当中我们通常不会再去指定列名，而是将需要统计的列名传入到聚合函数当中，那么执行select语句使用的还是SQLiteDatabase中的rawQuery()方法。

1. count()

```java
//其中count(1)就是用于去统计一共有多少行的。当然这里并不一定要用count(1)，使用count(*)或者count(主键)都可以。
SQLiteDatabase db = dbHelper.getWritableDatabase();
Cursor c = db.rawQuery("select count(1) from news", null);
if (c != null && c.moveToFirst()) {
	int count = c.getInt(0);
	Log.d("TAG", "result is " + count);
}
c.close();
```

2. sum()

```java
//
SQLiteDatabase db = dbHelper.getWritableDatabase();
Cursor c = db.rawQuery("select sum(commentcount) from news", null);
if (c != null && c.moveToFirst()) {
	int count = c.getInt(0);
	Log.d("TAG", "result is " + count);
}
c.close();
```


