package com.main.ecommerce.service;

import com.main.ecommerce.dao.FaqDao;
import com.main.ecommerce.entity.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatbotServiceImpl implements ChatbotService {

    @Autowired
    private FaqDao faqDao;

    @Override
    public String getAnswer(String userInput) {
        String cleanedInput = normalize(userInput);

        List<Faq> faqs = faqDao.getAllFaqs();
        Map<Faq, Integer> scoredFaqs = new HashMap<>();

        System.out.println("Total FAQs fetched: " + faqs.size());

        // ✅ Check for exact match (case-insensitive, normalized)
        for (Faq faq : faqs) {
            System.out.println("FAQ Question: " + faq.getQuestion());
            String cleanedQuestion = normalize(faq.getQuestion());

            if (cleanedInput.equals(cleanedQuestion)) {
                System.out.println("✅ Exact match found: " + faq.getQuestion());
                return faq.getAnswer();
            }
        }

        // 🔁 If no exact match found, apply similarity scoring
        for (Faq faq : faqs) {
            String cleanedQuestion = normalize(faq.getQuestion());
            int score = calculateSimilarityScore(cleanedInput, cleanedQuestion);
            System.out.println("Comparing to: " + faq.getQuestion() + " => Score: " + score);
            scoredFaqs.put(faq, score);
        }

        // 🔽 Sort by highest similarity score
        List<Map.Entry<Faq, Integer>> sorted = scoredFaqs.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .toList();

        if (!sorted.isEmpty() && sorted.get(0).getValue() > 1) {
            return sorted.get(0).getKey().getAnswer();
        }

        return "Sorry, I couldn't find an answer to that.";
    }

    // Normalize string: lowercase + remove non-alphanumeric chars
    private String normalize(String text) {
        return text.toLowerCase().replaceAll("[^a-z0-9 ]", "").trim();
    }

    // Calculate similarity based on word overlap
    private int calculateSimilarityScore(String input, String question) {
        Set<String> inputWords = new HashSet<>(Arrays.asList(input.split("\\s+")));
        Set<String> questionWords = new HashSet<>(Arrays.asList(question.split("\\s+")));

        inputWords.retainAll(questionWords);
        return inputWords.size();
    }
}










//package com.youtube.ecommerce.service;
//
//import com.youtube.ecommerce.dao.FaqDao;
//import com.youtube.ecommerce.entity.Faq;
//import com.youtube.ecommerce.service.ChatbotService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class ChatbotServiceImpl implements ChatbotService {
//
//    @Autowired
//    private FaqDao faqDao;
//
//    @Override
//    public String getAnswer(String userInput) {
//        String cleanedInput = normalize(userInput);
//
//        List<Faq> faqs = faqDao.getAllFaqs();
//        Map<Faq, Integer> scoredFaqs = new HashMap<>();
//
//        System.out.println("Total FAQs fetched: " + faqs.size());
//        for (Faq faq : faqs) {
//            System.out.println("FAQ Question: " + faq.getQuestion());
//        }
//
//
//        for (Faq faq : faqs) {
//            String cleanedQuestion = normalize(faq.getQuestion());
//            int score = calculateSimilarityScore(cleanedInput, cleanedQuestion);
//            System.out.println("Comparing to: " + faq.getQuestion() + " => Score: " + score);
//            scoredFaqs.put(faq, score);
//        }
//
//
//        // Sort FAQs by highest similarity score descending
//        List<Map.Entry<Faq, Integer>> sorted = scoredFaqs.entrySet().stream()
//                .sorted((a, b) -> b.getValue() - a.getValue())
//                .toList();
//
//        if (!sorted.isEmpty() && sorted.get(0).getValue() > 1) {
//            return sorted.get(0).getKey().getAnswer();
//        }
//
//        return "Sorry, I couldn't find an answer to that.";
//    }
//
//    private String normalize(String text) {
//        // Lowercase and remove special characters except letters and numbers
//        return text.toLowerCase().replaceAll("[^a-z0-9 ]", "").trim();
//    }
//
//    private int calculateSimilarityScore(String input, String question) {
//        // Simple word overlap count
//        Set<String> inputWords = new HashSet<>(Arrays.asList(input.split("\\s+")));
//        Set<String> questionWords = new HashSet<>(Arrays.asList(question.split("\\s+")));
//
//        inputWords.retainAll(questionWords);
//        return inputWords.size();
//    }
//}
