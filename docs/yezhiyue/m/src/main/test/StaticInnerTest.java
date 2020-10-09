interface Destination {
    String readLabel();
}

/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/8/11
 * Time 15:05
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/8/11
 * Time 15:05
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class StaticInnerTest {
    // 创建内部static类对象，不需要通过创建一个外部类对来调用

}

abstract class Contents {
    abstract public int value();
}

class Parcel10 {
    private static class PContents extends Contents {
        private int i = 11;

        public static Destination dest(String s) {
            return new PDestination(s);
        }

        public static Contents cont() {
            return new PContents();
        }

        public static void main(String[] args) {
            Contents c = cont();
            Destination d = dest("Tan");
        }

        public int value() {
            return i;
        }

        protected static class PDestination implements Destination {
            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }
    }
}
