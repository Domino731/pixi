package utils.vectors;

public class DimensionVector<T> {
    public T width;
    public T height;

    public DimensionVector(T width, T height) {
        this.width = width;
        this.height = height;
    }

    public DimensionVector(T size) {
        this.width = size;
        this.height = size;
    }
}