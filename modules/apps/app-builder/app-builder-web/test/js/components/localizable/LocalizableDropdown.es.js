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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import LocalizableDropdown, {
	LocalizableDropdownLabel,
	formatIcon,
	formatLabel,
} from '../../../../src/main/resources/META-INF/resources/js/components/localizable/LocalizableDropdown.es';

describe('LocalizableDropdown', () => {
	afterEach(() => {
		cleanup();
	});

	it('renders LocalizableDropdown with defaultLanguageId without pass props', () => {
		render(<LocalizableDropdown />);

		const defaultLanguageId = 'en-US';

		expect(document.querySelector('.localizable-dropdown')).toBeTruthy();
		expect(document.querySelector('.localizable-dropdown-ul')).toBeTruthy();

		const button = document.querySelector('button.dropdown-toggle');

		expect(button).toBeTruthy();
		expect(button.getAttribute('symbol')).toEqual(defaultLanguageId);
		expect(button.querySelector('.btn-section').textContent).toEqual(
			defaultLanguageId
		);
		expect(button.querySelector('svg').classList).toContain(
			'lexicon-icon-en-us'
		);
	});

	it('renders LocalizableDropdown passing translatedLanguages', () => {
		render(
			<LocalizableDropdown
				translatedLanguages={{
					ar_SA: 'لقبي',
					de_DE: 'mein titel',
					en_US: 'my title',
					es_ES: 'mi titulo',
					fr_FR: 'mon titre',
					pt_BR: 'meu titulo',
				}}
			/>
		);

		const list = document.querySelectorAll('.localizable-dropdown-ul li');

		const labels = [...list].map((li) => ({
			label: li.querySelector('.label-item').textContent,
			languageId: li
				.querySelector('.autofit-section')
				.textContent.replace(/\s/g, ''),
		}));

		expect(list.length).toBe(13);
		expect(labels).toEqual([
			{label: 'Translated', languageId: 'ar-SA'},
			{label: 'Not Translated', languageId: 'ca-ES'},
			{label: 'Translated', languageId: 'de-DE'},
			{label: 'Default', languageId: 'en-US'},
			{label: 'Translated', languageId: 'es-ES'},
			{label: 'Not Translated', languageId: 'fi-FI'},
			{label: 'Translated', languageId: 'fr-FR'},
			{label: 'Not Translated', languageId: 'hu-HU'},
			{label: 'Not Translated', languageId: 'ja-JP'},
			{label: 'Not Translated', languageId: 'nl-NL'},
			{label: 'Translated', languageId: 'pt-BR'},
			{label: 'Not Translated', languageId: 'sv-SE'},
			{label: 'Not Translated', languageId: 'zh-CN'},
		]);
	});

	it('change language when click in a dropdown label', () => {
		const onChangeCallback = jest.fn();

		render(
			<LocalizableDropdown
				onChangeLanguageId={(id) => onChangeCallback(id)}
			/>
		);

		const list = document.querySelectorAll('.localizable-dropdown-ul li');

		const button = [...list]
			.find(
				(li) =>
					li
						.querySelector('.autofit-section')
						.textContent.replace(/\s/g, '') === 'pt-BR'
			)
			.querySelector('button');

		fireEvent.click(button);

		expect(onChangeCallback.mock.calls.length).toBe(2);
		expect(onChangeCallback.mock.calls[0][0]).toEqual('en_US');
		expect(onChangeCallback.mock.calls[1][0]).toEqual('pt_BR');
	});

	it('renders dropdown menu when clicked', () => {
		render(<LocalizableDropdown />);

		const button = document.querySelector('.dropdown-toggle');
		fireEvent.click(button);

		expect(document.body.querySelector('.dropdown-menu')).toBeTruthy();
		expect(document.body).toMatchSnapshot();
	});
});

describe('LocalizableDropdownLabel', () => {
	it('renders LocalizableDropdownLabel with No Translated label', () => {
		const {getByText} = render(<LocalizableDropdownLabel />);

		expect(document.querySelector('.label.label-warning')).toBeTruthy();
		expect(getByText('Not Translated')).toBeTruthy();
	});

	it('renders LocalizableDropdownLabel with Translated label', () => {
		const {getByText} = render(<LocalizableDropdownLabel translated />);

		expect(document.querySelector('.label.label-success')).toBeTruthy();
		expect(getByText('Translated')).toBeTruthy();
	});

	it('renders LocalizableDropdownLabel with Default label', () => {
		const {getByText} = render(
			<LocalizableDropdownLabel defaultLanguageId translated />
		);

		expect(document.querySelector('.label.label-info')).toBeTruthy();
		expect(getByText('Default')).toBeTruthy();
	});
});

describe('LocalizableDropdown Util', () => {
	it('format string for label', () => {
		expect(formatLabel('en_US')).toEqual('en-US');
		expect(formatLabel('pt_BR')).toEqual('pt-BR');
	});

	it('format string for icon', () => {
		expect(formatIcon('en_US')).toEqual('en-us');
		expect(formatIcon('pt_BR')).toEqual('pt-br');
	});
});
