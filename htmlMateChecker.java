package htmlMateChecker;
import java.util.ArrayList;

import htmlMateChecker.Tag;

class Tag {
    public String tagContent;
    public int index;
};

public class htmlMateChecker {
    
    public String execute(String userInput) {
    	
        // main method that calls on other methods
        ArrayList<Tag> tags = new ArrayList<Tag>();
        ArrayList<Tag> missingTags = new ArrayList<Tag>();
        String formattedStr;
        
        tags = separate(userInput);
        missingTags = compare(tags);
        formattedStr = format(missingTags);
        
        // returning the final string to display to the user
        return formattedStr;
    }
    
    public ArrayList<Tag> separate(String userInput) {
        
        int leftAngleIndex = -1; //Holds the index of the left angle bracket, <
        Tag temp = new Tag();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        
        for (int i = 0; i < userInput.length(); i++) {
            
            if(userInput.charAt(i) == '<') {    // beginning of a new tag
                leftAngleIndex = i;        // store the location of the new tag
            }
            else if(userInput.charAt(i) == '>') {    // end of the tag?
                // we found a fully enclosed html tag e.g. <html>
                temp.tagContent = (userInput.substring(leftAngleIndex, i + 1));
                temp.index = leftAngleIndex;
                tags.add(temp);
                
                leftAngleIndex = -1; // reinitialize
            }
            
            temp = new Tag();    // reinitialize
        }
        
        return tags;
    }
    
    @SuppressWarnings("unchecked")
    
    public ArrayList<Tag> compare(ArrayList<Tag> tags) {
        
        ArrayList<Tag> tagsCopy = new ArrayList<Tag>();
        ArrayList<Tag> missingTags = new ArrayList<Tag>();
        boolean found = false;        // initialize
        Tag temp = new Tag();
        String searchTerm = "";
    
        //Make copy of the passed-in list to compare against
        tagsCopy = (ArrayList<Tag>) tags.clone();
        
        // compare tags to tagsCopy and find missing mates
        for(int i = 0; i < tags.size(); i++) {
            
            temp = tags.get(i);
            searchTerm = buildSearchTerm(temp);
            
            //Search
            found = false; //reinitialize
            
            for(int j = 0; j < tagsCopy.size(); j++) {
                
                if(tagsCopy.get(j).tagContent.equalsIgnoreCase(searchTerm)) {
                    // mate found
                    found = true;
                    tagsCopy.remove(j); // remove so the tag doesn't get double-counted
                    break; // search complete; get out of the loop
                }
            }
            
            if(found == true) {
                // mate found; do nothing
            }
            else {
                // mate not found
                missingTags.add(temp); //add to missing list
            }
        }
        
        return missingTags;
    }
    
    public String format(ArrayList<Tag> missingTags) {
        
        String formattedStr = "";
        
        if(missingTags.size() <= 0) {
        	// no mismatches were found
            formattedStr = "No mismatches found.";
        }
        else {
            for(int i = 0; i < missingTags.size(); i++) {
            	// creating error message to display to the user
                formattedStr = formattedStr + missingTags.get(i).tagContent 
                        + " at index " + missingTags.get(i).index 
                        + " is missing a matching mate.\n";
            }
        }
        
        return formattedStr;
    }
    
    public String buildSearchTerm (Tag temp){
        String searchTerm;

        //Build a search string by adding or removing /
        if(temp.tagContent.charAt(1) == '/') {
            
            //</html> --> <html>
            searchTerm = temp.tagContent.substring(0, 1)
                    .concat(temp.tagContent.substring(2, temp.tagContent.length()));
        }
        else {
            
            //<html> --> </html>
            searchTerm = temp.tagContent.substring(0, 1)
                    .concat("/")
                    .concat(temp.tagContent.substring(1, temp.tagContent.length()));
        }
        return searchTerm;
    }
            
}