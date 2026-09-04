# AGENTS.md

## Purpose

This repository implements a VBA client generator for OpenAPI Generator.
The project generates maintainable, strongly typed VBA REST API clients from OpenAPI 3.x specifications.

This file defines mandatory repository-wide instructions for coding agents, including Codex.

## Sources of truth

Read these files before changing production code:

1. `AGENTS.md` — mandatory working rules.
2. `SPEC.md` — product and architecture specification.
3. `COMPATIBILITY.md` — currently demonstrated feature support.
4. `ROADMAP.md` — implementation order and dependencies.
5. The assigned GitHub Issue — authoritative scope and acceptance criteria for the current change.

When these disagree, stop and request human guidance unless the conflict is clearly resolved by a more specific instruction.
Do not silently rewrite the specification to match an implementation shortcut.

## Architecture

Keep this responsibility split strict:

```text
OpenAPI Specification
        ↓
VbaClientCodegen
semantic transformation and VBA-specific decisions
        ↓
Mustache context
        ↓
Mustache templates
presentation only
        ↓
VBA .cls / .bas source
```

### Java generator responsibilities

`VbaClientCodegen` owns semantic decisions including:

- OpenAPI-to-VBA type mapping;
- nullable representation;
- VBA identifier normalization;
- VBA reserved-word handling;
- scalar-versus-object classification;
- enum representation;
- collection and map representation;
- model/API metadata enrichment;
- vendor extensions consumed by templates;
- warnings or failures for unsupported OpenAPI semantics.

### Mustache responsibilities

Mustache templates render VBA source from already prepared metadata.
Do not place complicated OpenAPI type reasoning in Mustache.

Bad:

```mustache
{{#isInteger}}Long{{/isInteger}}
```

Preferred:

```mustache
{{dataType}}
```

### Generated-code architecture

Generated output is conceptually divided into:

```text
Apis/
Models/
Runtime/
```

- API classes expose operations and describe requests.
- Model classes represent schemas and serialization boundaries.
- Runtime owns HTTP, authentication, serialization infrastructure, encoding, and response/error handling.
- Models must not perform HTTP requests.
- API classes must not duplicate transport implementation.

## VBA compatibility target

Primary target:

- Microsoft Excel VBA;
- Windows;
- VBA7;
- 32-bit and 64-bit Office where practical.

Do not claim a compatibility target without a fixture/test or an explicit documented limitation.
Prefer late binding for infrastructure dependencies when it materially reduces installation friction.
Do not require users to hand-edit generated source as the normal workflow.

## VBA coding rules

Every generated module must include:

```vb
Option Explicit
```

Use explicit access modifiers.
Prefer explicit `Public Function`, `Private Function`, `Public Property Get`, `Public Property Let`, and `Public Property Set` declarations.

Mandatory assignment rule:

- object assignment uses `Set`;
- scalar assignment does not use `Set`.

Do not rely on default members.
Avoid `Variant` unless the VBA/OpenAPI representation requires it.
Do not use `On Error Resume Next` except for a narrowly scoped, documented compatibility operation.

## HTTP

The default Windows transport is:

```text
WinHttp.WinHttpRequest.5.1
```

HTTP implementation belongs in Runtime/`ApiClient`, not in generated API methods.
API methods provide request metadata such as method, path, parameters, body, expected response type, and authentication requirements.

HTTP failures must preserve at least:

- status code;
- status text when available;
- response body when available.

## JSON

JSON parsing/encoding must be abstracted from API classes.
A VBA-JSON-compatible `Dictionary`/`Collection` representation is acceptable as the initial untyped boundary.
Typed deserialization should map that representation into generated model classes.

## OpenAPI support policy

Primary input target is OpenAPI 3.x.

Unsupported behavior must never be silently treated as supported behavior.
Prefer a generation-time warning or failure over a client that emits semantically incorrect requests.

A feature may move to `Supported` in `COMPATIBILITY.md` only when automated evidence demonstrates it.

## Testing policy

Every newly supported OpenAPI feature requires, where applicable:

1. an OpenAPI fixture;
2. a generator test;
3. assertions over generated VBA;
4. runtime tests when Runtime behavior changes.

Generated files are test output, not the source of truth.
Never manually patch generated output as the primary fix. Fix the generator, templates, or Runtime source.

Do not update golden files merely to make tests green without verifying the semantic change.

Before declaring a task complete, run:

```bash
mvn verify
```

Also run any feature-specific generation/verification commands required by the assigned Issue.

## PR-driven development

All implementation work is performed through Pull Requests.
Do not push implementation directly to `main`.

One PR should normally implement one GitHub Issue and one cohesive feature slice.
Do not combine unrelated roadmap phases in one PR.

The assigned GitHub Issue is the authoritative scope for that PR.

### Before implementation

1. Read this file.
2. Read `SPEC.md`.
3. Read `COMPATIBILITY.md`.
4. Read `ROADMAP.md`.
5. Read the assigned Issue completely.
6. Inspect the current implementation and tests.
7. Verify relevant OpenAPI Generator APIs against the version pinned by the repository.
8. Produce a short implementation plan.

### During implementation

- stay inside Issue scope;
- add tests with production changes;
- keep generated code deterministic;
- do not implement future roadmap work opportunistically;
- do not change `SPEC.md` unless the Issue explicitly authorizes a specification change;
- update `COMPATIBILITY.md` only for behavior demonstrated by tests.

### Before opening or completing a PR

- run `mvn verify`;
- generate relevant fixtures;
- inspect/verify generated VBA;
- update compatibility/documentation when required;
- ensure no unrelated roadmap work is included;
- reference the Issue using `Closes #<issue>`.

### PR description

Every implementation PR must contain:

- Issue;
- Purpose;
- Scope;
- Implementation;
- Acceptance Criteria status;
- Tests executed;
- Compatibility changes;
- Known limitations;
- Human decisions, if any;
- Out of scope.

## Automated task queue

Development work is queued in GitHub Issues.

Implementation issues use these labels:

- `codex` — eligible for Codex implementation;
- `ready` — currently eligible to start;
- `blocked` — waiting on a predecessor or human decision;
- `needs-human` — agent encountered a specification/design decision requiring human input.

Normally exactly one roadmap implementation Issue is `codex` + `ready` at a time.

The queue order is defined by the `Queue-Order:` field in roadmap Issue bodies and mirrored in `ROADMAP.md`.
A merged implementation PR may advance the queue, but queue automation must never merge a PR.

## Implementation-agent protocol

When an implementation agent starts:

1. read `codex/prompts/IMPLEMENT.md`;
2. read the assigned `codex` + `ready` Issue;
3. treat the Issue as the authoritative implementation scope;
4. implement and test only that Issue;
5. create a PR referencing the Issue;
6. do not merge the PR.

The implementation agent must not:

- approve its own PR;
- merge its own PR;
- push directly to `main`;
- implement a `blocked` Issue;
- silently choose between unresolved architectural alternatives;
- broaden the Issue merely because adjacent work is convenient.

## Human-decision protocol

If implementation requires a semantic or architectural decision not defined by `SPEC.md`, `AGENTS.md`, or the assigned Issue:

1. stop the ambiguous portion of implementation;
2. mark/report the task as `needs-human` when the integration permits it;
3. explain the exact question;
4. list viable alternatives;
5. explain consequences and compatibility impact;
6. provide a recommendation;
7. do not guess and continue as though the decision were settled.

Examples likely to require explicit design decisions include ambiguous `oneOf`/`anyOf` representation, nullable scalar semantics, and cross-bitness `int64` policy.

## Review protocol

Review is independent from implementation.
The reviewer must read and follow `codex/prompts/REVIEW.md`.
The reviewer must read the PR, and its referenced Issue.

The reviewer must review the PR against the GitHub Issue referenced by the PR.

Review must check both code correctness and Issue-scope correctness.
Tests that encode behavior contrary to `SPEC.md` are not sufficient evidence.
The reviewer must verify the implementation does notexceed the Issue scope.
The reviewer must verify generated VBA semantics, especially object/scalar assignment.
The reviewer must verify that COMPATIBILITY.md claims only behvior demonstrated by tests.

Codex review may recommend or request changes, but only a human may provide the final merge decision for roadmap implementation PRs.

## Merge and queue advancement

A roadmap PR is eligible for human merge only when:

- required CI passes;
- generated-output verification passes;
- review findings are resolved;
- compatibility documentation is accurate;
- required human approval is present.

After merge, queue automation may:

1. close the Issue through the PR's `Closes #...` reference;
2. remove `ready` from any completed item if necessary;
3. find the lowest `Queue-Order` open Issue labeled `codex` and `blocked` whose predecessor is complete;
4. replace `blocked` with `ready`;
5. allow the configured Codex automation to start the next task.

Queue automation must fail safely if it cannot identify exactly one next task.

## Security and secrets

Never commit credentials, tokens, API keys, passwords, or private certificates.
Do not place real service credentials in fixtures.
Use synthetic examples and environment-provided secrets for future integration tests.

## Definition of Done

A feature is complete only when:

- implementation exists in the correct architectural layer;
- required fixture(s) exist;
- automated tests pass;
- generated VBA is verified;
- `COMPATIBILITY.md` is accurate;
- user-facing documentation is updated when behavior changes;
- the PR references and satisfies its Issue;
- review findings are resolved;
- a human has decided to merge.

## Non-goals

Do not:

- rewrite OpenAPI Generator;
- implement HTTP independently in every API class;
- put business logic into Mustache;
- add Excel worksheet/business logic to generated SDKs;
- use manual edits to generated files as the normal repair path;
- turn unsupported OpenAPI constructs into `Variant` merely to avoid making a design decision;
- automatically merge roadmap PRs.
