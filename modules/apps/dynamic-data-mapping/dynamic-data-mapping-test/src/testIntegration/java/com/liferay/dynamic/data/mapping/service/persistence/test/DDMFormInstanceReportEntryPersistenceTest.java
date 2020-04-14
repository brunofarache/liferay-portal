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

package com.liferay.dynamic.data.mapping.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.exception.NoSuchFormInstanceReportEntryException;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceReportEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceReportEntryPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceReportEntryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class DDMFormInstanceReportEntryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.dynamic.data.mapping.service"));

	@Before
	public void setUp() {
		_persistence = DDMFormInstanceReportEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DDMFormInstanceReportEntry> iterator =
			_ddmFormInstanceReportEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
			_persistence.create(pk);

		Assert.assertNotNull(ddmFormInstanceReportEntry);

		Assert.assertEquals(ddmFormInstanceReportEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		_persistence.remove(newDDMFormInstanceReportEntry);

		DDMFormInstanceReportEntry existingDDMFormInstanceReportEntry =
			_persistence.fetchByPrimaryKey(
				newDDMFormInstanceReportEntry.getPrimaryKey());

		Assert.assertNull(existingDDMFormInstanceReportEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDDMFormInstanceReportEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			_persistence.create(pk);

		newDDMFormInstanceReportEntry.setMvccVersion(RandomTestUtil.nextLong());

		newDDMFormInstanceReportEntry.setGroupId(RandomTestUtil.nextLong());

		newDDMFormInstanceReportEntry.setCompanyId(RandomTestUtil.nextLong());

		newDDMFormInstanceReportEntry.setCreateDate(RandomTestUtil.nextDate());

		newDDMFormInstanceReportEntry.setModifiedDate(
			RandomTestUtil.nextDate());

		newDDMFormInstanceReportEntry.setFormInstanceId(
			RandomTestUtil.nextLong());

		newDDMFormInstanceReportEntry.setSummary(RandomTestUtil.randomString());

		_ddmFormInstanceReportEntries.add(
			_persistence.update(newDDMFormInstanceReportEntry));

		DDMFormInstanceReportEntry existingDDMFormInstanceReportEntry =
			_persistence.findByPrimaryKey(
				newDDMFormInstanceReportEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDDMFormInstanceReportEntry.getMvccVersion(),
			newDDMFormInstanceReportEntry.getMvccVersion());
		Assert.assertEquals(
			existingDDMFormInstanceReportEntry.getFormInstanceReportEntryId(),
			newDDMFormInstanceReportEntry.getFormInstanceReportEntryId());
		Assert.assertEquals(
			existingDDMFormInstanceReportEntry.getGroupId(),
			newDDMFormInstanceReportEntry.getGroupId());
		Assert.assertEquals(
			existingDDMFormInstanceReportEntry.getCompanyId(),
			newDDMFormInstanceReportEntry.getCompanyId());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingDDMFormInstanceReportEntry.getCreateDate()),
			Time.getShortTimestamp(
				newDDMFormInstanceReportEntry.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingDDMFormInstanceReportEntry.getModifiedDate()),
			Time.getShortTimestamp(
				newDDMFormInstanceReportEntry.getModifiedDate()));
		Assert.assertEquals(
			existingDDMFormInstanceReportEntry.getFormInstanceId(),
			newDDMFormInstanceReportEntry.getFormInstanceId());
		Assert.assertEquals(
			existingDDMFormInstanceReportEntry.getSummary(),
			newDDMFormInstanceReportEntry.getSummary());
	}

	@Test
	public void testCountByFormInstanceId() throws Exception {
		_persistence.countByFormInstanceId(RandomTestUtil.nextLong());

		_persistence.countByFormInstanceId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		DDMFormInstanceReportEntry existingDDMFormInstanceReportEntry =
			_persistence.findByPrimaryKey(
				newDDMFormInstanceReportEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDDMFormInstanceReportEntry, newDDMFormInstanceReportEntry);
	}

	@Test(expected = NoSuchFormInstanceReportEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DDMFormInstanceReportEntry>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"DDMFormInstanceReportEntry", "mvccVersion", true,
			"formInstanceReportEntryId", true, "groupId", true, "companyId",
			true, "createDate", true, "modifiedDate", true, "formInstanceId",
			true, "summary", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		DDMFormInstanceReportEntry existingDDMFormInstanceReportEntry =
			_persistence.fetchByPrimaryKey(
				newDDMFormInstanceReportEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDDMFormInstanceReportEntry, newDDMFormInstanceReportEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DDMFormInstanceReportEntry missingDDMFormInstanceReportEntry =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDDMFormInstanceReportEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry1 =
			addDDMFormInstanceReportEntry();
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry2 =
			addDDMFormInstanceReportEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMFormInstanceReportEntry1.getPrimaryKey());
		primaryKeys.add(newDDMFormInstanceReportEntry2.getPrimaryKey());

		Map<Serializable, DDMFormInstanceReportEntry>
			ddmFormInstanceReportEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(2, ddmFormInstanceReportEntries.size());
		Assert.assertEquals(
			newDDMFormInstanceReportEntry1,
			ddmFormInstanceReportEntries.get(
				newDDMFormInstanceReportEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newDDMFormInstanceReportEntry2,
			ddmFormInstanceReportEntries.get(
				newDDMFormInstanceReportEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DDMFormInstanceReportEntry>
			ddmFormInstanceReportEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(ddmFormInstanceReportEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMFormInstanceReportEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DDMFormInstanceReportEntry>
			ddmFormInstanceReportEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, ddmFormInstanceReportEntries.size());
		Assert.assertEquals(
			newDDMFormInstanceReportEntry,
			ddmFormInstanceReportEntries.get(
				newDDMFormInstanceReportEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DDMFormInstanceReportEntry>
			ddmFormInstanceReportEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(ddmFormInstanceReportEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDDMFormInstanceReportEntry.getPrimaryKey());

		Map<Serializable, DDMFormInstanceReportEntry>
			ddmFormInstanceReportEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, ddmFormInstanceReportEntries.size());
		Assert.assertEquals(
			newDDMFormInstanceReportEntry,
			ddmFormInstanceReportEntries.get(
				newDDMFormInstanceReportEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			DDMFormInstanceReportEntryLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<DDMFormInstanceReportEntry>() {

				@Override
				public void performAction(
					DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

					Assert.assertNotNull(ddmFormInstanceReportEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFormInstanceReportEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"formInstanceReportEntryId",
				newDDMFormInstanceReportEntry.getFormInstanceReportEntryId()));

		List<DDMFormInstanceReportEntry> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DDMFormInstanceReportEntry existingDDMFormInstanceReportEntry =
			result.get(0);

		Assert.assertEquals(
			existingDDMFormInstanceReportEntry, newDDMFormInstanceReportEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFormInstanceReportEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"formInstanceReportEntryId", RandomTestUtil.nextLong()));

		List<DDMFormInstanceReportEntry> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFormInstanceReportEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("formInstanceReportEntryId"));

		Object newFormInstanceReportEntryId =
			newDDMFormInstanceReportEntry.getFormInstanceReportEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"formInstanceReportEntryId",
				new Object[] {newFormInstanceReportEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFormInstanceReportEntryId = result.get(0);

		Assert.assertEquals(
			existingFormInstanceReportEntryId, newFormInstanceReportEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDMFormInstanceReportEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("formInstanceReportEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"formInstanceReportEntryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DDMFormInstanceReportEntry newDDMFormInstanceReportEntry =
			addDDMFormInstanceReportEntry();

		_persistence.clearCache();

		DDMFormInstanceReportEntry existingDDMFormInstanceReportEntry =
			_persistence.findByPrimaryKey(
				newDDMFormInstanceReportEntry.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(
				existingDDMFormInstanceReportEntry.getFormInstanceId()),
			ReflectionTestUtil.<Long>invoke(
				existingDDMFormInstanceReportEntry, "getOriginalFormInstanceId",
				new Class<?>[0]));
	}

	protected DDMFormInstanceReportEntry addDDMFormInstanceReportEntry()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
			_persistence.create(pk);

		ddmFormInstanceReportEntry.setMvccVersion(RandomTestUtil.nextLong());

		ddmFormInstanceReportEntry.setGroupId(RandomTestUtil.nextLong());

		ddmFormInstanceReportEntry.setCompanyId(RandomTestUtil.nextLong());

		ddmFormInstanceReportEntry.setCreateDate(RandomTestUtil.nextDate());

		ddmFormInstanceReportEntry.setModifiedDate(RandomTestUtil.nextDate());

		ddmFormInstanceReportEntry.setFormInstanceId(RandomTestUtil.nextLong());

		ddmFormInstanceReportEntry.setSummary(RandomTestUtil.randomString());

		_ddmFormInstanceReportEntries.add(
			_persistence.update(ddmFormInstanceReportEntry));

		return ddmFormInstanceReportEntry;
	}

	private List<DDMFormInstanceReportEntry> _ddmFormInstanceReportEntries =
		new ArrayList<DDMFormInstanceReportEntry>();
	private DDMFormInstanceReportEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}