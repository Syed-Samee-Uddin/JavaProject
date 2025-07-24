package com.main.ecommerce.service;

import com.main.ecommerce.dao.FaqDao;
import com.main.ecommerce.entity.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqDao faqDao;

    @Override
    public List<Faq> getAllFaqs() {
        return faqDao.getAllFaqs();
    }
}
