# Software-Engineering

Semester project for the course CSE/MYY803 - Software Engineering, Department of Computer Science and Engineering.

# Application

The application is called Advanced Text To Speech. It converts a document, in particular word and excel, into speech (its content). The implementation is based on the open source [FreeTTS](https://freetts.sourceforge.io/) library for the transformation.

# Design and Implementation

It has been designed with various pattern methods, such as GoF, to be maintainable. Thus, we can extend the application so it can support more document formats, such as .txt or .pdf. Another feuture that could be added, is more encoding strategies for the documents, without extensive changes to the implementation.
Another feature of the application is its usability by a user. The gui format is simple, so the user can interact with the application easily. The gui was designed with the [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/) library.
The design of the software is based to some functional requirements that are called the user stories. For more details go to chapter Sprint's Backlog of the [Sprint Report](https://github.com/GeoKrom/Software-Engineering-course/blob/main/SprintReport.pdf).
The application has also automated tests for checking the functionality of the system with the [JUnit 4](https://junit.org/junit4/).

# Java version
The system was created using the [java version](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html) jdk-14.0.
The application that was used is [eclipse](https://www.eclipse.org/downloads/) for java developers.

# External Resources
Apart from the main libraries, it has been also used the [apache poi 5.0.0](https://poi.apache.org/) library for opening the documents as .jar files.
