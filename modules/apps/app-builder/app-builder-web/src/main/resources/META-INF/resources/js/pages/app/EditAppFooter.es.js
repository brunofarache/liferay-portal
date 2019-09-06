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

import React, {useContext} from 'react';
import {withRouter} from 'react-router-dom';
import {AppContext} from './AppContext.es';
import Button from '../../components/button/Button.es';
import {updateItem, addItem} from '../../utils/client.es';

export default withRouter(({history, currentStep, dataDefinitionId}) => {
	const {app} = useContext(AppContext);

	const handleSubmit = () => {
		if (app.name.en_US === '') {
			return;
		}

		if (app.id) {
			updateItem(`/o/app-builder/v1.0/apps/${app.id}`, app).then(
				handleBack
			);
		} else {
			addItem(
				`/o/app-builder/v1.0/data-definitions/${dataDefinitionId}/apps`,
				app
			).then(handleBack);
		}
	};

	const handleBack = () => {
		history.push(`/custom-object/${dataDefinitionId}/apps`);
	};

	const handleForwardNavigation = () => {
		const nextStep = currentStep === 1 ? 'table-view' : 'deployment';
		history.push(`/custom-object/${dataDefinitionId}/apps/add/${nextStep}`);
	};

	const handleBackwardNavigation = () => {
		const prevStep = currentStep === 3 ? 'table-view' : 'form-view';
		history.push(`/custom-object/${dataDefinitionId}/apps/add/${prevStep}`);
	};

	return (
		<div className="card-footer bg-transparent">
			<div className="autofit-row">
				<div className="col-md-4">
					<Button displayType="secondary" onClick={handleBack}>
						{Liferay.Language.get('cancel')}
					</Button>
				</div>
				<div className="col-md-4 offset-md-4 text-right">
					{currentStep > 1 ? (
						<Button
							className="mr-2"
							displayType="secondary"
							onClick={handleBackwardNavigation}
						>
							{Liferay.Language.get('previous')}
						</Button>
					) : null}
					<Button
						displayType="primary"
						onClick={
							currentStep === 3
								? handleSubmit
								: handleForwardNavigation
						}
					>
						{currentStep === 3
							? Liferay.Language.get('save')
							: Liferay.Language.get('next')}
					</Button>
				</div>
			</div>
		</div>
	);
});
