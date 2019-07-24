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

import {cleanup, render} from '@testing-library/react';
import {disableActWarnings, restoreConsole} from '../../utils.es';
import SearchContainer from '../../../../src/main/resources/META-INF/resources/js/components/search-container/SearchContainer.es';
import lang from '../../../../src/main/resources/META-INF/resources/js/utils/lang.es';
import React from 'react';
import {waitForElementToBeRemoved} from '@testing-library/dom';

import {
	ACTIONS,
	COLUMNS,
	EMPTY_STATE,
	ENDPOINT,
	FORMATTER,
	RESPONSES
} from '../../constants.es';

const setup = () => {
	let originalError;
	let originalSub;

	beforeAll(() => {
		originalError = disableActWarnings();
	});

	afterAll(() => {
		restoreConsole(originalError);
	});

	beforeEach(() => {
		originalSub = lang.sub;
		lang.sub = jest.fn();
	});

	afterEach(() => {
		lang.sub = originalSub;
		cleanup();
	});
};

describe('SearchContainer', () => {
	setup();

	it('renders with empty state', async () => {
		fetch.mockResponse(JSON.stringify(RESPONSES.NO_ITEMS));

		const {queryByText} = render(
			<SearchContainer
				actions={ACTIONS}
				columns={COLUMNS}
				emptyState={EMPTY_STATE}
				endpoint={ENDPOINT}
				formatter={FORMATTER}
			/>
		);

		await waitForElementToBeRemoved(() =>
			document.querySelector('span.loading-animation')
		);

		expect(queryByText(EMPTY_STATE.title)).toBeTruthy();
		expect(queryByText(EMPTY_STATE.description)).toBeTruthy();
	});

	it('renders with 1 item', async () => {
		fetch.mockResponse(JSON.stringify(RESPONSES.ONE_ITEM));

		const {container, queryAllByTestId} = render(
			<SearchContainer
				actions={ACTIONS}
				columns={COLUMNS}
				emptyState={EMPTY_STATE}
				endpoint={ENDPOINT}
				formatter={FORMATTER}
			/>
		);

		await waitForElementToBeRemoved(() =>
			document.querySelector('span.loading-animation')
		);

		expect(queryAllByTestId('item').length).toBe(1);
		expect(container.querySelectorAll('li.page-item').length).toBe(3);

		const [first, second, third] = lang.sub.mock.calls[0][1];
		expect(first).toBe(1);
		expect(second).toBe(1);
		expect(third).toBe(1);
	});

	it('renders with 21 items and 2 pages', async () => {
		fetch.mockResponse(JSON.stringify(RESPONSES.TWENTY_ONE_ITEMS));

		const {container, queryAllByTestId} = render(
			<SearchContainer
				actions={ACTIONS}
				columns={COLUMNS}
				emptyState={EMPTY_STATE}
				endpoint={ENDPOINT}
				formatter={FORMATTER}
			/>
		);

		await waitForElementToBeRemoved(() => {
			return document.querySelector('span.loading-animation');
		});

		expect(queryAllByTestId('item').length).toBe(20);
		expect(container.querySelectorAll('li.page-item').length).toBe(4);
		expect(
			container.querySelector('li.page-item.active').firstElementChild
				.textContent
		).toBe('1');

		const [first, second, third] = lang.sub.mock.calls[0][1];
		expect(first).toBe(1);
		expect(second).toBe(20);
		expect(third).toBe(21);
	});
});
