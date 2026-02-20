.DEFAULT_GOAL := help

APP_NAME := core
BIN_DIR := bin
MAIN := main.go

help: ## Show this help message
	@echo "Available Options:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | \
	awk 'BEGIN {FS = ":.*?## "}; {printf "  %-15s %s\n", $$1, $$2}'

run: ## Run the application
	@go run $(MAIN)

build: ## Build the binary
	@mkdir -p $(BIN_DIR)
	@go build -o $(BIN_DIR)/$(APP_NAME) .

install: ## Install binary to GOPATH/bin
	@go install .

test: ## Run tests
	@go test ./...

fmt: ## Format all Go files
	@go fmt ./...

vet: ## Run go vet
	@go vet ./...

tidy: ## Clean up go.mod and go.sum
	@go mod tidy

clean: ## Remove build artifacts
	@rm -rf $(BIN_DIR)

deps: ## Download dependencies
	@go mod download
