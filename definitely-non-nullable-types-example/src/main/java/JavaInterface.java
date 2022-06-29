import org.jetbrains.annotations.NotNull;

public interface JavaInterface<T> {
    void put(@NotNull T value);
    @NotNull T get(int index);
}
