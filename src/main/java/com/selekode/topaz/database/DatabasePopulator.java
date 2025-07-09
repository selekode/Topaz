package com.selekode.topaz.database;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

public class DatabasePopulator {

    public static void main(String[] args) throws Exception {
        // Change your DB URL accordingly
        String dbUrl = "jdbc:sqlite:C:/Topaz/data/topazdatabase.db";

        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            conn.setAutoCommit(false);

            //populateInnerWorkTags(conn);
            populateJournal(conn, 50);
            populateRevision(conn, 50);
            populateInnerWorkEntries(conn, 30);

            conn.commit();
            System.out.println("Database seeded successfully.");
        }
    }

    

    private static void populateJournal(Connection conn, int count) throws SQLException {
        String insertSQL = "INSERT INTO journal (" +
                "date, title, contentGeneral, contentSaludFisica, contentBienestarMental, contentRelacionesSociales," +
                "contentCarreraProfesional, contentEstabilidadFinanciera, contentCrecimientoPersonal, contentPasatiemposCreatividad," +
                "contentEspiritualidadProposito, contentRecreacionDiversion, contentContribucionLegado, contentErroresCometidos) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Random random = new Random();

        try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
            for (int i = 0; i < count; i++) {
                long date = randomDateIn2025(random);
                String title = randomTitle(random, "Journal Entry");

                ps.setLong(1, date);
                ps.setString(2, title);
                ps.setString(3, randomParagraph(random));
                ps.setString(4, randomParagraph(random));
                ps.setString(5, randomParagraph(random));
                ps.setString(6, randomParagraph(random));
                ps.setString(7, randomParagraph(random));
                ps.setString(8, randomParagraph(random));
                ps.setString(9, randomParagraph(random));
                ps.setString(10, randomParagraph(random));
                ps.setString(11, randomParagraph(random));
                ps.setString(12, randomParagraph(random));
                ps.setString(13, randomParagraph(random));
                ps.setString(14, randomParagraph(random));

                ps.executeUpdate();
            }
        }

        System.out.println("journal populated.");
    }

    public static void populateRevision(Connection conn, int count) throws SQLException {
        String insertSQL = "INSERT INTO revision (" +
                "date, estadoEmocional, estadoEmocionalWhy, importanteParaMi, aprendidoSobreMi, " +
                "valoracionDisciplina, valoracionOrden, valoracionImpulsividad, valoracionConstancia, valoracionTolerancia, " +
                "valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos, " +
                "explicacionValoracion, objetivosPersonales, " +
                "emocionAlegria, emocionTristeza, emocionIra, emocionMiedo, emocionAnsiedad, emocionAmor, emocionSorpresa, " +
                "emocionVerguenza, emocionFrustracion, emocionSatisfaccion, emocionAburrimiento, emocionSerenidad, " +
                "emocionConfianza, emocionAbrumado, emocionEsperanza) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Random random = new Random();

        try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
            for (int i = 0; i < count; i++) {
                long date = randomDateIn2025(random);

                ps.setLong(1, date);
                ps.setString(2, randomEmotion(random));
                ps.setString(3, randomSentence(random));
                ps.setString(4, randomSentence(random));
                ps.setString(5, randomSentence(random));

                // Ratings 1-10
                ps.setInt(6, random.nextInt(10) + 1);
                ps.setInt(7, random.nextInt(10) + 1);
                ps.setInt(8, random.nextInt(10) + 1);
                ps.setInt(9, random.nextInt(10) + 1);
                ps.setInt(10, random.nextInt(10) + 1);
                ps.setInt(11, random.nextInt(10) + 1);
                ps.setInt(12, random.nextInt(10) + 1);
                ps.setInt(13, random.nextInt(10) + 1);
                ps.setInt(14, random.nextInt(10) + 1);

                ps.setString(15, randomParagraph(random));
                ps.setString(16, randomParagraph(random));

                // Emotions booleans (~40% chance true)
                ps.setBoolean(17, random.nextDouble() < 0.4); // Alegría
                ps.setBoolean(18, random.nextDouble() < 0.4); // Tristeza
                ps.setBoolean(19, random.nextDouble() < 0.4); // Ira
                ps.setBoolean(20, random.nextDouble() < 0.4); // Miedo
                ps.setBoolean(21, random.nextDouble() < 0.4); // Ansiedad
                ps.setBoolean(22, random.nextDouble() < 0.4); // Amor
                ps.setBoolean(23, random.nextDouble() < 0.4); // Sorpresa
                ps.setBoolean(24, random.nextDouble() < 0.4); // Vergüenza
                ps.setBoolean(25, random.nextDouble() < 0.4); // Frustración
                ps.setBoolean(26, random.nextDouble() < 0.4); // Satisfacción
                ps.setBoolean(27, random.nextDouble() < 0.4); // Aburrimiento
                ps.setBoolean(28, random.nextDouble() < 0.4); // Serenidad
                ps.setBoolean(29, random.nextDouble() < 0.4); // Confianza
                ps.setBoolean(30, random.nextDouble() < 0.4); // Abrumado
                ps.setBoolean(31, random.nextDouble() < 0.4); // Esperanza

                ps.executeUpdate();
            }
        }

        System.out.println("revision populated.");
    }

    

    private static void populateInnerWorkEntries(Connection conn, int count) throws SQLException {
        // Get all tag IDs first
        String selectTagsSQL = "SELECT id FROM inner_work_tags;";
        List<Integer> tagIds = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectTagsSQL)) {
            while (rs.next()) {
                tagIds.add(rs.getInt("id"));
            }
        }

        if (tagIds.isEmpty()) {
            System.out.println("No tags found for inner_work_entry population.");
            return;
        }

        String insertSQL = "INSERT INTO inner_work_entry (date, tag_id, title, content) VALUES (?, ?, ?, ?);";

        Random random = new Random();

        try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
            for (int i = 0; i < count; i++) {
                long date = randomDateIn2025(random);
                int randomTagId = tagIds.get(random.nextInt(tagIds.size()));
                String title = randomTitle(random, "Inner Work Entry");
                String content = randomParagraph(random);

                ps.setLong(1, date);
                ps.setInt(2, randomTagId);
                ps.setString(3, title);
                ps.setString(4, content);

                ps.executeUpdate();
            }
        }

        System.out.println("inner_work_entry populated.");
    }

    private static long randomDateIn2025(Random random) {
        int dayOfYear = 1 + random.nextInt(365); // 1 to 365
        LocalDate date = LocalDate.ofYearDay(2025, dayOfYear);
        return date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
    }

    private static String randomTitle(Random random, String prefix) {
        String[] words = {
                "Reflections", "Journey", "Insights", "Growth", "Challenges", "Wins",
                "Thoughts", "Progress", "Mindset", "Focus", "Balance", "Energy"
        };
        return prefix + ": " + words[random.nextInt(words.length)];
    }

    private static String randomEmotion(Random random) {
        String[] emotions = {
                "Alegría", "Tristeza", "Ira", "Miedo", "Ansiedad", "Amor",
                "Sorpresa", "Vergüenza", "Frustración", "Satisfacción",
                "Aburrimiento", "Serenidad", "Confianza", "Abrumado", "Esperanza"
        };
        return emotions[random.nextInt(emotions.length)];
    }

    private static String randomSentence(Random random) {
        String[] sentences = {
                "I felt very productive today.",
                "There were some challenges I need to overcome.",
                "I learned a lot about myself.",
                "This experience was very insightful.",
                "I am grateful for the support around me.",
                "I noticed improvements in my discipline.",
                "Today was emotionally intense.",
                "I need to work on managing my impulsivity.",
                "I felt calm and focused.",
                "My motivation was strong."
        };
        return sentences[random.nextInt(sentences.length)];
    }

    private static String randomParagraph(Random random) {
        int sentencesCount = 3 + random.nextInt(3); // 3-5 sentences
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < sentencesCount; i++) {
            paragraph.append(randomSentence(random)).append(" ");
        }
        return paragraph.toString().trim();
    }
}
