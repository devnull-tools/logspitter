LogSpitter
==========

LogSpitter is a simple tool that generates log messages. It can be useful for testing log configurations on an Application Server (currently, this project aims only JBoss EAP / Wildfly), log management tools or log monitors.

## How to use

Just build the project using Maven:

    $ mvn package

And deploy it on you server. It will expose some REST endpoints that allows to publish a log entry to be forwarded to the log engine. The endpoint are:

    POST: logspitter/{level}
    POST: logspitter/{level}/{category}

The query params are:

- message - the message (mandatory)
- exceptionClass - the exception to throw (optional)

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
