package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 멀티 쓰레드 환경에서 여러개가 동시에 접근할 떄 hashMap을 사용하면 안된다.
    // ConcurrentHashMap을 사용해야한다. 동시에 접근하면 값이 꼬일 수 있기 때문
    // item의 id가 long 타입이어서
    private static final Map<Long, Item> store = new HashMap<>();  // static
    private static long sequence = 0L;  // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
