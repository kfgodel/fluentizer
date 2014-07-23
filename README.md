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
