package io.swagger.codegen.options;

import com.google.common.collect.ImmutableMap;
import io.swagger.codegen.CodegenConstants;
import io.swagger.codegen.languages.TypeScriptFetchClientCodegen;

import java.util.Map;

public class TypeScriptFetchClientOptionsProvider implements OptionsProvider {
    public static final String SORT_PARAMS_VALUE = "false";
    public static final String ENSURE_UNIQUE_PARAMS_VALUE = "true";
    public static final Boolean SUPPORTS_ES6_VALUE = false;
    public static final String MODEL_PROPERTY_NAMING_VALUE = "camelCase";
    public static final String ENUM_PROPERTY_NAMING_VALUE = "snake_case";
    private static final String NMP_NAME = "npmName";
    private static final String NMP_VERSION = "1.0.0";
    private static final String NPM_REPOSITORY = "https://registry.npmjs.org";
    public static final String ALLOW_UNICODE_IDENTIFIERS_VALUE = "false";


    @Override
    public String getLanguage() {
        return "typescript-fetch";
    }

    @Override
    public Map<String, String> createOptions() {
        ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<String, String>();
        return builder.put(CodegenConstants.SORT_PARAMS_BY_REQUIRED_FLAG, SORT_PARAMS_VALUE)
                .put(CodegenConstants.ENSURE_UNIQUE_PARAMS, ENSURE_UNIQUE_PARAMS_VALUE)
                .put(CodegenConstants.MODEL_PROPERTY_NAMING, MODEL_PROPERTY_NAMING_VALUE)
                .put(CodegenConstants.ENUM_PROPERTY_NAMING, ENUM_PROPERTY_NAMING_VALUE)
                .put(CodegenConstants.SUPPORTS_ES6, String.valueOf(SUPPORTS_ES6_VALUE))
                .put(TypeScriptFetchClientCodegen.NPM_NAME, NMP_NAME)
                .put(TypeScriptFetchClientCodegen.NPM_VERSION, NMP_VERSION)
                .put(TypeScriptFetchClientCodegen.NPM_REPOSITORY, NPM_REPOSITORY)
                .put(TypeScriptFetchClientCodegen.SNAPSHOT, Boolean.FALSE.toString())
                .put(TypeScriptFetchClientCodegen.WITH_INTERFACES, Boolean.FALSE.toString())
                .put(CodegenConstants.ALLOW_UNICODE_IDENTIFIERS, ALLOW_UNICODE_IDENTIFIERS_VALUE)
                .build();
    }

    @Override
    public boolean isServer() {
        return false;
    }
}
