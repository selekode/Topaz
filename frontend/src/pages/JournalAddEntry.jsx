import { useState } from "react";

export default function JournalAddEntry() {
  const [entry, setEntry] = useState({
    title: "",
    contentGeneral: "",
    contentSaludFisica: "",
    contentBienestarMental: "",
    contentRelacionesSociales: "",
    contentCarreraProfesional: "",
    contentEstabilidadFinanciera: "",
    contentCrecimientoPersonal: "",
    contentPasatiemposCreatividad: "",
    contentEspiritualidadProposito: "",
    contentRecreacionDiversion: "",
    contentContribucionLegado: "",
    contentErroresCometidos: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEntry(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await fetch("/api/journal", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(entry),
    });
    alert("Entrada guardada!");
    // Reset form
    setEntry({
      title: "",
      contentGeneral: "",
      contentSaludFisica: "",
      contentBienestarMental: "",
      contentRelacionesSociales: "",
      contentCarreraProfesional: "",
      contentEstabilidadFinanciera: "",
      contentCrecimientoPersonal: "",
      contentPasatiemposCreatividad: "",
      contentEspiritualidadProposito: "",
      contentRecreacionDiversion: "",
      contentContribucionLegado: "",
      contentErroresCometidos: "",
    });
  };

  const textareas = [
    { label: "General", name: "contentGeneral", placeholder: "¿Qué has hecho hoy?" },
    { label: "Salud Física", name: "contentSaludFisica", placeholder: "Hoy he ido al gimnasio..." },
    { label: "Bienestar Mental", name: "contentBienestarMental", placeholder: "Hoy he meditado..." },
    { label: "Relaciones Sociales", name: "contentRelacionesSociales", placeholder: "Hoy he pasado tiempo con mi familia y amigos..." },
    { label: "Carrera Profesional", name: "contentCarreraProfesional", placeholder: "Hoy he trabajado en..." },
    { label: "Estabilidad Financiera", name: "contentEstabilidadFinanciera", placeholder: "Hoy he ganado... Hoy he gastado..." },
    { label: "Crecimiento Personal", name: "contentCrecimientoPersonal", placeholder: "Hoy he leído..." },
    { label: "Pasatiempos y Creatividad", name: "contentPasatiemposCreatividad", placeholder: "Hoy he hecho música... Hoy he dibujado..." },
    { label: "Espiritualidad y Propósito", name: "contentEspiritualidadProposito", placeholder: "Hoy he reflexionado sobre..." },
    { label: "Recreación y Diversión", name: "contentRecreacionDiversion", placeholder: "Hoy he jugado a..." },
    { label: "Contribución y Legado", name: "contentContribucionLegado", placeholder: "Hoy he ayudado a... Hoy he enseñado a..." },
    { label: "Errores Cometidos", name: "contentErroresCometidos", placeholder: "Hoy he hecho mal..." },
  ];

  return (
    <form onSubmit={handleSubmit} className="p-6 flex flex-col gap-4 bg-[#0F2529]">
      <input
        type="text"
        name="title"
        value={entry.title}
        onChange={handleChange}
        placeholder="Título"
        className="text-2xl font-bold mb-6 w-full bg-[#0F2529] text-white p-3 rounded-lg"
      />

      {textareas.map(({ label, name, placeholder }) => (
        <div key={name}>
          <label className="text-[#23A3BB] text-lg font-semibold mb-2 block">{label}</label>
          <textarea
            name={name}
            value={entry[name]}
            onChange={handleChange}
            placeholder={placeholder}
            className="w-full h-[150px] resize-y p-4 rounded-lg bg-[#0F2529] text-base text-white mb-6"
          />
        </div>
      ))}

      <div className="mt-6 flex">
        <button
          type="submit"
          className="ml-auto text-white bg-[#23A3BB] transition-colors duration-300 hover:bg-[#1A8F9A] py-2 px-4 rounded-full select-none"
        >
          Guardar
        </button>
      </div>
    </form>
  );
}
