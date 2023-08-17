package com.example.demo.service;

import com.example.demo.model.LichHen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ServiceLichHen {
    void save(LichHen lh);

    void update(LichHen lh);

    List<LichHen> getAll();

    LichHen getById(UUID id);

    LichHen getByIdOp(UUID id);

    void deleteById(UUID id);

    boolean existById(UUID id);

    List<LichHen> getByKH(UUID id);

    int deleteByKh(UUID id);

    Page<LichHen> paging(Pageable pageable);

    List<LichHen> findAllAndSort(Sort sort);

    LichHen existsByMa(String ma);

    Page<LichHen> findByStatus(int status , Pageable pageable);

    Page<LichHen> findByType(boolean type , Pageable pageable);

    Page<LichHen> findByStatusAndType(int status ,boolean type, Pageable pageable);

    Page<LichHen> findByName(String keyword, Pageable pageable);
}
