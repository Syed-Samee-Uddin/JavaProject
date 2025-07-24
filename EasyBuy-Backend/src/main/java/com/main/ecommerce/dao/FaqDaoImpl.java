package com.main.ecommerce.dao;

import com.main.ecommerce.entity.Faq;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class FaqDaoImpl implements FaqDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Faq> getAllFaqs() {

        List<Faq> faqs = entityManager.createQuery("FROM Faq", Faq.class).getResultList();
        System.out.println("Fetched FAQs from DB: " + faqs.size()); // Add this line
        return faqs;

//        return entityManager.createQuery("FROM Faq", Faq.class).getResultList();

    }
}
