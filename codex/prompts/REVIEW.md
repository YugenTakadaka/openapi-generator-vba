Read AGENTS.md, SPEC.md, COMPATIBILITY.md, ROADMAP.md, and the pull request diff before reviewing.

Act as an independent reviewer. Do not merge the PR and do not broaden its scope.

Review in this order:
1. Does the diff stay within the stated PR scope?
2. Is behavior consistent with SPEC.md and the pinned OpenAPI Generator version?
3. Are OpenAPI-to-VBA semantic decisions in Java rather than duplicated in Mustache where practical?
4. Are VBA object/scalar assignment, identifiers, types, Option Explicit, and 32/64-bit concerns correct for the affected code?
5. Are generated outputs deterministic and free of hand-patched fixes?
6. Does every new support claim have a fixture and automated generated-code assertions?
7. Does COMPATIBILITY.md claim only demonstrated behavior?
8. Are HTTP/JSON/runtime responsibilities correctly separated?
9. Are errors preserved rather than silently swallowed?
10. Do `mvn verify` and representative generation pass?

Report findings by severity: Blocker, Major, Minor, Nit. For each finding give file/location, why it matters, and the smallest in-scope fix. Distinguish pre-existing/out-of-scope observations from PR regressions.

If there are no blocking findings, explicitly say the PR is review-ready, but do not merge it. End with any recommended follow-up PRs that should remain out of scope.
