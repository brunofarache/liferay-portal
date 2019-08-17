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

import ClayDropDown, {Align} from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import React, {useContext, useState} from 'react';
import {SearchContext} from '../SearchContext.es';
import Button from '../../button/Button.es';
import lang from '../../../utils/lang.es';

const {Item, ItemList} = ClayDropDown;

export default ({itemsCount, totalCount}) => {
	const {
		dispatch,
		state: {
			query: {page, pageSize}
		}
	} = useContext(SearchContext);

	const [active, setActive] = useState(false);
	const options = [5, 10, 20, 30, 50, 75];
	const firstEntry = pageSize * (page - 1) + 1;
	const lastEntry = firstEntry + itemsCount - 1;

	return (
		<>
			<ClayDropDown
				active={active}
				alignmentPosition={Align.RightCenter}
				className="pagination-items-per-page"
				onActiveChange={newVal => setActive(newVal)}
				trigger={
					<Button className="page-link" displayType="unstyled">
						{`${pageSize} Entries`}s
						<ClayIcon symbol="caret-double-l" />
					</Button>
				}
			>
				<ItemList>
					{options.map((size, index) => (
						<Item
							active={pageSize === size}
							key={index}
							onClick={() => {
								setActive(false);
								dispatch({
									pageSize: size,
									type: 'CHANGE_PAGE_SIZE'
								});
							}}
						>
							{size}
						</Item>
					))}
				</ItemList>
			</ClayDropDown>

			<p className="pagination-results">
				{lang.sub(Liferay.Language.get('showing-x-to-x-of-x-entries'), [
					firstEntry,
					lastEntry,
					totalCount
				])}
			</p>
		</>
	);
};
