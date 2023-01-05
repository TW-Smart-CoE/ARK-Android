#!/usr/bin/env bash

set -euo pipefail

PROJECT_DIR="$(git rev-parse --show-toplevel)"
SOURCE_HOOK_DIR="$PROJECT_DIR/scripts/githooks/hooks"
TARGET_HOOK_DIR="$PROJECT_DIR/.git/hooks"

create_hooks() {
  echo "Linking $1 hook"

  ln -sf "$SOURCE_HOOK_DIR/$1" "$TARGET_HOOK_DIR/$1"
}

hooks=(
  pre-commit
  commit-msg
  pre-push
)

for hook in ${hooks[@]}
do
  create_hooks $hook
done
