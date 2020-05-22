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

const decimalAdjustToFloor = (value) => {
	value = value.toString().split('e');
	value = Math.floor(+(value[0] + 'e' + (value[1] ? +value[1] - -1 : -(-1))));
	value = value.toString().split('e');

	return +(value[0] + 'e' + (value[1] ? +value[1] + -1 : -1));
};

const sumTotalEntries = (values) =>
	Object.values(values).reduce((acc, value) => acc + value, 0);

const toDataArray = (values) =>
	Object.entries(values)
		.map(([label, count]) => ({count, label}))
		.sort((a, b) => (a.count > b.count ? -1 : b.count > a.count ? 1 : 0));

export default toDataArray;
export {sumTotalEntries, decimalAdjustToFloor};
