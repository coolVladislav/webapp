function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "studentYear",
		message: "Поле «Год» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "reason",
		message: "Поле «Причина» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "dateOfOrder",
		message: "Поле «Дата» не заполнено",
		checker: checkNotEmpty
	},])) {
		return false;
	}
	return true;
}
