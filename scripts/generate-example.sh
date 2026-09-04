#!/usr/bin/env bash
set -euo pipefail
CLI_JAR="${OPENAPI_GENERATOR_CLI_JAR:?Set OPENAPI_GENERATOR_CLI_JAR to openapi-generator-cli.jar}"
mvn -q package
JAR=$(find target -maxdepth 1 -name 'openapi-generator-vba-*.jar' ! -name '*sources*' | head -1)
rm -rf build/generated-example
java -cp "$JAR:$CLI_JAR" org.openapitools.codegen.OpenAPIGenerator generate -g vba -i examples/fixtures/minimal.yaml -o build/generated-example
