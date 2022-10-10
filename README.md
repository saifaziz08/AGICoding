# Getting Started

### Installation
This requires Java 8

### Start
Go into the AGICoding directory

`java -jar build/libs/AGICoding-0.0.1-SNAPSHOT.jar`

### API Guide
Please consider the following sections:
* [Are Same CSVs] http://localhost:8080/areSameCSV?string1=a,b,c&string2=c,b,a
* [CSV Union] http://localhost:8080/csvUnion?string1=a,b,c&string2=c,b,a,d
* [CSV Intersection] http://localhost:8080/csvIntersection?string1=a,b,c&string2=c,b,a,d
* [Inflationary] http://localhost:8080/inflationary?string1=Anyone up for tennis?
* [Is Anagrams] http://localhost:8080/areAnagrams?string1=A decimal point&string2=I'm a dot in place
* [Is Palindrome] http://localhost:8080/isPalindrome?string1=A man, a plan, a canal, Panama!
* [Spoonerism] http://localhost:8080/spoonerism?string1=pig&string2=sick
* [Inflationary] http://localhost:8080/inflationary?string1=Anyone up for tennis?
* [Fizz Buzz] http://localhost:8080/fizzBuzz?string1=16

### Additional Endpoint
This endpoint is added to get results in one shot.

* [combineResults] http://localhost:8080/combineResults with body as [{'operation': 'Are same CSVs' , 'input1': 'a,b,c', 'input2': 'c,b,a' }, {'operation': 'CSV Union', 'input1': 'a,b,c,d', 'input2': 'a,b,c'} ...]