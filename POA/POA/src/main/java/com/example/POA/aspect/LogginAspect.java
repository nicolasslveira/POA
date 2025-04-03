package com.example.POA.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {
    private final Logger log= LoggerFactory.getLogger(this.getClass());
    @Pointcut("within(com.example.POA.controller)")
    public void controllerMethod(){

    }
    @Pointcut("within(com.example.POA.service)")
    public void serviceMethod(){

    }
    @Before("controllerMethod() || serviceMethod()")
    public void logBefore(JoinPoint joinPoint){
        log.info("Iniciando execução de metodo: {} - arg: {}",
                joinPoint.getSignature().getName(),joinPoint.getArgs());
    }
    @AfterReturning(pointcut = "controllerMethod() || serviceMethod()",
            returning = "resultadp")
    public void logAfterReturning(JoinPoint joinpoint, Object resultado){
        log.info("Metodo: {} executado com sucesso - Retorno: {}",
                joinpoint.getSignature().getName(),resultado);
    }
    @AfterThrowing(pointcut = "controlleMethod() || serviceMethod()",
            throwing = "throwable")
    public void logAfterThowing (JoinPoint joinPoint, Throwable throwable){
        log.info("Exceção no metodo {} - Menssagem: {}",
                joinPoint.getSignature().getName(),throwable.getMessage());
    }
}
