#!/usr/bin/env bash

set -euo pipefail

PROJECT_DIR="$(git rev-parse --show-toplevel)"

# Setup git hooks
$PROJECT_DIR/scripts/githooks/setup-hooks.sh

# Setup fastlane
bundle install