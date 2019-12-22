# API Assignment for SDET

**Author**: Mikhail Miroshnichenko

**Email**: hikari.no.mikem@gmail.com


**Requirements:**
- JDK
- Maven

**Usage:**
- File with links are placed in 'files' folder
- By default app uses "file1.txt" and "file2.txt", but you can change this in source code if needed
- To run test execute `mvn test`from command line (or launch from IDE)
- There are two test sets in project: 'linkSetTest' to process links set from two files and 'frameworkTest' to test framework itself
- To run one test set from project, use `mvn -Dtest=*** test` , where '***' is name of test class (see previous item)

Assumptions:
- Framework will compare only JSONs. If request returned something else (or responce cant ne parsed to JSON), comparison result will be FALSE