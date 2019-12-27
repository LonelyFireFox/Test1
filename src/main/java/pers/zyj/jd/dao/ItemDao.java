package pers.zyj.jd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.zyj.jd.bean.Item;


public interface ItemDao  extends JpaRepository<Item,Long> {
}
