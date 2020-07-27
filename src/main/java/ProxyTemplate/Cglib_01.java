package ProxyTemplate;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class HelloInterceptor implements MethodInterceptor{
    PrintHello printHello;

    public HelloInterceptor(PrintHello printHello) {
        this.printHello = printHello;
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("在输出之前进行拦截");
        Object rst = method.invoke(printHello, args);
        System.out.println("在输出之后进行拦截");
        return rst;
    }
}

public class Cglib_01 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        PrintHello printHello = new PrintHello();
        enhancer.setSuperclass(printHello.getClass());
        enhancer.setCallback(new HelloInterceptor(printHello));
        PrintHello proxyHello = (PrintHello) enhancer.create();
        proxyHello.print("Hello world");
    }
}
