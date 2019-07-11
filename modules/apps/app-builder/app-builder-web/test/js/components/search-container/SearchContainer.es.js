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

import {
	ACTIONS,
	COLUMNS,
	EMPTY_STATE,
	ENDPOINT,
	FORMATTER,
	RESPONSES
} from '../../constants';
import {cleanup, render} from 'react-testing-library';
import {disableActWarnings, restoreConsole} from '../../utils';
import SearchContainer from '../../../../src/main/resources/META-INF/resources/js/components/search-container/SearchContainer.es';
import React from 'react';
import {waitForElementToBeRemoved} from '@testing-library/dom';

const setup = () => {
	afterEach(cleanup);

	let originalError;
	let originalWarn;

	beforeAll(() => {
		[originalError, originalWarn] = disableActWarnings();
	});

	afterAll(() => {
		restoreConsole(originalError, originalWarn);
	});
};

describe('SearchContainer', () => {
	setup();

	it('renders with one item', async () => {
		fetch.mockResponse(JSON.stringify(RESPONSES.ONE));

		const {queryAllByTestId} = render(
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

		expect(queryAllByTestId('row').length).toBe(1);
	});
});
