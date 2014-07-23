fluentizer
==========

Java library to ease fluent apis development
(Work in progress)

```
        describe("using fluentizer", () -> {

            context().fluentizer(() -> Fluentizer.create());

            describe("a traditional interface can be made fluent", () -> {

                context().traditionalApi(() -> mock(TraditionalApiExample.class));
                context().fluentApi(()-> {
                    FluentizerApi fluentizer = context().fluentizer();
                    TraditionalApiExample traditionalApi = context().traditionalApi();
                    return fluentizer.expressAs(FluentApiExample.class, traditionalApi);
                });

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
