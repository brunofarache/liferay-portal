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

import {act, cleanup, render} from '@testing-library/react';
import React from 'react';

import ManagementToolbar from '../../../../src/main/resources/META-INF/resources/js/components/management-toolbar/ManagementToolbar.es';
import SearchContext from '../../../../src/main/resources/META-INF/resources/js/components/management-toolbar/SearchContext.es';

const addButton = onClick => <button onClick={onClick}>Add</button>;

describe('ManagementToolbar', () => {
	afterEach(() => {
		cleanup();
	});

	it('render with props', async () => {
		const dispatch = jest.fn();
		const onClickButton = jest.fn();
		const query = {
			keywords: '',
		};

		const columns = [
			{
				asc: true,
				key: 'App',
				sortable: true,
				value: 'App',
			},
			{
				key: 'Date Modified',
			},
		];

		const {container, findByTestId, queryByText} = render(
			<SearchContext.Provider value={[query, dispatch]}>
				<ManagementToolbar
					addButton={() => addButton(onClickButton)}
					columns={columns}
				/>
			</SearchContext.Provider>
		);

		const dropdown = queryByText('filter-and-order');
		const appItem = queryByText('App');

		expect(appItem).toBeTruthy();
		expect(dropdown).toBeTruthy();
		expect(queryByText('Date Modified')).toBeFalsy();

		await act(async () => {
			dropdown.dispatchEvent(new MouseEvent('click', {bubbles: true}));
		});

		await act(async () => {
			appItem.dispatchEvent(new MouseEvent('click', {bubbles: true}));
		});

		expect(dispatch.mock.calls.length).toBe(1);

		const sort = container.querySelector(
			'ul .nav-item:nth-child(2) button'
		);

		await act(async () => {
			sort.dispatchEvent(new MouseEvent('click', {bubbles: true}));
		});

		expect(dispatch.mock.calls.length).toBe(2);

		const setMobile = await findByTestId('toggleMobileToolbarRight');

		await act(async () => {
			setMobile.dispatchEvent(new MouseEvent('click', {bubbles: true}));
		});

		const add = queryByText('Add');

		await act(async () => {
			add.dispatchEvent(new MouseEvent('click', {bubbles: true}));
		});

		expect(onClickButton.mock.calls.length).toBe(1);
	});
});
