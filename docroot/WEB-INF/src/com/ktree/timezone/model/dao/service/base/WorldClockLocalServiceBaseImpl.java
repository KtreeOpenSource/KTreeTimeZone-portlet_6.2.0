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

package com.ktree.timezone.model.dao.service.base;

import com.ktree.timezone.model.dao.model.WorldClock;
import com.ktree.timezone.model.dao.service.WorldClockLocalService;
import com.ktree.timezone.model.dao.service.persistence.WorldClockPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the world clock local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ktree.timezone.model.dao.service.impl.WorldClockLocalServiceImpl}.
 * </p>
 *
 * @author vikash@KTree
 * @see com.ktree.timezone.model.dao.service.impl.WorldClockLocalServiceImpl
 * @see com.ktree.timezone.model.dao.service.WorldClockLocalServiceUtil
 * @generated
 */
public abstract class WorldClockLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements WorldClockLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.ktree.timezone.model.dao.service.WorldClockLocalServiceUtil} to access the world clock local service.
	 */

	/**
	 * Adds the world clock to the database. Also notifies the appropriate model listeners.
	 *
	 * @param worldClock the world clock
	 * @return the world clock that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorldClock addWorldClock(WorldClock worldClock)
		throws SystemException {
		worldClock.setNew(true);

		return worldClockPersistence.update(worldClock);
	}

	/**
	 * Creates a new world clock with the primary key. Does not add the world clock to the database.
	 *
	 * @param id the primary key for the new world clock
	 * @return the new world clock
	 */
	@Override
	public WorldClock createWorldClock(int id) {
		return worldClockPersistence.create(id);
	}

	/**
	 * Deletes the world clock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the world clock
	 * @return the world clock that was removed
	 * @throws PortalException if a world clock with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorldClock deleteWorldClock(int id)
		throws PortalException, SystemException {
		return worldClockPersistence.remove(id);
	}

	/**
	 * Deletes the world clock from the database. Also notifies the appropriate model listeners.
	 *
	 * @param worldClock the world clock
	 * @return the world clock that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorldClock deleteWorldClock(WorldClock worldClock)
		throws SystemException {
		return worldClockPersistence.remove(worldClock);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(WorldClock.class,
			clazz.getClassLoader());
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
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return worldClockPersistence.findWithDynamicQuery(dynamicQuery);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return worldClockPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
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
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return worldClockPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return worldClockPersistence.countWithDynamicQuery(dynamicQuery);
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
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return worldClockPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public WorldClock fetchWorldClock(int id) throws SystemException {
		return worldClockPersistence.fetchByPrimaryKey(id);
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
	public WorldClock getWorldClock(int id)
		throws PortalException, SystemException {
		return worldClockPersistence.findByPrimaryKey(id);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return worldClockPersistence.findByPrimaryKey(primaryKeyObj);
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
	public List<WorldClock> getWorldClocks(int start, int end)
		throws SystemException {
		return worldClockPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of world clocks.
	 *
	 * @return the number of world clocks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getWorldClocksCount() throws SystemException {
		return worldClockPersistence.countAll();
	}

	/**
	 * Updates the world clock in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param worldClock the world clock
	 * @return the world clock that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorldClock updateWorldClock(WorldClock worldClock)
		throws SystemException {
		return worldClockPersistence.update(worldClock);
	}

	/**
	 * Returns the world clock local service.
	 *
	 * @return the world clock local service
	 */
	public com.ktree.timezone.model.dao.service.WorldClockLocalService getWorldClockLocalService() {
		return worldClockLocalService;
	}

	/**
	 * Sets the world clock local service.
	 *
	 * @param worldClockLocalService the world clock local service
	 */
	public void setWorldClockLocalService(
		com.ktree.timezone.model.dao.service.WorldClockLocalService worldClockLocalService) {
		this.worldClockLocalService = worldClockLocalService;
	}

	/**
	 * Returns the world clock remote service.
	 *
	 * @return the world clock remote service
	 */
	public com.ktree.timezone.model.dao.service.WorldClockService getWorldClockService() {
		return worldClockService;
	}

	/**
	 * Sets the world clock remote service.
	 *
	 * @param worldClockService the world clock remote service
	 */
	public void setWorldClockService(
		com.ktree.timezone.model.dao.service.WorldClockService worldClockService) {
		this.worldClockService = worldClockService;
	}

	/**
	 * Returns the world clock persistence.
	 *
	 * @return the world clock persistence
	 */
	public WorldClockPersistence getWorldClockPersistence() {
		return worldClockPersistence;
	}

	/**
	 * Sets the world clock persistence.
	 *
	 * @param worldClockPersistence the world clock persistence
	 */
	public void setWorldClockPersistence(
		WorldClockPersistence worldClockPersistence) {
		this.worldClockPersistence = worldClockPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.ktree.timezone.model.dao.model.WorldClock",
			worldClockLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.ktree.timezone.model.dao.model.WorldClock");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return WorldClock.class;
	}

	protected String getModelClassName() {
		return WorldClock.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = worldClockPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.ktree.timezone.model.dao.service.WorldClockLocalService.class)
	protected com.ktree.timezone.model.dao.service.WorldClockLocalService worldClockLocalService;
	@BeanReference(type = com.ktree.timezone.model.dao.service.WorldClockService.class)
	protected com.ktree.timezone.model.dao.service.WorldClockService worldClockService;
	@BeanReference(type = WorldClockPersistence.class)
	protected WorldClockPersistence worldClockPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private WorldClockLocalServiceClpInvoker _clpInvoker = new WorldClockLocalServiceClpInvoker();
}