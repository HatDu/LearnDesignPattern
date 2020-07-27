package ProxyTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class HelloInvocationHandler implements InvocationHandler{
    private PrintHello printHello;

    public HelloInvocationHandler(PrintHello printHello) {
        this.printHello = printHello;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在输出之前进行拦截");
        Object rst = method.invoke(printHello, args);
        System.out.println("在输出之后进行拦截");
        return rst;
    }
}
public class Proxy_01 {
    public static void main(String[] args) {
        final PrintHello hello = new PrintHello();
        IPrintHello printHello = (IPrintHello)Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                new HelloInvocationHandler(hello));
        printHello.print("Hello world");
    }
}
