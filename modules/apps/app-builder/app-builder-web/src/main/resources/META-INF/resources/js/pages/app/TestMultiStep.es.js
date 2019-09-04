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

import React, {useState} from 'react';
import ClayMultiStepNav from '@clayui/multi-step-nav';

export default ({dataDefinitionId}) => {
	const [value, setValue] = useState(0);

	const steps = [
		{
			active: value === 0,
			complete: value > 0,
			onClick: () => setValue(0)
		},
		{
			active: value === 1,
			complete: value > 1,
			onClick: () => setValue(1)
		},
		{
			active: value === 2,
			complete: value > 2,
			onClick: () => setValue(2)
		}
	];

	return (
		<div className="autofit-row">
			<div className="col-md-12">
				<ClayMultiStepNav>
					{steps.map(
						({active, complete, onClick, subTitle, title}, i) => (
							<ClayMultiStepNav.Item
								active={active}
								complete={complete}
								expand={i + 1 !== steps.length}
								key={i}
							>
								                                                
								<ClayMultiStepNav.Title>
									{title}
								</ClayMultiStepNav.Title>
								<ClayMultiStepNav.Divider />
								<ClayMultiStepNav.Indicator
									complete={complete}
									label={1 + i}
									onClick={onClick}
									subTitle={subTitle}
								/>
							</ClayMultiStepNav.Item>
						)
					)}
				</ClayMultiStepNav>
			</div>
		</div>
	);
};
