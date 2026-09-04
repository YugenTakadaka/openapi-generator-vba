# AGENTS.md

## Project Goal
This repository implements a VBA client generator for OpenAPI Generator.
Generate maintainable, strongly typed VBA REST API clients from OpenAPI 3.x specifications.

## Working Agreement
- Read `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before editing.
- Keep changes scoped to the requested roadmap phase/PR.
- Inspect the APIs of the OpenAPI Generator version pinned in `pom.xml`; do not assume APIs from another version.
- Fix generator/templates, never hand-patch generated output as the primary solution.
- Every newly supported OpenAPI feature requires a fixture and automated assertions.
- Do not mark a feature Supported unless tests demonstrate it.

## Architecture
OpenAPI Specification -> `VbaClientCodegen` semantic transformation -> Mustache context -> Mustache rendering -> `.cls`/`.bas` files.

### Java layer responsibilities
`VbaClientCodegen` owns OpenAPI-to-VBA semantics: type mapping, nullable handling, identifier normalization, reserved words, enum representation, object/scalar distinctions, and vendor extensions consumed by templates.

### Mustache responsibilities
Templates render VBA. Keep semantic/type decisions out of Mustache whenever possible. Prefer `{{dataType}}` prepared by Java over condition trees that reconstruct type mapping.

## Generated Code Architecture
Generated output is organized conceptually into `Apis/`, `Models/`, and `Runtime/`. Models never perform HTTP. API classes describe operations and delegate transport/serialization infrastructure to Runtime.

## VBA Compatibility and Style
- Target Microsoft Excel VBA on Windows.
- Account for 32-bit and 64-bit Office where relevant.
- Every generated module uses `Option Explicit`.
- Use explicit access modifiers and no implicit/default members.
- Object assignment uses `Set`; scalar assignment does not.
- Avoid `Variant` unless required by the VBA/OpenAPI type model.
- Avoid `On Error Resume Next` except for a narrow, documented cleanup/probe scope.
- Prefer late binding for infrastructure dependencies unless early binding is deliberately part of the public contract.

## HTTP and JSON
Default transport is `WinHttp.WinHttpRequest.5.1`. HTTP implementation belongs in `ApiClient`, not individual API classes. JSON parsing/serialization is abstracted from API classes. Initial interoperability with VBA-JSON-style `Dictionary`/`Collection` structures is acceptable; typed model conversion belongs in generated model mapping or a centralized serializer.

## Errors
Never silently swallow errors. HTTP failures preserve at least status code, status text, and response body.

## OpenAPI Scope
Primary target is OpenAPI 3.x. Unsupported behavior must not silently degrade into incorrect behavior. Prefer a generation-time warning/error when semantics cannot be represented safely.

## Tests
For each feature add: (1) OpenAPI fixture, (2) generator test, (3) generated-code assertions. Where practical validate generated VBA syntax/importability. Golden output changes require semantic review; never refresh snapshots only to make CI green.

## Pull Requests
Keep PRs small: normally one feature or one closely related group. Update `COMPATIBILITY.md` whenever demonstrated support changes.

## Definition of Done
Implementation + template support + fixture + passing tests + generated output inspection + compatibility update + user-facing docs where behavior changed.

## Non-goals
Do not rewrite OpenAPI Generator, duplicate HTTP logic per API, put business logic in Mustache, add worksheet-specific application logic, or solve generator defects by editing generated files.

## PR-Driven Development (mandatory)
All implementation changes are made on a dedicated branch and delivered through a pull request. Never commit implementation work directly to `main`, never combine roadmap phases merely for convenience, and never merge automatically.

Before implementation: read this file, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md`; identify one PR scope; inspect current tests and the pinned OpenAPI Generator API; state a short plan.

During implementation: remain inside scope; add or update fixtures and tests with behavior changes; fix generator/runtime/templates rather than hand-editing generated artifacts; keep generated output deterministic; record discovered out-of-scope work instead of implementing it opportunistically.

Before opening/updating a PR: run `mvn test`, run representative generation, inspect generated VBA, update `COMPATIBILITY.md` only for tested behavior, and update user docs when behavior changes. A failing check means the PR is not ready.

Every PR description must cover Purpose, Scope, Implementation, Tests, Compatibility changes, Known limitations, Out of scope, and Suggested next PR. Use `.github/pull_request_template.md` as the checklist.

Review is a separate phase from implementation. Review against the specification, compatibility claims, VBA semantics, generated output, and tests. Findings should be fixed on the same PR branch when in scope. Human approval is required before merge. Codex must not merge its own PR.

If a requested change is too large for one reviewable PR, stop and propose a PR split before implementing the extra scope.
