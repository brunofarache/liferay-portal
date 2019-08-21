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

import React, {useContext, useEffect, useRef, useState} from 'react';
import {SearchContext} from '../../search-container/SearchContext.es';
import Button from '../../../components/button/Button.es';
import {useKeyDown} from '../../../hooks/index.es';
import getCN from 'classnames';

export default ({isDisabled}) => {
	const {
		dispatch,
		state: {query}
	} = useContext(SearchContext);

	const [keywords, setKeywords] = useState('');

	useEffect(() => {
		setKeywords(query.keywords);
	}, [query.keywords]);

	const onChange = event => {
		setKeywords(event.target.value);
	};

	const onClick = () => {
		if (!isDisabled) dispatch({keywords: keywords.trim(), type: 'SEARCH'});
	};

	const inputRef = useRef();
	useKeyDown(onClick, 13, inputRef);

	return (
		<div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
			<div className="container-fluid container-fluid-max-xl">
				<div className="input-group">
					<div className="input-group-item">
						<input
							aria-label={Liferay.Language.get('search-for')}
							className={getCN(
								'form-control',
								'input-group-inset',
								'input-group-inset-after'
							)}
							disabled={isDisabled}
							onChange={onChange}
							placeholder={Liferay.Language.get('search-for')}
							ref={inputRef}
							type="text"
							value={keywords}
						/>

						<div className="input-group-inset-item input-group-inset-item-after">
							<Button
								disabled={isDisabled}
								displayType="unstyled"
								onClick={onClick}
								symbol="search"
							/>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};
