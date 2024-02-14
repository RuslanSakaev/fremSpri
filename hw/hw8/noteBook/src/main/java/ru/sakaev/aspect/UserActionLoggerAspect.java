package ru.sakaev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sakaev.model.UserActionLog;
import ru.sakaev.repository.UserActionLogRepository;

@Aspect
@Component
public class UserActionLoggerAspect {

    private final UserActionLogRepository userActionLogRepository; // репозиторий для работы с записями журнала действий

    @Autowired
    public UserActionLoggerAspect(UserActionLogRepository userActionLogRepository) {
        this.userActionLogRepository = userActionLogRepository;
    }

    @Pointcut("@annotation(ru.sakaev.annotation.TrackUserAction)")
    public void trackUserAction() {}

    @AfterReturning(pointcut = "trackUserAction()", returning = "result")
    public void logUserAction(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        String arguments = "";
        if (args.length > 0) {
            arguments = args[0].toString(); // преобразование аргументов в строку
        }
        System.out.println("User action: Method " + methodName + " in class " + className + " called with arguments: " + arguments);

        UserActionLog log = new UserActionLog(methodName, className, arguments); // Создание объекта записи журнала действий пользователя
        userActionLogRepository.save(log); // Сохранение записи в базу данных
    }
}

