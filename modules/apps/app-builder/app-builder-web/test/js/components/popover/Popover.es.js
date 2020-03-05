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
import {Align} from 'metal-position';
import React from 'react';

import Popover from '../../../../src/main/resources/META-INF/resources/js/components/popover/Popover.es';

const getTitle = () => <h1>Title</h1>;
const getContent = () => <p>Content</p>;
const getFooter = () => <span>Footer</span>;

describe('Popover', () => {
	afterEach(() => {
		cleanup();
		jest.restoreAllMocks();
	});

	it('run popover on left visible with ref', () => {
		const popoverRef = React.createRef();

		const spy = jest.spyOn(Align, 'align').mockImplementation(() => 0);

		const {container, queryByText} = render(
			<Popover ref={popoverRef} suggestedPosition="left" visible />
		);

		expect(spy).toBeCalled();
		expect(container.querySelector('div.arrow')).not.toBeNull();

		expect(queryByText('Title')).toBeNull();
		expect(queryByText('Content')).toBeNull();
		expect(queryByText('Footer')).toBeNull();
	});

	it('run popover on the right with childrens', () => {
		const {container, queryByText} = render(
			<Popover
				content={getContent}
				footer={getFooter}
				showArrow
				suggestedPosition="right"
				title={getTitle}
				visible
			/>
		);

		expect(container.querySelector('div.arrow')).not.toBeNull();

		expect(queryByText('Title')).not.toBeNull();
		expect(queryByText('Content')).not.toBeNull();
		expect(queryByText('Footer')).not.toBeNull();
	});

	it('run popover with placement none and no content', () => {
		const popoverRef = React.createRef();

		const spy = jest.spyOn(Align, 'align').mockImplementation(() => null);

		const {container, queryByText} = render(
			<Popover ref={popoverRef} showArrow title={getTitle} visible />
		);

		expect(spy).toBeCalled();
		expect(container.querySelector('div.arrow')).toBeNull();
		expect(container.querySelector('.no-content')).not.toBeNull();
		expect(container.querySelector('.hide')).toBeNull();

		expect(queryByText('Title')).not.toBeNull();
		expect(queryByText('Content')).toBeNull();
		expect(queryByText('Footer')).toBeNull();
	});
});
