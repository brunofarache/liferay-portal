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

describe('ControlMenu', () => {
	afterEach(() => {
		cleanup();
		jest.restoreAllMocks();
	});

	it('renders ControlMenu as standalone with InlineControlMenu', () => {
		const context = {
			appDeploymentType: 'standalone',
			controlMenuElementId: 'standalone',
		};

		const props = {
			backURL: '../',
			title: 'title',
			tooltip: 'tooltip',
		};

		const element = document.createElement('div');
		element.id = context.controlMenuElementId;
		document.body.appendChild(element);

		const {baseElement, queryByText} = render(
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
			baseElement.querySelector('.app-builder-control-menu.standalone')
		).toBeTruthy();
		expect(
			baseElement.querySelector(`[data-title="${tooltip}"]`)
		).toBeTruthy();

		document.getElementsByTagName('html')[0].innerHTML = '';
	});

	it('renders ControlMenu as widget with InlineControlMenu', () => {
		const context = {
			appDeploymentType: 'widget',
		};

		const props = {
			backURL: 'https://liferay.com/api',
			tooltip: 'myapp-tips',
		};

		const {baseElement} = render(
			<HashRouter>
				<AppContext.Provider value={context}>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		expect(
			baseElement.querySelector('.app-builder-control-menu.widget')
		).toBeTruthy();
		expect(
			baseElement.querySelector(`[data-title="${props.tooltip}"]`)
		).toBeTruthy();
		expect(baseElement.querySelector('a').href).toBe(props.backURL);
	});

	it('renders ControlMenu with PortalControlMenu and validate actions and links', () => {
		const context = {
			appDeploymentType: 'normal',
		};

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

		const {container, queryByRole, queryByText} = render(
			<HashRouter>
				<AppContext.Provider value={context}>
					<div className="tools-control-group">
						<div className="control-menu-level-1-heading">
							title
						</div>
						<div className="taglib-icon-help"></div>
						<div className="sites-control-group">
							<div className="control-menu-nav">xx</div>
						</div>
						<li className="control-menu-nav-category user-control-group">
							<ul>
								<li>
									<div className="control-menu-icon" />
								</li>
							</ul>
						</li>
					</div>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		const remove = queryByText('remove');
		const update = queryByText('update');
		const dropDown = container.querySelector('.dropdown-toggle');

		expect(queryByRole('link').href).toBe(`${props.backURL}/`);
		expect(queryByText(props.title)).toBeTruthy();
		expect(
			container.querySelector(`[title="${props.tooltip}"]`)
		).toBeTruthy();

		expect(remove).toBeTruthy();
		expect(update).toBeTruthy();
		expect(dropDown).toBeTruthy();

		fireEvent.click(remove);
		fireEvent.click(update);

		expect(actions[0].action.mock.calls.length).toBe(1);
	});

	it('renders ControlMenu with PortalControlMenu without actions and link', () => {
		const context = {
			appDeploymentType: 'normal',
		};

		const props = {
			actions: [],
			title: 'myapp',
		};

		const {container} = render(
			<HashRouter>
				<AppContext.Provider value={context}>
					<div className="tools-control-group">
						<div className="control-menu-level-1-heading">
							title
						</div>
					</div>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		expect(
			container.querySelector('.control-menu-level-1-heading').innerHTML
		).not.toBe('title');
		expect(
			container.querySelector('.control-menu-level-1-heading').innerHTML
		).toBe(props.title);
	});

	it('renders ControlMenu with PortalControlMenu without tooltip', () => {
		const context = {
			appDeploymentType: 'normal',
		};

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
					<div className="tools-control-group">
						<div className="control-menu-level-1-heading">
							title
						</div>
						<div className="taglib-icon-help"></div>
						<div className="sites-control-group">
							<div className="control-menu-nav">xx</div>
						</div>
						<li className="control-menu-nav-category user-control-group">
							<ul>
								<li>
									<div className="control-menu-icon" />
								</li>
							</ul>
						</li>
					</div>
					<ControlMenu {...props} />
				</AppContext.Provider>
			</HashRouter>
		);

		const [dropDown, remove] = queryAllByRole('button');

		fireEvent.click(remove);

		expect(props.actions[0].action.mock.calls.length).toBe(1);

		fireEvent.click(dropDown);

		expect(queryByText(props.title)).toBeTruthy();
	});
});
