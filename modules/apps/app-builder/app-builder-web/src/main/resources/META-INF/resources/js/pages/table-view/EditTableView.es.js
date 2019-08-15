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

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import ClayNavigationBar from '@clayui/navigation-bar';
import React, {useEffect, useState} from 'react';
import Button from '../../components/button/Button.es';
import {Loading} from '../../components/loading/Loading.es';
import Sidebar from '../../components/sidebar/Sidebar.es';
import {addItem, getItem} from '../../utils/client.es';

const {Item} = ClayNavigationBar;
const {Body, Header} = Sidebar;

export default ({
	history,
	match: {
		params: {dataDefinitionId}
	}
}) => {
	const [isOpen, setOpen] = useState(true);

	const toggle = () => {
		setOpen(!isOpen);
	};

	const [name, setName] = useState('');
	const [dataDefinition, setDataDefinition] = useState(null);

	const addTableView = () => {
		addItem(
			`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-list-views`,
			{
				name: {
					value: name
				}
			}
		).then(() => history.goBack());
	};

	const onChange = event => {
		setName(event.target.value);
	};

	useEffect(() => {
		getItem(
			`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}`
		).then(setDataDefinition);
	}, [dataDefinitionId]);

	const {dataDefinitionFields = []} = dataDefinition || {};

	return (
		<Loading isLoading={dataDefinition === null}>
			<nav className="component-tbar subnav-tbar-light tbar tbar-article">
				<div className="container-fluid container-fluid-max-xl">
					<ul className="tbar-nav">
						<li className="tbar-item tbar-item-expand">
							<div className="input-group">
								<div className="input-group-item">
									<input
										aria-label={Liferay.Language.get(
											'untitled-table-view'
										)}
										className="form-control form-control-inline"
										onChange={onChange}
										placeholder={Liferay.Language.get(
											'untitled-table-view'
										)}
										type="text"
										value={name}
									/>
								</div>
							</div>
						</li>
						<li className="tbar-item">
							<div className="tbar-section">
								<ClayButton
									className="mr-3"
									displayType="secondary"
									onClick={() => history.goBack()}
									small
								>
									{Liferay.Language.get('cancel')}
								</ClayButton>
								<ClayButton
									className="mr-3"
									onClick={addTableView}
									small
								>
									{Liferay.Language.get('save')}
								</ClayButton>
							</div>
						</li>
					</ul>
				</div>
			</nav>
			<Sidebar isOpen={isOpen}>
				<Header>
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
										onClick={toggle}
										symbol="angle-right"
									/>
								</div>
							</div>
						</div>
					</div>
				</Header>
				<Body>
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
				</Body>
			</Sidebar>
		</Loading>
	);
};
