package api.carreras.events.domain;

import java.util.function.Consumer;

import api.carreras.shared.domain.Logger;

/* base on
 * https://howtocodetutorial.wordpress.com/generic-builder-pattern-in-java-8/
 */
public class Builder<T> {
    private T instance;

    public Builder(Class<T> clazz) {
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            Logger.log(e.getMessage());
            e.printStackTrace();
        }
    }

    public Builder<T> with(Consumer<T> setter) {
        setter.accept(instance);

        return this;
    }

    public T build() {
        return instance;
    }

    public static <T> Builder<T> set(Class<T> clazz) {
        return new Builder<>(clazz);
    }
}