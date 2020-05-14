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

import ClayMultiSelect from 'clay-multi-select';
import Component from 'metal-jsx';
import {Config} from 'metal-state';

class Email extends Component {
	init() {
		this.setState({
			emailContent: this._emailContentValueFn(),
		});
	}

	isEmailAddressValid(email) {
		const emailRegex = /.+@.+\..+/i;

		return emailRegex.test(email);
	}

	render() {
		const {emailAddresses} = this.props;
		const {emailContent} = this.state;

		return (
			<div class="share-form-modal-item-email">
				{emailAddresses && (
					<ClayMultiSelect
						autocompleteFilterCondition="label"
						dataSource={emailAddresses}
						events={{
							inputChange: this._handleInputChange.bind(this),
							labelItemAdded: this._handleLabelItemAdded.bind(
								this
							),
							labelItemRemoved: this._handleLabelItemRemoved.bind(
								this
							),
						}}
						helpText={Liferay.Language.get(
							'you-can-use-a-comma-to-enter-multiple-emails'
						)}
						label={Liferay.Language.get('to')}
						placeholder={Liferay.Language.get(
							'enter-email-addresses'
						)}
						ref={'multiSelectRef'}
						selectedItems={emailContent.addresses}
						showSelectButton={false}
						spritemap={this.props.spritemap}
					/>
				)}
				<div class="form-group">
					<label for="subject">
						{Liferay.Language.get('subject')}
					</label>
					<div class="input-group">
						<div class="input-group-item">
							<input
								class="form-control"
								data-oninput={this._handleSubjectChanged.bind(
									this
								)}
								id="subject"
								type="text"
								value={emailContent.subject}
							/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="message">
						{Liferay.Language.get('message')}
					</label>
					<div class="input-group">
						<div class="input-group-item">
							<textarea
								class="form-control"
								data-oninput={this._handleMessageChanged.bind(
									this
								)}
								id="message"
								type="text"
							>
								{emailContent.message}
							</textarea>
						</div>
					</div>
				</div>
			</div>
		);
	}

	_emailContentValueFn() {
		return {
			addresses: [],
			message: Liferay.Language.get(
				'could-you-take-a-moment-to-fill-in-this-form'
			),
			subject: this.props.localizedName[themeDisplay.getLanguageId()],
		};
	}

	_handleInputChange(event) {
		const {value} = event.data;

		if (this.isEmailAddressValid(value)) {
			this.refs.multiSelectRef.creatable = true;
		}
		else {
			this.refs.multiSelectRef.creatable = false;
		}
	}

	_handleLabelItemAdded(event) {
		const {selectedItems} = event.data;
		const {emailContent} = this.state;

		this.setState({
			emailContent: {
				...emailContent,
				addresses: selectedItems,
			},
		});
	}

	_handleLabelItemRemoved(event) {
		const {selectedItems} = event.data;
		const {emailContent} = this.state;

		this.setState({
			emailContent: {
				...emailContent,
				addresses: selectedItems,
			},
		});
	}

	_handleMessageChanged(event) {
		const {value} = event.target;
		const {emailContent} = this.state;

		this.setState({
			emailContent: {
				...emailContent,
				message: value,
			},
		});
	}

	_handleSubjectChanged(event) {
		const {value} = event.target;
		const {emailContent} = this.state;

		this.setState({
			emailContent: {
				...emailContent,
				subject: value,
			},
		});
	}
}

Email.PROPS = {

	/**
	 * @default undefined
	 * @instance
	 * @memberof Email
	 * @type {!array}
	 */
	emailAddresses: Config.array(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Email
	 * @type {!object}
	 */
	localizedName: Config.object(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Email
	 * @type {!spritemap}
	 */
	spritemap: Config.string().required(),
};

Email.STATE = {

	/**
	 * @default undefined
	 * @instance
	 * @memberof Email
	 * @type {!array}
	 */
	emailContent: Config.object().valueFn('_emailContentValueFn'),
};

export default Email;
