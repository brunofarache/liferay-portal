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
import ClayMultiStepNav from '@clayui/multi-step-nav';

export default ({currentStep}) => {
	const steps = [
		{
			active: currentStep === 1,
			complete: currentStep > 1
		},
		{
			active: currentStep === 2,
			complete: currentStep > 2
		},
		{
			active: currentStep === 3,
			complete: false
		}
	];

	return (
		<ClayMultiStepNav>
			{steps.map(({active, complete}, i) => (
				<ClayMultiStepNav.Item
					active={active}
					complete={complete}
					expand={i + 1 !== steps.length}
					key={i}
				>
					<ClayMultiStepNav.Divider />

					<ClayMultiStepNav.Indicator
						complete={complete}
						label={1 + i}
					/>
				</ClayMultiStepNav.Item>
			))}
		</ClayMultiStepNav>
	);
};
