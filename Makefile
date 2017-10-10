top: 
	javac -d classfiles -sourcepath src src/main/kruskal.java

clean:
	rm -f /classfiles/* .class

test:
	@echo testing
	java -classpath classfiles/ kruskal -r 0 testfile