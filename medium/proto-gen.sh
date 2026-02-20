#!/bin/bash

set -e  # exit on a command failure

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # no Color

echo -e "${BLUE}  Starting Proto Generation Process${NC}"
echo ""

# Define paths
GO_GRPC_DIR="core/grpc"
JAVA_GRPC_DIR="services/auth/src/main/java/com/auth/grpc"
JAVA_AUTH_DIR="services/auth"

# Clean old generated files
echo -e "${YELLOW}[1/3] Cleaning old generated files...${NC}"

if [ -d "$GO_GRPC_DIR" ]; then
    echo -e "  - Deleting: ${PWD}/${GO_GRPC_DIR}"
    rm -rf "$GO_GRPC_DIR"
    echo -e "  ${GREEN}✓${NC} Go grpc directory deleted"
fi

# Clean up any .pb.go files in core directory
if ls core/*.pb.go 1> /dev/null 2>&1; then
    echo -e "  - Deleting old .pb.go files in core/"
    rm -f core/*.pb.go
    echo -e "  ${GREEN}✓${NC} Old .pb.go files deleted"
fi

if [ -d "$JAVA_GRPC_DIR" ]; then
    echo -e "  - Deleting: ${PWD}/${JAVA_GRPC_DIR}"
    rm -rf "$JAVA_GRPC_DIR"
    echo -e "  ${GREEN}✓${NC} Java grpc directory deleted"
else
    echo -e "  - Java grpc directory not found (skipping)"
fi

echo ""

# Generate Go files
echo -e "${YELLOW}[2/3] Generating Go protobuf files...${NC}"
echo -e "  - Proto source: proto/auth.proto"
echo -e "  - Output directory: ${PWD}/${GO_GRPC_DIR}"

# Create the directory
mkdir -p "$GO_GRPC_DIR"

# Generate directly into the grpc directory
protoc --proto_path=proto \
  --go_out="$GO_GRPC_DIR" \
  --go_opt=paths=source_relative \
  --go-grpc_out="$GO_GRPC_DIR" \
  --go-grpc_opt=paths=source_relative \
  proto/auth.proto

if [ $? -eq 0 ]; then
    echo -e "  ${GREEN}✓${NC} Go files generated successfully!"
    # List generated files
    if [ -d "$GO_GRPC_DIR" ] && [ "$(ls -A $GO_GRPC_DIR)" ]; then
        echo -e "  Generated files:"
        ls -1 "$GO_GRPC_DIR" | sed 's/^/    - /'
    fi
else
    echo -e "  ${RED}✗${NC} Go generation failed!"
    exit 1
fi

echo ""

# Generate Java files using Maven
echo -e "${YELLOW}[3/3] Generating Java protobuf files using Maven...${NC}"
echo -e "  - Working directory: ${PWD}/${JAVA_AUTH_DIR}"
echo -e "  - Output directory: ${PWD}/${JAVA_GRPC_DIR}"

cd "$JAVA_AUTH_DIR"

mvn protobuf:compile protobuf:compile-custom -q

if [ $? -eq 0 ]; then
    echo -e "  ${GREEN}✓${NC} Java files generated successfully!"
    cd ../..
    # List generated files
    if [ -d "$JAVA_GRPC_DIR" ] && [ "$(ls -A $JAVA_GRPC_DIR)" ]; then
        echo -e "  Generated files:"
        ls -1 "$JAVA_GRPC_DIR" | sed 's/^/    - /'
    fi
else
    echo -e "  ${RED}✗${NC} Java generation failed!"
    cd ../..
    exit 1
fi

echo ""
echo -e "${GREEN}  ✓ All proto files generated successfully!${NC}"
