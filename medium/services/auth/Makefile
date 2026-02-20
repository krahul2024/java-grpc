.DEFAULT_GOAL := help

MVN := mvn

help: ## Show this help message
	@echo "Available Options:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | \
	awk 'BEGIN {FS = ":.*?## "}; {printf "  %-15s %s\n", $$1, $$2}'

compile: ## Compile the project
	$(MVN) clean compile

run: ## Compile and run the application
	$(MVN) clean compile exec:java

package: ## Build the JAR file
	$(MVN) clean package

install: ## Install artifact to local Maven repository
	$(MVN) clean install

test: ## Run tests
	$(MVN) test

clean: ## Clean target directory
	$(MVN) clean

dep-tree: ## Show dependency tree
	$(MVN) dependency:tree
