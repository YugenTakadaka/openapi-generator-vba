# VBA OpenAPI Generator Specification

## Goal
Generate usable, maintainable VBA REST API SDKs from OpenAPI 3.x documents.

Desired usage:
```vb
Dim client As New ApiClient
client.BaseUrl = "https://example.com"

Dim api As New UsersApi
Set api.Client = client

Dim user As User
Set user = api.GetUser("123")
Debug.Print user.Name
```

## Components
### API classes
Prefer one class per OpenAPI tag when practical. Expose typed methods, construct request metadata, invoke `ApiClient`, and deserialize responses. Do not implement transport independently.

### Model classes
Generate one class per schema where representable. Preserve OpenAPI types where VBA can express them and provide serialization/deserialization hooks.

### ApiClient
Own base URL, authentication injection, headers, query/path encoding, HTTP execution, timeout configuration, and response handling.

### ApiResponse
Represent at least `StatusCode`, `StatusText`, `Body`, and headers.

### Serialization
JSON object -> Dictionary -> generated model. JSON array -> Collection of model/scalar values. Request model -> Dictionary -> JSON. Keep the JSON engine replaceable.

## Type Mapping Baseline
| OpenAPI | VBA |
|---|---|
| string | String |
| boolean | Boolean |
| integer/int32 | Long |
| number/float | Single |
| number/double | Double |
| date | Date |
| date-time | Date |
| array | Collection |
| object/map | Object (initially) |

`int64` requires an explicit cross-Office design. Nullable scalar semantics must be explicit and must not silently erase `null`.

## Parameters
Target path, query, header, and cookie parameters. JSON request bodies first. Later add multipart/form-data and binary bodies. Serialization styles must only be claimed when implemented/tested.

## Responses
Target no-content, scalar, model, scalar array, model array, and binary responses.

## Authentication
Target Bearer, API key/header, and Basic. OAuth access-token acquisition should be externally pluggable; generated SDKs should not force interactive OAuth ownership.

## Composition
`oneOf`, `anyOf`, `allOf`, discriminators, and polymorphism are explicit design work. Do not collapse them to `Variant` merely to claim support.

## Compatibility Rule
Unsupported OpenAPI behavior must never be silently interpreted as supported. Prefer generation-time diagnostics over a client with wrong wire semantics.
