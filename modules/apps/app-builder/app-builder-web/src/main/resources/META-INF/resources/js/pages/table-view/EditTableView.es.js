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

import React, {useEffect, useState} from 'react';
import ControlMenu from '../../components/control-menu/ControlMenu.es';
import FieldTypeList from '../../components/field-types/FieldTypeList.es';
import {Loading} from '../../components/loading/Loading.es';
import Sidebar from '../../components/sidebar/Sidebar.es';
import UpperToolbar from '../../components/upper-toolbar/UpperToolbar.es';
import {addItem, getItem, updateItem} from '../../utils/client.es';

export default ({
	history,
	match: {
		params: {dataDefinitionId, dataListViewId}
	}
}) => {
	const [state, setState] = useState({
		dataDefinition: null,
		dataListView: null
	});

	const [keywords, setKeywords] = useState('');

	let title = Liferay.Language.get('new-table-view');

	if (dataListViewId) {
		title = Liferay.Language.get('edit-table-view');
	}

	const onInput = event => {
		const name = event.target.value;

		setState(prevState => ({
			...prevState,
			dataListView: {
				...prevState.dataListView,
				name: {
					en_US: name
				}
			}
		}));
	};

	const validate = () => {
		const {dataListView} = state;

		if (!dataListView) {
			return null;
		}

		const name = dataListView.name.en_US.trim();

		if (name === '') {
			return null;
		}

		return {
			...dataListView,
			name: {
				en_US: name
			}
		};
	};

	const handleSubmit = () => {
		const dataListView = validate();

		if (dataListView === null) {
			return;
		}

		if (dataListViewId) {
			updateItem(
				`/o/data-engine/v1.0/data-list-views/${dataListViewId}`,
				dataListView
			).then(() => history.goBack());
		} else {
			addItem(
				`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-list-views`,
				dataListView
			).then(() => history.goBack());
		}
	};

	useEffect(() => {
		const getDataDefinition = getItem(
			`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}`
		);

		if (dataListViewId) {
			const getDataListView = getItem(
				`/o/data-engine/v1.0/data-list-views/${dataListViewId}`
			);

			Promise.all([getDataDefinition, getDataListView]).then(
				([dataDefinition, dataListView]) => {
					setState({
						dataDefinition,
						dataListView
					});
				}
			);
		} else {
			getDataDefinition.then(dataDefinition => {
				setState(prevState => ({
					...prevState,
					dataDefinition
				}));
			});
		}
	}, [dataDefinitionId, dataListViewId]);

	const {dataDefinition, dataListView} = state;
	const {dataDefinitionFields = []} = dataDefinition || {};
	const {name: {en_US: dataListViewName = ''} = {}} = dataListView || {};

	return (
		<>
			<ControlMenu backURL="../" title={title} />

			<Loading isLoading={dataDefinition === null}>
				<form
					onSubmit={event => {
						event.preventDefault();

						handleSubmit();
					}}
				>
					<UpperToolbar>
						<UpperToolbar.Input
							onInput={onInput}
							placeholder={Liferay.Language.get(
								'untitled-table-view'
							)}
							value={dataListViewName}
						/>
						<UpperToolbar.Group>
							<UpperToolbar.Button
								displayType="secondary"
								onClick={() => history.goBack()}
							>
								{Liferay.Language.get('cancel')}
							</UpperToolbar.Button>

							<UpperToolbar.Button
								disabled={dataListViewName.trim() === ''}
								onClick={handleSubmit}
							>
								{Liferay.Language.get('save')}
							</UpperToolbar.Button>
						</UpperToolbar.Group>
					</UpperToolbar>
				</form>
				<Sidebar onSearch={setKeywords}>
					<Sidebar.Body>
						<Sidebar.Tab tabs={[Liferay.Language.get('columns')]} />

						<Sidebar.TabContent>
							<FieldTypeList
								fieldTypes={dataDefinitionFields.map(field => ({
									description: field.fieldType,
									icon: field.fieldType,
									label: field.name,
									name: field.fieldType
								}))}
								keywords={keywords}
							/>
						</Sidebar.TabContent>
					</Sidebar.Body>
				</Sidebar>
			</Loading>
		</>
	);
};
