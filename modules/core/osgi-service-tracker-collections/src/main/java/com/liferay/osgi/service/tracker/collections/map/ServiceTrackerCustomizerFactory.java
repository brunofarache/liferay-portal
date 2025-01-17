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

package com.liferay.osgi.service.tracker.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Carlos Sierra Andrés
 */
public class ServiceTrackerCustomizerFactory {

	public static <S, T> Function<BundleContext, ServiceTrackerCustomizer<S, T>>
		createFromFunction(BiFunction<ServiceReference<S>, S, T> function) {

		return b -> new ServiceTrackerCustomizer<S, T>() {

			public T addingService(ServiceReference<S> serviceReference) {
				S service = b.getService(serviceReference);

				try {
					return function.apply(serviceReference, service);
				}
				catch (Exception exception) {
					b.ungetService(serviceReference);

					throw exception;
				}
			}

			public void modifiedService(
				ServiceReference<S> serviceReference, T t) {

				removedService(serviceReference, t);

				addingService(serviceReference);
			}

			public void removedService(
				ServiceReference<S> serviceReference, T t) {

				b.ungetService(serviceReference);
			}

		};
	}

	public static <S> ServiceTrackerCustomizer<S, ServiceWrapper<S>>
		serviceWrapper(final BundleContext bundleContext) {

		return new ServiceTrackerCustomizer<S, ServiceWrapper<S>>() {

			@Override
			public ServiceWrapper<S> addingService(
				final ServiceReference<S> serviceReference) {

				final S service = bundleContext.getService(serviceReference);

				if (service == null) {
					return null;
				}

				try {
					final Map<String, Object> properties = _getProperties(
						serviceReference);

					return new ServiceWrapper<S>() {

						@Override
						public Map<String, Object> getProperties() {
							return properties;
						}

						@Override
						public S getService() {
							return service;
						}

					};
				}
				catch (Throwable throwable) {
					bundleContext.ungetService(serviceReference);

					throw throwable;
				}
			}

			@Override
			public void modifiedService(
				ServiceReference<S> serviceReference,
				ServiceWrapper<S> serviceWrapper) {
			}

			@Override
			public void removedService(
				ServiceReference<S> serviceReference,
				ServiceWrapper<S> serviceWrapper) {

				bundleContext.ungetService(serviceReference);
			}

		};
	}

	public interface ServiceWrapper<S> {

		public Map<String, Object> getProperties();

		public S getService();

	}

	private static <S> Map<String, Object> _getProperties(
		final ServiceReference<S> serviceReference) {

		Map<String, Object> properties = new HashMap<>();

		String[] propertyKeys = serviceReference.getPropertyKeys();

		for (String propertyKey : propertyKeys) {
			properties.put(
				propertyKey, serviceReference.getProperty(propertyKey));
		}

		return properties;
	}

}