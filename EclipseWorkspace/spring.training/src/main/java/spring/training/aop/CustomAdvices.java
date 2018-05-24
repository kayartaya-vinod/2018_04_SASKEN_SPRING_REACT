package spring.training.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import spring.training.dao.DaoException;
import spring.training.entity.Contact;

@Component
@Aspect
public class CustomAdvices {

	// advice
	@Before("execution(* spring..ContactsDao.findBy*(..))")
	public void print(JoinPoint jp) {
		System.out.println("executing " + jp.getSignature().getName() + " method with arguments: "
				+ Arrays.toString(jp.getArgs()));
	}

	@Around("execution(* spring..*.findById(..))")
	public Object convertContactNameToUpperCase(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = pjp.proceed();
		// System.out.println("Got an instnaceof " + obj.getClass());
		// System.out.println("Returned value is " + obj.toString());
		if (obj instanceof Contact) {
			Contact c = (Contact) obj;
			c.setFirstName(c.getFirstName().toUpperCase());
			c.setLastName(c.getLastName().toUpperCase());
		}
		return obj;
	}

	@AfterThrowing(pointcut = "execution(* spring..*Dao.*(..))", throwing = "ex")
	public void convertToDaoException(Exception ex) throws DaoException {
		throw new DaoException(ex);
	}
}






