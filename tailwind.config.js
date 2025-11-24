// tailwind.config.js
module.exports = {
  content: ["./src/main/resources/templates/**/*.html"],
  theme: {
    extend: {
      colors: {
        background: '#FFFFFF',
        surface: '#F2F2F5',
        border: '#D1D1D6',
        textPrimary: '#1C1C1E',
        textSecondary: '#3C3C43',
        textSubtle: '#8E8E93',
        accent: '#00BFA5',
        alert: '#FF453A',
        success: '#32D74B',
      },
    },
  },
  plugins: [],
}
