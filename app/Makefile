.PHONY: run-dist run-example run-example-wsl

run-dist:
	./build/install/app/bin/app "$(file1)" "$(file2)" --format "$(format)"

run-example:
	$(MAKE) run-dist \
		file1="/c/Users/Admin/MyDocs/JavaProjects/java-project-71/file1.json" \
		file2="/c/Users/Admin/MyDocs/JavaProjects/java-project-71/file2.json" \
		format="json"

run-example-wsl:
	$(MAKE) run-dist \
		file1="/mnt/c/Users/Admin/MyDocs/JavaProjects/java-project-71/file1.json" \
		file2="/mnt/c/Users/Admin/MyDocs/JavaProjects/java-project-71/file2.json" \
		format="json"