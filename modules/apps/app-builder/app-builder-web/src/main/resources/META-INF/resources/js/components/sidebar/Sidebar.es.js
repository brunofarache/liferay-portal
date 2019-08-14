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

import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import ClayNavigationBar from '@clayui/navigation-bar';
import React from 'react';
import Button from '../button/Button.es';

const {Item} = ClayNavigationBar;

export default ({dataDefinitionFields}) => {
	return (
		<div className="sidebar-container">
			<div className="sidebar sidebar-light">
				<div className="sidebar-header">
					<div className="autofit-row sidebar-section">
						<div className="autofit-col autofit-col-expand">
							<div className="input-group">
								<div className="input-group-item">
									<input
										aria-label={Liferay.Language.get(
											'search'
										)}
										className="form-control input-group-inset input-group-inset-after"
										placeholder={Liferay.Language.get(
											'search'
										)}
										type="text"
									/>

									<div className="input-group-inset-item input-group-inset-item-after">
										<button
											className="btn btn-unstyled"
											type="button"
										>
											<ClayIcon symbol="search" />
										</button>
									</div>
								</div>
								<div className="input-group-item input-group-item-shrink">
									<Button
										displayType="secondary"
										symbol="angle-right"
									/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div className="sidebar-body">
					<ClayNavigationBar triggerLabel="Item 1">
						<Item active>
							<ClayLink
								className="nav-link"
								displayType="unstyled"
							>
								{Liferay.Language.get('columns')}
							</ClayLink>
						</Item>
						<Item>
							<ClayLink
								className="nav-link"
								displayType="unstyled"
							>
							    {Liferay.Language.get('filters')}
							</ClayLink>
						</Item>
					</ClayNavigationBar>
					<dl className="sidebar-dl sidebar-section">
						<dd className="sidebar-dd">
							<ul className="list-group sidebar-list-group">
								{dataDefinitionFields.map(
									(dataDefinitionField, index) => (
										<li
											className="list-group-item list-group-item-flex"
											key={index}
										>
											<div className="autofit-col">
												<div className="sticker sticker-secondary">
													<span className="inline-item">
														<ClayIcon symbol="drag" />
													</span>
												</div>
											</div>
											<div className="autofit-col autofit-col-expand">
												<section className="autofit-section">
													<div className="list-group-title text-truncate-inline">
														{
															dataDefinitionField.name
														}
													</div>
												</section>
											</div>
										</li>
									)
								)}
							</ul>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	);
};
