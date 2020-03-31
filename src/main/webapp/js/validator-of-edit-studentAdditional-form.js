function validateEditAuthor(form) {
	if(!validateForm(form, [{
		id: "studentYear",
		message: "Поле «StudentYear» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "sum",
		message: "Поле «Сумма» не заполнено",
		checker: checkNotEmpty
	}, {
		id: "reason",
		message: "Поле «Причина» не заполнено",
		checker: checkNotEmpty
	},
	{
		id: "month",
		message: "Поле «Месяц» не заполнено",
		checker: checkNotEmpty
	},
	{
		id: "isProved",
		message: "Поле «Одобренна» не заполнено",
		checker: checkNotEmpty
	},
	{
		id: "dateOfProve",
		message: "Поле «Дата Одобрения» не заполнено",
		checker: checkNotEmpty
	},])) {
		return false;
	}
	return true;
}
