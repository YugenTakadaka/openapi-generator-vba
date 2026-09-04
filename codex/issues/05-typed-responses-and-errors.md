# [Phase 5] Typed Responses and HTTP Errors

Queue-Order: 50

# Goal

Return useful typed VBA values from generated operations and preserve structured HTTP failure information.

# Dependencies

Queue-Order 40 roadmap Issue

# Scope

- Support operations with no response body.
- Support scalar response bodies.
- Support typed model response bodies.
- Support arrays of scalars and arrays of models.
- Expose response status/body/header metadata through Runtime as needed.
- Preserve structured HTTP errors with status code, status text when available, and response body when available.

# Acceptance Criteria

- [ ] Fixtures cover no-content, scalar, model, array-of-scalar, and array-of-model responses.
- [ ] Generated API methods return/assign object and scalar results with correct VBA semantics.
- [ ] Non-success HTTP responses preserve required diagnostic fields.
- [ ] Response deserialization is centralized and not duplicated per operation.
- [ ] Tests exercise Runtime and generated API integration where practical.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- new authentication schemes
- multipart/binary
- advanced composition
- automatic retry policy

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
