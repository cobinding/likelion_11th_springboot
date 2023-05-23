package likelion.springbootbobae.service;

import likelion.springbootbobae.domain.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ItemService {
    @Transactional
    public Item save(Item item);

    @Transactional(readOnly = true)
    public List<Item>  findAll();

    @Transactional(readOnly = true)
    public Item findById(Long id);

    @Transactional
    public void update(Long id, Item item);
}

/*
 * JpaRepository: db랑 상호작용하기 위한 CRUD 작업 메서드 제공
 * - findById: 식별자(id)를 활용하여 db에서 entity를 찾고, 주어진 Id에 해당하는 단일 엔티티를 반환한다.
 * - findAll: db의 모든 엔티티를 조회. db의 모든 기록을 가져와서 ❗️entity 객체의 컬렉션❗️으로 반환
 *
 * */