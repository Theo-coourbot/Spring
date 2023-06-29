package com.tpAnnonce.annonce.Repository;

import com.tpAnnonce.annonce.entity.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementRepository extends CrudRepository<Announcement,Integer> {
}
