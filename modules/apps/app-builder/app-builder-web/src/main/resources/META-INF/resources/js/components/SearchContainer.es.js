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

import PageSize from './PageSize.es';
import Pagination from './Pagination.es';
import React, {Fragment, useState} from 'react';
import Table from './Table.es';
import {useResource} from '@clayui/data-provider';

export default function SearchContainer(props) {
	const {columns, endpoint, formatter} = props;

	const [state, setState] = useState({
		page: 1,
		pageSize: 20
	});

	const {page, pageSize} = state;

	const result = useResource({
		link: endpoint,
		variables: {
			p_auth: Liferay.authToken,
			...state
		}
	});

	let items = [],
		totalCount = 0,
		totalPages = 1;

	if (result) {
		const {resource} = result;

		if (resource) {
			({items, totalCount, lastPage: totalPages} = resource);
		}
	}

	return (
		<Fragment>
			<Table columns={columns} rows={formatter(items)} />
			<PageSize
				itemsCount={items.length}
				onPageSizeChange={pageSize => setState({page: 1, pageSize})}
				page={page}
				pageSize={pageSize}
				totalCount={totalCount}
			/>
			<Pagination
				page={page}
				onPageChange={page =>
					setState(prevState => ({
						page,
						pageSize: prevState.pageSize
					}))
				}
				totalPages={totalPages}
			/>
		</Fragment>
	);
}
