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

import {openToast} from 'frontend-js-web';
import React, {createContext} from 'react';

const ToastContext = createContext();

const ToastContextProvider = ({children}) => {
    const addToast = toast => {
        if (toast.displayType) {
            toast.type = toast.displayType;
            delete toast.displayType;
        }
        openToast(toast);
    };

    const successToast = ({message, title} = {}) => {
        addToast({
            message:
                message ||
                Liferay.Language.get('your-request-completed-successfully'),
            title: title || `${Liferay.Language.get('success')}:`,
            type: 'success',
        });
    };

    const errorToast = ({message, title} = {}) => {
        addToast({
            message,
            title: title || `${Liferay.Language.get('error')}:`,
            type: 'danger',
        });
    };

    return (
        <ToastContext.Provider value={{addToast, errorToast, successToast}}>
            {children}
        </ToastContext.Provider>
    );
};

export {ToastContext, ToastContextProvider};

