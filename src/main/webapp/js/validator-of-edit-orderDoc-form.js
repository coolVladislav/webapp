function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "order",
		message: "Поле «Заявка» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "reasonDoc",
		message: "Поле «Документы Причины» не заполнено",
		checker: checkNotEmpty
	},])) {
		return false;
	}
	return true;
}
