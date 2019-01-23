package jp.co.soramitsu.iroha.java.detail.router;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


/**
 * Router, which uses pattern matching to simplify processing of complex structures.
 *
 * It is similar to how web servers and their middlewares are defined:
 *
 * @param <T> extract type {@link R} from type {@link T}
 * @param <R> type, which is used in Router.handle
 *
 * for every one item {@link T} processes exactly one item {@link R}
 *
 * <ol>
 * <li>Define lambda, which extracts type {@link R} from type {@link T}</li>
 * <li>Creatr Router</li>
 * <li>Define set of middlewares - functions, which accept {@link T} and return {@link T} (possibly
 * different)</li>
 * <li>Define set of handlers - functions, which assign function-handlers to process type {@link
 * R}</li>
 * <li>Execute {@link Router#process} on type {@link T}</li>
 * </ol>
 * @see jp.co.soramitsu.iroha.java.routers
 */

@RequiredArgsConstructor
public class Router<T, R> {

  private Consumer<T> defaultHandler = c -> {
  };
  private Map<R, Consumer<T>> handlers = new HashMap<>();
  private Function<T, T> middleware = c -> c;

  @NonNull
  private final Function<T, R> getType;


  private void processType(final R type, final T el) {
    handlers.getOrDefault(type, defaultHandler).accept(el);
  }

  public Router use(Function<T, T> m) {
    this.middleware = this.middleware.andThen(m);
    return this;
  }

  public Router handleDefault(Consumer<T> c) {
    this.defaultHandler = c;
    return this;
  }

  public Router handle(R type, Consumer<T> h) {
    handlers.put(type, h);
    return this;
  }

  public void process(final T el) {
    final T t = middleware.apply(el);
    final R type = getType.apply(t);
    processType(type, t);
  }
}
