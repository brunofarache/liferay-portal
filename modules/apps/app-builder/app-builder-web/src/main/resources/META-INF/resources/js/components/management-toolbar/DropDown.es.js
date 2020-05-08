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

import ClayDropDown from '@clayui/drop-down';
import {ClayCheckbox, ClayRadio} from '@clayui/form';
import React from 'react';

export const CheckboxGroup = ({
	items = [],
	label,
	onAdd,
	onRemove,
	selected = [],
}) => {
	return (
		<ClayDropDown.Group header={label}>
			<ClayDropDown.ItemList>
				{items.map(({value, ...item}, index) => (
					<ClayDropDown.Section key={index}>
						<ClayCheckbox
							{...item}
							checked={selected.includes(value)}
							onChange={({target: {checked}}) =>
								checked ? onAdd(value) : onRemove(value)
							}
							value={value}
						/>
					</ClayDropDown.Section>
				))}
			</ClayDropDown.ItemList>
		</ClayDropDown.Group>
	);
};

export const RadioGroup = ({items = [], label, onChange, selected}) => {
	return (
		<ClayDropDown.Group header={label}>
			<ClayDropDown.ItemList>
				{items.map(({value, ...item}, index) => (
					<ClayDropDown.Section key={index}>
						<ClayRadio
							{...item}
							checked={selected === value}
							onChange={() => onChange(value)}
							value={value}
						/>
					</ClayDropDown.Section>
				))}
			</ClayDropDown.ItemList>
		</ClayDropDown.Group>
	);
};

export default ({children, footerContent, ...otherProps}) => {
	return (
		<ClayDropDown {...otherProps}>
			<div className={footerContent ? 'inline-scroller' : ''}>
				<ClayDropDown.ItemList>{children}</ClayDropDown.ItemList>
			</div>

			{footerContent && (
				<div className="dropdown-footer dropdown-section pt-3">
					{footerContent}
				</div>
			)}
		</ClayDropDown>
	);
};
