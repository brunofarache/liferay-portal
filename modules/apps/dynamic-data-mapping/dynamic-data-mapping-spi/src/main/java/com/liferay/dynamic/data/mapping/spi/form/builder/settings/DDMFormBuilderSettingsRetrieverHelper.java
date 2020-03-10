package com.liferay.dynamic.data.mapping.spi.form.builder.settings;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.json.JSONArray;

import java.util.Locale;

public interface DDMFormBuilderSettingsRetrieverHelper {
	String getDDMDataProviderInstanceParameterSettingsURL();

	String getDDMDataProviderInstancesURL();

	String getDDMFieldSetDefinitionURL();

	String getDDMFieldSettingsDDMFormContextURL();

	String getDDMFormContextProviderURL();

	String getDDMFunctionsURL();

	JSONArray getFieldSetsMetadataJSONArray(
		long companyId, long scopeGroupId, long fieldSetClassNameId,
		Locale locale);

	String getRolesURL();

	String getSerializedDDMExpressionFunctionsMetadata(Locale locale);

	String getSerializedDDMFormRules(DDMForm ddmForm);
}
