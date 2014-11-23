import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;

@BTrace public class ClassFoundErrorTrace{
   @OnMethod(
       clazz="java.lang.NoClassDefFoundException",
       method="<init>"
   )
   public static void traceNoClassDefFoundException(){
        jstack();
   }
   @OnMethod(
       clazz="java.lang.ClassNotFoundException",
       method="<init>"
   )
   public static void traceClassNotFoundException(){
        jstack();
   }
}
