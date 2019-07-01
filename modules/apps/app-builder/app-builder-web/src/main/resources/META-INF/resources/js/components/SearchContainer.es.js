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

import {list} from '../utils/client.es';
import Pagination from './Pagination.es';
import React, {Fragment, useEffect, useState} from 'react';
import Table from './Table.es';

export default function SearchContainer(props) {
	const [state, setState] = useState({
		currentPage: 1,
		items: [],
		totalPages: 1
	});

	const onPageChange = page => {
		list(props.endpoint, page).then(({items, totalPages}) =>
			setState({
				currentPage: page,
				items: props.formatter(items),
				totalPages
			})
		);
	};

	useEffect(() => {
		list(props.endpoint, 1).then(({items, totalPages}) =>
			setState({
				currentPage: 1,
				items: props.formatter(items),
				totalPages
			})
		);
	}, [props]);

	return (
		<Fragment>
			<Table columns={props.columns} rows={state.items} />
			<Pagination
				currentPage={state.currentPage}
				onPageChange={onPageChange}
				totalPages={state.totalPages}
			/>
		</Fragment>
	);
}
