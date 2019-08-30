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

import React from 'react';
import Table from '../../components/table/Table.es';

const generateItems = (columns, rows = 10) => {
	const items = [];

	for (let i = 0; i < rows; i++) {
		items.push(generateItem(columns, i));
	}

	return items;
};

const generateItem = (columns, index) =>
	columns.reduce(
		(acc, column) => ({
			...acc,
			[column]: `${column} ${index + 1}`
		}),
		{}
	);

const DropZone = ({columns}) => {
	if (columns.length == 0) {
		return (
			<div className="empty-drop-zone">
				{Liferay.Language.get(
					'drag-columns-from-the-sidebar-and-drop-here'
				)}
			</div>
		);
	}

	return (
		<Table
			actions={[]}
			columns={columns.map(fieldName => ({
				key: fieldName,
				value: fieldName
			}))}
			items={generateItems(columns)}
		/>
	);
};

export default DropZone;
