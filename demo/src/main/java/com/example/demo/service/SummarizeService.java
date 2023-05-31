package com.example.demo.service;


import jakarta.persistence.Tuple;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.Token;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class SummarizeService {
    private final Komoran komoran;
    private final int maxSentences;

    public SummarizeService() {
        this.komoran = new Komoran(DEFAULT_MODEL.FULL);
        this.maxSentences = 3; // 요약 결과에 포함될 문장 수
    }

    public String summarize(String text) {

        Pattern regex = Pattern.compile("[가-힣\\s.]");
        Matcher regexMatcher = regex.matcher(text);

        StringBuilder sb = new StringBuilder();

        while(regexMatcher.find()) {
            sb.append(regexMatcher.group());
        }

        text = sb.toString().trim();


        // 문장 분리
        List<String> sentences = splitSentences(text);

        // 각 문장의 중요도 계산
        List<Double> scores = calculateScores(sentences);

        // 중요도가 높은 문장들의 인덱스 추출
        List<Integer> topIndexes = getTopSentenceIndexes(scores, maxSentences);

        // 결과 생성
        StringBuilder summary = new StringBuilder();
        for (int i : topIndexes) {
            summary.append(sentences.get(i).toString().trim()).append(" ");
        }

        System.out.println("한글만 추출된 텍스트는 다음과 같습니다.");
        System.out.println(summary.toString().trim());
        System.out.println("******************************************************************");
        return summary.toString().trim();
    }

    private List<String> splitSentences(String text) {
        // 간단한 문장 분리기 사용
        String[] rawSentences = text.split("(?<=[?!.])");
        List<String> sentences = new ArrayList<>();
        for (String raw : rawSentences) {
            String trimmed = raw.trim();
            if (!trimmed.isEmpty()) {
                sentences.add(trimmed);
            }
        }
        return sentences;
    }

    private List<Double> calculateScores(List<String> sentences) {
        List<Double> scores = new ArrayList<>(Collections.nCopies(sentences.size(), 0.0));
        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            List<Token> tokens = komoran.analyze(sentence).getTokenList();
            for (Token token : tokens) {
                String pos = token.getPos();
                if (pos.equals("NNG") || pos.equals("NNP")) { // 명사와 고유명사만 고려
                    scores.set(i, scores.get(i) + 1.0);
                }
            }
        }
        return scores;
    }

    private List<Integer> getTopSentenceIndexes(List<Double> scores, int n) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            indexes.add(i);
        }
        indexes.sort((i, j) -> scores.get(j).compareTo(scores.get(i)));

        // 상위 n개의 문장 인덱스를 반환합니다.
        return indexes.subList(0, Math.min(n, indexes.size()));
    }
}




