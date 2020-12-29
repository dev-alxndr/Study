package io.alxndr.jpashop.service;

import io.alxndr.jpashop.domain.item.Item;
import io.alxndr.jpashop.domain.item.Movie;
import io.alxndr.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void 전체조회() throws Exception {
        // given
        Movie movie1 = new Movie();
        movie1.setName("노인을 위한 나라는 없다.");
        Movie movie2 = new Movie();
        movie2.setName("어벤져스");
        Movie movie3 = new Movie();
        movie3.setName("인터스텔라");

        // when
        itemService.saveItem(movie1);
        itemService.saveItem(movie2);
        itemService.saveItem(movie3);

        // then
        List<Item> items = itemService.findItems();
        assertEquals(items.size(), 3);
    }

    @Test
    public void 단건조회() throws Exception {
        // given
        Movie movie1 = new Movie();
        movie1.setName("노인을 위한 나라는 없다.");
        Movie movie2 = new Movie();
        movie2.setName("어벤져스");
        Movie movie3 = new Movie();
        movie3.setName("인터스텔라");

        // when
        itemService.saveItem(movie1);
        itemService.saveItem(movie2);
        itemService.saveItem(movie3);

        // then
        assertEquals(movie1, itemService.findOne(movie1.getId()));
    }

    @Test
    public void 저장() throws Exception {
        // given
        Movie movie1 = new Movie();
        movie1.setName("노인을 위한 나라는 없다.");
        // when
        itemService.saveItem(movie1);
        // then
        assertNotNull(movie1.getId());
    }

}