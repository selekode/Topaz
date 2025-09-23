import React from "react";
import ReactDOM from "react-dom/client";
import JournalAddEntry from "./pages/JournalAddEntry";
import "./index.css"; // Tailwind CSS

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <JournalAddEntry />
  </React.StrictMode>
);
