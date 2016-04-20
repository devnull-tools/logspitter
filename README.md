LogSpitter
==========

LogSpitter is a simple tool that generates log messages. It can be useful for testing log configurations on an Application Server (currently, this project aims only JBoss EAP 6 / AS 7), log management tools or log monitors.

## How to use

Just build the project using Maven:

    $ mvn package

And deploy it on you server. It will expose a REST endpoint that allows to publish a log entry to be forwarded to the log engine. The endpoint is:

    POST: logspitter/log/{level}

The query params are:

- message - the message
- category - the category of the message (optional)
- exceptionClass - the exception to throw (optional)

## Logging profiles

The LogSpitter Maven Build has a profile that maps LogSpitter to a [Logging Profile][logging-profile] named **logspitter**, you can define a logging profile and test it using LogSpitter. The profile id is **logging-profile** and the `cli/logging-profile.cli` file contains a JBoss CLI script that creates this logging profile in standalone mode (for domain mode, just add the desired profile before the `/subsystem`).

[logging-profile]: https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.1/html/Administration_and_Configuration_Guide/Example_Logging_Profile_Configuration.html

## How it works

A log entry has four attributes:

- level
- category
- message
- exception

The exception is totally optional and, if present, means that the log entry represents a thrown exception. LogSpitter can create this exception at runtime (even if it is not on its classpath) and pass it to the log engine.

The endpoint receives this four attributes as parameters and the bash script has options to configure them. The defaults are:

- level: INFO
- category: tools.devnull.logspitter
- message: "" (an empty message)
- exception: none

Behind the scenes, there is a DSL exposed by a set of interfaces that forwards an entry to the log engine. The DSL starts with the `LogSpitter` interface and ends on any `void` method. Check the javadocs and the sources for more information about the code.
