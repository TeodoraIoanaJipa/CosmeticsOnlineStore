package com.aop.cosmeticsonlinestore.aspects;
import com.aop.cosmeticsonlinestore.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class UserServiceAspectV2 {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceAspectV2.class);

    @AfterReturning(
            pointcut = "execution(* UserService.save(..))",
            returning = "result")
    public void saveUser(JoinPoint jp, Object result) {
        logger.info("UserService - User saved to database : " + result);
    }
}
