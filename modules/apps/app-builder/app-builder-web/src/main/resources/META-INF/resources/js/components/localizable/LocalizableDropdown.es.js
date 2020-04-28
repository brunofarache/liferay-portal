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
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import classNames from 'classnames';
import React, {useEffect, useState} from 'react';

const DEFAULT_LANGUAGE_ID = Liferay.ThemeDisplay.getLanguageId();

export const formatLabel = (label) => label.replace('_', '-');

export const formatIcon = (label) => formatLabel(label).toLowerCase();

export const LocalizableDropdownLabel = ({defaultLanguageId, translated}) => {
	let className = 'label-warning';
	let label = Liferay.Language.get('not-translated');

	if (defaultLanguageId) {
		className = 'label-info';
		label = Liferay.Language.get('default');
	}
	else if (translated) {
		className = 'label-success';
		label = Liferay.Language.get('translated');
	}

	return (
		<span className="autofit-col">
			<span className={classNames('label', className)}>
				<span className="label-item label-item-expand">{label}</span>
			</span>
		</span>
	);
};

export default ({onChangeLanguageId, translatedLanguages}) => {
	const [active, setActive] = useState(false);
	const [availableLanguageIds, setAvailableLanguageIds] = useState([]);
	const [editingLocalizableId, setEditingLocalizableId] = useState(
		DEFAULT_LANGUAGE_ID
	);

	useEffect(() => {
		if (onChangeLanguageId) {
			onChangeLanguageId(editingLocalizableId);
		}
	}, [editingLocalizableId, onChangeLanguageId]);

	useEffect(() => {
		const availableLanguageIds = Liferay.Language.available;

		setAvailableLanguageIds(
			Object.keys(availableLanguageIds).map((languageId) => ({
				defaultLanguageId: languageId === DEFAULT_LANGUAGE_ID,
				languageId,
				...(translatedLanguages && {
					translated: !!translatedLanguages[languageId],
				}),
			}))
		);
	}, [translatedLanguages]);

	return (
		<ClayDropDown
			active={active}
			className="localizable-dropdown"
			onActiveChange={setActive}
			trigger={
				<ClayButton
					displayType="secondary"
					small
					symbol={formatLabel(editingLocalizableId)}
				>
					<span className="inline-item">
						<ClayIcon symbol={formatIcon(editingLocalizableId)} />
					</span>
					<span className="btn-section">
						{formatLabel(editingLocalizableId)}
					</span>
				</ClayButton>
			}
		>
			{!!availableLanguageIds.length && (
				<ClayDropDown.ItemList className="localizable-dropdown-ul">
					{availableLanguageIds.map(
						(
							{defaultLanguageId, languageId, translated},
							index
						) => (
							<ClayDropDown.Item
								className="autofit-row"
								key={index}
								onClick={() => {
									setEditingLocalizableId(languageId);
									setActive(false);
								}}
							>
								<span className="autofit-col autofit-col-expand">
									<span className="autofit-section">
										<span className="inline-item inline-item-before">
											<ClayIcon
												symbol={formatIcon(languageId)}
											/>
										</span>

										{formatLabel(languageId)}
									</span>
								</span>
								{translatedLanguages && (
									<LocalizableDropdownLabel
										defaultLanguageId={defaultLanguageId}
										translated={translated}
									/>
								)}
							</ClayDropDown.Item>
						)
					)}
				</ClayDropDown.ItemList>
			)}
		</ClayDropDown>
	);
};
