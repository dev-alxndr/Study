package me.alxndr.restapi.events;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Alexander Choi
 * @date : 2021/08/02
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
