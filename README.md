CorgFu
======

CMPUT 301 Team Project
----------------------

Contents
=======

* [Description](#description)
* [References](#references)
* [Licence](#licence)
* [Glossary](/CorgFu.wiki/Glossary.md)
* [UML](/App/doc/CorgFu_UML_diagram.png)
* [Meeting Notes](/CorgFu.wiki/Meeting Notes.md)
* [Refactoring](https://github.com/CMPUT301F14T05/CorgFu/wiki/Refectoring)

Description
=======

What's worse than having a question only to realize Ask Jeeves no longer provides the answers we are looking for? At CorgFu we decided enough is enough and began developing CorgStack - Android question and answer application. When you have a question you simply submit it to the app, which then allows all other users to see your question and give you the answers you need. Staying true to the expression a picture says a thousand words you can attach pictures to your questions and answer. If you dont understand a question, or even an answer, you can reply to the question to gain clarification. We are also implementing an up-vote counter so you can see questions that other users find helpful. Obviously there is no down-vote button, as there is no such thing as a bad question.
 
References
=======

In our testcases (UC: 07-09) we used an automated class BogoPicGen.java to 
generate mock pictures to help test how we handled images. 
BogoPicGen.java is directly imported in our code and can be found at:

    https://github.com/CMPUT301F14T05/CorgFu/blob/master/CorgFuAppTestingTest/src/ca/ualberta/cs/corgfuapp/test/BogoPicGen.java

The code (authored by Abram Hindle) was directly taken from his github at the following site:
   
    https://github.com/abramhindle/BogoPicGen/blob/master/src/es/softwareprocess/bogopicgen/BogoPicGen.java 

For the elasticsearch portion of this poject we based our code off of Victor Guana's code for a movie system that used elastic search. The code is available at:
    https://github.com/guana/elasticsearch

Licence
=======

Copyright 2014 Alex Makepeace, Wyatt Fleming, Ahmed Beshry, Devon Sigurdson,
	       Anthony Wu, and Oleksii Shevchenko

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
