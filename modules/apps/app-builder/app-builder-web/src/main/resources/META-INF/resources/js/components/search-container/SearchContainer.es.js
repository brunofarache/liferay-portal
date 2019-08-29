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

import React, {useContext} from 'react';
import {ClayPaginationWithBar} from '@clayui/pagination';
import {SearchContext} from './SearchContext.es';
import {withEmpty} from './table/EmptyState.es';
import Table from './table/Table.es';
import {withLoading} from '../loading/Loading.es';

const SearchContainer = ({actions, columns, items, totalCount}) => {
	const {
		dispatch,
		state: {
			query: {page, pageSize}
		}
	} = useContext(SearchContext);

	return (
		<div className="container-fluid container-fluid-max-xl">
			<Table actions={actions} columns={columns} items={items} />

			<div className="taglib-search-iterator-page-iterator-bottom">
				<ClayPaginationWithBar
					deltas={[5, 10, 20, 30, 50, 75].map(size => {
						return {label: size};
					})}
					ellipsisBuffer={3}
					initialActivePage={page}
					initialSelectedDelta={{label: pageSize}}
					onDeltaChange={pageSize =>
						dispatch({pageSize, type: 'CHANGE_PAGE_SIZE'})
					}
					onPageChange={page => dispatch({page, type: 'CHANGE_PAGE'})}
					totalItems={totalCount}
				/>
			</div>
		</div>
	);
};

export default withLoading(withEmpty(SearchContainer));
