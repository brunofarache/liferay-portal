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

package com.liferay.data.engine.rest.internal.rule.function.v1_0;

import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.BaseDataRuleFunctionTest;
import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.constants.DataDefinitionRuleConstants;
import com.liferay.data.engine.spi.rule.function.DataRuleFunction;
import com.liferay.data.engine.spi.rule.function.DataRuleFunctionResult;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Marcelo Mello
 */
public class EmptyDataRuleFunctionTest extends BaseDataRuleFunctionTest {

	@Test
	public void testEmptyFieldName() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put(" ", "value");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_NOT_BE_EMPTY,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testEmptyValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put(fieldName, " ");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_NOT_BE_EMPTY,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testMultipleValues() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put(fieldName, "text1");
					put(fieldName, "text2");
					put(fieldName, "text3");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertTrue(dataRuleFunctionResult.isValid());
		Assert.assertNull(dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testNotEmptyValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put(fieldName, "text");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertTrue(dataRuleFunctionResult.isValid());
		Assert.assertNull(dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testNullValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put(fieldName, null);
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_NOT_BE_EMPTY,
			dataRuleFunctionResult.getErrorCode());
	}

	@Override
	protected DataRuleFunction getDataRuleFunction() {
		return new EmptyDataRuleFunction();
	}

	@Override
	protected String getFieldType() {
		return "text";
	}

}