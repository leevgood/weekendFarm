package leevgood.weekend_farm.mapper;

public interface BaseMapper<E, D> {
    public E toEntity(D dto);
    public D toDto(E entity);
}