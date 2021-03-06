import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


// Compile : javac -cp .:json-simple-1.1.1.jar Game.java
// Execute : java -cp .:json-simple-1.1.1.jar Game

public class SpellInit{
	
	private static String JSON_SPELL_PATH = "Spell.json";
	
	private ArrayList<Spell> spells;	
		
	public SpellInit(){
		
		this.spells = new ArrayList<Spell>();
		
		FileReader jsonFile = null;
        try {
            // lecture du fichier json
            jsonFile = new FileReader(JSON_SPELL_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        //TODO parser le fichier
			JSONParser parser = new JSONParser();
			Object jsonParsed = null;
			try{
				jsonParsed = parser.parse(jsonFile);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
			
			JSONArray root = (JSONArray) jsonParsed;
			for(int i = 0; i < root.size(); i++){
				JSONObject spellObject = (JSONObject) root.get(i);
				
				int spellId = Math.toIntExact((long) spellObject.get("id"));
				String spellName = (String) spellObject.get("name");
				String spellWeakness = (String) spellObject.get("weakness");
				String spellType = (String) spellObject.get("type");
				
				Spell spell = new Spell(spellId, spellName, spellWeakness, spellType);
				this.spells.add(spell);
			}		
	} 

	public String getSpellType(int choice){
		Spell spell1= this.spells.get(choice-1);
		return spell1.getType();		
	}

	public String getSpellWeakness(int choice){
		Spell spell1= this.spells.get(choice-1);
		return spell1.getWeakness();		
	}

	
}