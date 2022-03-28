package com.aop.cosmeticsonlinestore.aspects;

import com.aop.cosmeticsonlinestore.model.User;

public aspect UserServiceAspect {
    pointcut saveToDatabase(User user) : execution (* com.aop.cosmeticsonlinestore.service.UserService.save(User))
            && args(user);

    before(User user): saveToDatabase(user) {
        System.out.println("Saving to database user with username " + user.getUsername() + " and first name " + user.getFirstName());
    }
}
