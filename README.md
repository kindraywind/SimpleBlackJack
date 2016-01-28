# SimpleBlackJack
####Command line simple version of 2 players blackjack
-
This software is a part of the Software Engineering II 's final report at Keio University.


The report is to create a test suite by applying combination model based testing technique.

##Usage

The software can be executed and tested via `maven` or IDE such as `Eclipse`, `NetBean` or `Intellij`.
>For Maven:
>
>1.	Go to the project directory.
>2.	```mvn clean compile```
>3.	```mvn exec:java -Dexec.mainClass="com.Main"```

To run the unittests type: ```mvn clean test```

-

There are 45 unit-tests. 21 of them are for making sure that the software will work fine. And another 24 tests are created by using NVDA table.All 24 tests are in src/test/java/com/Game/BlackJackTest.java starting from TestV1_1 (Variant1 testcase1) to TestV5_7 (Variant5 testcase7).

##License
SimpleBlackJack is available under the MIT license. See the LICENSE file for more info.
