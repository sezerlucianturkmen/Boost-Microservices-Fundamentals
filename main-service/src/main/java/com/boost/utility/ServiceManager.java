package com.boost.utility;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public class ServiceManager<T, ID> implements IService<T, ID> {
    /**
     * MusteriRepository
     * UrunRepository
     * SatisRepository
     * @param t
     * @return
     */
    private final ElasticsearchRepository<T, ID> repository;

    public ServiceManager(ElasticsearchRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }
    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        return repository.saveAll(t);
    }
    @Override
    public T update(T t) {
        return repository.save(t);
    }
    @Override
    public void delete(T t) {
        repository.delete(t);
    }
    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    @Override
    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public Iterable<T> findAll() {
        return repository.findAll();
    }
}