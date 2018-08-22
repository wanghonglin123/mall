import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * StringJoiner使用示例
 * Created by wang.honglin on 2018/8/8.
 */
public class StringJoinerTest {
    public static void main(String[] args) {
        // 创建一个带有,分隔符字符串连接器，结果1,2,3
        StringJoiner sj = new StringJoiner(",").setEmptyValue("123");
        System.out.println(sj.toString());
        sj.add("1").add("2").add("3");
        System.out.println(sj.toString());

        // 创建一个带有,分隔符,s前缀，d后缀的字符串连接器，结果s1,,2,,3d
        StringJoiner sj1 = new StringJoiner(",", "s", "d");
        sj.add("1").add("2").add("3");
        sj1.add("he");
        System.out.println(sj1.toString());

        System.out.println(sj.merge(sj1));

        System.out.println(sj.setEmptyValue(""));
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        String commaSeparatedNumbers = numbers.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(", "));
        System.out.println(commaSeparatedNumbers);
    }
}
