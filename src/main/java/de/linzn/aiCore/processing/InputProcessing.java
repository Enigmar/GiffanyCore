package de.linzn.aiCore.processing;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.KeywordContainer;
import de.linzn.aiCore.internal.ObjectContainer;
import de.linzn.aiCore.internal.Reflector;
import de.linzn.aiCore.internal.ResultContainer;
import de.linzn.aiCore.internal.SentenceContainer;

public class InputProcessing {
	private App app;
	// All illegal symbols
	char symbols[] = { '!', '"', '§', '$', '%', '&', '/', '(', ')', '=', '?', '`', '²', '³', '{', '[', ']', '}', '*',
			'€', '@', '#', '-', '.', '_', ':', ',', ';', '<', '>', '|', 'µ', '^', '°', '~', '+', '¡', '¢', '£', '¤',
			'¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶', '·', '¸', '¹', 'º',
			'»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï',
			'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä',
			'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù',
			'ú', 'û', 'ü', 'ý', 'þ', 'ÿ', '€', '‚', 'ƒ', '„', '…', '†', '‡', 'ˆ', '‰', 'Š', '‹', 'Œ', 'Ž', '‘', '’',
			'“', '”', '•', '–', '—', '˜', '™', 'š', '›', 'œ', 'ž', 'Ÿ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©',
			'ª', '«', '¬', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶', '·', '¸', 'º', '»', '¼', '½', '¾', '¿', 'À',
			'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ',
			'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê',
			'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ',
			'€', '‚', 'ƒ', '„', '…', '†', '‡', 'ˆ', '‰', 'Š', '‹', 'Œ', 'Ž', '‘', '’', '“', '”', '•', '–', '—', '˜',
			'™', 'š', '›', 'œ', 'ž', 'Ÿ', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '®', '¯', '°',
			'±', '²', '³', '´', 'µ', '¶', '·', '¸', 'º', '»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ',
			'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û',
			'Ü', 'Ý', 'Þ', 'ß', 'à', 'á', 'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð',
			'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ', '€', '‚', 'ƒ', '„', '…', '†',
			'‡', 'ˆ', '‰', 'Š', '‹', 'Œ', 'Ž', '‘', '’', '“', '”', '•', '–', '—', '˜', '™', 'š', '›', 'œ', 'ž', 'Ÿ',
			'¡', '¡', '¡', '¡', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '®', '¯', '°', '±', '²', '³', '´', 'µ', '¶',
			'·', '¸', 'º', '»', '¼', '½', '¾', '¿', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì',
			'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', '×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 'ß', 'à', 'á',
			'â', 'ã', 'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö',
			'÷', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'þ', 'ÿ' };

	public InputProcessing(App app) {
		this.app = app;
	}

	public void processingInput(String input) {
		if (input.isEmpty()) {
			return;
		}
		// First clean up the string
		for (char c : symbols) {
			input = input.replace(String.valueOf(c), "");
		}
		// For special case
		if (input.toCharArray()[0] == ' ') {
			input = input.replaceFirst(" ", "");
		}

		// Replace in case than more than one spacer
		input = input.replaceAll("[ ]{2,}", " ");
		input = input.toLowerCase();

		// Split the string in substrings
		String[] splitedInput = input.split(" ");
		ObjectContainer objectCon = null;
		KeywordContainer keywordCon = null;
		ResultContainer resultCon = null;

		for (String split : splitedInput) {
			if (objectCon == null) {
				objectCon = this.app.mysqlData.dbobject.getObject(split);
			}
		}

		if (objectCon == null) {
			// Nothing found
			App.logger("No ObjectContainer found");
			this.textSearch(input);
		} else {
			for (String split : splitedInput) {
				if (keywordCon == null) {
					keywordCon = this.app.mysqlData.dbkeyword.getKeyword(objectCon, split);
				}
			}

			if (keywordCon == null) {
				// No keyword found
				App.logger("No KeywordContainer found");
				keywordCon = new KeywordContainer(0, objectCon.objectID, null, null);
			}

			if (keywordCon.keywordID != 0) {
				new Reflector().classRunner(objectCon, keywordCon);
			}

			resultCon = this.app.mysqlData.dbresult.getResultByObjects(objectCon, keywordCon);

			if (resultCon == null) {
				// No keyword found
				App.logger("No ResultContainer found");
			} else {

				App.logger("Result: " + resultCon.result);
			}
		}

	}

	private void textSearch(String input) {
		SentenceContainer sentenceCon;
		ResultContainer resultCon;
		sentenceCon = this.app.mysqlData.dbsentence.getSentence(input);
		if (sentenceCon == null) {
			// No keyword found
			App.logger("No SentenceContainer found");
		} else {

			resultCon = this.app.mysqlData.dbresult.getResultByText(sentenceCon);

			if (resultCon == null) {
				// No keyword found
				App.logger("No ResultContainer found");
			} else {
				App.logger("Sentence: " + resultCon.result);
			}
		}
	}

}
