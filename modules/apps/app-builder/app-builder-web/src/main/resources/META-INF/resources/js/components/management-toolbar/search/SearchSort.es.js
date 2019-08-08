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
import {SearchContext} from '../../search-container/SearchContext.es';

const {Group, Item, ItemList} = ClayDropDown;

export default ({columns}) => {
	const {dispatch} = useContext(SearchContext);
	columns = columns.filter(column => column.sortable);

	let defaultColumn = columns.find(column =>
		Object.hasOwnProperty.call(column, 'asc')
	);

	if (!defaultColumn) {
		defaultColumn = {
			...columns[0],
			asc: true
		};
	}

	const [state, setState] = useState({
		active: false,
		asc: defaultColumn.asc,
		column: defaultColumn.key
	});

	const sort = (asc, column) => {
		setState({
			active: false,
			asc,
			column
		});

		dispatch({type: 'SORT', sort: `${column}:${asc ? 'asc' : 'desc'}`});
	};

	const {active, asc, column} = state;

	return (
		<ul className="navbar-nav">
			<li className="dropdown nav-item">
				<ClayDropDown
					active={active}
					alignmentPosition={Align.BottomLeft}
					onActiveChange={newActive =>
						setState(prevState => ({
							...prevState,
							active: newActive
						}))
					}
					trigger={
						<button
							aria-expanded="false"
							aria-haspopup="true"
							className="btn nav-link btn-unstyled"
							type="button"
						>
							<span className="navbar-breakpoint-down-d-none">
								{Liferay.Language.get('filter-and-order')}

								<ClayIcon
									className="inline-item inline-item-after"
									symbol="caret-bottom"
								/>
							</span>
						</button>
					}
				>
					<ItemList>
						<Group header={Liferay.Language.get('order-by')}>
							{columns.map((columnObject, index) => (
								<Item
									active={column === columnObject.key}
									href="javascript:;"
									key={index}
									onClick={() => sort(asc, columnObject.key)}
								>
									{columnObject.value}
								</Item>
							))}
						</Group>
					</ItemList>
				</ClayDropDown>
			</li>

			<li className="nav-item">
				<a
					className={`nav-link nav-link-monospaced ${
						asc
							? 'order-arrow-up-active'
							: 'order-arrow-down-active'
					}`}
					href="javascript:;"
					onClick={() => sort(!asc, column)}
					title={Liferay.Language.get('reverse-sort-direction')}
				>
					<ClayIcon symbol="order-arrow" />
				</a>
			</li>
		</ul>
	);
};
