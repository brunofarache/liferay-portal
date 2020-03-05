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
import React from 'react';

import {PopoverBase} from '../../../../src/main/resources/META-INF/resources/js/components/popover/PopoverBase.es';

const placements = ['bottom', 'left', 'none', 'right', 'top'];

describe('PopoverBase', () => {
	afterEach(() => {
		cleanup();
	});

	placements.forEach(placement => {
		it(`render popover on ${placement}`, () => {
			const propsConfig = {
				none: {},
				rest: {
					placement,
					visible: true,
				},
			};
			const props = propsConfig[placement] || propsConfig.rest;
			const {container} = render(
				<PopoverBase {...props}>
					<PopoverBase.Header>
						<h1>{`Awesome Header at ${placement}`}</h1>
					</PopoverBase.Header>
					<PopoverBase.Body>
						<p>{`Awesome Body at ${placement}`}</p>
					</PopoverBase.Body>
					<PopoverBase.Footer>
						<span>{`Awesome Footer at ${placement}`}</span>
					</PopoverBase.Footer>
				</PopoverBase>
			);
			const assertsType = ['toBeFalsy', 'toBeTruthy'];
			const assertValue = +(placement !== 'none');
			const assert = {
				original: assertsType[assertValue],
				revert: assertsType[+!assertValue],
			};

			expect(container.querySelector('div.arrow'))[assert.original]();
			expect(
				container.querySelector(
					`div.popover.clay-popover-${placement}.hide`
				)
			)[assert.revert]();
			expect(
				container.querySelector(`div.popover.clay-popover-${placement}`)
			).toBeTruthy();
			expect(
				container.querySelector('.popover-header h1').innerHTML
			).toBe(`Awesome Header at ${placement}`);
			expect(container.querySelector('.popover-body p').innerHTML).toBe(
				`Awesome Body at ${placement}`
			);
			expect(
				container.querySelector('.popover-footer span').innerHTML
			).toBe(`Awesome Footer at ${placement}`);
		});
	});
});
