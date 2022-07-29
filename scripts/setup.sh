#!/usr/bin/env bash

set -euo pipefail

PROJECT_DIR="$(git rev-parse --show-toplevel)"

$PROJECT_DIR/scripts/githooks/setup-hooks.sh