#### My Personal Project
## Mineralogy ID Tool

The application will allow users to practice identifying minerals based on their physical properties. Users can keep
track of minerals they think they have learned, and minerals they think need more review. 
When the user starts the app, they will be prompted to choose between either organizing their review and learned
folders, entering minerals into their list, and studying minerals. The minerals that they can study will be the 
minerals on the review list that they created. 

**The properties each mineral will have associated with it are:**
- lab number (int)
- name (String)
- color (String)
- hardness (int)
- crystal system (String, 6 categories)

**User Stories:**
- As a user, I want to be able to add a mineral with a name, lab number, and properties to a list of minerals
- As a user, I want to be able to reveal individual properties of a mineral randomly selected from the list
- As a user, I want to be able to guess the name of a mineral based on revealed properties
- As a user, I want to be able to move minerals from a list of minerals to review to a list of learned minerals (and 
  back).