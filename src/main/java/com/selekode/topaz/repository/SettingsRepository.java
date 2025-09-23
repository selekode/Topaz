package com.selekode.topaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selekode.topaz.model.Settings;

public interface SettingsRepository extends JpaRepository<Settings, String> {
}
