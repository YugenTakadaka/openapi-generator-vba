# [Phase 9] Distribution and Consumer Experience

Queue-Order: 90

# Goal

Package the generator and generated Runtime so a consumer can reproducibly generate and use a VBA SDK.

# Dependencies

Queue-Order 80 roadmap Issue

# Scope

- Produce a reproducible release artifact.
- Document generator invocation and pinned compatibility expectations.
- Provide an example generated SDK from a committed fixture/spec.
- Define a package layout suitable for VPM-style consumption.
- Document Runtime dependencies such as VBA-JSON expectations and Office/Windows requirements.
- Add release/consumer smoke tests.

# Acceptance Criteria

- [ ] A clean checkout can build the release artifact with documented commands.
- [ ] A documented example command generates the example SDK.
- [ ] Release smoke tests validate expected output layout.
- [ ] Consumer documentation explains how to import/use generated classes and Runtime dependencies.
- [ ] No manual post-generation source edits are required for the documented happy path.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- publishing to a specific external package registry unless separately requested
- Office Script support
- non-Windows HTTP transports unless separately scoped

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
