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

const getTitle = position => <h1>{`Hi Title at ${position}`}</h1>;
const getContent = position => <p>{`Hi Content at ${position}`}</p>;
const getFooter = position => <span>{`Hi Footer at ${position}`}</span>;

const placements = ['bottom', 'left', 'none', 'right', 'top'];

function getPlacemenetIndex(placement) {
	const positions = {
		bottom: 0,
		'bottom-left': 1,
		'bottom-right': 2,
		left: 3,
		right: 4,
		top: 5,
		'top-left': 6,
		'top-right': 7,
	};

	return positions[placement];
}

describe('Popover', () => {
	afterEach(() => {
		cleanup();
		jest.restoreAllMocks();
	});

	placements.forEach(placement => {
		it(`render popover at ${placement}`, async () => {
			const popoverRef = React.createRef();
			const propsConfig = {
				left: {
					ref: popoverRef,
					title: () => getTitle(placement),
					visible: true,
				},
				rest: {
					content: () => getContent(placement),
					footer: () => getFooter(placement),
					ref: popoverRef,
					showArrow: true,
					suggestedPosition: placement,
					title: () => getTitle(placement),
					visible: true,
				},
				right: {
					suggestedPosition: 'right',
				},
			};
			const props = propsConfig[placement] || propsConfig.rest;

			const spy = jest
				.spyOn(Align, 'align')
				.mockImplementation(() => getPlacemenetIndex(placement));

			const {container} = render(<Popover {...props}></Popover>);
			if (props.ref) {
				expect(spy).toBeCalled();
			}

			expect(container.querySelector('div.arrow'))[
				placement !== 'none' ? 'toBeTruthy' : 'toBeFalsy'
			]();

			const title = container.querySelector('.popover-header h1');
			const content = container.querySelector('.popover-body p');
			const footer = container.querySelector('.popover-footer span');

			expect(props.title && props.content ? title.innerHTML : title).toBe(
				props.title && props.content ? `Hi Title at ${placement}` : null
			);

			expect(props.content ? content.innerHTML : content).toBe(
				props.content ? `Hi Content at ${placement}` : null
			);

			expect(
				props.footer && props.content ? footer.innerHTML : footer
			).toBe(props.footer ? `Hi Footer at ${placement}` : null);
		});
	});
});
