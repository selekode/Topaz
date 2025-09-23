import { BrowserRouter, Routes, Route } from "react-router-dom";
import JournalAddEntry from "./pages/JournalAddEntry";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<JournalAddEntry />} />
      </Routes>
    </BrowserRouter>
  );
}
