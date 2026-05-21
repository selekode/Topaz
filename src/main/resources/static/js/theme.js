// resources/static/js/theme.js

document.addEventListener("DOMContentLoaded", function () {
    const toggleBtn = document.getElementById('theme-toggle');
    const toggleText = document.getElementById('theme-toggle-text');

    // Selectores de los iconos
    const sunIcon = document.querySelector('.theme-icon-sun');
    const moonIcon = document.querySelector('.theme-icon-moon');

    // Función auxiliar para actualizar los textos e iconos visuales del botón
    function updateToggleButton(isDark) {
        if (!toggleBtn) return;

        if (isDark) {
            if (toggleText) toggleText.textContent = "Modo Oscuro";
            if (moonIcon) sunIcon.classList.remove('hidden');
            if (sunIcon) moonIcon.classList.add('hidden');
        } else {
            if (toggleText) toggleText.textContent = "Modo Claro";
            if (moonIcon) sunIcon.classList.add('hidden');
            if (sunIcon) moonIcon.classList.remove('hidden');
        }
    }

    // 1. Sincronización inicial al cargar la pantalla
    const isDarkMode = localStorage.getItem('theme') === 'dark';
    if (isDarkMode) {
        document.documentElement.classList.add('dark');
        updateToggleButton(true);
    } else {
        document.documentElement.classList.remove('dark');
        updateToggleButton(false);
    }

    // 2. Evento Click del botón
    if (toggleBtn) {
        toggleBtn.addEventListener('click', () => {
            const willBeDark = !document.documentElement.classList.contains('dark');

            if (willBeDark) {
                document.documentElement.classList.add('dark');
                localStorage.setItem('theme', 'dark');
                updateToggleButton(true);
            } else {
                document.documentElement.classList.remove('dark');
                localStorage.setItem('theme', 'light');
                updateToggleButton(false);
            }
        });
    }
});