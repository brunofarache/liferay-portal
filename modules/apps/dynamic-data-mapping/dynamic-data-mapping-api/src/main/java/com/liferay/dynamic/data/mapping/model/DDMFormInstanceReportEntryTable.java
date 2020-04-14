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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;DDMFormInstanceReportEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportEntry
 * @generated
 */
public class DDMFormInstanceReportEntryTable
	extends BaseTable<DDMFormInstanceReportEntryTable> {

	public static final DDMFormInstanceReportEntryTable INSTANCE =
		new DDMFormInstanceReportEntryTable();

	public final Column<DDMFormInstanceReportEntryTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<DDMFormInstanceReportEntryTable, Long>
		formInstanceReportEntryId = createColumn(
			"formInstanceReportEntryId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<DDMFormInstanceReportEntryTable, Long> groupId =
		createColumn("groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DDMFormInstanceReportEntryTable, Long> companyId =
		createColumn(
			"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DDMFormInstanceReportEntryTable, Date> createDate =
		createColumn(
			"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DDMFormInstanceReportEntryTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<DDMFormInstanceReportEntryTable, Long> formInstanceId =
		createColumn(
			"formInstanceId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<DDMFormInstanceReportEntryTable, String> summary =
		createColumn(
			"summary", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private DDMFormInstanceReportEntryTable() {
		super(
			"DDMFormInstanceReportEntry", DDMFormInstanceReportEntryTable::new);
	}

}