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

import React, {useState} from 'react';

import Card from './components/card/Card.es';
import BarChart from './components/chart/bar/BarChart.es';
import PieChart from './components/chart/pie/PieChart.es';
import EmptyState from './components/empty-state/EmptyState.es';
import List from './components/list/List.es';
import Sidebar from './components/sidebar/Sidebar.es';
import toDataArray, {sumTotalEntries, toArray} from './utils/data.es';
import fieldTypes from './utils/fieldTypes.es';

const chartFactory = (options, type, values, totalEntries, onClick) => {
	switch (type) {
		case 'checkbox_multiple':
			return (
				<BarChart
					data={toDataArray(options, values)}
					totalEntries={totalEntries}
				/>
			);

		case 'radio':
		case 'select':
			return (
				<PieChart
					data={toDataArray(options, values)}
					totalEntries={totalEntries}
				/>
			);

		case 'text':
			return (
				<List
					data={toArray(values)}
					onClick={onClick}
					totalEntries={totalEntries}
				/>
			);

		default:
			return null;
	}
};

export default ({data, fields, portletNamespace, url}) => {
	let hasCards = false;
	const [open, setOpen] = useState(false);

	const onClick = () => {
		setOpen(!open);
	};

	const fieldSummary = fields.map(({label, name, options, type}, index) => {
		const {values = {}, totalEntries: total} = data[name] || {};
		let totalEntries = sumTotalEntries(values);
		if (type == 'text') {
			totalEntries = total;
		}

		const chart = chartFactory(options, type, values, totalEntries, onClick);

		if (chart === null) {
			return null;
		}
		else {
			hasCards = true;
		}

		const field = {
			label,
			name,
			type,
			...fieldTypes[type],
		};

		return (
			<>
				<Card field={field} key={index} totalEntries={totalEntries}>
					{chart}
				</Card>

				{type == 'text' && open ? (
					<Sidebar
						field={field}
						onClick={onClick}
						open={open}
						portletNamespace={portletNamespace}
						totalEntries={totalEntries}
						url={url}
					/>
				) : (
					''
				)}
			</>
		);
	});

	if (!hasCards) {
		return <EmptyState />;
	}

	return fieldSummary;
};
