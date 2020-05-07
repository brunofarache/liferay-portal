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

import com.liferay.dynamic.data.mapping.constants.DDMFormInstanceReportConstants;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Marcos Martins
 */
@RunWith(MockitoJUnitRunner.class)
public class CheckboxMultipleDDMFormFieldTypeReportProcessorTest
	extends PowerMockito {

	@Before
	public void setUp() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	@Test
	public void testProcess1() throws Exception {
		CheckboxMultipleDDMFormFieldTypeReportProcessor
			checkboxMultipleDDMFormFieldTypeReportProcessor =
				new CheckboxMultipleDDMFormFieldTypeReportProcessor();

		DDMFormFieldValue ddmFormFieldValue = mock(DDMFormFieldValue.class);

		when(
			ddmFormFieldValue.getType()
		).thenReturn(
			DDMFormFieldType.CHECKBOX_MULTIPLE
		);
		when(
			ddmFormFieldValue.getName()
		).thenReturn(
			"fieldName"
		);

		Value value = new LocalizedValue();

		value.addString(value.getDefaultLocale(), "[\"test1\"]");

		value.setDefaultLocale(LocaleUtil.US);

		when(
			ddmFormFieldValue.getValue()
		).thenReturn(
			value
		);

		JSONObject jsonObject =
			checkboxMultipleDDMFormFieldTypeReportProcessor.process(
				ddmFormFieldValue, JSONFactoryUtil.createJSONObject(),
				DDMFormInstanceReportConstants.EVENT_ADD_RECORD_VERSION);

		JSONObject fieldJSONObject = jsonObject.getJSONObject("fieldName");

		JSONObject fieldValueJSONObject = fieldJSONObject.getJSONObject(
			"values");

		Assert.assertEquals(
			DDMFormFieldType.CHECKBOX_MULTIPLE,
			fieldJSONObject.getString("type"));

		Assert.assertEquals(1, fieldValueJSONObject.getLong("test1"));
	}

	@Test
	public void testProcess2() throws Exception {
		CheckboxMultipleDDMFormFieldTypeReportProcessor
			checkboxMultipleDDMFormFieldTypeReportProcessor =
				new CheckboxMultipleDDMFormFieldTypeReportProcessor();

		DDMFormFieldValue ddmFormFieldValue = mock(DDMFormFieldValue.class);

		when(
			ddmFormFieldValue.getType()
		).thenReturn(
			DDMFormFieldType.CHECKBOX_MULTIPLE
		);
		when(
			ddmFormFieldValue.getName()
		).thenReturn(
			"fieldName"
		);

		Value value = new LocalizedValue();

		value.addString(value.getDefaultLocale(), "[\"test1\", \"test2\"]");

		value.setDefaultLocale(LocaleUtil.US);

		when(
			ddmFormFieldValue.getValue()
		).thenReturn(
			value
		);

		JSONObject reportJSONObject = JSONUtil.put(
			ddmFormFieldValue.getName(),
			JSONUtil.put(
				"type", DDMFormFieldType.CHECKBOX_MULTIPLE
			).put(
				"values", JSONFactoryUtil.createJSONObject("{test1 : 1}")
			));

		JSONObject jsonObject =
			checkboxMultipleDDMFormFieldTypeReportProcessor.process(
				ddmFormFieldValue, reportJSONObject,
				DDMFormInstanceReportConstants.EVENT_ADD_RECORD_VERSION);

		JSONObject fieldJSONObject = jsonObject.getJSONObject("fieldName");

		JSONObject fieldValueJSONObject = fieldJSONObject.getJSONObject(
			"values");

		Assert.assertEquals(2, fieldValueJSONObject.getLong("test1"));
		Assert.assertEquals(1, fieldValueJSONObject.getLong("test2"));
	}

}