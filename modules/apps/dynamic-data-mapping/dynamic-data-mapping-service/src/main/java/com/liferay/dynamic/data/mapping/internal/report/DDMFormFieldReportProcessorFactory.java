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

import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Map;

/**
 * @author Marcos Martins
 */
public class DDMFormFieldReportProcessorFactory {

	public static DDMFormFieldReportProcessor getProcessor(String fieldType) {
		return _fieldReportProcessorMap.get(fieldType);
	}

	private static final Map<String, DDMFormFieldReportProcessor>
		_fieldReportProcessorMap =
			HashMapBuilder.<String, DDMFormFieldReportProcessor>put(
				DDMFormFieldType.CHECKBOX_MULTIPLE,
				new DDMFormMultipleSelectionFieldReportProcessor()
			).put(
				DDMFormFieldType.RADIO,
				new DDMFormSingleSelectionFieldReportProcessor()
			).build();

}