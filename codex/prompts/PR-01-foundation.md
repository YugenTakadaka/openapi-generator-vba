Read `AGENTS.md`, `SPEC.md`, `COMPATIBILITY.md`, and `ROADMAP.md` before making changes.

Implement **Phase 1 only**.

Goals:
- Make the custom `vba` generator build successfully and reproducibly.
- Ensure OpenAPI Generator can discover it using `-g vba`.
- Add/use a minimal OpenAPI 3.x fixture.
- Generate at least one API class and one model class.
- Add automated tests covering generator discovery and generated output.
- Make generation deterministic.

Do not implement later roadmap phases unless strictly required for the foundation.

Before changing code:
1. Inspect the current repository.
2. Inspect the actual OpenAPI Generator APIs for the version pinned by `pom.xml`.
3. Write a short implementation plan in your response/work log.

Then implement it. Run all relevant tests before finishing. Never manually patch generated output to make a test pass; fix `VbaClientCodegen` or templates. Update `COMPATIBILITY.md` only for behavior demonstrated by tests.

At completion report: files changed, design decisions, tests executed/results, unsupported behavior discovered, and the recommended next PR.
