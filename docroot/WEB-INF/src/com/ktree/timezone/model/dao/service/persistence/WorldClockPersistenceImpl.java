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

package com.ktree.timezone.model.dao.service.persistence;

import com.ktree.timezone.model.dao.NoSuchWorldClockException;
import com.ktree.timezone.model.dao.model.WorldClock;
import com.ktree.timezone.model.dao.model.impl.WorldClockImpl;
import com.ktree.timezone.model.dao.model.impl.WorldClockModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the world clock service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author vikash@KTree
 * @see WorldClockPersistence
 * @see WorldClockUtil
 * @generated
 */
public class WorldClockPersistenceImpl extends BasePersistenceImpl<WorldClock>
	implements WorldClockPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WorldClockUtil} to access the world clock persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WorldClockImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
			WorldClockModelImpl.FINDER_CACHE_ENABLED, WorldClockImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
			WorldClockModelImpl.FINDER_CACHE_ENABLED, WorldClockImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
			WorldClockModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public WorldClockPersistenceImpl() {
		setModelClass(WorldClock.class);
	}

	/**
	 * Caches the world clock in the entity cache if it is enabled.
	 *
	 * @param worldClock the world clock
	 */
	@Override
	public void cacheResult(WorldClock worldClock) {
		EntityCacheUtil.putResult(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
			WorldClockImpl.class, worldClock.getPrimaryKey(), worldClock);

		worldClock.resetOriginalValues();
	}

	/**
	 * Caches the world clocks in the entity cache if it is enabled.
	 *
	 * @param worldClocks the world clocks
	 */
	@Override
	public void cacheResult(List<WorldClock> worldClocks) {
		for (WorldClock worldClock : worldClocks) {
			if (EntityCacheUtil.getResult(
						WorldClockModelImpl.ENTITY_CACHE_ENABLED,
						WorldClockImpl.class, worldClock.getPrimaryKey()) == null) {
				cacheResult(worldClock);
			}
			else {
				worldClock.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all world clocks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WorldClockImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WorldClockImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the world clock.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WorldClock worldClock) {
		EntityCacheUtil.removeResult(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
			WorldClockImpl.class, worldClock.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WorldClock> worldClocks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WorldClock worldClock : worldClocks) {
			EntityCacheUtil.removeResult(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
				WorldClockImpl.class, worldClock.getPrimaryKey());
		}
	}

	/**
	 * Creates a new world clock with the primary key. Does not add the world clock to the database.
	 *
	 * @param id the primary key for the new world clock
	 * @return the new world clock
	 */
	@Override
	public WorldClock create(int id) {
		WorldClock worldClock = new WorldClockImpl();

		worldClock.setNew(true);
		worldClock.setPrimaryKey(id);

		return worldClock;
	}

	/**
	 * Removes the world clock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the world clock
	 * @return the world clock that was removed
	 * @throws com.ktree.timezone.model.dao.NoSuchWorldClockException if a world clock with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorldClock remove(int id)
		throws NoSuchWorldClockException, SystemException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the world clock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the world clock
	 * @return the world clock that was removed
	 * @throws com.ktree.timezone.model.dao.NoSuchWorldClockException if a world clock with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorldClock remove(Serializable primaryKey)
		throws NoSuchWorldClockException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorldClock worldClock = (WorldClock)session.get(WorldClockImpl.class,
					primaryKey);

			if (worldClock == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWorldClockException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(worldClock);
		}
		catch (NoSuchWorldClockException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected WorldClock removeImpl(WorldClock worldClock)
		throws SystemException {
		worldClock = toUnwrappedModel(worldClock);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(worldClock)) {
				worldClock = (WorldClock)session.get(WorldClockImpl.class,
						worldClock.getPrimaryKeyObj());
			}

			if (worldClock != null) {
				session.delete(worldClock);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (worldClock != null) {
			clearCache(worldClock);
		}

		return worldClock;
	}

	@Override
	public WorldClock updateImpl(
		com.ktree.timezone.model.dao.model.WorldClock worldClock)
		throws SystemException {
		worldClock = toUnwrappedModel(worldClock);

		boolean isNew = worldClock.isNew();

		Session session = null;

		try {
			session = openSession();

			if (worldClock.isNew()) {
				session.save(worldClock);

				worldClock.setNew(false);
			}
			else {
				session.merge(worldClock);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
			WorldClockImpl.class, worldClock.getPrimaryKey(), worldClock);

		return worldClock;
	}

	protected WorldClock toUnwrappedModel(WorldClock worldClock) {
		if (worldClock instanceof WorldClockImpl) {
			return worldClock;
		}

		WorldClockImpl worldClockImpl = new WorldClockImpl();

		worldClockImpl.setNew(worldClock.isNew());
		worldClockImpl.setPrimaryKey(worldClock.getPrimaryKey());

		worldClockImpl.setId(worldClock.getId());
		worldClockImpl.setPlace(worldClock.getPlace());
		worldClockImpl.setTimezone(worldClock.getTimezone());
		worldClockImpl.setUserid(worldClock.getUserid());

		return worldClockImpl;
	}

	/**
	 * Returns the world clock with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the world clock
	 * @return the world clock
	 * @throws com.ktree.timezone.model.dao.NoSuchWorldClockException if a world clock with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorldClock findByPrimaryKey(Serializable primaryKey)
		throws NoSuchWorldClockException, SystemException {
		WorldClock worldClock = fetchByPrimaryKey(primaryKey);

		if (worldClock == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchWorldClockException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return worldClock;
	}

	/**
	 * Returns the world clock with the primary key or throws a {@link com.ktree.timezone.model.dao.NoSuchWorldClockException} if it could not be found.
	 *
	 * @param id the primary key of the world clock
	 * @return the world clock
	 * @throws com.ktree.timezone.model.dao.NoSuchWorldClockException if a world clock with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorldClock findByPrimaryKey(int id)
		throws NoSuchWorldClockException, SystemException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the world clock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the world clock
	 * @return the world clock, or <code>null</code> if a world clock with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorldClock fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		WorldClock worldClock = (WorldClock)EntityCacheUtil.getResult(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
				WorldClockImpl.class, primaryKey);

		if (worldClock == _nullWorldClock) {
			return null;
		}

		if (worldClock == null) {
			Session session = null;

			try {
				session = openSession();

				worldClock = (WorldClock)session.get(WorldClockImpl.class,
						primaryKey);

				if (worldClock != null) {
					cacheResult(worldClock);
				}
				else {
					EntityCacheUtil.putResult(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
						WorldClockImpl.class, primaryKey, _nullWorldClock);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WorldClockModelImpl.ENTITY_CACHE_ENABLED,
					WorldClockImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return worldClock;
	}

	/**
	 * Returns the world clock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the world clock
	 * @return the world clock, or <code>null</code> if a world clock with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WorldClock fetchByPrimaryKey(int id) throws SystemException {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the world clocks.
	 *
	 * @return the world clocks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorldClock> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<WorldClock> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the world clocks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ktree.timezone.model.dao.model.impl.WorldClockModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of world clocks
	 * @param end the upper bound of the range of world clocks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of world clocks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WorldClock> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<WorldClock> list = (List<WorldClock>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WORLDCLOCK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WORLDCLOCK;

				if (pagination) {
					sql = sql.concat(WorldClockModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WorldClock>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WorldClock>(list);
				}
				else {
					list = (List<WorldClock>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the world clocks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (WorldClock worldClock : findAll()) {
			remove(worldClock);
		}
	}

	/**
	 * Returns the number of world clocks.
	 *
	 * @return the number of world clocks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WORLDCLOCK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the world clock persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.ktree.timezone.model.dao.model.WorldClock")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorldClock>> listenersList = new ArrayList<ModelListener<WorldClock>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorldClock>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(WorldClockImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_WORLDCLOCK = "SELECT worldClock FROM WorldClock worldClock";
	private static final String _SQL_COUNT_WORLDCLOCK = "SELECT COUNT(worldClock) FROM WorldClock worldClock";
	private static final String _ORDER_BY_ENTITY_ALIAS = "worldClock.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WorldClock exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WorldClockPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id"
			});
	private static WorldClock _nullWorldClock = new WorldClockImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WorldClock> toCacheModel() {
				return _nullWorldClockCacheModel;
			}
		};

	private static CacheModel<WorldClock> _nullWorldClockCacheModel = new CacheModel<WorldClock>() {
			@Override
			public WorldClock toEntityModel() {
				return _nullWorldClock;
			}
		};
}