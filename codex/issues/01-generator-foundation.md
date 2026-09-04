# [Phase 1] Generator Foundation

Queue-Order: 10

# Goal

Establish a reproducible, testable OpenAPI Generator custom generator for VBA.

# Dependencies

none

# Scope

- Make the Maven project build successfully.
- Ensure OpenAPI Generator discovers the custom generator as `-g vba`.
- Add/retain a minimal OpenAPI 3.x fixture.
- Generate at least one VBA API class and one VBA model class.
- Make generated output deterministic.
- Add automated tests for generator discovery and representative generated output.

# Acceptance Criteria

- [ ] `mvn verify` succeeds.
- [ ] The custom generator is discoverable as `vba`.
- [ ] The minimal fixture generates an API class and model class.
- [ ] Repeated generation from identical input is deterministic for tested files.
- [ ] Tests exercise the real generator/templates rather than hand-authored expected VBA only.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- nullable semantics
- enum semantics beyond what is strictly needed to compile
- advanced authentication
- multipart/binary
- OpenAPI composition (`oneOf`/`anyOf`/`allOf`)

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
