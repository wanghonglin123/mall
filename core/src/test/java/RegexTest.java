import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wang.honglin on 2018/7/31.
 * 正则表达式使用详解
 * test() [] 使用 ^ 符号使用
 */
public class RegexTest {
    /**
     * 正则表达式符号使用
     * [] test()
     * @param args
     */
    public static void main(String[] args) {
        /**
         * [] 只能用于匹配一个字符，[0-9] [a-z] 匹配0-9,a-z的任意一个字符多可以匹配成功。
         * ^ 取非结果，如果[^0-9],那么0-9范围内的多不会匹配，其他只要是单个字符的多会匹配成功
         */
        String str = "abc1";
        String regEx = "abc[0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());

        // \ 转义符作用，可以用于匹配\n(换行符)
        /**
         * 元字符	说明
         [\b]	回退（删除）一个字符
         \f	换页符
         \n	换行符
         \r	回车符
         \t	制表符
         \v	垂直制表符
         */
        str = "1bv\rc";
        System.out.println(str);
        regEx = "1bv\\rc";
        pattern = Pattern.compile(regEx);
        matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }

    /**
     *
     * [] 只能用于匹配一个字符，[0-9] [a-z] 匹配0-9,a-z的任意一个字符多可以匹配成功。
     * ^ 取非结果，如果[^0-9],那么0-9范围内的多不会匹配，其他只要是单个字符的多会匹配成功
     */
    @org.junit.Test
    public void test(){
        // abcbb abcbbb abc1 匹配结果=false
        String str = "abc1";
        String regEx = "abc[0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());

        //
        str = "1bv\nc";
        System.out.println(str);
        regEx = "1bv\\nc";
        pattern = Pattern.compile(regEx);
        matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }


}
