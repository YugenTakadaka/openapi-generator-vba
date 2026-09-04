# Codex Pull Request Review

Perform an independent review. Do not assume the implementation agent's summary is correct.

Read:

- `AGENTS.md`;
- `SPEC.md`;
- `COMPATIBILITY.md`;
- `ROADMAP.md`;
- the Pull Request description and diff;
- the GitHub Issue referenced by the Pull Request.

The Issue defines the intended scope and acceptance criteria.

## Review dimensions

Review at least:

1. **Scope correctness**
   - Does the PR implement the referenced Issue and only that cohesive slice?
   - Did unrelated future-roadmap behavior leak in?

2. **OpenAPI semantics**
   - Does generated behavior preserve the OpenAPI meaning claimed by the Issue?
   - Are unsupported semantics rejected/warned rather than silently misrepresented?

3. **VBA correctness**
   - `Option Explicit`;
   - correct `Set` use for object assignment;
   - no `Set` for scalars;
   - valid property/function declarations;
   - reserved identifiers handled;
   - 32/64-bit assumptions documented/tested where relevant.

4. **Architecture**
   - semantic decisions belong in `VbaClientCodegen` or Runtime, not complex Mustache conditionals;
   - API classes do not duplicate HTTP transport;
   - models do not perform HTTP;
   - generated output is not being hand-patched.

5. **Tests**
   - acceptance criteria are demonstrated by automated tests where feasible;
   - fixtures exercise production generator paths;
   - tests do not merely assert accidental current text;
   - generated output is deterministic where required.

6. **Compatibility documentation**
   - `COMPATIBILITY.md` claims no more than tests demonstrate;
   - limitations are explicit.

7. **Security and robustness**
   - no secrets/real credentials;
   - errors are not silently swallowed;
   - malformed/unsupported input fails predictably.

## Validation

Run the relevant test suite when the review environment permits it, normally:

```bash
mvn verify
```

Use focused generation checks as needed.

A green test suite is evidence, not proof of specification correctness.
If a test encodes behavior inconsistent with `SPEC.md`, report the inconsistency.

## Findings

Prioritize actionable findings by severity:

- Blocking — incorrect semantics, data/security risk, build unusable, or Issue cannot be considered complete;
- High — significant correctness/compatibility defect;
- Medium — meaningful maintainability/test/edge-case defect that should be fixed before merge;
- Low — optional improvement or polish.

Reference concrete files/lines and explain the failure mode.

## Verdict

Return one of:

- `PASS` — no Blocking/High/Medium findings remain and acceptance criteria are adequately demonstrated;
- `CHANGES_REQUESTED` — one or more Blocking/High/Medium findings remain;
- `NEEDS_HUMAN` — correctness depends on an unresolved product/architecture decision.

Do not merge the Pull Request.
