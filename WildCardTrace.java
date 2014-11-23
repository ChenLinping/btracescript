import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;

@BTrace public class DAOTrace{
    @TLS
    private static long startTime = 0;

    @OnMethod(clazz = "/com\\.alibaba\\.timor\\.dal\\.ibatis\\..*/", method = "/.*/")
    public static void startMethod() {
         startTime = timeNanos();
     }

    @OnMethod(clazz = "/com\\.alibaba\\.timor\\.dal\\.ibatis\\..*/", method = "/.*/", location = @Location(Kind.RETURN))
    public static void endMethod() {

         print(strcat(strcat(name(probeClass()), "."), probeMethod()));
         print("   [");
         print(strcat("Time taken : ", str((timeNanos() - startTime)/(1000*1000))));
         println("]");
     }

    @OnMethod(clazz = "com.alibaba.timor.core.MTManagaerImpl", method = "/.*/")
    public static void startMTMethod() {
         startTime = timeNanos();
     }

    @OnMethod(clazz = "com.alibaba.timor.core.MTManagaerImpl", method = "/.*/", location = @Location(Kind.RETURN))
    public static void endMTMethod() {

         print(strcat(strcat(name(probeClass()), "."), probeMethod()));
         print("   [");
         print(strcat("Time taken : ", str((timeNanos() - startTime)/(1000*1000))));
         println("]");
     }
}
