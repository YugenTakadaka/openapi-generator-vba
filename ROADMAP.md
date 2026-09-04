# Roadmap

This roadmap defines implementation order. GitHub Issues are the authoritative task scopes and acceptance criteria.
PR numbers are intentionally not fixed here because bug fixes and maintenance PRs may appear between roadmap changes.

## Queue rules

- Each roadmap implementation task has a `Queue-Order` in its GitHub Issue.
- Normally one open Issue is labeled `codex` + `ready`.
- Later tasks remain `codex` + `blocked` until their predecessor is merged/closed.
- A task with `needs-human` does not automatically advance.
- Human-approved merges advance the queue; agents do not auto-merge roadmap PRs.

## Phase 1 — Generator Foundation

Queue-Order: 10  
Status: Planned / first task  
Depends on: none

Goals:

- reproducible Maven build;
- custom generator discovery via `-g vba`;
- minimal OpenAPI 3.x fixture;
- at least one generated API class and model class;
- deterministic generation;
- automated foundation tests.

## Phase 2 — VBA Type and Identifier System

Queue-Order: 20  
Status: Planned  
Depends on: Phase 1

Goals:

- central scalar mappings;
- object/scalar assignment metadata;
- reserved identifier normalization;
- nullable representation policy and tests;
- `int64` policy across supported VBA/Office targets;
- arrays/maps baseline;
- enums baseline.

## Phase 3 — Typed Model Serialization

Queue-Order: 30  
Status: Planned  
Depends on: Phase 2

Goals:

- generated typed properties;
- `FromDictionary` mapping;
- `ToDictionary` mapping;
- nested models;
- collections of models/scalars;
- null/optional handling consistent with Phase 2.

## Phase 4 — HTTP Parameters and JSON Requests

Queue-Order: 40  
Status: Planned  
Depends on: Phase 3

Goals:

- path parameters;
- query parameters;
- header parameters;
- cookie parameters where Runtime can represent them correctly;
- JSON request bodies;
- encoding and serialization tests.

## Phase 5 — Typed Responses and HTTP Errors

Queue-Order: 50  
Status: Planned  
Depends on: Phase 4

Goals:

- no-content responses;
- scalar responses;
- typed model responses;
- arrays of scalars/models;
- response metadata;
- structured HTTP error propagation.

## Phase 6 — Authentication

Queue-Order: 60  
Status: Planned  
Depends on: Phase 5

Goals:

- Bearer authentication;
- API key in supported locations;
- Basic authentication;
- externally supplied OAuth access tokens;
- no interactive OAuth flow inside generated clients unless separately specified.

## Phase 7 — Advanced OpenAPI Data Shapes

Queue-Order: 70  
Status: Planned  
Depends on: Phase 6

Goals:

- `additionalProperties`/maps hardening;
- `allOf` support where VBA semantics are well defined;
- explicit design and implementation for `oneOf`/`anyOf`;
- discriminator handling where supported;
- unsupported-composition diagnostics.

This phase requires human design approval before union-like constructs are implemented.

## Phase 8 — Multipart and Binary I/O

Queue-Order: 80  
Status: Planned  
Depends on: Phase 6; Phase 7 only where data-shape dependencies require it

Goals:

- binary download;
- binary upload;
- `multipart/form-data`;
- safe byte-array/file boundaries suitable for VBA;
- focused Runtime tests.

## Phase 9 — Distribution and Consumer Experience

Queue-Order: 90  
Status: Planned  
Depends on: Phases 1–8 required for the initial release target

Goals:

- reproducible release artifact;
- documented generator invocation;
- example generated SDK;
- package layout suitable for VPM-style consumption;
- installation/runtime dependency documentation;
- release smoke tests.

## Future work

Potential work after the initial release target includes:

- broader OpenAPI serialization styles;
- callbacks/webhooks if a VBA client use case is established;
- alternate HTTP transports behind Runtime abstractions;
- richer OAuth helpers supplied as optional Runtime extensions;
- additional Office hosts beyond Excel.
