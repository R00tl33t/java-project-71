.PHONY: run-dist run-example run-example-wsl report run-example2 run-example2-wsl

run-dist:
	./build/install/app/bin/app "$(file1)" "$(file2)" --format "$(format)"

run-example:
	$(MAKE) run-dist \
		file1="/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file1.json" \
		file2="/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file2.json" \
		format="json"

run-example-wsl:
	$(MAKE) run-dist \
		file1="/mnt/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file1.json" \
		file2="/mnt/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file2.json" \
		format="json"

run-example2:
	$(MAKE) run-dist \
		file1="/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file1.yml" \
		file2="/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file2.yml" \
		format="yml"

run-example2-wsl:
	$(MAKE) run-dist \
		file1="/mnt/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file1.yml" \
		file2="/mnt/c/Users/Admin/MyDocs/JavaProjects/java-project-71/app/src/test/resources/file2.yml" \
		format="yml"
