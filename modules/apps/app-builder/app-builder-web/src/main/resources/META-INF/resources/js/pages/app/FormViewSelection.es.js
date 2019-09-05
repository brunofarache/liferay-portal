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

import moment from 'moment';
import React, {useState, useEffect, useContext} from 'react';
import {withRouter} from 'react-router-dom';
import {ClayRadio, ClayRadioGroup} from '@clayui/form';
import ClayTable from '@clayui/table';
import {getItem} from '../../utils/client.es';
import {AppContext} from './AppContext.es';
import Button from '../../components/button/Button.es';

export default withRouter(({match: {params: {dataDefinitionId}}}) => {
	const {app, setApp} = useContext(AppContext);
	const [formViews, setFormViews] = useState([]);
	const [selectedIndex, setSelectedIndex] = useState(null);

	useEffect(() => {
		const getFormViews = getItem(
			`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-layouts`
		);

		getFormViews.then(response => {
			setFormViews(response.items);
		});
	}, [dataDefinitionId]);

	const handleSelectedFormViewChange = newIndex => {
		setSelectedIndex(newIndex);
		setApp({
			...app,
			dataLayoutId: formViews[newIndex].id
		});
	};

	return (
		<>
			<div className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<h2>{Liferay.Language.get('select-a-form-view')}</h2>
				</div>
			</div>

			<adiv className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<div className="input-group">
						<div className="input-group-item">
							<input
								aria-label={Liferay.Language.get('search-for')}
								className="form-control input-group-inset input-group-inset-after"
								disabled={true}
								placeholder={Liferay.Language.get('search-for')}
								type="text"
							/>

							<div className="input-group-inset-item input-group-inset-item-after">
								<Button
									disabled={true}
									displayType="unstyled"
									symbol="search"
								/>
							</div>
						</div>
					</div>
				</div>
			</adiv>

			<div className="autofit-row pl-4 pr-4">
				<div className="autofit-col-expand">
					<table
						className={
							'table table-responsive table-autofit table-hover table-heading-nowrap table-nowrap'
						}
					>
						<ClayTable.Head>
							<ClayTable.Row>
								<ClayTable.Cell expanded={true} headingCell>
									{Liferay.Language.get('name')}
								</ClayTable.Cell>
								<ClayTable.Cell headingCell>
									{Liferay.Language.get('create-date')}
								</ClayTable.Cell>
								<ClayTable.Cell headingCell>
									{Liferay.Language.get('modified-date')}
								</ClayTable.Cell>
								<ClayTable.Cell headingCell></ClayTable.Cell>
							</ClayTable.Row>
						</ClayTable.Head>

						<ClayTable.Body>
							{formViews.map((formView, index) => {
								return (
									<ClayTable.Row key={index}>
										<ClayTable.Cell align="left">
											{formView.name.en_US}
										</ClayTable.Cell>
										<ClayTable.Cell>
											{moment(
												formView.dateCreated
											).fromNow()}
										</ClayTable.Cell>
										<ClayTable.Cell>
											{moment(
												formView.dateModified
											).fromNow()}
										</ClayTable.Cell>
										<ClayTable.Cell align={'right'}>
											<ClayRadioGroup
												inline
												onSelectedValueChange={
													handleSelectedFormViewChange
												}
												selectedValue={selectedIndex}
											>
												<ClayRadio
													value={index}
												></ClayRadio>
											</ClayRadioGroup>
										</ClayTable.Cell>
									</ClayTable.Row>
								);
							})}
						</ClayTable.Body>
					</table>
				</div>
			</div>
		</>
	);
});
