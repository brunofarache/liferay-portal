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

import com.liferay.dynamic.data.mapping.report.DDMFormFieldTypeReportProcessor;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

/**
 * @author Marcos Martins
 */
public abstract class BaseDDMFormFieldTypeReportProcessor
	implements DDMFormFieldTypeReportProcessor {

	@Override
	public JSONObject process(
			DDMFormFieldValue ddmFormFieldValue, long formInstanceRecordId,
			JSONObject formInstanceReportDataJSONObject,
			String formInstanceReportEvent)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			formInstanceReportDataJSONObject.toJSONString());

		if (!jsonObject.has(ddmFormFieldValue.getName())) {
			jsonObject.put(
				ddmFormFieldValue.getName(),
				JSONUtil.put(
					"type", ddmFormFieldValue.getType()
				).put(
					"values", JSONFactoryUtil.createJSONObject()
				));
		}

		return doProcess(
			ddmFormFieldValue, formInstanceRecordId, jsonObject,
			formInstanceReportEvent);
	}

	protected abstract JSONObject doProcess(
			DDMFormFieldValue ddmFormFieldValue, long formInstanceRecordId,
			JSONObject formInstanceReportDataJSONObject,
			String formInstanceReportEvent)
		throws Exception;

}