package validator;

import java.util.HashMap;
import java.util.Map;

import domain.Entity;
import domain.Order;
import domain.OrderDoc;
import domain.Reason;
import domain.ReasonDoc;
import domain.Student;
import domain.StudentAdditional;
import domain.StudentYear;
import domain.User;

public class ValidatorFactory {
	private static Map<Class<? extends Entity>, Class<? extends Validator<?>>> validators = new HashMap<>();

	static {
		validators.put(Order.class, OrderValidator.class);
		validators.put(OrderDoc.class, OrderDocValidator.class);
		validators.put(Reason.class, ReasonValidator.class);
		validators.put(ReasonDoc.class, ReasonDocValidator.class);
		validators.put(Student.class, StudentValidator.class);
		validators.put(StudentAdditional.class, StudentAdditionalValidator.class);validators.put(Reason.class, ReasonValidator.class);
		validators.put(StudentYear.class, StudentYearValidator.class);validators.put(Reason.class, ReasonValidator.class);
		validators.put(User.class, UserValidator.class);
	}

	@SuppressWarnings("unchecked")
	public static <Type extends Entity> Validator<Type> createValidator(Class<Type> entity) {
		try {
			return (Validator<Type>)validators.get(entity).newInstance();
		} catch(InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
