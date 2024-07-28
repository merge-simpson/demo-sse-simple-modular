rootProject.name = "demo-sse-springboot3-modular"

// category ────────────────────────────────────────────────────────────────────────────────────────────────────────────
include(
        "common",
        "core-modules",
        "services",
)

// core module ─────────────────────────────────────────────────────────────────────────────────────────────────────────
include(
        "core-modules:letsdev-webmvc-sse-core",
)
findProject(":core-modules:letsdev-webmvc-sse-core")?.name = "letsdev-webmvc-sse-core"

// service module ──────────────────────────────────────────────────────────────────────────────────────────────────────
include(
        "services:inquiry-service",
        "services:demo-simple-sse-standalone",
        "services:demo-single-tag-sse-standalone",
)
findProject(":services:inquiry-service")?.name = "inquiry-service"
findProject(":services:demo-simple-sse-standalone")?.name = "demo-simple-sse-standalone"
findProject(":services:demo-single-tag-sse-standalone")?.name = "demo-single-tag-sse-standalone"

// internal module ─────────────────────────────────────────────────────────────────────────────────────────────────────
include(
        "services:inquiry-service:api",
        "services:inquiry-service:api:domain",
        "services:inquiry-service:api:exception",
        "services:inquiry-service:api:readmodel",
)
findProject(":services:inquiry-service:api")?.name = "inquiry-api" // last label of module name should be unique.
findProject(":services:inquiry-service:api:domain")?.name = "inquiry-domain"
findProject(":services:inquiry-service:api:exception")?.name = "inquiry-exception"
findProject(":services:inquiry-service:api:readmodel")?.name = "inquiry-readmodel"

// driving module ──────────────────────────────────────────────────────────────────────────────────────────────────────

// driven module ───────────────────────────────────────────────────────────────────────────────────────────────────────

// unordered module ────────────────────────────────────────────────────────────────────────────────────────────────────
