# Codex workflow

This repository uses GitHub Issues as the implementation queue and Pull Requests as the only normal path to `main`.

## Repository responsibilities

- `AGENTS.md` — mandatory agent rules and architecture map.
- `SPEC.md` — target behavior and design specification.
- `COMPATIBILITY.md` — tested support matrix.
- `ROADMAP.md` — ordered implementation phases.
- `codex/prompts/IMPLEMENT.md` — stable implementation-agent instructions.
- `codex/prompts/REVIEW.md` — stable independent-review instructions.
- `codex/issues/*.md` — bootstrap copies of the initial roadmap Issue bodies.
- `.github/workflows/ci.yml` — required verification.
- `.github/workflows/next-task.yml` — promotes the next queued Issue after a merge when no task is already ready.

## GitHub labels

Create these labels in the repository before relying on queue automation:

- `codex`
- `ready`
- `blocked`
- `needs-human`

Recommended semantics:

- `codex` identifies implementation tasks suitable for Codex.
- `ready` means the task may start now.
- `blocked` means a predecessor is incomplete.
- `needs-human` pauses autonomous progress pending a design/product decision.

Normally exactly one open roadmap Issue should be `codex` + `ready`.

## Codex environment

Configure the Codex environment with:

- repository access to this GitHub repository;
- Java 17;
- Maven;
- Git;
- network access sufficient for dependency setup and, when required, official OpenAPI Generator documentation/source inspection.

Do not store service credentials in the repository.

## Implementation automation

Use a stable prompt equivalent to:

```text
Read codex/prompts/IMPLEMENT.md.
Work on the open GitHub Issue labeled codex and ready that is assigned to this run.
Follow AGENTS.md.
Implement and test only that Issue.
Create a Pull Request referencing it with Closes #<issue>.
Do not merge the Pull Request.
```

The orchestration layer should select exactly one ready Issue. If it cannot, the run should stop rather than guess.

## Review automation

Enable Codex code review for Pull Requests and use `codex/prompts/REVIEW.md` as repository-specific review guidance where the product configuration permits it.
The reviewer should compare the PR with its referenced Issue and `SPEC.md`, not only with tests.

## Merge policy

Roadmap implementation PRs require human merge approval.
Codex must not auto-merge them.

After a human merge, `.github/workflows/next-task.yml` promotes the next `codex` + `blocked` Issue by `Queue-Order` if no `codex` + `ready` Issue already exists.

Promotion and execution are deliberately separate responsibilities:

```text
human merge
  -> GitHub queue promotion
  -> next Issue becomes ready
  -> Codex automation observes/receives ready task
  -> implementation PR
  -> CI + independent review
  -> human merge
```

## Bootstrap

The initial roadmap Issues are mirrored in `codex/issues/` so a new repository can recreate the queue without relying on old PR-specific prompt files.
After Issues exist in GitHub, GitHub Issues are authoritative for task scope/status; the mirrored Markdown files are bootstrap/reference material.
