Read the repository guidance first. Implement Roadmap Phase 2 as a focused PR, preserving the architecture in `AGENTS.md`.

Design the VBA type model before coding, especially nullable scalars and `int64` across 32/64-bit Office. Add fixtures and tests for every mapping and reserved-identifier rule. Do not implement model JSON mapping or advanced OpenAPI composition in this PR. Do not use `Variant` as a blanket escape hatch.
