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
import CreationMultiStep from './CreationMultiStep.es';
import Button from '../../components/button/Button.es';
import EditAppHeader from './EditAppHeader.es';

export default ({
	history,
	match: {
		params: {dataDefinitionId}
	}
}) => {
	const handleCancel = () => {
		history.push(`/custom-object/${dataDefinitionId}/apps/`);
	};

	const handlePrevious = () => {
		history.push(`/custom-object/${dataDefinitionId}/apps/add/form-view`);
	};

	const handleForwardNavigation = () => {
		history.push(`/custom-object/${dataDefinitionId}/apps/add/deployment`);
	};

	return (
		<>
			<div className="container-fluid container-fluid-max-lg mt-4">
				<div className="card card-root shadowless-card">
					<EditAppHeader />

					<div className="card-body pt-0 pr-0 pl-0">
						<h4 className="card-divider mb-4"></h4>

						<div className="autofit-row">
							<div className="col-md-12">
								<CreationMultiStep
									currentStep={2}
									totalSteps={3}
								/>
							</div>
						</div>

						<h4 className="card-divider"></h4>

						<div className="card-footer bg-transparent">
							<div className="autofit-row">
								<div className="col-md-4">
									<Button
										displayType="secondary"
										onClick={handleCancel}
									>
										{Liferay.Language.get('cancel')}
									</Button>
								</div>
								<div className="col-md-4 offset-md-4 text-right">
									<Button
										className="mr-2"
										displayType="secondary"
										onClick={handlePrevious}
									>
										{Liferay.Language.get('previous')}
									</Button>
									<Button
										displayType="primary"
										onClick={handleForwardNavigation}
									>
										{Liferay.Language.get('next')}
									</Button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</>
	);
};
