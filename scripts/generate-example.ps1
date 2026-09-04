$ErrorActionPreference = "Stop"
if (-not $env:OPENAPI_GENERATOR_CLI_JAR) { throw "Set OPENAPI_GENERATOR_CLI_JAR to openapi-generator-cli.jar" }
mvn -q package
$jar = Get-ChildItem target/openapi-generator-vba-*.jar | Where-Object { $_.Name -notmatch 'sources' } | Select-Object -First 1
Remove-Item -Recurse -Force build/generated-example -ErrorAction SilentlyContinue
java -cp "$($jar.FullName);$($env:OPENAPI_GENERATOR_CLI_JAR)" org.openapitools.codegen.OpenAPIGenerator generate -g vba -i examples/fixtures/minimal.yaml -o build/generated-example
