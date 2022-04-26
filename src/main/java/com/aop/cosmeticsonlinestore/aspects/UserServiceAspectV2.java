package com.aop.cosmeticsonlinestore.aspects;
import com.aop.cosmeticsonlinestore.model.request.AuthRequest;
import com.aop.cosmeticsonlinestore.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;

@Aspect
public class UserServiceAspectV2 {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceAspectV2.class);

    @AfterReturning(
            pointcut = "execution(* com.aop.cosmeticsonlinestore.service.UserService.save(..))",
            returning = "result")
    public void saveUser(JoinPoint jp, Object result) {
        logger.info("UserService - User saved to database : " + result);
    }


    @AfterThrowing(
            pointcut = "execution(* com.aop.cosmeticsonlinestore.service.UserService.login(..)) && args(authenticationManager, request)",
            throwing = "error")
    public void badCredentialsLogin(AuthenticationManager authenticationManager, AuthRequest request, JoinPoint jp, Throwable error) {
        logger.debug("Login was not successfull " + error.getMessage());
    }
}
