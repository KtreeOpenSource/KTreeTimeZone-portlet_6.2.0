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
 * Provides a wrapper for {@link WorldClockLocalService}.
 *
 * @author vikash@KTree
 * @see WorldClockLocalService
 * @generated
 */
public class WorldClockLocalServiceWrapper implements WorldClockLocalService,
	ServiceWrapper<WorldClockLocalService> {
	public WorldClockLocalServiceWrapper(
		WorldClockLocalService worldClockLocalService) {
		_worldClockLocalService = worldClockLocalService;
	}

	/**
	* Adds the world clock to the database. Also notifies the appropriate model listeners.
	*
	* @param worldClock the world clock
	* @return the world clock that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.ktree.timezone.model.dao.model.WorldClock addWorldClock(
		com.ktree.timezone.model.dao.model.WorldClock worldClock)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.addWorldClock(worldClock);
	}

	/**
	* Creates a new world clock with the primary key. Does not add the world clock to the database.
	*
	* @param id the primary key for the new world clock
	* @return the new world clock
	*/
	@Override
	public com.ktree.timezone.model.dao.model.WorldClock createWorldClock(
		int id) {
		return _worldClockLocalService.createWorldClock(id);
	}

	/**
	* Deletes the world clock with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the world clock
	* @return the world clock that was removed
	* @throws PortalException if a world clock with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.ktree.timezone.model.dao.model.WorldClock deleteWorldClock(
		int id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.deleteWorldClock(id);
	}

	/**
	* Deletes the world clock from the database. Also notifies the appropriate model listeners.
	*
	* @param worldClock the world clock
	* @return the world clock that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.ktree.timezone.model.dao.model.WorldClock deleteWorldClock(
		com.ktree.timezone.model.dao.model.WorldClock worldClock)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.deleteWorldClock(worldClock);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _worldClockLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ktree.timezone.model.dao.model.impl.WorldClockModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ktree.timezone.model.dao.model.impl.WorldClockModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.ktree.timezone.model.dao.model.WorldClock fetchWorldClock(int id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.fetchWorldClock(id);
	}

	/**
	* Returns the world clock with the primary key.
	*
	* @param id the primary key of the world clock
	* @return the world clock
	* @throws PortalException if a world clock with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.ktree.timezone.model.dao.model.WorldClock getWorldClock(int id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.getWorldClock(id);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the world clocks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ktree.timezone.model.dao.model.impl.WorldClockModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of world clocks
	* @param end the upper bound of the range of world clocks (not inclusive)
	* @return the range of world clocks
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.ktree.timezone.model.dao.model.WorldClock> getWorldClocks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.getWorldClocks(start, end);
	}

	/**
	* Returns the number of world clocks.
	*
	* @return the number of world clocks
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getWorldClocksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.getWorldClocksCount();
	}

	/**
	* Updates the world clock in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param worldClock the world clock
	* @return the world clock that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.ktree.timezone.model.dao.model.WorldClock updateWorldClock(
		com.ktree.timezone.model.dao.model.WorldClock worldClock)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _worldClockLocalService.updateWorldClock(worldClock);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _worldClockLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_worldClockLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _worldClockLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WorldClockLocalService getWrappedWorldClockLocalService() {
		return _worldClockLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWorldClockLocalService(
		WorldClockLocalService worldClockLocalService) {
		_worldClockLocalService = worldClockLocalService;
	}

	@Override
	public WorldClockLocalService getWrappedService() {
		return _worldClockLocalService;
	}

	@Override
	public void setWrappedService(WorldClockLocalService worldClockLocalService) {
		_worldClockLocalService = worldClockLocalService;
	}

	private WorldClockLocalService _worldClockLocalService;
}