package com.master.openapi.vba;

import io.swagger.v3.oas.models.media.Schema;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.CodegenModel;
import org.openapitools.codegen.CodegenOperation;
import org.openapitools.codegen.CodegenProperty;
import org.openapitools.codegen.CodegenType;
import org.openapitools.codegen.DefaultCodegen;
import org.openapitools.codegen.SupportingFile;
import org.openapitools.codegen.meta.GeneratorMetadata;
import org.openapitools.codegen.meta.Stability;
import org.openapitools.codegen.model.ModelMap;
import org.openapitools.codegen.model.OperationsMap;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.openapitools.codegen.utils.CamelizeOption.LOWERCASE_FIRST_LETTER;
import static org.openapitools.codegen.utils.StringUtils.camelize;

public class VbaClientCodegen extends DefaultCodegen implements CodegenConfig {
    public VbaClientCodegen() {
        super();
        generatorMetadata = GeneratorMetadata.newBuilder(generatorMetadata).stability(Stability.EXPERIMENTAL).build();
        outputFolder = "generated-code" + File.separator + "vba";
        embeddedTemplateDir = templateDir = "vba";
        apiPackage = "Apis";
        modelPackage = "Models";
        modelTemplateFiles.put("model.mustache", ".cls");
        apiTemplateFiles.put("api.mustache", ".cls");
        supportingFiles.add(new SupportingFile("ApiClient.mustache", "Runtime", "ApiClient.cls"));
        supportingFiles.add(new SupportingFile("ApiResponse.mustache", "Runtime", "ApiResponse.cls"));
        supportingFiles.add(new SupportingFile("ApiException.mustache", "Runtime", "ApiException.cls"));
        supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));
        typeMapping.clear();
        typeMapping.put("string", "String");
        typeMapping.put("boolean", "Boolean");
        typeMapping.put("integer", "Long");
        typeMapping.put("int", "Long");
        typeMapping.put("long", "Variant");
        typeMapping.put("float", "Single");
        typeMapping.put("double", "Double");
        typeMapping.put("number", "Double");
        typeMapping.put("date", "Date");
        typeMapping.put("DateTime", "Date");
        typeMapping.put("array", "Collection");
        typeMapping.put("map", "Object");
        typeMapping.put("object", "Object");
        typeMapping.put("file", "Variant");
        languageSpecificPrimitives.addAll(Arrays.asList(
                "Boolean", "Byte", "Date", "Double", "Long", "Single", "String", "Variant"
        ));
        setReservedWordsLowerCase(Arrays.asList("as","boolean","byref","byte","byval","call","case","class","const","date","dim","do","double","each","else","elseif","end","enum","error","event","exit","false","for","friend","function","get","goto","if","implements","integer","let","long","loop","me","mod","new","next","not","nothing","object","on","option","optional","or","private","property","public","redim","resume","select","set","single","static","string","sub","then","true","type","variant","wend","while","with"));
    }
    @Override
    public String getName() {
        return "vba";
    }

    @Override
    public String getHelp() {
        return "Generates an experimental synchronous VBA REST client.";
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    @Override
    public String escapeReservedWord(String name) {
        return "_" + name;
    }

    @Override
    public String toVarName(String name) {
        String normalizedName = camelize(sanitizeName(name), LOWERCASE_FIRST_LETTER);
        return isReservedWord(normalizedName) ? escapeReservedWord(normalizedName) : normalizedName;
    }

    @Override
    public String toModelName(String name) {
        return camelize(sanitizeName(name));
    }

    @Override
    public String toApiName(String name) {
        return camelize(sanitizeName(name)) + "Api";
    }

    @Override
    public String getTypeDeclaration(String name) {
        if (name == null) {
            return "Variant";
        }
        if (name.startsWith("array[")) {
            return "Collection";
        }
        if (name.startsWith("map[")) {
            return "Object";
        }
        return super.getTypeDeclaration(name);
    }

    @Override
    public void postProcessModelProperty(CodegenModel model, CodegenProperty property) {
        super.postProcessModelProperty(model, property);
        property.vendorExtensions.put(
                "x-vba-is-object",
                !languageSpecificPrimitives.contains(property.dataType) || "Variant".equals(property.dataType)
        );
    }

    @Override
    public OperationsMap postProcessOperationsWithModels(OperationsMap operations, List<ModelMap> allModels) {
        OperationsMap processedOperations = super.postProcessOperationsWithModels(operations, allModels);
        for (CodegenOperation operation : processedOperations.getOperations().getOperation()) {
            if (operation.returnType != null && !operation.isArray && !operation.returnTypeIsPrimitive) {
                // Typed JSON hydration is not implemented yet. Expose ParseJson's object boundary
                // instead of promising a generated model instance that the runtime cannot return.
                operation.returnType = "Object";
                operation.returnBaseType = "Object";
            }
        }
        return processedOperations;
    }

    @Override
    public String toDefaultValue(Schema schema) {
        return null;
    }

    @Override
    public void processOpts() {
        super.processOpts();
        additionalProperties.put("generatorVersion", "0.1.0");
    }
}
