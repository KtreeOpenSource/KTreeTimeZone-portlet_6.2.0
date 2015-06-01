/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.ktree.timezone.model.dao.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WorldClockService}.
 *
 * @author vikash@KTree
 * @see WorldClockService
 * @generated
 */
public class WorldClockServiceWrapper implements WorldClockService,
	ServiceWrapper<WorldClockService> {
	public WorldClockServiceWrapper(WorldClockService worldClockService) {
		_worldClockService = worldClockService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _worldClockService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_worldClockService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _worldClockService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WorldClockService getWrappedWorldClockService() {
		return _worldClockService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWorldClockService(WorldClockService worldClockService) {
		_worldClockService = worldClockService;
	}

	@Override
	public WorldClockService getWrappedService() {
		return _worldClockService;
	}

	@Override
	public void setWrappedService(WorldClockService worldClockService) {
		_worldClockService = worldClockService;
	}

	private WorldClockService _worldClockService;
}