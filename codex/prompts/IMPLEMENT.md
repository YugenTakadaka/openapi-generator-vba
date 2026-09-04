# Codex Implementation Task

Read `AGENTS.md` before doing anything.

Then read:

- `SPEC.md`;
- `COMPATIBILITY.md`;
- `ROADMAP.md`;
- the GitHub Issue assigned to this task.

The assigned GitHub Issue is the authoritative scope and acceptance criteria for this implementation.

## Task selection

Work only on an open Issue labeled both `codex` and `ready`, or on the exact Issue explicitly assigned by the orchestration layer.
Do not work on `blocked` or `needs-human` Issues.
If task selection is ambiguous, stop and report the ambiguity rather than choosing arbitrarily.

## Before implementation

1. Inspect the current repository.
2. Inspect relevant production code and tests.
3. Verify the OpenAPI Generator APIs actually available in the version pinned by `pom.xml`.
4. Compare the Issue with `SPEC.md` and `COMPATIBILITY.md`.
5. Produce a short implementation plan.

Do not modify files before understanding the existing architecture and the Issue's acceptance criteria.

## Implementation

Implement only functionality requested by the assigned Issue.

Preserve the architecture:

```text
OpenAPI
  -> VbaClientCodegen semantic transformation
  -> prepared Mustache context
  -> Mustache presentation
  -> generated VBA
```

Keep transport/serialization infrastructure in Runtime rather than duplicating it in API templates.
Do not implement later roadmap items unless the current Issue explicitly requires a prerequisite-sized portion of them.

Never manually patch generated output as the primary fix.
Fix generator Java code, templates, Runtime source, or tests/fixtures as appropriate.

## Testing

Add automated tests demonstrating the requested behavior.
When applicable, add a focused OpenAPI fixture.

Run:

```bash
mvn verify
```

Also run any focused generation or verification commands required by the Issue.
Inspect generated VBA for VBA-specific correctness, including `Set` semantics and declarations.

If tests cannot be executed, report exactly why and do not claim the feature is verified.

## Documentation

Update `COMPATIBILITY.md` only for functionality demonstrated by tests.
Update `SPEC.md` only if the assigned Issue explicitly authorizes a specification change.
Update user-facing documentation when invocation or generated-client behavior changes.

## Ambiguity / human decision

If an architectural or semantic decision is required and is not defined by `AGENTS.md`, `SPEC.md`, or the Issue, do not guess.

Report:

- the exact question;
- viable alternatives;
- compatibility consequences;
- implementation consequences;
- your recommendation.

Mark/report `needs-human` when the integration supports Issue updates, and stop the ambiguous portion of work.

## Completion

Create a Pull Request that references the assigned Issue using:

```text
Closes #<issue-number>
```

The PR description must include:

- implementation summary;
- files changed;
- acceptance-criteria status;
- tests added;
- tests executed and results;
- compatibility changes;
- known limitations;
- human decisions, if any;
- out-of-scope items deliberately not implemented.

Do not approve or merge the Pull Request.
