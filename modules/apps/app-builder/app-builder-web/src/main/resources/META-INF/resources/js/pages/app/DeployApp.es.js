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
import ProductMenuSettings from './ProductMenuSettings.es';
import WidgetSettings from './WidgetSettings.es';

export default () => {
	return (
		<>
			<div className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<h2>{`${Liferay.Language.get('deploy-as')}...`}</h2>
				</div>
			</div>

			<ProductMenuSettings />

			<div className="autofit-row pl-2 pr-2 mb-4">
				<div className="col-md-12">
					<h4 className="card-divider"></h4>
				</div>
			</div>

			<WidgetSettings />

			<div className="autofit-row pl-2 pr-2 mb-2">
				<div className="col-md-12">
					<h4 className="card-divider"></h4>
				</div>
			</div>
		</>
	);
};
