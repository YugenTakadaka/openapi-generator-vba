# Codex PR Workflow

Use one Codex task per pull request. Start with the numbered prompt matching the roadmap work. Codex must read the repository instructions first, implement only that PR, run CI-equivalent checks, and prepare a PR description using the repository template.

After implementation, run a separate review task using `prompts/REVIEW.md`. Review does not merge. Fix in-scope findings on the same branch, rerun checks, then request human approval. `main` should be protected in GitHub so CI and review are required before merge.

Recommended loop: Issue/roadmap item → feature branch → Codex implementation → tests/generation → PR → independent Codex review → fixes → CI green → human approval → merge.
