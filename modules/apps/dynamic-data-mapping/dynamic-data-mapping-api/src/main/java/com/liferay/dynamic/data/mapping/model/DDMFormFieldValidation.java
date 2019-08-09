/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.petra.lang.HashUtil;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldValidation implements Serializable {

	public DDMFormFieldValidation() {
	}

	public DDMFormFieldValidation(
		DDMFormFieldValidation ddmFormFieldValidation) {

		_expression = ddmFormFieldValidation._expression;
		_localizedErrorMessage = ddmFormFieldValidation._localizedErrorMessage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormFieldValidation)) {
			return false;
		}

		DDMFormFieldValidation ddmFormFieldValidation =
			(DDMFormFieldValidation)obj;

		if (Objects.equals(
				_localizedErrorMessage,
				ddmFormFieldValidation._localizedErrorMessage) &&
			Objects.equals(_expression, ddmFormFieldValidation._expression)) {

			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 #getLocalizedErrorMessage()}
	 */
	@Deprecated
	public String getErrorMessage() {
		return _localizedErrorMessage.getString(
			_localizedErrorMessage.getDefaultLocale());
	}

	public String getExpression() {
		return _expression;
	}

	public LocalizedValue getLocalizedErrorMessage() {
		return _localizedErrorMessage;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _localizedErrorMessage);

		return HashUtil.hash(hash, _expression);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 #setLocalizedErrorMessage(LocalizedValue)}
	 */
	@Deprecated
	public void setErrorMessage(String errorMessage) {
		LocalizedValue localizedValue = new LocalizedValue();

		localizedValue.addString(
			localizedValue.getDefaultLocale(), errorMessage);

		setLocalizedErrorMessage(localizedValue);
	}

	public void setExpression(String expression) {
		_expression = expression;
	}

	public void setLocalizedErrorMessage(LocalizedValue localizedErrorMessage) {
		_localizedErrorMessage = localizedErrorMessage;
	}

	private String _expression;
	private LocalizedValue _localizedErrorMessage;

}