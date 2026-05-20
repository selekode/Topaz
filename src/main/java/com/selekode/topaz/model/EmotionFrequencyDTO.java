package com.selekode.topaz.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmotionFrequencyDTO {
	// Core Emotions
	private int emocionAlegriaCount;
	private int emocionTristezaCount;
	private int emocionIraCount;
	private int emocionMiedoCount;
	private int emocionConfianzaCount;
	private int emocionSorpresaCount;
	private int emocionAnticipacionCount;
	private int emocionRechazoCount;

	// Mild Emotions
	private int emocionSerenidadCount;
	private int emocionMelancoliaCount;
	private int emocionFastidioCount;
	private int emocionAprensionCount;
	private int emocionAceptacionCount;
	private int emocionDistraccionCount;
	private int emocionInteresCount;
	private int emocionAburrimientoCount;

	// Intense Emotions
	private int emocionExtasisCount;
	private int emocionPenaDolorCount;
	private int emocionFuriaCount;
	private int emocionTerrorCount;
	private int emocionAdmiracionCount;
	private int emocionAsombroCount;
	private int emocionVigilanciaCount;
	private int emocionAscoCount;

	// Complex / Reflective States
	private int emocionAnsiedadCount;
	private int emocionFrustracionCount;
	private int emocionVerguenzaCount;
	private int emocionEsperanzaCount;
	private int emocionOrgulloCount;
	private int emocionAgobioCount;

	// Baseline State
	private int emocionNeutralCount;

	// Top 4 emotions
	private String topEmotion1;
	private int topEmotion1Count;
	private String topEmotion2;
	private int topEmotion2Count;
	private String topEmotion3;
	private int topEmotion3Count;
	private String topEmotion4;
	private int topEmotion4Count;

	// Constructor matching all fields
	public EmotionFrequencyDTO(int emocionAlegriaCount, int emocionTristezaCount, int emocionIraCount,
							   int emocionMiedoCount, int emocionConfianzaCount, int emocionSorpresaCount,
							   int emocionAnticipacionCount, int emocionRechazoCount,
							   int emocionSerenidadCount, int emocionMelancoliaCount, int emocionFastidioCount,
							   int emocionAprensionCount, int emocionAceptacionCount, int emocionDistraccionCount,
							   int emocionInteresCount, int emocionAburrimientoCount,
							   int emocionExtasisCount, int emocionPenaDolorCount, int emocionFuriaCount,
							   int emocionTerrorCount, int emocionAdmiracionCount, int emocionAsombroCount,
							   int emocionVigilanciaCount, int emocionAscoCount,
							   int emocionAnsiedadCount, int emocionFrustracionCount, int emocionVerguenzaCount,
							   int emocionEsperanzaCount, int emocionOrgulloCount, int emocionAgobioCount,
							   int emocionNeutralCount,
							   String topEmotion1, int topEmotion1Count, String topEmotion2, int topEmotion2Count,
							   String topEmotion3, int topEmotion3Count, String topEmotion4, int topEmotion4Count) {
		this.emocionAlegriaCount = emocionAlegriaCount;
		this.emocionTristezaCount = emocionTristezaCount;
		this.emocionIraCount = emocionIraCount;
		this.emocionMiedoCount = emocionMiedoCount;
		this.emocionConfianzaCount = emocionConfianzaCount;
		this.emocionSorpresaCount = emocionSorpresaCount;
		this.emocionAnticipacionCount = emocionAnticipacionCount;
		this.emocionRechazoCount = emocionRechazoCount;
		this.emocionSerenidadCount = emocionSerenidadCount;
		this.emocionMelancoliaCount = emocionMelancoliaCount;
		this.emocionFastidioCount = emocionFastidioCount;
		this.emocionAprensionCount = emocionAprensionCount;
		this.emocionAceptacionCount = emocionAceptacionCount;
		this.emocionDistraccionCount = emocionDistraccionCount;
		this.emocionInteresCount = emocionInteresCount;
		this.emocionAburrimientoCount = emocionAburrimientoCount;
		this.emocionExtasisCount = emocionExtasisCount;
		this.emocionPenaDolorCount = emocionPenaDolorCount;
		this.emocionFuriaCount = emocionFuriaCount;
		this.emocionTerrorCount = emocionTerrorCount;
		this.emocionAdmiracionCount = emocionAdmiracionCount;
		this.emocionAsombroCount = emocionAsombroCount;
		this.emocionVigilanciaCount = emocionVigilanciaCount;
		this.emocionAscoCount = emocionAscoCount;
		this.emocionAnsiedadCount = emocionAnsiedadCount;
		this.emocionFrustracionCount = emocionFrustracionCount;
		this.emocionVerguenzaCount = emocionVerguenzaCount;
		this.emocionEsperanzaCount = emocionEsperanzaCount;
		this.emocionOrgulloCount = emocionOrgulloCount;
		this.emocionAgobioCount = emocionAgobioCount;
		this.emocionNeutralCount = emocionNeutralCount;
		this.topEmotion1 = topEmotion1;
		this.topEmotion1Count = topEmotion1Count;
		this.topEmotion2 = topEmotion2;
		this.topEmotion2Count = topEmotion2Count;
		this.topEmotion3 = topEmotion3;
		this.topEmotion3Count = topEmotion3Count;
		this.topEmotion4 = topEmotion4;
		this.topEmotion4Count = topEmotion4Count;
	}
}
