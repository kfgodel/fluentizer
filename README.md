fluentizer
==========

Java library to ease fluent apis development
(Work in progress)

```
        describe("using fluentizer", () -> {

            .... setup...

            describe("a traditional interface can be made fluent", () -> {

                ... setup...

                it("joining two method calls with one arg each, into one method call with two args", ()->{

                    context().fluentApi().everyExampleHas("Hello").and("World");

                    verify(context().traditionalApi()).everyExampleHasAnd("Hello","World");
                });

                it("joining no arg method calls to chain decisions", ()->{
                    context().fluentApi().red().andBlue().allowsOnlyViolet();
                    context().fluentApi().red().andYellow().allowsOnlyOrange();

                    verify(context().traditionalApi()).redAndBlueAllowsOnlyViolet();
                    verify(context().traditionalApi()).redAndYellowAllowsOnlyOrange();
                });

            });
        });
```

### Maven dependency ###

* Add this repository to your pom:  
```
#!xml
    <repository>
      <id>kfgodel_mosquito</id>
      <name>Repo Mosquito</name>
      <url>http://kfgodel.info:8081/nexus/content/groups/public/</url>
    </repository>
```

* Declare the dependency
```
#!xml

<dependency>
  <groupId>ar.com.dgarcia</groupId>
  <artifactId>fluentizer</artifactId>
  <version>1.0</version>
</dependency>
```