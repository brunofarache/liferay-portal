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
import {act, cleanup, fireEvent, render} from '@testing-library/react';
import {createMemoryHistory} from 'history';
import React from 'react';
import {HashRouter} from 'react-router-dom';

import {AppContextProvider} from '../../../../src/main/resources/META-INF/resources/js/AppContext.es';
import ListCustomObjects from '../../../../src/main/resources/META-INF/resources/js/pages/custom-object/ListCustomObjects.es';
import * as toasts from '../../../../src/main/resources/META-INF/resources/js/utils/toast.es';
import {RESPONSES} from '../../constants.es';

describe('ListCustomObject', () => {
	let history;
	let ListCustomObjectsWrapper;
	let spySuccessToast;

	beforeEach(() => {
		jest.useFakeTimers();
		history = createMemoryHistory();
		spySuccessToast = jest
			.spyOn(toasts, 'successToast')
			.mockImplementation();

		ListCustomObjectsWrapper = () => (
			<AppContextProvider value={{}}>
				<div className="tools-control-group">
					<div className="control-menu-level-1-heading" />
				</div>

				<HashRouter>
					<ListCustomObjects history={history} />
				</HashRouter>
			</AppContextProvider>
		);
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

	it('renders with empty state and new item', async () => {
		fetch.mockResponse(JSON.stringify(RESPONSES.NO_ITEMS));

		const {
			container,
			queryAllByRole,
			queryAllByText,
			queryByPlaceholderText,
			queryByText,
		} = render(<ListCustomObjectsWrapper />);

		await waitForElementToBeRemoved(() =>
			document.querySelector('span.loading-animation')
		);

		const [filter, sort] = queryAllByRole('button');

		expect(
			queryByText(
				'custom-objects-define-the-types-of-data-your-business-application-needs'
			)
		).toBeTruthy();
		expect(queryByText('there-are-no-custom-objects-yet')).toBeTruthy();
		expect(
			container.querySelector('.control-menu-level-1-heading')
		).not.toBe('title');
		expect(queryByPlaceholderText('search...').disabled).toBe(true);
		expect(filter.disabled).toBe(true);
		expect(sort.disabled).toBe(true);
		expect(fetch.mock.calls.length).toEqual(1);

		const continueButton = container.querySelector('.btn-sm.btn-primary');
		const nameInput = container.querySelector('#customObjectNameInput');
		const checkbox = container.querySelector('input[type=checkbox]');
		const [newCustomObject] = queryAllByText('new-custom-object');

		expect(nameInput.value).toBe('');
		expect(checkbox.checked).toBe(true);

		fireEvent.click(newCustomObject);

		window.Liferay.Util.PortletURL = {
			createRenderURL: jest.fn(),
		};

		fireEvent.change(nameInput, {target: {value: 'test'}});

		expect(nameInput.value).toBe('test');

		fireEvent.click(continueButton);
	});

	it('renders with empty state and create an item', async () => {
		fetch.mockResponseOnce(JSON.stringify(RESPONSES.NO_ITEMS));
		fetch.mockResponseOnce(
			JSON.stringify({
				id: 38317,
				name: {
					en_US: 'test',
				},
			})
		);
		fetch.mockResponseOnce(
			JSON.stringify({
				actions: {},
				items: [
					{
						availableLanguages: ['en-US'],
						dateCreated: '2020-03-05T20:06:51Z',
						dateModified: '2020-03-05T20:06:51Z',
						description: '',
						id: 30302,
						name: 'Account Administrator',
						roleType: 'regular',
					},
				],
				lastPage: 1,
				page: 1,
				pageSize: 20,
				totalCount: 1,
			})
		);

		const {container, queryAllByText} = render(
			<ListCustomObjectsWrapper />
		);

		await waitForElementToBeRemoved(() =>
			document.querySelector('span.loading-animation')
		);

		expect(fetch.mock.calls.length).toEqual(1);

		const continueButton = container.querySelector('.btn-sm.btn-primary');
		const nameInput = container.querySelector('#customObjectNameInput');
		const checkbox = container.querySelector('input[type=checkbox]');
		const [newCustomObject] = queryAllByText('new-custom-object');

		const [cancel] = queryAllByText('cancel');

		expect(nameInput.value).toBe('');
		expect(checkbox.checked).toBe(true);
		expect(container.querySelector('.form-group.has-error')).toBeFalsy();

		fireEvent.click(newCustomObject);
		expect(container.querySelector('.popover.hide')).toBeFalsy();

		fireEvent.click(cancel);
		expect(container.querySelector('.popover.hide')).toBeTruthy();

		fireEvent.click(newCustomObject);
		fireEvent.click(continueButton);
		fireEvent.click(checkbox);

		expect(container.querySelector('.form-group.has-error')).toBeTruthy();

		fireEvent.change(nameInput, {target: {value: 'test'}});

		expect(nameInput.value).toBe('test');
		expect(checkbox.checked).toBe(false);

		fireEvent.click(continueButton);

		expect(fetch.mock.calls.length).toEqual(2);
	});

	it('renders with data and remove item', async () => {
		fetch
			.mockResponseOnce(JSON.stringify(RESPONSES.ONE_ITEM))
			.mockResponseOnce(JSON.stringify({}))
			.mockResponseOnce(JSON.stringify(RESPONSES.NO_ITEMS));

		const confirmWindow = jest
			.spyOn(window, 'confirm')
			.mockImplementation(() => true);

		const {container, queryAllByTestId, queryByText} = render(
			<ListCustomObjectsWrapper />
		);

		await waitForElementToBeRemoved(() =>
			document.querySelector('span.loading-animation')
		);

		expect(
			queryByText(
				'custom-objects-define-the-types-of-data-your-business-application-needs'
			)
		).toBeFalsy();
		expect(queryByText('there-are-no-custom-objects-yet')).toBeFalsy();

		expect(fetch.mock.calls.length).toBe(1);
		expect(queryAllByTestId('item').length).toBe(1);

		const deleteButton = queryByText('delete');

		fireEvent.click(
			container.querySelector(
				'.dropdown-toggle.page-link.btn.btn-unstyled'
			)
		);
		expect(document.querySelector('.dropdown-menu.show')).toBeTruthy();
		expect(
			document.querySelectorAll('.dropdown-menu.show .dropdown-item')
				.length
		).toBe(5);

		await act(async () => {
			fireEvent.click(deleteButton);
		});

		expect(spySuccessToast.mock.calls.length).toBe(1);
		expect(confirmWindow.mock.calls.length).toBe(1);
		expect(fetch.mock.calls.length).toBe(3);

		expect(
			queryByText(
				'custom-objects-define-the-types-of-data-your-business-application-needs'
			)
		).toBeTruthy();
		expect(queryByText('there-are-no-custom-objects-yet')).toBeTruthy();
	});

	it('renders with data and update item permission', async () => {
		const permissionItem = {
			availableLanguages: ['en-US'],
			dateCreated: '2020-03-05T20:06:51Z',
			dateModified: '2020-03-05T20:06:51Z',
			description: '',
			id: 30302,
			name: 'Account Administrator',
			roleType: 'regular',
		};

		const permissionResponse = {
			...RESPONSES.ONE_ITEM,
			actions: {},
			items: [permissionItem],
		};

		fetch
			.mockResponseOnce(JSON.stringify(RESPONSES.ONE_ITEM))
			.mockResponseOnce(
				JSON.stringify({
					dataDefinitionId: 38408,
					dataRecordCollectionKey: '38407',
					description: {},
					id: 38410,
					name: {
						en_US: 'qwex',
					},
					siteId: 20129,
				})
			)
			.mockResponseOnce(JSON.stringify(permissionResponse))
			.mockResponse(JSON.stringify());

		const {queryAllByTestId, queryByText} = render(
			<ListCustomObjectsWrapper />
		);

		await waitForElementToBeRemoved(() =>
			document.querySelector('span.loading-animation')
		);

		expect(fetch.mock.calls.length).toBe(1);
		expect(queryAllByTestId('item').length).toBe(1);

		const permission = queryByText('app-permissions');

		await act(async () => {
			fireEvent.click(permission);
		});

		await act(async () => {
			jest.runAllTimers();
		});

		expect(fetch.mock.calls.length).toBe(4);
		expect(queryByText(permissionItem.name)).toBeTruthy();

		const checkboxes = document.querySelectorAll(
			'.modal-body input[type="checkbox"]'
		);

		expect(checkboxes.length).toBe(4);

		const [addPermission] = checkboxes;

		expect(addPermission.checked).toBe(false);

		fireEvent.click(addPermission);

		expect(addPermission.checked).toBe(true);

		const save = queryByText('save');

		await act(async () => {
			fireEvent.click(save);
		});

		expect(fetch.mock.calls.length).toBe(6);
		expect(spySuccessToast.mock.calls.length).toBe(1);
	});

	it('renders with data and hit actions', async () => {
		fetch.mockResponseOnce(JSON.stringify(RESPONSES.ONE_ITEM));

		const {baseElement} = render(<ListCustomObjectsWrapper />);

		expect(history.length).toBe(1);
		expect(history.location.pathname).toBe('/');

		await waitForElementToBeRemoved(() =>
			baseElement.querySelector('span.loading-animation')
		);

		const dropDownMenu = baseElement.querySelectorAll('.dropdown-menu');
		const actions = dropDownMenu[1].querySelectorAll('.dropdown-item');

		expect(actions.length).toBe(5);
		expect(history.length).toBe(1);
		expect(history.location.pathname).toBe('/');

		const [formView, tableView, apps] = actions;

		fireEvent.click(formView);

		expect(history.length).toBe(2);
		expect(history.location.pathname).toBe('/custom-object/1/form-views');

		fireEvent.click(tableView);

		expect(history.length).toBe(3);
		expect(history.location.pathname).toBe('/custom-object/1/table-views');

		fireEvent.click(apps);

		expect(history.length).toBe(4);
		expect(history.location.pathname).toBe('/custom-object/1/apps');
	});
});
