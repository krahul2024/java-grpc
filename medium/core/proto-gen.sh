#!/usr/bin/env bash
set -e  # exit on the first error

BASE_DIR="$(cd "$(dirname "$0")"; pwd)"
PROTO_DIR="$BASE_DIR/proto"
JAVA_PROJECT_DIR="$BASE_DIR/services/math"
GO_PROJECT_DIR="$BASE_DIR/core"

echo "Java gRPC/protobuf classes..."
cd "$JAVA_PROJECT_DIR"
mvn clean generate-sources
echo "Java classes generated at: $JAVA_PROJECT_DIR/target/generated-sources/protobuf/java/"

echo "Go gRPC/protobuf classes..."
cd "$GO_PROJECT_DIR"

# Make sure protoc-gen-go and protoc-gen-go-grpc are installed
command -v protoc-gen-go >/dev/null 2>&1 || { echo >&2 "protoc-gen-go is not installed. Run 'go install google.golang.org/protobuf/cmd/protoc-gen-go@latest'"; exit 1; }
command -v protoc-gen-go-grpc >/dev/null 2>&1 || { echo >&2 "protoc-gen-go-grpc is not installed. Run 'go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest'"; exit 1; }

protoc \
  --proto_path="$PROTO_DIR" \
  --go_out=. \
  --go-grpc_out=. \
  "$PROTO_DIR"/*.proto

echo "Go proto-buf stuff generated at: $GO_PROJECT_DIR/"
