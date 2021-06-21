package takeo.spring_basic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@component <- componentスキャンでコンテーナーに登録してもいいし直接settingファイルに書いてもいい。
public class TimeTraceAop {

    @Around("execution(* takeo.spring_basic..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        // joinPoint.toString() => 실행되는 메서드를 찍어볼수있음.
        System.out.println("START: = " + joinPoint.toString());
        try {
            // 実行するメソッド
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
