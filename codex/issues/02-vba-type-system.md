# [Phase 2] VBA Type and Identifier System

Queue-Order: 20

# Goal

Create the central VBA type/identifier policy used by later templates and serialization.

# Dependencies

Queue-Order 10 roadmap Issue

# Scope

- Centralize scalar OpenAPI-to-VBA mappings in `VbaClientCodegen`.
- Provide explicit metadata for object versus scalar assignment so templates can emit `Set` correctly.
- Normalize invalid/reserved VBA identifiers deterministically.
- Define and implement the agreed nullable representation for the supported baseline.
- Define and implement the agreed `int64` representation across supported Office bitness targets.
- Add baseline array/map and enum representation needed by generated models.

# Acceptance Criteria

- [ ] Fixtures cover representative scalar types, arrays/maps, enums, nullable values, reserved words, and `int64`.
- [ ] Generated declarations use prepared VBA types rather than complex Mustache type branching.
- [ ] Object assignments use `Set`; scalar assignments do not.
- [ ] Identifier normalization is deterministic and collision behavior is tested.
- [ ] `COMPATIBILITY.md` reflects only demonstrated mappings.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- typed `FromDictionary`/`ToDictionary` model serialization
- HTTP parameter serialization
- authentication
- multipart/binary
- union/composition types

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
