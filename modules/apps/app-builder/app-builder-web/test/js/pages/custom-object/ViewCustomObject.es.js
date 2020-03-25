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

import {act, cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';
import {HashRouter} from 'react-router-dom';

import {AppContextProvider} from '../../../../src/main/resources/META-INF/resources/js/AppContext.es';
import ViewCustomObject from '../../../../src/main/resources/META-INF/resources/js/pages/custom-object/ViewCustomObject.es';

describe('ViewCustomObject', () => {
	beforeEach(() => {
		jest.useFakeTimers();
	});

	afterEach(() => {
		cleanup();
		jest.restoreAllMocks();
		jest.clearAllTimers();
		jest.clearAllMocks();
	});

	afterAll(() => {
		jest.useRealTimers();
	});

	it('renders ViewCustomObjects', async () => {
		const match = {
			params: {
				dataDefinitionId: 123,
			},
		};

		const response = {
			name: {
				en_US: 'Test',
			},
		};

		fetch.mockResponse(JSON.stringify(response));

		const {container, queryByText} = render(
			<>
				<div className="tools-control-group">
					<div className="control-menu-level-1-heading" />
				</div>
				<HashRouter>
					<AppContextProvider value={{}}>
						<ViewCustomObject match={match}></ViewCustomObject>
					</AppContextProvider>
				</HashRouter>
			</>
		);

		await act(async () => {
			jest.runAllTimers();
		});

		const navbarItems = container.querySelectorAll('.nav-link');

		expect(queryByText(response.name.en_US)).toBeTruthy();
		expect(navbarItems.length).toBe(3);

		const [formView, tableView, apps] = navbarItems;

		expect(formView.innerHTML).toBe('form-views');
		expect(tableView.innerHTML).toBe('table-views');
		expect(apps.innerHTML).toBe('apps');

		expect(formView.classList.contains('active')).toBeFalsy();

		fireEvent.click(formView);

		expect(formView.classList.contains('active')).toBeTruthy();
	});
});
