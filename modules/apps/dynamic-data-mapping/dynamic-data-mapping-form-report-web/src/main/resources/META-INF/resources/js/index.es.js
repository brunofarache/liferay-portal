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

import EmptyState from '../js/components/card/EmptyState.es';
import App from './App.es';

export default ({data, fields}) => {
	if (!data || data.length === 0) {
		return (
			<EmptyState
				description={Liferay.Language.get(
					'when-the-form-starts-to-receive-submissions-the-entry-summaries-will-show-up-here'
				)}
				title={Liferay.Language.get('there-are-no-entries')}
			/>
		);
	}

	return (
		<div className="form-report">
			<App data={JSON.parse(data)} fields={fields} />
		</div>
	);
};
