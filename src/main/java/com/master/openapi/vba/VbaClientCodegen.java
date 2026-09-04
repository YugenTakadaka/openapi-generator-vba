package com.master.openapi.vba;

import org.openapitools.codegen.*;
import org.openapitools.codegen.meta.GeneratorMetadata;
import org.openapitools.codegen.meta.Stability;
import org.openapitools.codegen.meta.features.*;
import java.io.File;
import java.util.*;

public class VbaClientCodegen extends DefaultCodegen implements CodegenConfig {
    public VbaClientCodegen() {
        super();
        generatorMetadata = GeneratorMetadata.newBuilder(generatorMetadata).stability(Stability.EXPERIMENTAL).build();
        outputFolder = "generated-code" + File.separator + "vba";
        embeddedTemplateDir = templateDir = "vba";
        apiPackage = "Apis"; modelPackage = "Models";
        modelTemplateFiles.put("model.mustache", ".cls");
        apiTemplateFiles.put("api.mustache", ".cls");
        supportingFiles.add(new SupportingFile("ApiClient.mustache", "Runtime", "ApiClient.cls"));
        supportingFiles.add(new SupportingFile("ApiResponse.mustache", "Runtime", "ApiResponse.cls"));
        supportingFiles.add(new SupportingFile("ApiException.mustache", "Runtime", "ApiException.cls"));
        supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));
        typeMapping.clear();
        typeMapping.put("string", "String"); typeMapping.put("boolean", "Boolean");
        typeMapping.put("integer", "Long"); typeMapping.put("int", "Long");
        typeMapping.put("long", "Variant"); typeMapping.put("float", "Single");
        typeMapping.put("double", "Double"); typeMapping.put("number", "Double");
        typeMapping.put("date", "Date"); typeMapping.put("DateTime", "Date");
        typeMapping.put("array", "Collection"); typeMapping.put("map", "Object");
        typeMapping.put("object", "Object"); typeMapping.put("file", "Variant");
        setReservedWordsLowerCase(Arrays.asList("as","boolean","byref","byte","byval","call","case","class","const","date","dim","do","double","each","else","elseif","end","enum","error","event","exit","false","for","friend","function","get","goto","if","implements","integer","let","long","loop","me","mod","new","next","not","nothing","object","on","option","optional","or","private","property","public","redim","resume","select","set","single","static","string","sub","then","true","type","variant","wend","while","with"));
    }
    @Override public String getName() { return "vba"; }
    @Override public String getHelp() { return "Generates an experimental synchronous VBA REST client."; }
    @Override public CodegenType getTag() { return CodegenType.CLIENT; }
    @Override public String escapeReservedWord(String name) { return "_" + name; }
    @Override public String toVarName(String name) { String n = sanitizeName(name); n = camelize(n, LOWERCASE_FIRST_LETTER); if (isReservedWord(n)) n = escapeReservedWord(n); return n; }
    @Override public String toModelName(String name) { return camelize(sanitizeName(name)); }
    @Override public String toApiName(String name) { return camelize(sanitizeName(name)) + "Api"; }
    @Override public String getTypeDeclaration(String name) {
        if (name == null) return "Variant";
        if (name.startsWith("array[")) return "Collection";
        if (name.startsWith("map[")) return "Object";
        return super.getTypeDeclaration(name);
    }
    @Override public String toDefaultValue(org.openapitools.codegen.CodegenSchema p) { return null; }
    @Override public void processOpts() { super.processOpts(); additionalProperties.put("generatorVersion", "0.1.0"); }
}
