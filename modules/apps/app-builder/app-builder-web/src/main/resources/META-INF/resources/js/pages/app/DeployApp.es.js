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
import EditAppHeader from './EditAppHeader.es';
import EditAppFooter from './EditAppFooter.es';

export default ({
	match: {
		params: {dataDefinitionId}
	}
}) => {
	return (
		<>
			<div className="container-fluid container-fluid-max-lg mt-4">
				<div className="card card-root shadowless-card">
					<EditAppHeader />

					<h4 className="card-divider mb-4"></h4>

					<div className="card-body pt-0 pr-0 pl-0">
						<div className="autofit-row">
							<div className="col-md-12">
								<CreationMultiStep
									currentStep={3}
									totalSteps={3}
								/>
							</div>
						</div>

						<h4 className="card-divider"></h4>

						<EditAppFooter
							currentStep={3}
							dataDefinitionId={dataDefinitionId}
						/>
					</div>
				</div>
			</div>
		</>
	);
};
