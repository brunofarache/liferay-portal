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

package com.liferay.data.engine.rest.resource.v2_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.data.engine.intrinsic.object.DataEngineIntrinsicObject;
import com.liferay.data.engine.rest.dto.v2_0.DataDefinition;
import com.liferay.data.engine.rest.resource.v2_0.DataDefinitionResource;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Jeyvison Nascimento
 */
@RunWith(Arquillian.class)
public class DataEngineIntrinsicObjectTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		Group testGroup = GroupTestUtil.addGroup();

		_testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		Bundle bundle = FrameworkUtil.getBundle(
			DataEngineIntrinsicObjectTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceReference<DataEngineIntrinsicObject> serviceReference =
			bundleContext.getServiceReference(DataEngineIntrinsicObject.class);

		if (serviceReference != null) {
			bundleContext.ungetService(serviceReference);
		}

		bundleContext.registerService(
			DataEngineIntrinsicObject.class, DataDefinition.class::getName,
			new HashMapDictionary<>());

		_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			_testCompany.getCompanyId(), DataDefinition.class.getName(),
			RandomTestUtil.randomLong());
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		Company company = CompanyLocalServiceUtil.getCompany(
			_testCompany.getCompanyId());

		DataDefinitionResource dataDefinitionResource =
			DataDefinitionResource.builder(
			).user(
				company.getDefaultUser()
			).checkPermissions(
				false
			).build();

		DataDefinition dataDefinition =
			dataDefinitionResource.
				getSiteDataDefinitionByContentTypeByDataDefinitionKey(
					_testCompany.getGroupId(), "intrinsic-object",
					DataDefinition.class.getName());

		dataDefinitionResource.deleteDataDefinition(dataDefinition.getId());
	}

	@Test
	public void testAddAttribute() throws PortalException {
		String attributeName = _addAttribute();

		long classPK = _expandoBridge.getClassPK();

		_expandoBridge.setClassPK(1);

		Assert.assertEquals(
			StringPool.BLANK, _expandoBridge.getAttribute(attributeName));

		_expandoBridge.setClassPK(classPK);
	}

	@Test
	public void testAddAttributeWithDefaultValue() throws PortalException {
		String defaultValue = RandomTestUtil.randomString();

		String attributeName = _addAttributeWithValue(defaultValue);

		long classPK = _expandoBridge.getClassPK();

		_expandoBridge.setClassPK(1);

		Assert.assertEquals(
			defaultValue, _expandoBridge.getAttribute(attributeName));

		_expandoBridge.setClassPK(classPK);
	}

	@Test
	public void testGetAttributeDefault() throws PortalException {
		String defaultValue = RandomTestUtil.randomString();

		String attributeName = _addAttributeWithValue(defaultValue);

		Assert.assertEquals(
			defaultValue, _expandoBridge.getAttributeDefault(attributeName));
	}

	@Test
	public void testGetAttributeNames() {
		List<String> attributeNames = ListUtil.fromEnumeration(
			_expandoBridge.getAttributeNames());

		Assert.assertEquals(
			_attributes.toString(), attributeNames.size(), _attributes.size());
	}

	@Test
	public void testGetAttributeProperties() throws PortalException {
		String attributeName = _addAttribute();

		UnicodeProperties unicodeProperties =
			_expandoBridge.getAttributeProperties(attributeName);

		Assert.assertEquals("text", unicodeProperties.getProperty("fieldType"));
	}

	@Test
	public void testGetAttributes() {
		Map<String, Serializable> attributes = _expandoBridge.getAttributes(
			_attributes.keySet());

		Assert.assertEquals(
			attributes.toString(), _attributes.size(), attributes.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetAttributeType() {
		_expandoBridge.getAttributeType(null);
	}

	@Test
	public void testHasAttributes() throws PortalException {
		String attributeName = _addAttribute();

		Assert.assertTrue(_expandoBridge.hasAttribute(attributeName));
	}

	@Test
	public void testSetAttributeAndGetValue() throws PortalException {
		String attributeValue = RandomTestUtil.randomString();

		String attributeName = _addAttributeWithValue(attributeValue);

		_expandoBridge.setAttribute(attributeName, attributeValue);

		Serializable value = _expandoBridge.getAttribute(attributeName);

		Assert.assertEquals(attributeValue, value);
	}

	@Test
	public void testSetAttributeProperties() throws PortalException {
		String attributeName = _addAttribute();

		UnicodeProperties unicodeProperties =
			_expandoBridge.getAttributeProperties(attributeName);

		Assert.assertEquals("text", unicodeProperties.getProperty("fieldType"));

		unicodeProperties.put("fieldType", "numeric");

		_expandoBridge.setAttributeProperties(attributeName, unicodeProperties);

		unicodeProperties = _expandoBridge.getAttributeProperties(
			attributeName);

		Assert.assertEquals(
			"numeric", unicodeProperties.getProperty("fieldType"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetAttributesWithServiceContext() {
		_expandoBridge.setAttributes(new ServiceContext());
	}

	@Test
	public void testSetDefaultAttribute() throws PortalException {
		String defaultValue = RandomTestUtil.randomString();

		String attributeName = _addAttributeWithValue(defaultValue);

		Assert.assertEquals(
			defaultValue, _expandoBridge.getAttributeDefault(attributeName));

		defaultValue = RandomTestUtil.randomString();

		_expandoBridge.setAttributeDefault(attributeName, defaultValue);

		Assert.assertEquals(
			defaultValue, _expandoBridge.getAttributeDefault(attributeName));
	}

	private String _addAttribute() throws PortalException {
		String attributeName = RandomTestUtil.randomString();

		_expandoBridge.addAttribute(attributeName);

		_attributes.put(attributeName, StringPool.BLANK);

		return attributeName;
	}

	private String _addAttributeWithValue(Serializable value)
		throws PortalException {

		String attributeName = RandomTestUtil.randomString();

		_expandoBridge.addAttribute(attributeName, "text", value);

		_attributes.put(attributeName, value);

		return attributeName;
	}

	private static final Map<String, Serializable> _attributes =
		new HashMap<>();
	private static ExpandoBridge _expandoBridge;
	private static Company _testCompany;

}