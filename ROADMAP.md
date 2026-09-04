# Roadmap

## Phase 1 — Generator Foundation
- Build succeeds reproducibly.
- Custom generator discovery works with `-g vba`.
- Minimal OpenAPI 3.x fixture generates at least one API and model.
- Output is deterministic.
- Automated generator tests exist.

## Phase 2 — VBA Type System
- scalar mappings
- object vs scalar assignment
- nullable design
- int64 / 32-bit vs 64-bit Office
- arrays and maps
- enums
- VBA reserved identifiers and safe naming

## Phase 3 — Models
- typed properties
- `FromDictionary`
- `ToDictionary`
- nested models
- collections of models

## Phase 4 — HTTP Operations
- path/query/header/cookie parameters
- JSON request body
- status handling
- no-content responses
- typed responses

## Phase 5 — Authentication
- Bearer
- API key
- Basic
- externally supplied OAuth access token

## Phase 6 — Advanced OpenAPI
- multipart
- binary upload/download
- serialization styles
- additionalProperties
- composition (`oneOf`/`allOf`/`anyOf`) after explicit design

## Phase 7 — Distribution
- reproducible release artifact
- example generated SDK
- VPM-compatible package layout
- installation/import documentation
