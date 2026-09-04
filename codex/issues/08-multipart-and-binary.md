# [Phase 8] Multipart and Binary I/O

Queue-Order: 80

# Goal

Add practical binary download/upload and multipart request support to the VBA Runtime and generated APIs.

# Dependencies

Queue-Order 70 roadmap Issue

# Scope

- Define a VBA-safe binary representation for Runtime boundaries.
- Support binary response download.
- Support binary request upload.
- Support `multipart/form-data` for the explicitly tested baseline.
- Keep byte/file transport mechanics centralized in Runtime.

# Acceptance Criteria

- [ ] Fixtures cover binary response, binary request, and multipart form data.
- [ ] Generated API methods do not duplicate multipart encoding logic.
- [ ] Binary data is not coerced through lossy text conversions.
- [ ] Failure behavior is tested for malformed/unsupported multipart shapes.
- [ ] 32/64-bit Office assumptions are documented where relevant.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- streaming very large files unless explicitly designed
- advanced OAuth flows
- distribution packaging

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
