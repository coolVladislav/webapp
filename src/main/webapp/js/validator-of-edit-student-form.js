function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "last_name",
		message: "Поле «Фамилия» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "first_name",
		message: "Поле «Имя» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "patronymic",
		message: "Поле «Отчество» не заполнено",
		checker: checkNotEmpty
	},])) {
		return false;
	}
	return true;
}
