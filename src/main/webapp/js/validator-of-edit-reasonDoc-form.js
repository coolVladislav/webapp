function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "number",
		message: "Поле «Номер» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "docName",
		message: "Поле «Описание» не заполнено",
		checker: checkNotEmpty
	},])) {
		return false;
	}
	return true;
}
