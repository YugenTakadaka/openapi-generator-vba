# [Phase 3] Typed Model Serialization

Queue-Order: 30

# Goal

Generate typed VBA model classes that can map to and from JSON-compatible Dictionary/Collection structures.

# Dependencies

Queue-Order 20 roadmap Issue

# Scope

- Generate typed model properties using Phase 2 metadata.
- Generate or centralize `FromDictionary` mapping for schema models.
- Generate or centralize `ToDictionary` mapping for request serialization.
- Support nested model properties.
- Support collections of scalars and collections of models.
- Preserve the agreed nullable/optional semantics.

# Acceptance Criteria

- [ ] A model fixture round-trips representative scalar fields through Dictionary form.
- [ ] Nested models deserialize and serialize through production generated code paths.
- [ ] Collections of models/scalars are mapped correctly.
- [ ] Object/scalar property assignment is VBA-correct.
- [ ] Missing/nullable values follow the Phase 2 policy and are tested.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- HTTP request execution
- query/path/header serialization
- authentication
- multipart/binary
- `oneOf`/`anyOf`

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
