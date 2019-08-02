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
import lang from '../../../utils/lang.es';

export default ({emptyState, keywords = ''}) => {
	const defaultEmpty = {
		title: Liferay.Language.get('there-are-no-entries'),
		description: null
	};

	const defaultSearch = {
		title: Liferay.Language.get('no-results-were-found'),
		description: lang.sub(
			Liferay.Language.get('sorry,-there-are-not-results-for-x'),
			[keywords]
		)
	};

	const empty = {
		...defaultEmpty,
		...emptyState.empty
	};

	const search = {
		...defaultSearch,
		...emptyState.search
	};

	const isSearch = keywords !== '';
	const {description, title} = isSearch ? search : empty;
	const className = isSearch ? 'taglib-search-state' : 'taglib-empty-state';

	return (
		<div className="taglib-empty-result-message">
			<div className="text-center">
				<div className={className} />

				{title && (
					<h1 className="taglib-empty-result-message-title">
						{title}
					</h1>
				)}

				{description && (
					<p className="taglib-empty-result-message-description">
						{description}
					</p>
				)}
			</div>
		</div>
	);
};
