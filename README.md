# API Assignment for SDET

**Author**: Mikhail Miroshnichenko

**Email**: hikari.no.mikem@gmail.com

**Requirements:**
- JDK
- Maven

**Test scenario**

A. Check links from files
1. Get links pairs from two text files (included into project)
2. Send http requests with these links
3. Get responce
4. If responses are JSONs, then compare it


B. Self testing
There are list of tests, that verify comparasion library working with different surce data

**Usage:**
- File with links are placed in 'files' folder
- By default app uses "file1.txt" and "file2.txt", but you can change this in source code if needed
- To run test execute `mvn test`from command line (or launch from IDE)
- There are two test sets in project: 'linkSetTest' to process links set from two files and 'frameworkTest' to test framework itself
- To run one test set from project, use `mvn -Dtest=*** test` , where '***' is name of test class (see previous item)

**Assumptions:**
- Framework will compare only JSONs. If request returned something else (or response cant be parsed to JSON), comparison result will be FALSE

**Note:**
- No multi-threaded execution. Sorry.
