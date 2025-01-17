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

import React from 'react';

import {SIDEBAR_PANEL_IDS} from '../constants/sidebarPanelIds';
import {ConstantsProvider} from '../contexts/ConstantsContext';
import {SelectedMenuItemIdProvider} from '../contexts/SelectedMenuItemIdContext';
import {SidebarPanelIdProvider} from '../contexts/SidebarPanelIdContext';
import {AppLayout} from './AppLayout';
import {EmptyState} from './EmptyState';
import {Menu} from './Menu';
import {MenuItemSettingsPanel} from './MenuItemSettingsPanel';
import {MenuSettingsPanel} from './MenuSettingsPanel';
import {Toolbar} from './Toolbar';

const SIDEBAR_PANELS = [
	{
		component: MenuItemSettingsPanel,
		sidebarPanelId: SIDEBAR_PANEL_IDS.menuItemSettings,
	},
	{
		component: MenuSettingsPanel,
		sidebarPanelId: SIDEBAR_PANEL_IDS.menuSettings,
	},
];

export function App(props) {
	const {siteNavigationMenuItems} = props;

	return (
		<ConstantsProvider constants={props}>
			<SelectedMenuItemIdProvider>
				<SidebarPanelIdProvider>
					<AppLayout
						contentChildren={
							!siteNavigationMenuItems.length ? (
								<EmptyState />
							) : (
								<Menu />
							)
						}
						sidebarPanels={SIDEBAR_PANELS}
						toolbarChildren={<Toolbar />}
					/>
				</SidebarPanelIdProvider>
			</SelectedMenuItemIdProvider>
		</ConstantsProvider>
	);
}
