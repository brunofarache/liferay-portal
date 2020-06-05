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

import Sidebar from '../../../../src/main/resources/META-INF/resources/js/components/sidebar/Sidebar.es';
import SidebarContext from '../../../../src/main/resources/META-INF/resources/js/components/sidebar/SidebarContext.es';

const field = {
    icon: "text",
	label: "Text",
	name: "Text",
	options: {Option: "Option"},
	title: "Text",
	type: "text",
}

const sidebarState = {
	field,
	isOpen: true,
	totalEntries: 6
}

const context = {
	...sidebarState,
	formReportRecordsFieldValuesURL: '/',
	portletNamespace: '_com_liferay_dynamic_data_mapping_form_report_web_portlet_DDMFormReportPortlet_',
	toggleSidebar: () => !sidebarState.isOpen
}


describe('SidebarContext', async () => {
	afterEach(cleanup);

	it('renders', () => {
		fetch.mockResponseOnce(JSON.stringify(LIST));

		// const {asFragment} = render(
		// 	<SidebarContext value={context}>
		// 		<Sidebar />
		// 	</SidebarContext>
		// );

		// expect(asFragment()).toMatchSnapshot();
	});
});

const LIST = ['name1', 'name2', 'name3', 'name4', 'name5', 'name6'];
