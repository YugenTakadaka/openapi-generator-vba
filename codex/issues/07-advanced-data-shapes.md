# [Phase 7] Advanced OpenAPI Data Shapes

Queue-Order: 70

# Goal

Harden maps/composition support and implement only composition semantics that have an explicit VBA design.

# Dependencies

Queue-Order 60 roadmap Issue

# Scope

- Harden `additionalProperties` map handling.
- Implement `allOf` where semantics can be represented safely in generated VBA.
- Design `oneOf`/`anyOf` representation before implementation.
- Add discriminator handling only for supported composition cases.
- Emit clear diagnostics for unsupported composition shapes.

# Acceptance Criteria

- [ ] Map/additionalProperties fixtures demonstrate supported behavior.
- [ ] Supported `allOf` fixtures generate deterministic usable models.
- [ ] `oneOf`/`anyOf` implementation does not begin until the Issue records a human-approved representation decision.
- [ ] Unsupported composition produces an explicit diagnostic.
- [ ] Compatibility matrix distinguishes partial/supported composition features.

# Required Tests / Fixtures

- Add focused OpenAPI fixture(s) for every newly claimed feature where applicable.
- Add generator/runtime tests that exercise production code paths.
- Run `mvn verify`.
- Inspect/verify generated VBA for VBA-specific syntax and assignment correctness.

# Out of Scope

- multipart/binary transport
- distribution packaging
- inventing a universal Variant-based union representation

# Working Rules

- Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before implementation.
- Follow `codex/prompts/IMPLEMENT.md`.
- Do not implement later roadmap work opportunistically.
- Update `COMPATIBILITY.md` only for behavior demonstrated by tests.
- Create a PR with `Closes #<this-issue>`.
- Do not auto-merge the PR.

# Human Decisions

None unless implementation encounters an ambiguity explicitly requiring `needs-human`.
