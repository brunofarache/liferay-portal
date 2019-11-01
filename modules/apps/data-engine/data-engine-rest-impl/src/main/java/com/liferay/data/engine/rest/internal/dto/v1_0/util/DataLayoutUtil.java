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

package com.liferay.data.engine.rest.internal.dto.v1_0.util;

import com.liferay.data.engine.field.type.util.LocalizedValueUtil;
import com.liferay.data.engine.rest.dto.v1_0.DataLayout;
import com.liferay.data.engine.rest.dto.v1_0.DataLayoutColumn;
import com.liferay.data.engine.rest.dto.v1_0.DataLayoutPage;
import com.liferay.data.engine.rest.dto.v1_0.DataLayoutRow;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * @author Jeyvison Nascimento
 */
public class DataLayoutUtil {

	public static boolean existsAnyField(
		String[] removedFields, String[] savedFields) {

		return ArrayUtil.exists(
			savedFields, field -> ArrayUtil.contains(removedFields, field));
	}

	public static String[] removeFields(
		String[] removedFields, String[] savedFields) {

		return ArrayUtil.filter(
			savedFields,
			fieldName -> !ArrayUtil.contains(removedFields, fieldName));
	}

	public static boolean removeFieldsDataLayout(
		DataLayout dataLayout, List<String> removedFields) {

		Stream<DataLayoutPage> pages = Arrays.stream(
			dataLayout.getDataLayoutPages());

		AtomicReference<Boolean> modified = new AtomicReference<>(false);

		pages.forEach(
			dataLayoutPage -> {
				Stream<DataLayoutRow> streamRows = Arrays.stream(
					dataLayoutPage.getDataLayoutRows());

				streamRows.forEach(
					dataLayoutRow -> {
						Stream<DataLayoutColumn> columns = Arrays.stream(
							dataLayoutRow.getDataLayoutColumns());

						columns.forEach(
							dataLayoutColumn -> {
								if (existsAnyField(
										ArrayUtil.toStringArray(removedFields),
										dataLayoutColumn.getFieldNames())) {

									modified.set(true);

									dataLayoutColumn.setFieldNames(
										removeFields(
											ArrayUtil.toStringArray(
												removedFields),
											dataLayoutColumn.getFieldNames()));
								}
							});

						dataLayoutRow.setDataLayoutColumns(
							ArrayUtil.filter(
								dataLayoutRow.getDataLayoutColumns(),
								column -> !ArrayUtil.isEmpty(
									column.getFieldNames())));
					});

				dataLayoutPage.setDataLayoutRows(
					ArrayUtil.filter(
						dataLayoutPage.getDataLayoutRows(),
						row -> !ArrayUtil.isEmpty(row.getDataLayoutColumns())));
			});

		return modified.get();
	}

	public static DataLayout toDataLayout(String json) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

		return new DataLayout() {
			{
				dataLayoutPages = JSONUtil.toArray(
					jsonObject.getJSONArray("pages"),
					pageJSONObject -> _toDataLayoutPage(pageJSONObject),
					DataLayoutPage.class);
				paginationMode = jsonObject.getString("paginationMode");
			}
		};
	}

	public static String toJSON(DataLayout dataLayout) throws Exception {
		if (!Objects.equals(dataLayout.getPaginationMode(), "pagination") &&
			!Objects.equals(dataLayout.getPaginationMode(), "wizard")) {

			throw new Exception(
				"Pagination mode must be \"pagination\" or \"wizard\"");
		}

		return JSONUtil.put(
			"pages",
			JSONUtil.toJSONArray(
				dataLayout.getDataLayoutPages(),
				dataLayoutPage -> _toJSONObject(dataLayoutPage))
		).put(
			"paginationMode", dataLayout.getPaginationMode()
		).toString();
	}

	private static DataLayoutColumn _toDataLayoutColumn(JSONObject jsonObject) {
		return new DataLayoutColumn() {
			{
				columnSize = jsonObject.getInt("columnSize");
				fieldNames = JSONUtil.toStringArray(
					jsonObject.getJSONArray("fieldNames"));
			}
		};
	}

	private static DataLayoutPage _toDataLayoutPage(JSONObject jsonObject)
		throws Exception {

		return new DataLayoutPage() {
			{
				dataLayoutRows = JSONUtil.toArray(
					jsonObject.getJSONArray("rows"),
					rowJSONObject -> _toDataLayoutRow(rowJSONObject),
					DataLayoutRow.class);
				description = LocalizedValueUtil.toLocalizedValues(
					jsonObject.getJSONObject("description"));
				title = LocalizedValueUtil.toLocalizedValues(
					jsonObject.getJSONObject("title"));
			}
		};
	}

	private static DataLayoutRow _toDataLayoutRow(JSONObject jsonObject)
		throws Exception {

		return new DataLayoutRow() {
			{
				dataLayoutColumns = JSONUtil.toArray(
					jsonObject.getJSONArray("columns"),
					columnJSONObject -> _toDataLayoutColumn(columnJSONObject),
					DataLayoutColumn.class);
			}
		};
	}

	private static JSONObject _toJSONObject(DataLayoutColumn dataLayoutColumn)
		throws Exception {

		return JSONUtil.put(
			"columnSize", dataLayoutColumn.getColumnSize()
		).put(
			"fieldNames", JSONUtil.put(dataLayoutColumn.getFieldNames())
		);
	}

	private static JSONObject _toJSONObject(DataLayoutPage dataLayoutPage)
		throws Exception {

		if (MapUtil.isEmpty(dataLayoutPage.getTitle())) {
			throw new Exception("Title is required");
		}

		return JSONUtil.put(
			"description",
			LocalizedValueUtil.toJSONObject(dataLayoutPage.getDescription())
		).put(
			"rows",
			JSONUtil.toJSONArray(
				dataLayoutPage.getDataLayoutRows(),
				dataLayoutRow -> _toJSONObject(dataLayoutRow))
		).put(
			"title", LocalizedValueUtil.toJSONObject(dataLayoutPage.getTitle())
		);
	}

	private static JSONObject _toJSONObject(DataLayoutRow dataLayoutRow)
		throws Exception {

		return JSONUtil.put(
			"columns",
			JSONUtil.toJSONArray(
				dataLayoutRow.getDataLayoutColumns(),
				dataLayoutColumn -> _toJSONObject(dataLayoutColumn)));
	}

}