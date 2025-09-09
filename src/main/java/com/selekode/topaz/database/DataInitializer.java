package com.selekode.topaz.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.selekode.topaz.model.InnerWorkTag;
import com.selekode.topaz.repository.InnerWorkTagRepository;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initTags(InnerWorkTagRepository tagRepository) {
        return args -> {
            if (tagRepository.count() == 0) {
                List<String> tags = List.of(
                    "Asertividad", "Disciplina", "Impulsividad", "Empatía", "Proactividad",
                    "Comunicación", "Gratitud", "Perseverancia", "Autoestima", "Autoconciencia"
                );
                tags.forEach(tag -> tagRepository.save(new InnerWorkTag(null, tag)));
            }
        };
    }
}

