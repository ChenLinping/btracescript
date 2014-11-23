//��������map����list��Сsize()
import static com.sun.btrace.BTraceUtils.*;

import java.lang.reflect.Field;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace
public class MTManagerTrace {
        @TLS
        private static long startTime = 0;

        /*
 *       * ע����������������Ǿ����ʵ���࣬�����ǽӿڡ�
 *               */
        private static Field sizeField =
                    field("java.util.ArrayList", "size");

        @OnMethod(clazz = "com.alibaba.timor.core.MTManagaerImpl", method = "fetchRoutedDetail")
        public static void startMethod() {
                startTime = timeNanos();
        }

        @OnMethod(clazz = "com.alibaba.timor.core.MTManagaerImpl", method = "fetchRoutedDetail", location = @Location(Kind.RETURN))
        /*
 *       * ����Ĳ�������Ҫ��Դ������������һ�£�һ���ǽӿڶ����Ǿ����ʵ���ࡣ
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
