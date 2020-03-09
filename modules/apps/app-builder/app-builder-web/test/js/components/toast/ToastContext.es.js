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

import {
	ToastContext,
	ToastContextProvider,
} from '../../../../src/main/resources/META-INF/resources/js/components/toast/ToastContext.es';

describe('ListView', () => {
	afterEach(() => {
		cleanup();
	});

	it('renders toast and dismiss manually', async () => {
		const message = 'Toast Called';
		const {container, queryByText} = render(
			<ToastContextProvider>
				<ToastContext.Consumer>
					{({addToast}) => (
						<button onClick={() => addToast({message})}>
							Click Here
						</button>
					)}
				</ToastContext.Consumer>
			</ToastContextProvider>
		);

		const button = queryByText('Click Here');
		expect(button).toBeTruthy();
		expect(queryByText(message)).toBeNull();
		expect(
			container.querySelector('div.alert.alert-dismissible.alert-info')
		).toBeNull();
		expect(queryByText).toBeTruthy();

		act(() => {
			button.dispatchEvent(new MouseEvent('click', {bubbles: true}));
		});

		expect(
			container.querySelector('div.alert.alert-dismissible.alert-info')
		).toBeTruthy();

		const closeButton = container.querySelector('button.close');

		expect(queryByText(message)).toBeTruthy();

		act(() => {
			closeButton.dispatchEvent(new MouseEvent('click', {bubbles: true}));
		});

		expect(
			container.querySelector('div.alert.alert-dismissible.alert-info')
		).toBeNull();
	});
});
