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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DDMTemplateVersion service. Represents a row in the &quot;DDMTemplateVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateVersionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.dynamic.data.mapping.model.impl.DDMTemplateVersionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplateVersion
 * @generated
 */
@ProviderType
public interface DDMTemplateVersionModel
	extends AttachedModel, BaseModel<DDMTemplateVersion>, LocalizedModel,
			MVCCModel, ShardedModel, WorkflowedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ddm template version model instance should use the {@link DDMTemplateVersion} interface instead.
	 */

	/**
	 * Returns the primary key of this ddm template version.
	 *
	 * @return the primary key of this ddm template version
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ddm template version.
	 *
	 * @param primaryKey the primary key of this ddm template version
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this ddm template version.
	 *
	 * @return the mvcc version of this ddm template version
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this ddm template version.
	 *
	 * @param mvccVersion the mvcc version of this ddm template version
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the template version ID of this ddm template version.
	 *
	 * @return the template version ID of this ddm template version
	 */
	public long getTemplateVersionId();

	/**
	 * Sets the template version ID of this ddm template version.
	 *
	 * @param templateVersionId the template version ID of this ddm template version
	 */
	public void setTemplateVersionId(long templateVersionId);

	/**
	 * Returns the group ID of this ddm template version.
	 *
	 * @return the group ID of this ddm template version
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this ddm template version.
	 *
	 * @param groupId the group ID of this ddm template version
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this ddm template version.
	 *
	 * @return the company ID of this ddm template version
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ddm template version.
	 *
	 * @param companyId the company ID of this ddm template version
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this ddm template version.
	 *
	 * @return the user ID of this ddm template version
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this ddm template version.
	 *
	 * @param userId the user ID of this ddm template version
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ddm template version.
	 *
	 * @return the user uuid of this ddm template version
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ddm template version.
	 *
	 * @param userUuid the user uuid of this ddm template version
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this ddm template version.
	 *
	 * @return the user name of this ddm template version
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this ddm template version.
	 *
	 * @param userName the user name of this ddm template version
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this ddm template version.
	 *
	 * @return the create date of this ddm template version
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this ddm template version.
	 *
	 * @param createDate the create date of this ddm template version
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the fully qualified class name of this ddm template version.
	 *
	 * @return the fully qualified class name of this ddm template version
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this ddm template version.
	 *
	 * @return the class name ID of this ddm template version
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this ddm template version.
	 *
	 * @param classNameId the class name ID of this ddm template version
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this ddm template version.
	 *
	 * @return the class pk of this ddm template version
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this ddm template version.
	 *
	 * @param classPK the class pk of this ddm template version
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the template ID of this ddm template version.
	 *
	 * @return the template ID of this ddm template version
	 */
	public long getTemplateId();

	/**
	 * Sets the template ID of this ddm template version.
	 *
	 * @param templateId the template ID of this ddm template version
	 */
	public void setTemplateId(long templateId);

	/**
	 * Returns the version of this ddm template version.
	 *
	 * @return the version of this ddm template version
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ddm template version.
	 *
	 * @param version the version of this ddm template version
	 */
	public void setVersion(String version);

	/**
	 * Returns the name of this ddm template version.
	 *
	 * @return the name of this ddm template version
	 */
	public String getName();

	/**
	 * Returns the localized name of this ddm template version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this ddm template version
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this ddm template version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm template version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this ddm template version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this ddm template version
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this ddm template version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this ddm template version
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this ddm template version.
	 *
	 * @return the locales and localized names of this ddm template version
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this ddm template version.
	 *
	 * @param name the name of this ddm template version
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this ddm template version in the language.
	 *
	 * @param name the localized name of this ddm template version
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this ddm template version in the language, and sets the default locale.
	 *
	 * @param name the localized name of this ddm template version
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this ddm template version from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this ddm template version
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this ddm template version from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this ddm template version
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this ddm template version.
	 *
	 * @return the description of this ddm template version
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this ddm template version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this ddm template version
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this ddm template version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm template version. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this ddm template version in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this ddm template version
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this ddm template version in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this ddm template version
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this ddm template version.
	 *
	 * @return the locales and localized descriptions of this ddm template version
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this ddm template version.
	 *
	 * @param description the description of this ddm template version
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this ddm template version in the language.
	 *
	 * @param description the localized description of this ddm template version
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this ddm template version in the language, and sets the default locale.
	 *
	 * @param description the localized description of this ddm template version
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(
		String description, Locale locale, Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this ddm template version from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm template version
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this ddm template version from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this ddm template version
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale);

	/**
	 * Returns the language of this ddm template version.
	 *
	 * @return the language of this ddm template version
	 */
	@AutoEscape
	public String getLanguage();

	/**
	 * Sets the language of this ddm template version.
	 *
	 * @param language the language of this ddm template version
	 */
	public void setLanguage(String language);

	/**
	 * Returns the script of this ddm template version.
	 *
	 * @return the script of this ddm template version
	 */
	@AutoEscape
	public String getScript();

	/**
	 * Sets the script of this ddm template version.
	 *
	 * @param script the script of this ddm template version
	 */
	public void setScript(String script);

	/**
	 * Returns the status of this ddm template version.
	 *
	 * @return the status of this ddm template version
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this ddm template version.
	 *
	 * @param status the status of this ddm template version
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this ddm template version.
	 *
	 * @return the status by user ID of this ddm template version
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this ddm template version.
	 *
	 * @param statusByUserId the status by user ID of this ddm template version
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this ddm template version.
	 *
	 * @return the status by user uuid of this ddm template version
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this ddm template version.
	 *
	 * @param statusByUserUuid the status by user uuid of this ddm template version
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this ddm template version.
	 *
	 * @return the status by user name of this ddm template version
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this ddm template version.
	 *
	 * @param statusByUserName the status by user name of this ddm template version
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this ddm template version.
	 *
	 * @return the status date of this ddm template version
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this ddm template version.
	 *
	 * @param statusDate the status date of this ddm template version
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns <code>true</code> if this ddm template version is approved.
	 *
	 * @return <code>true</code> if this ddm template version is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this ddm template version is denied.
	 *
	 * @return <code>true</code> if this ddm template version is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this ddm template version is a draft.
	 *
	 * @return <code>true</code> if this ddm template version is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this ddm template version is expired.
	 *
	 * @return <code>true</code> if this ddm template version is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this ddm template version is inactive.
	 *
	 * @return <code>true</code> if this ddm template version is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this ddm template version is incomplete.
	 *
	 * @return <code>true</code> if this ddm template version is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this ddm template version is pending.
	 *
	 * @return <code>true</code> if this ddm template version is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this ddm template version is scheduled.
	 *
	 * @return <code>true</code> if this ddm template version is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

}