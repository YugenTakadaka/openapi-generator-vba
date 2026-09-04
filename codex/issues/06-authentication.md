# [Phase 6] Authentication

Queue-Order: 60

# Goal

Add common OpenAPI authentication schemes without coupling generated clients to interactive token acquisition.

# Dependencies

Queue-Order 50 roadmap Issue

# Scope

- Support Bearer token injection.
- Support API keys in explicitly supported OpenAPI locations.
- Support HTTP Basic authentication.
- Support OAuth-protected operations through an externally supplied access token or credential provider boundary, without forcing an interactive OAuth flow into generated clients.
- Apply operation security requirements through prepared generator metadata and Runtime.

# Acceptance Criteria

- [ ] Fixtures cover each claimed security scheme and protected/unprotected operations.
- [ ] Secrets are never emitted into generated source or test fixtures.
- [ ] Authentication headers/parameters are applied centrally by Runtime.
- [ ] Externally supplied OAuth access tokens can be used without generated interactive login code.
- [ ] Unsupported security combinations are explicit rather than silently ignored.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- interactive OAuth authorization-code UI
- credential persistence
- Windows Credential Manager integration
- multipart/binary
- OpenAPI union/composition types

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
