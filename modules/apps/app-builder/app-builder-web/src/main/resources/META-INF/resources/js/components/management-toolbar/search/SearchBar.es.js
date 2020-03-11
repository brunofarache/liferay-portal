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

import React, {useContext, useState} from 'react';

import SearchContext from './SearchContext.es';
import {SearchInputWithForm} from './SearchInput';
import SearchOptions from './SearchOptions';
import SearchSort from './SearchSort.es';

export default ({addButton, columns, totalCount}) => {
	const [{keywords}, dispatch] = useContext(SearchContext);
	const [showSearch, setSearchMobile] = useState(false);
	const disabled = keywords === '' && totalCount === 0;

	return (
		<>
			<SearchSort columns={columns} disabled={disabled} />
			<SearchInputWithForm
				clearButton
				disabled={disabled}
				onSubmit={searchText =>
					dispatch({keywords: searchText, type: 'SEARCH'})
				}
				searchText={keywords}
				setSearchMobile={setSearchMobile}
				showSearch={showSearch}
			/>
			<SearchOptions
				addButton={addButton}
				setSearchMobile={setSearchMobile}
			/>
		</>
	);
};
