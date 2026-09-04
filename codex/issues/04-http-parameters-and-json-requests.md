# [Phase 4] HTTP Parameters and JSON Requests

Queue-Order: 40

# Goal

Generate correct request metadata and Runtime behavior for common OpenAPI parameters and JSON request bodies.

# Dependencies

Queue-Order 30 roadmap Issue

# Scope

- Support path-parameter substitution with URL encoding.
- Support query parameters for the baseline serialization styles explicitly claimed by the project.
- Support header parameters.
- Support cookie parameters if the chosen Runtime transport can represent them correctly; otherwise fail/document explicitly.
- Serialize typed JSON request bodies through model/serializer boundaries.
- Keep HTTP transport implementation in Runtime/`ApiClient`.

# Acceptance Criteria

- [ ] Fixtures exercise path, query, header, cookie decision, and JSON body behavior.
- [ ] Reserved characters in path/query values are encoded according to the documented baseline.
- [ ] Generated API classes describe requests without duplicating WinHTTP transport code.
- [ ] Typed request models serialize through the model serializer rather than hand-built JSON in API methods.
- [ ] Unsupported parameter serialization styles fail or warn explicitly.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- typed response deserialization beyond prerequisites
- new authentication schemes
- multipart/form-data
- binary upload/download
- advanced OpenAPI composition

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
