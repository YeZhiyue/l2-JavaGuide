/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/8/12
 * Time 12:00
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/8/12
 * Time 12:00
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class Person {


    private Integer age;
    private String name;

    /**
     * 这里我们特殊实现equals方法，只对用户名称进行比较来判断是否相等
     */
    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    /**
     * 这里hashCode正常实现
     */
    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Person p1 = new Person(20, "张三");
        Person p2 = new Person(21, "张三");
        // equals比较
        // true
        System.out.println(p1.equals(p2));
        // hashCode比较
        // false
        System.out.println(p1.hashCode() == p2.hashCode());
        // 结果：题目的答案被推翻，equals比较相等，但是hashCode不同
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
