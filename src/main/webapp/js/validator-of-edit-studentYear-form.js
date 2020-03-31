function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "course",
		message: "Поле «Курс» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "group",
		message: "Поле «Группа» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "isFreeibe",
		message: "Поле «Бесплатник» не заполнено",
		checker: checkNotEmpty
	},
	{
		id: "student",
		message: "Поле «Студент» не заполнено",
		checker: checkNotEmpty
	},
	{
		id: "year",
		message: "Поле «Год» не заполнено",
		checker: checkNotEmpty
	},])) {
		return false;
	}
	return true;
}
