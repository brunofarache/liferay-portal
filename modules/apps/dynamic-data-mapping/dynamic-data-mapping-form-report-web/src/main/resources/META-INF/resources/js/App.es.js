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

import Card from './components/Card.es';
import PieChart from './components/PieChart.es';

export default ({data}) => {
	const chartFactory = (type, values) => {
		if (type === 'radio') {
			return <PieChart values={values} />;
		}

		return null;
	};

	return Object.entries(data).map(([fieldName, {type, values}], index) => {
		const chart = chartFactory(type, values);

		if (chart === null) {
			return null;
		}

		return (
			<Card fieldName={fieldName} key={index} type={type} values={values}>
				{chart}
			</Card>
		);
	});
};
