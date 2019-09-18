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

export default ({
	children,
	isActive,
	optionDescription,
	optionKey,
	optionTitle,
	onToggle
}) => {
	return (
		<>
			<div className="autofit-row mb-2">
				<div className="autofit-col-expand">
					<section className="autofit-section">
						<h3>
							<strong>{optionTitle}</strong>
						</h3>
						<p className="list-group-subtext">
							<small>{optionDescription}</small>
						</p>
					</section>
				</div>

				<div className="autofit-col right">
					<label className="toggle-switch">
						<input
							checked={isActive}
							className="toggle-switch-check"
							onChange={() => onToggle(optionKey)}
							type="checkbox"
						/>
						<span aria-hidden="true" className="toggle-switch-bar">
							<span className="toggle-switch-handle"></span>
						</span>
					</label>
				</div>
			</div>

			{isActive && children}

			<div className="autofit-row mb-4">
				<div className="autofit-col-expand">
					<h4 className="card-divider"></h4>
				</div>
			</div>
		</>
	);
};
