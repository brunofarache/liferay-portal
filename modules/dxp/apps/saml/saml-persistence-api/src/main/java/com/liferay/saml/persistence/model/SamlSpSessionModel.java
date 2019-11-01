/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.saml.persistence.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SamlSpSession service. Represents a row in the &quot;SamlSpSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.saml.persistence.model.impl.SamlSpSessionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.saml.persistence.model.impl.SamlSpSessionImpl</code>.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpSession
 * @generated
 */
@ProviderType
public interface SamlSpSessionModel
	extends AuditedModel, BaseModel<SamlSpSession>, ShardedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a saml sp session model instance should use the {@link SamlSpSession} interface instead.
	 */

	/**
	 * Returns the primary key of this saml sp session.
	 *
	 * @return the primary key of this saml sp session
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this saml sp session.
	 *
	 * @param primaryKey the primary key of this saml sp session
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the saml sp session ID of this saml sp session.
	 *
	 * @return the saml sp session ID of this saml sp session
	 */
	public long getSamlSpSessionId();

	/**
	 * Sets the saml sp session ID of this saml sp session.
	 *
	 * @param samlSpSessionId the saml sp session ID of this saml sp session
	 */
	public void setSamlSpSessionId(long samlSpSessionId);

	/**
	 * Returns the company ID of this saml sp session.
	 *
	 * @return the company ID of this saml sp session
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this saml sp session.
	 *
	 * @param companyId the company ID of this saml sp session
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this saml sp session.
	 *
	 * @return the user ID of this saml sp session
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this saml sp session.
	 *
	 * @param userId the user ID of this saml sp session
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this saml sp session.
	 *
	 * @return the user uuid of this saml sp session
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this saml sp session.
	 *
	 * @param userUuid the user uuid of this saml sp session
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this saml sp session.
	 *
	 * @return the user name of this saml sp session
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this saml sp session.
	 *
	 * @param userName the user name of this saml sp session
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this saml sp session.
	 *
	 * @return the create date of this saml sp session
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this saml sp session.
	 *
	 * @param createDate the create date of this saml sp session
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this saml sp session.
	 *
	 * @return the modified date of this saml sp session
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this saml sp session.
	 *
	 * @param modifiedDate the modified date of this saml sp session
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the saml idp entity ID of this saml sp session.
	 *
	 * @return the saml idp entity ID of this saml sp session
	 */
	@AutoEscape
	public String getSamlIdpEntityId();

	/**
	 * Sets the saml idp entity ID of this saml sp session.
	 *
	 * @param samlIdpEntityId the saml idp entity ID of this saml sp session
	 */
	public void setSamlIdpEntityId(String samlIdpEntityId);

	/**
	 * Returns the saml sp session key of this saml sp session.
	 *
	 * @return the saml sp session key of this saml sp session
	 */
	@AutoEscape
	public String getSamlSpSessionKey();

	/**
	 * Sets the saml sp session key of this saml sp session.
	 *
	 * @param samlSpSessionKey the saml sp session key of this saml sp session
	 */
	public void setSamlSpSessionKey(String samlSpSessionKey);

	/**
	 * Returns the assertion xml of this saml sp session.
	 *
	 * @return the assertion xml of this saml sp session
	 */
	@AutoEscape
	public String getAssertionXml();

	/**
	 * Sets the assertion xml of this saml sp session.
	 *
	 * @param assertionXml the assertion xml of this saml sp session
	 */
	public void setAssertionXml(String assertionXml);

	/**
	 * Returns the j session ID of this saml sp session.
	 *
	 * @return the j session ID of this saml sp session
	 */
	@AutoEscape
	public String getJSessionId();

	/**
	 * Sets the j session ID of this saml sp session.
	 *
	 * @param jSessionId the j session ID of this saml sp session
	 */
	public void setJSessionId(String jSessionId);

	/**
	 * Returns the name ID format of this saml sp session.
	 *
	 * @return the name ID format of this saml sp session
	 */
	@AutoEscape
	public String getNameIdFormat();

	/**
	 * Sets the name ID format of this saml sp session.
	 *
	 * @param nameIdFormat the name ID format of this saml sp session
	 */
	public void setNameIdFormat(String nameIdFormat);

	/**
	 * Returns the name ID name qualifier of this saml sp session.
	 *
	 * @return the name ID name qualifier of this saml sp session
	 */
	@AutoEscape
	public String getNameIdNameQualifier();

	/**
	 * Sets the name ID name qualifier of this saml sp session.
	 *
	 * @param nameIdNameQualifier the name ID name qualifier of this saml sp session
	 */
	public void setNameIdNameQualifier(String nameIdNameQualifier);

	/**
	 * Returns the name ID sp name qualifier of this saml sp session.
	 *
	 * @return the name ID sp name qualifier of this saml sp session
	 */
	@AutoEscape
	public String getNameIdSPNameQualifier();

	/**
	 * Sets the name ID sp name qualifier of this saml sp session.
	 *
	 * @param nameIdSPNameQualifier the name ID sp name qualifier of this saml sp session
	 */
	public void setNameIdSPNameQualifier(String nameIdSPNameQualifier);

	/**
	 * Returns the name ID value of this saml sp session.
	 *
	 * @return the name ID value of this saml sp session
	 */
	@AutoEscape
	public String getNameIdValue();

	/**
	 * Sets the name ID value of this saml sp session.
	 *
	 * @param nameIdValue the name ID value of this saml sp session
	 */
	public void setNameIdValue(String nameIdValue);

	/**
	 * Returns the session index of this saml sp session.
	 *
	 * @return the session index of this saml sp session
	 */
	@AutoEscape
	public String getSessionIndex();

	/**
	 * Sets the session index of this saml sp session.
	 *
	 * @param sessionIndex the session index of this saml sp session
	 */
	public void setSessionIndex(String sessionIndex);

	/**
	 * Returns the terminated of this saml sp session.
	 *
	 * @return the terminated of this saml sp session
	 */
	public boolean getTerminated();

	/**
	 * Returns <code>true</code> if this saml sp session is terminated.
	 *
	 * @return <code>true</code> if this saml sp session is terminated; <code>false</code> otherwise
	 */
	public boolean isTerminated();

	/**
	 * Sets whether this saml sp session is terminated.
	 *
	 * @param terminated the terminated of this saml sp session
	 */
	public void setTerminated(boolean terminated);

}