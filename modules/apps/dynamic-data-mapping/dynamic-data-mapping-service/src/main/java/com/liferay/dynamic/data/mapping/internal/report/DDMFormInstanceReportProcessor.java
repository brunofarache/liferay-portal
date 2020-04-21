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

package com.liferay.dynamic.data.mapping.internal.report;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceReportLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

/**
 * @author Marcos Martins
 */
public class DDMFormInstanceReportProcessor {

	public DDMFormInstanceReportProcessor(
		DDMFormInstanceReportLocalService ddmFormInstanceReportLocalService) {

		_ddmFormInstanceReportLocalService = ddmFormInstanceReportLocalService;
	}

	public JSONObject computeFormRecordInReport(
			DDMFormInstanceRecord ddmFormInstanceRecord)
		throws PortalException {

		DDMFormValues ddmFormValues = ddmFormInstanceRecord.getDDMFormValues();

		DDMFormInstanceReport ddmFormInstanceReport =
			_ddmFormInstanceReportLocalService.getFormInstanceReport(
				ddmFormInstanceRecord.getFormInstanceId());

		JSONObject reportDataJSONObject = JSONFactoryUtil.createJSONObject(
			ddmFormInstanceReport.getData());

		for (DDMFormFieldValue ddmFormFieldValue :
				ddmFormValues.getDDMFormFieldValues()) {

			JSONObject reportFieldJSONObject =
				reportDataJSONObject.getJSONObject(ddmFormFieldValue.getName());

			if (reportFieldJSONObject == null) {
				reportFieldJSONObject = _createFieldSummary(ddmFormFieldValue);

				reportDataJSONObject.put(
					ddmFormFieldValue.getName(), reportFieldJSONObject);
			}

			if (StringUtil.equals(ddmFormFieldValue.getType(), "radio")) {
				JSONObject reportFieldValuesJSONObject =
					reportFieldJSONObject.getJSONObject("values");

				Value value = ddmFormFieldValue.getValue();

				for (Locale availableLocale : value.getAvailableLocales()) {
					String valueString = value.getString(availableLocale);

					reportFieldValuesJSONObject.put(
						valueString,
						reportFieldValuesJSONObject.getInt(valueString) + 1);
				}
			}
		}

		return reportDataJSONObject;
	}

	private JSONObject _createFieldSummary(
		DDMFormFieldValue ddmFormFieldValue) {

		JSONObject jsonObject = JSONUtil.put(
			"values", JSONFactoryUtil.createJSONObject());

		return jsonObject.put("type", ddmFormFieldValue.getType());
	}

	private final DDMFormInstanceReportLocalService
		_ddmFormInstanceReportLocalService;

}