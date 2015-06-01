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

package com.ktree.timezone.model.dao.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WorldClock}.
 * </p>
 *
 * @author vikash@KTree
 * @see WorldClock
 * @generated
 */
public class WorldClockWrapper implements WorldClock, ModelWrapper<WorldClock> {
	public WorldClockWrapper(WorldClock worldClock) {
		_worldClock = worldClock;
	}

	@Override
	public Class<?> getModelClass() {
		return WorldClock.class;
	}

	@Override
	public String getModelClassName() {
		return WorldClock.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("place", getPlace());
		attributes.put("timezone", getTimezone());
		attributes.put("userid", getUserid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer id = (Integer)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String place = (String)attributes.get("place");

		if (place != null) {
			setPlace(place);
		}

		Date timezone = (Date)attributes.get("timezone");

		if (timezone != null) {
			setTimezone(timezone);
		}

		Long userid = (Long)attributes.get("userid");

		if (userid != null) {
			setUserid(userid);
		}
	}

	/**
	* Returns the primary key of this world clock.
	*
	* @return the primary key of this world clock
	*/
	@Override
	public int getPrimaryKey() {
		return _worldClock.getPrimaryKey();
	}

	/**
	* Sets the primary key of this world clock.
	*
	* @param primaryKey the primary key of this world clock
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_worldClock.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this world clock.
	*
	* @return the ID of this world clock
	*/
	@Override
	public int getId() {
		return _worldClock.getId();
	}

	/**
	* Sets the ID of this world clock.
	*
	* @param id the ID of this world clock
	*/
	@Override
	public void setId(int id) {
		_worldClock.setId(id);
	}

	/**
	* Returns the place of this world clock.
	*
	* @return the place of this world clock
	*/
	@Override
	public java.lang.String getPlace() {
		return _worldClock.getPlace();
	}

	/**
	* Sets the place of this world clock.
	*
	* @param place the place of this world clock
	*/
	@Override
	public void setPlace(java.lang.String place) {
		_worldClock.setPlace(place);
	}

	/**
	* Returns the timezone of this world clock.
	*
	* @return the timezone of this world clock
	*/
	@Override
	public java.util.Date getTimezone() {
		return _worldClock.getTimezone();
	}

	/**
	* Sets the timezone of this world clock.
	*
	* @param timezone the timezone of this world clock
	*/
	@Override
	public void setTimezone(java.util.Date timezone) {
		_worldClock.setTimezone(timezone);
	}

	/**
	* Returns the userid of this world clock.
	*
	* @return the userid of this world clock
	*/
	@Override
	public long getUserid() {
		return _worldClock.getUserid();
	}

	/**
	* Sets the userid of this world clock.
	*
	* @param userid the userid of this world clock
	*/
	@Override
	public void setUserid(long userid) {
		_worldClock.setUserid(userid);
	}

	@Override
	public boolean isNew() {
		return _worldClock.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_worldClock.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _worldClock.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_worldClock.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _worldClock.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _worldClock.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_worldClock.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _worldClock.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_worldClock.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_worldClock.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_worldClock.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WorldClockWrapper((WorldClock)_worldClock.clone());
	}

	@Override
	public int compareTo(
		com.ktree.timezone.model.dao.model.WorldClock worldClock) {
		return _worldClock.compareTo(worldClock);
	}

	@Override
	public int hashCode() {
		return _worldClock.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.ktree.timezone.model.dao.model.WorldClock> toCacheModel() {
		return _worldClock.toCacheModel();
	}

	@Override
	public com.ktree.timezone.model.dao.model.WorldClock toEscapedModel() {
		return new WorldClockWrapper(_worldClock.toEscapedModel());
	}

	@Override
	public com.ktree.timezone.model.dao.model.WorldClock toUnescapedModel() {
		return new WorldClockWrapper(_worldClock.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _worldClock.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _worldClock.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_worldClock.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorldClockWrapper)) {
			return false;
		}

		WorldClockWrapper worldClockWrapper = (WorldClockWrapper)obj;

		if (Validator.equals(_worldClock, worldClockWrapper._worldClock)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public WorldClock getWrappedWorldClock() {
		return _worldClock;
	}

	@Override
	public WorldClock getWrappedModel() {
		return _worldClock;
	}

	@Override
	public void resetOriginalValues() {
		_worldClock.resetOriginalValues();
	}

	private WorldClock _worldClock;
}