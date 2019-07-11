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

import {waitForElementToBeRemoved} from '@testing-library/dom';

import {cleanup, render} from 'react-testing-library';
import SearchContainer from '../../../../src/main/resources/META-INF/resources/js/components/search-container/SearchContainer.es';
import React from 'react';

describe('SearchContainer', () => {
	afterEach(cleanup);

	// this is just a little hack to silence a warning that we'll get until react
	// fixes this: https://github.com/facebook/react/pull/14853
	// https://github.com/testing-library/react-testing-library/issues/281
	// eslint-disable-next-line no-console
	const originalError = console.error;
	// eslint-disable-next-line no-console
	const originalWarn = console.warn;
	beforeAll(() => {
		// eslint-disable-next-line no-console
		console.error = (...args) => {
			if (
				/Warning.*not wrapped in act/.test(args[0]) ||
				/DataProvider: Error making/.test(args[0])
			) {
				return;
			}
			originalError.call(console, ...args);
		};
		// eslint-disable-next-line no-console
		console.warn = (...args) => {
			if (/DataProvider: Trying/.test(args[0])) {
				return;
			}
			originalWarn.call(console, ...args);
		};
	});

	afterAll(() => {
		// eslint-disable-next-line no-console
		console.error = originalError;
		// eslint-disable-next-line no-console
		console.warn = originalWarn;
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

		const {debug, queryAllByTestId} = render(
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

		debug();
	});
});
