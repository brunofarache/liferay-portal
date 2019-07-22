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

import ClayNavigationBar from '@clayui/navigation-bar';
import React from 'react';
import {NavLink, withRouter} from 'react-router-dom';

const {Item} = ClayNavigationBar;

function NavigationBar({
	match: {
		params: {dataDefinitionId}
	}
}) {
	const blur = event => {
		event.target.blur();
	};

	return (
		<ClayNavigationBar
			inverted
			spritemap={`${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`}
			triggerLabel={Liferay.Language.get('form-views')}
		>
			<Item>
				<NavLink
					activeClassName="active"
					className="nav-link"
					onClick={blur}
					to={`/custom-object/${dataDefinitionId}/form-views`}
				>
					{Liferay.Language.get('form-views')}
				</NavLink>
			</Item>
			<Item>
				<NavLink
					activeClassName="active"
					className="nav-link"
					onClick={blur}
					to={`/custom-object/${dataDefinitionId}/table-views`}
				>
					{Liferay.Language.get('table-views')}
				</NavLink>
			</Item>
			<Item>
				<NavLink
					activeClassName="active"
					className="nav-link"
					onClick={blur}
					to={`/custom-object/${dataDefinitionId}/deployments`}
				>
					{Liferay.Language.get('deployments')}
				</NavLink>
			</Item>
		</ClayNavigationBar>
	);
}

export default withRouter(NavigationBar);
