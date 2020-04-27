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

package com.liferay.dynamic.data.mapping.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalService;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceReportLocalService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceReportPersistence;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Marcos Martins
 */
@RunWith(Arquillian.class)
public class DDMFormInstanceReportLocalServiceTest
	extends BaseDDMServiceTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		Class<?> formInstanceClass = DDMFormInstance.class;

		_classNameId = PortalUtil.getClassNameId(formInstanceClass.getName());
	}

	@Test
	public void testAddFormInstanceReportWhenAddFormInstance()
		throws Exception {

		DDMStructure ddmStructure = addStructure(
			0, _classNameId, null, "Test Structure", null,
			read("ddm-structure-radio-field.xsd"), StorageType.JSON.getValue(),
			DDMStructureConstants.TYPE_DEFAULT);

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm("radio");

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group, TestPropsValues.getUserId());

		_ddmFormInstance = DDMFormInstanceLocalServiceUtil.addFormInstance(
			ddmStructure.getUserId(), ddmStructure.getGroupId(),
			ddmStructure.getStructureId(), ddmStructure.getNameMap(),
			ddmStructure.getNameMap(),
			DDMFormValuesTestUtil.createDDMFormValues(ddmForm), serviceContext);

		DDMFormInstanceReport ddmFormInstanceReport =
			_ddmFormInstanceReportPersistence.findByFormInstanceId(
				_ddmFormInstance.getFormInstanceId());

		Assert.assertNotNull(ddmFormInstanceReport);
	}

	@Test
	public void testUpdateFormInstanceReportWhenAddFormInstanceRecord()
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group, TestPropsValues.getUserId());

		DDMFormInstanceRecord ddmFormInstanceRecord =
			_createDDMFormInstanceRecord(serviceContext);

		DDMFormInstanceReport ddmFormInstanceReport =
			_ddmFormInstanceReportPersistence.findByFormInstanceId(
				ddmFormInstanceRecord.getFormInstanceId());

		JSONObject ddmFormInstanceReportDataJSONObject =
			JSONFactoryUtil.createJSONObject(ddmFormInstanceReport.getData());

		Assert.assertEquals(
			1, ddmFormInstanceReportDataJSONObject.getInt("totalItems"));

		JSONObject radioFieldJSONObject =
			ddmFormInstanceReportDataJSONObject.getJSONObject("radio");

		JSONObject radioFieldValuesJSONObject =
			radioFieldJSONObject.getJSONObject("values");

		Assert.assertEquals(1, radioFieldValuesJSONObject.getInt("value 1"));
	}

	@Test
	public void testUpdateFormInstanceReportWhenDeleteFormInstanceRecord()
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group, TestPropsValues.getUserId());

		DDMFormInstanceRecord ddmFormInstanceRecord =
			_createDDMFormInstanceRecord(serviceContext);

		_ddmFormInstanceRecordLocalService.deleteDDMFormInstanceRecord(
			ddmFormInstanceRecord);

		DDMFormInstanceReport ddmFormInstanceReport =
			_ddmFormInstanceReportPersistence.findByFormInstanceId(
				ddmFormInstanceRecord.getFormInstanceId());

		JSONObject ddmFormInstanceReportDataJSONObject =
			JSONFactoryUtil.createJSONObject(ddmFormInstanceReport.getData());

		Assert.assertEquals(
			0, ddmFormInstanceReportDataJSONObject.getInt("totalItems"));

		JSONObject radioFieldJSONObject =
			ddmFormInstanceReportDataJSONObject.getJSONObject("radio");

		JSONObject radioFieldValuesJSONObject =
			radioFieldJSONObject.getJSONObject("values");

		Assert.assertEquals(0, radioFieldValuesJSONObject.getInt("value 1"));
	}

	@Test
	public void testUpdateFormInstanceReportWhenUpdateFormInstanceRecord()
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group, TestPropsValues.getUserId());

		DDMFormInstanceRecord ddmFormInstanceRecord =
			_createDDMFormInstanceRecord(serviceContext);

		DDMFormValues ddmFormValues = ddmFormInstanceRecord.getDDMFormValues();

		DDMForm ddmForm = ddmFormValues.getDDMForm();

		DDMFormValues newDDMFormValues =
			DDMFormValuesTestUtil.createDDMFormValues(ddmForm);

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"radio",
				new LocalizedValue() {
					{
						addString(LocaleUtil.US, "value 2");
					}
				});

		newDDMFormValues.addDDMFormFieldValue(ddmFormFieldValue);

		_ddmFormInstanceRecordLocalService.updateFormInstanceRecord(
			TestPropsValues.getUserId(),
			ddmFormInstanceRecord.getFormInstanceRecordId(), false,
			newDDMFormValues, serviceContext);

		DDMFormInstanceReport ddmFormInstanceReport =
			_ddmFormInstanceReportPersistence.findByFormInstanceId(
				ddmFormInstanceRecord.getFormInstanceId());

		JSONObject ddmFormInstanceReportDataJSONObject =
			JSONFactoryUtil.createJSONObject(ddmFormInstanceReport.getData());

		Assert.assertEquals(
			1, ddmFormInstanceReportDataJSONObject.getInt("totalItems"));

		JSONObject radioFieldJSONObject =
			ddmFormInstanceReportDataJSONObject.getJSONObject("radio");

		JSONObject radioFieldValuesJSONObject =
			radioFieldJSONObject.getJSONObject("values");

		Assert.assertEquals(0, radioFieldValuesJSONObject.getInt("value 1"));
		Assert.assertEquals(1, radioFieldValuesJSONObject.getInt("value 2"));
	}

	private DDMFormInstanceRecord _createDDMFormInstanceRecord(
			ServiceContext serviceContext)
		throws Exception {

		DDMStructure ddmStructure = addStructure(
			0, _classNameId, null, "Test Structure", null,
			read("ddm-structure-radio-field.xsd"), StorageType.JSON.getValue(),
			DDMStructureConstants.TYPE_DEFAULT);

		DDMForm ddmForm = DDMFormTestUtil.createDDMForm("radio");

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmForm);

		DDMFormFieldValue ddmFormFieldValue =
			DDMFormValuesTestUtil.createDDMFormFieldValue(
				"radio",
				new LocalizedValue() {
					{
						addString(LocaleUtil.US, "value 1");
					}
				});

		ddmFormValues.addDDMFormFieldValue(ddmFormFieldValue);

		_ddmFormInstance = DDMFormInstanceLocalServiceUtil.addFormInstance(
			ddmStructure.getUserId(), ddmStructure.getGroupId(),
			ddmStructure.getStructureId(), ddmStructure.getNameMap(),
			ddmStructure.getNameMap(), ddmFormValues, serviceContext);

		return DDMFormInstanceRecordLocalServiceUtil.addFormInstanceRecord(
			TestPropsValues.getUserId(), group.getGroupId(),
			_ddmFormInstance.getFormInstanceId(), ddmFormValues,
			serviceContext);
	}

	private static long _classNameId;

	@DeleteAfterTestRun
	private DDMFormInstance _ddmFormInstance;

	@Inject
	private DDMFormInstanceRecordLocalService
		_ddmFormInstanceRecordLocalService;

	@Inject
	private DDMFormInstanceReportLocalService
		_ddmFormInstanceReportLocalService;

	@Inject
	private DDMFormInstanceReportPersistence _ddmFormInstanceReportPersistence;

}