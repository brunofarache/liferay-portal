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

import EmptyState from '../../../../src/main/resources/META-INF/resources/js/components/search-container/EmptyState.es';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

describe('EmptyState', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {queryByText} = render(
			<EmptyState title='title' description='description' />
		);

		expect(queryByText('title')).toBeTruthy();
		expect(queryByText('description')).toBeTruthy();
	});

	it('renders without title', () => {
		const {queryByText} = render(<EmptyState description='description' />);

		expect(queryByText('title')).toBeNull();
		expect(queryByText('description')).toBeTruthy();
	});
});
