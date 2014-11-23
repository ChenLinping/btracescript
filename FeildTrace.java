//解析返回map或者list大小size()
import static com.sun.btrace.BTraceUtils.*;

import java.lang.reflect.Field;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace
public class MTManagerTrace {
        @TLS
        private static long startTime = 0;

        /*
 *       * 注意这里的类名必须是具体的实现类，不能是接口。
 *               */
        private static Field sizeField =
                    field("java.util.ArrayList", "size");

        @OnMethod(clazz = "com.alibaba.timor.core.MTManagaerImpl", method = "fetchRoutedDetail")
        public static void startMethod() {
                startTime = timeNanos();
        }

        @OnMethod(clazz = "com.alibaba.timor.core.MTManagaerImpl", method = "fetchRoutedDetail", location = @Location(Kind.RETURN))
        /*
 *       * 这里的参数类名要与源方法声明保持一致，一般是接口而不是具体的实现类。
 *               */
        public static void endMethod(@Return java.util.List lst) {
                print(strcat(strcat(name(probeClass()), "."), probeMethod()));
                print("   [");
                print(strcat("Time taken : ", str((timeNanos() - startTime)
                                / (1000 * 1000))));
                println("]");

                println(str(sizeField));
                printFields(lst);
                printFields(get(sizeField,lst));
        }
}
