function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "number",
		message: "Поле «Номер» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "note",
		message: "Поле «Описание» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "max_coef",
		message: "Поле «Максимальный коефициент в б.в.» не заполнено",
		checker: checkNotEmpty
	},])) {
		return false;
	}
	return true;
}
