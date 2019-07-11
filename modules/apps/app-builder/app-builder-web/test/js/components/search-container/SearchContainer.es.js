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

import {cleanup, render} from 'react-testing-library';
import {disableActWarnings, restoreConsole} from '../../utils';
import SearchContainer from '../../../../src/main/resources/META-INF/resources/js/components/search-container/SearchContainer.es';
import React from 'react';
import {waitForElementToBeRemoved} from '@testing-library/dom';

describe('SearchContainer', () => {
	afterEach(cleanup);

	let originalError;
	let originalWarn;

	beforeAll(() => {
		[originalError, originalWarn] = disableActWarnings();
	});

	afterAll(() => {
		restoreConsole(originalError, originalWarn);
	});

	const items = [
		{
			id: 1,
			name: 'Name',
			dateCreated: 'Today',
			dateModified: 'Today'
		}
	];

	const response = {
		items,
		lastPage: 1,
		page: 1,
		pageSize: 1,
		totalCount: 1
	};

	fetch.mockResponse(JSON.stringify(response));

	it('renders with empty state', async () => {
		const actions = [
			{
				name: 'Delete',
				callback: () => {}
			}
		];

		const columns = [
			{
				name: 'Name'
			},
			{
				dateCreated: 'Created Date'
			},
			{
				dateModified: 'Modified Date'
			}
		];

		const emptyState = {
			title: 'title',
			description: 'description'
		};

		const formatter = items => items;
		const endpoint = '/endpoint';

		const {queryAllByTestId} = render(
			<SearchContainer
				actions={actions}
				columns={columns}
				emptyState={emptyState}
				endpoint={endpoint}
				formatter={formatter}
			/>
		);

		await waitForElementToBeRemoved(() =>
			document.querySelector('span.loading-animation')
		);

		expect(queryAllByTestId('row').length).toBe(1);
	});
});
