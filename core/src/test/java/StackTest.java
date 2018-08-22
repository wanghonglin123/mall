import java.util.Stack;

/**
 * Created by wang.honglin on 2018/8/8.
 * 堆栈数据结构，应优先使用Deque接口及其实现，提供了更完整和一致的LIFO(后进先出)堆栈
 */
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        // 移除此堆栈顶部的对象（后进先出），并且返回该移除的对象。如果堆栈为空，将抛出EmptyStackException
        // 使用pop（）方法的时候，使用empty()判断一下，否则可能出现异常
        stack.pop();

        // 返回堆栈顶部对象，但是不移除该对象
        stack.peek();

        // 判断堆栈是否为空
        stack.empty();

        // 查询该对象在堆栈中的位置，堆栈index从1开始，顺序从顶部到底部，上面示例：比如3的index=1，1的index=3
        stack.search(5);
        stack.add("hhhh");

        while(! stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
