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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';
import {HashRouter} from 'react-router-dom';

import {AppContext} from '../../../../src/main/resources/META-INF/resources/js/AppContext.es';
import ControlMenu from '../../../../src/main/resources/META-INF/resources/js/components/control-menu/ControlMenu.es';

function addElement({classEl, el = 'div'}) {
	const element = document.createElement(el);
	element.classList.add(classEl);

	return document.body.appendChild(element);
}

describe('ControlMenu', () => {
	afterEach(() => {
		cleanup();
		jest.restoreAllMocks();
		document.getElementsByTagName('html')[0].innerHTML = '';
	});

	xit('renders ControlMenu as standalone with InlineControlMenu', () => {
		const context = {
			appDeploymentType: 'standalone',
		};

		const props = {
			backURL: '../',
			title: 'title',
			tooltip: 'tooltip',
		};

		const {container, queryByText} = render(
			<HashRouter>
				<AppContext.Provider value={context}>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		const {title, tooltip} = props;

		expect(queryByText(title)).toBeTruthy();
		expect(document.title).toBe(title);
		expect(
			container.querySelector('.app-builder-control-menu.standalone')
		).toBeTruthy();
		expect(
			container.querySelector(`[data-title="${tooltip}"]`)
		).toBeTruthy();
	});

	it('hello', () => {
		const context = {
			appDeploymentType: 'widget',
		};

		// const spy = jest
		// 	.spyOn(document, 'getElementById')
		// 	.mockReturnValueOnce(addElement({classEl: 'widget-container'}));

		const props = {
			backURL: 'https://liferay.com/api',
			tooltip: 'myapp-tips',
		};

		const {baseElement, debug} =render(
			<>
				<div className="widget-container" id="widget">hello</div>
				<HashRouter>
					<AppContext.Provider value={context}>
						<ControlMenu {...props} />
					</AppContext.Provider>
				</HashRouter>
			</>
		);

		debug();

		expect(document.querySelector(".widget-container")).toBeTruthy();

		// expect(spy.mock.calls.length).toBe(1);
		expect(
			document.querySelector('.app-builder-control-menu.widget')
		).toBeTruthy();
		expect(
			document.querySelector(`[data-title="${props.tooltip}"]`)
		).toBeTruthy();
		expect(document.querySelector('a').href).toBe(props.backURL);
	});

	xit('renders ControlMenu with PortalControlMenu and validate actions and links', () => {
		const context = {
			appDeploymentType: 'normal',
		};
		const spy = jest
			.spyOn(document, 'querySelector')
			.mockReturnValueOnce(addElement({classEl: 'el1'}))
			.mockReturnValueOnce(addElement({classEl: 'el2'}))
			.mockReturnValueOnce(addElement({classEl: 'el3'}))
			.mockReturnValueOnce(addElement({classEl: 'el4'}));

		const actions = [
			{
				action: jest.fn(),
				name: 'remove',
			},
			{
				name: 'update',
			},
		];

		const props = {
			actions,
			backURL: 'http://localhost:8080',
			title: 'myapp',
			tooltip: 'myapp-tips',
		};

		const {queryByText} = render(
			<HashRouter>
				<AppContext.Provider value={context}>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		expect(spy.mock.calls.length).toBe(4);

		const remove = queryByText('remove');
		const update = queryByText('update');
		const dropDown = document.querySelector('.el2 .dropdown button');

		expect(document.querySelector(`.el1 a`).href).toBe(`${props.backURL}/`);
		expect(document.querySelector('.el3').innerHTML).toBe(props.title);
		expect(
			document.querySelector(`.el4[title="${props.tooltip}"]`)
		).toBeTruthy();

		expect(remove).toBeTruthy();
		expect(update).toBeTruthy();
		expect(dropDown).toBeTruthy();

		fireEvent.click(remove);
		fireEvent.click(update);

		expect(actions[0].action.mock.calls.length).toBe(1);
	});

	xit('renders ControlMenu with PortalControlMenu without actions and link', () => {
		const context = {
			appDeploymentType: 'normal',
		};
		const spy = jest
			.spyOn(document, 'querySelector')
			.mockReturnValueOnce(addElement({classEl: 'el1'}))
			.mockReturnValueOnce(null);

		const props = {
			actions: [],
			title: 'myapp',
		};

		const {queryByText} = render(
			<HashRouter>
				<AppContext.Provider value={context}>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		expect(queryByText(props.title)).toBeTruthy();
		expect(spy.mock.calls.length).toBe(2);
	});

	xit('renders ControlMenu with PortalControlMenu without tooltip', () => {
		const context = {
			appDeploymentType: 'normal',
		};
		const spy = jest
			.spyOn(document, 'querySelector')
			.mockReturnValueOnce(addElement({classEl: 'el1'}))
			.mockReturnValueOnce(addElement({classEl: 'el2'}))
			.mockReturnValueOnce(addElement({classEl: 'el3'}))
			.mockReturnValueOnce(addElement({classEl: 'el4'}))
			.mockReturnValueOnce(addElement({classEl: 'el5'}));

		const props = {
			actions: [
				{
					action: jest.fn(),
					name: 'remove',
				},
			],
			backURL: '../../',
			title: 'myapp',
		};

		const {queryAllByRole, queryByText} = render(
			<HashRouter>
				<AppContext.Provider value={context}>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		expect(spy.mock.calls.length).toBe(4);

		const [dropDown, remove] = queryAllByRole('button');

		fireEvent.click(remove);

		expect(props.actions[0].action.mock.calls.length).toBe(1);

		fireEvent.click(dropDown);

		expect(queryByText(props.title)).toBeTruthy();

		expect(spy.mock.calls.length).toBe(5);
	});
});
