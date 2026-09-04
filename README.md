# openapi-generator-vba

A small experimental custom generator for OpenAPI Generator which emits VBA `.cls` clients/models.

## Build

```bash
mvn package
```

Use the resulting JAR together with the OpenAPI Generator CLI JAR on the Java classpath. The custom generator is registered through Java SPI (`META-INF/services/org.openapitools.codegen.CodegenConfig`).

Linux/macOS example:

```bash
java -cp "target/openapi-generator-vba-0.1.0.jar:openapi-generator-cli.jar" \
  org.openapitools.codegen.OpenAPIGenerator generate \
  -g vba -i examples/petstore-mini.yaml -o out
```

Windows uses `;` instead of `:` between classpath entries.

## Design

OpenAPI Generator's Java codegen class handles semantic mapping; Mustache handles VBA syntax. Runtime HTTP/JSON concerns live in generated supporting classes rather than being repeated in every API class.

## Important

This is an MVP scaffold, not yet a production-complete VBA generator. See the generated README for unsupported areas.

## Codex development workflow

Before asking Codex to change the generator, have it read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md`. Ready-to-use scoped prompts live in `codex/prompts/`; start with `PR-01-foundation.md` and merge/test each phase before moving on.

The repository intentionally separates semantic conversion (`VbaClientCodegen`) from rendering (Mustache). Generated output is an artifact of the generator and must not be manually patched as the primary fix.

## PR-driven Codex development

Implementation is intentionally PR-driven. Start with `codex/prompts/PR-01-foundation.md`, use one branch/PR per numbered prompt, and run a separate review using `codex/prompts/REVIEW.md`. GitHub Actions runs Maven verification and a generation smoke test. See `AGENTS.md` and `codex/README.md`; Codex must never merge its own PR.
