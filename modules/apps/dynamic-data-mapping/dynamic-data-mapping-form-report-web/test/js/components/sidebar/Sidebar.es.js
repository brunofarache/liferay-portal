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

const props = {
	field: {icon: 'text', name: 'fieldName'},
	onClick: () => {},
	open: false,
	totalEntries: 5,
	url:
		'http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_dynamic_data_mapping_form_report_web_portlet_DDMFormReportPortlet&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=%2Fform-report%2Fget_records_field_values&p_p_cacheability=cacheLevelPage&_com_liferay_dynamic_data_mapping_form_report_web_portlet_DDMFormReportPortlet_mvcRenderCommandName=%2Fadmin%2Fedit_form_instance&_com_liferay_dynamic_data_mapping_form_report_web_portlet_DDMFormReportPortlet_redirect=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F%7E%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview&_com_liferay_dynamic_data_mapping_form_report_web_portlet_DDMFormReportPortlet_formInstanceId=36185',
};

describe('Sidebar', () => {
	afterEach(cleanup);

	it('renders loading animation while requisition is loading', async () => {
		fetch.mockResponseOnce(JSON.stringify({response: ['name1']}));

		const {asFragment} = render(<Sidebar {...props} />);

		expect(asFragment()).toMatchSnapshot();
	});
});
